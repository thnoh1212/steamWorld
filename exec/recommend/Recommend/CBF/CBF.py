import pandas as pd
import csv
from ast import literal_eval
from CBF import loadData


class ContentsBasedFiltering:

    def __init__(self, gameids):
        readData = loadData.ReadData()
        self.percent = 0.9
        self.gameIds = gameids
        self.pointDic = {}
        self.pointList = []
        self.data = readData.gameData
        self.genreData = readData.genreData
        self.isAppidValid = False

    # 점수표 제작
    def makePoint(self):
        for tag in self.genreData['genre']:
            if str(tag) not in self.pointDic:
                self.pointDic[str(tag)] = 0
        for target in self.data[self.data['appid'] == self.gameIds]['genre']:
            point = 10
            for genre in target:
                if genre == 'VR' or genre == 'Free to Play' or genre == 'Early Access':
                    continue
                if genre == "Beat 'em up":
                    genre = "Beat'em up"
                if genre == "Dystopian ":
                    genre = "Dystopian"
                if genre == "Shoot 'Em Up":
                    genre = "Shoot'Em Up"
                if genre == "Parody ":
                    genre = 'Parody'
                self.pointDic[genre] += point
                self.isAppidValid = True
                point -= 1

    # 데이터 정제
    def refine(self):
        # Weighted rating(WR) = (v / (v + m)) * R + (m / (v + m)) * C
        # R: 평점, V: 투표수, M: 최소 투표수, C: 평균 평점
        # 전체 평균 평점
        C = self.data['score'].mean()

        # 최소 투표수
        m = self.data['vote'].quantile(self.percent)
        self.data = self.data.loc[self.data['vote'] >= m]

        # 가중치 계산
        def weight_rating(data, m=m, c=C):
            v = data['vote']
            R = data['score']
            return (v / (v + m)) * R + (m / (v + m)) * c

        # 데이터 정제
        self.data['score'] = self.data.apply(weight_rating, axis=1)

    def simEval(self):
        for i in self.data.index:
            point = 0
            game = self.data.at[i, 'genre']  # get_value(i, 'genre')
            appid = self.data.at[i, 'appid']
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
                point += self.pointDic[genre] * cnt
                cnt -= 1
            if appid == self.gameIds:
                point = -1
            self.pointList.append(point)

        # 계산된 유사도를 데이터에 추가
        self.data.loc[:, 'point'] = self.pointList

    def result(self, n=10):
        # 데이터 출력
        result = self.data.sort_values(by=['point'], ascending=False).head(n)
        result = result.drop(['genre', 'vote', 'point'], axis='columns')

        return result