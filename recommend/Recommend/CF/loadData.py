import pandas as pd


class ReadData:
    def __init__(self):
        self.userData = pd.read_csv('CF/data/userOwnedResult.csv', sep=",")
        self.appList = pd.read_csv('CF/data/appList.csv', sep=",")
