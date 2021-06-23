import pandas as pd
from ast import literal_eval

class ReadData:
    def __init__(self):
        self.gameData = pd.read_csv('CBF/data/GameTagData.csv', sep=",", converters={'genre': literal_eval})
        self.genreData = pd.read_csv('CBF/data/tag.csv', sep=",")