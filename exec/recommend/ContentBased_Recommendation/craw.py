# 스팀 api를 사용한 데이터 수집

import pandas as pd
import requests
import time
import json

csv_game = pd.read_csv('D:\crawling\\applist.csv');
game_data_result = [{'appid', 'name', }]
cnt = 0;
with open("./data/gamedata.json", "a+") as f:
    with open(".data/failappid.txt", "a+") as f2:
        for appid in csv_game['appid'][22158:]:
            cnt += 1
            url = "https://steamspy.com/api.php?request=appdetails&appid=" + str(appid)
            data = requests.get(url)
            print(str(cnt), ". ", appid, ": ", end='')
            if data.text == 'Connection failed: Too many connections' or data.status_code != 200:
                f2.write(str(appid) + '\n')
                print('failed')
                continue
            data = data.json()
            print('success')
            f.write(json.dumps(data) + ',\n')
            time.sleep(0.1)
    f.write(']')
