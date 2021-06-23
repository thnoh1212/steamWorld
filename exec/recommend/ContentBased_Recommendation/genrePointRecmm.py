import pandas as pd
import csv
from ast import literal_eval

data = pd.read_csv('./data/GameTagData.csv', sep=",", converters={'genre': literal_eval})
tagdata = pd.read_csv('./data/tag.csv', sep=",")

# 게임 이름 및 가중치 입력
gamename = {'Hollow Knight' : 1, 'Spelunky': 1}

# 점수표 제작
def makePoint(pointDic, data, gamenames):
    for gamename in gamenames:
        for tag in tagdata['genre']:
            if str(tag) not in pointDic:
                pointDic[str(tag)] = 0

        for target in data[data['name'] == gamename]['genre']:
            point = 10
            for genre in target:
                if genre == 'VR' or genre == 'Free to Play':
                    continue
                pointDic[genre] += point * gamenames[gamename]
                point -= 1
    return pointDic

pointDic = {}
pointDic = makePoint(pointDic, data, gamename)

# Weighted rating(WR) = (v / (v + m)) * R + (m / (v + m)) * C
# R: 평점, V: 투표수, M: 최소 투표수, C: 평균 평점

# 전체 평균 평점
C = data['score'].mean()

# 최소 투표수
m = data['vote'].quantile(0.9)
data = data.loc[data['vote'] >= m]

# 가중치 계산
def wegight_rating(data, m=m, C=C):
    v = data['vote']
    R = data['score']

    return (v / (v + m)) * R + (m / (v + m)) * C

# 데이터 정제
data['score'] = data.apply(wegight_rating, axis=1)


# 게임 별 점수 측정
pointList = []
def simEval(data, gamenames, pointList):
    for i in data.index:
        point = 0
        game = data.at[i, 'genre']  #get_value(i, 'genre')
        name = data.at[i, 'name']

        cnt = 10
        for genre in game:
            if genre == "Beat 'em up":
                genre = "Beat'em up"
            if genre == "Dystopian ":
                genre = "Dystopian"
            if genre == "Shoot 'Em Up":
                genre = "Shoot'Em Up"
            if genre == "Parody ":
                genre = 'Parody'
            point += pointDic[genre] * cnt
            cnt -= 1
        if name in gamenames:
           point = -1
        pointList.append(point)
    return pointList

# 계산된 유사도를 데이터에 추가
pointList = simEval(data, gamename, pointList)
data.loc[:, 'point'] = pointList

# 데이터 출력
data = data.sort_values(by=['point'], ascending=False).head(20)
print(data)