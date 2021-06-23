import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

input_file = './data/GameTagData.csv'
data = pd.read_csv(input_file, sep=',')


# Weighted rating(WR) = (v / (v + m)) * R + (m / (v + m)) * C
# R: 평점, V: 투표수, M: 최소 투표수, C: 평균 평점

# VR게임 제거
def remove_VR(data):
    return data[~data.genre.str.contains('VR')]

data = remove_VR(data)

# 전체 평균 평점
C = data['score'].mean()

# 최소 투표수
m = data['vote'].quantile(0.8)
data = data.loc[data['vote'] >= m]

# 가중치 계산
def wegight_rating(data, m=m, C=C):
    v = data['vote']
    R = data['score']

    return (v / (v + m)) * R + (m / (v + m)) * C


# 데이터 정제 (공백을 구분자로 사용 및 태그 중 '무료' 태그 제거)
data['score'] = data.apply(wegight_rating, axis=1)
min = 0
for genre in data['genre']:
    if min > genre.count(','):
        min = genre.count(',')
data['genre'] = data['genre'].apply(lambda x : x.replace(',', ' ')).apply(lambda x : x.replace('FreetoPlay,','')).apply(lambda x : x.replace('FreetoPlay',''))

# 콘텐츠 기반 필터링
count_vector = CountVectorizer(ngram_range=(1, 3))
c_vector_genre = count_vector.fit_transform(data['genre'])

# 코사인 유사도를 구한 벡터 저장
genre_c_sim = cosine_similarity(c_vector_genre, c_vector_genre).argsort()[:, ::-1]

# 추천 함수
def get_recommend_game_list(data, game_name, top=100):
    # 유사도를 검사할 게임을 추출
    target_game_index = data[data['name'] == game_name].index.values
    print(data[data['name'] == game_name])
    # 코사인 유사도 중 비슷한 코사인 유사도를 가진 정보를 뽑아낸다
    sim_index = genre_c_sim[target_game_index, :top].reshape(-1)
    # 본인을 제외
    sim_index = sim_index[sim_index != target_game_index]

    # datafram으로 만들고 score으로 정렬한 뒤 return
    result = data.iloc[sim_index].sort_values('vote', ascending=False)[:15]
    return result

print(get_recommend_game_list(data, game_name="Little Nightmares"))
print(min)