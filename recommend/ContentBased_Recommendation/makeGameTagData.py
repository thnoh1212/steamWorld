import csv
import pandas as pd
input_file_name = "./data/GamedataRow.csv"
output_file_name = "./data/GameTagData.csv"

with open(output_file_name, 'w', encoding='UTF-8') as output_file:
    csvData = []
    dic = {}
    input_data = pd.read_csv(input_file_name, sep=',')
    for name, genre, score, vote in zip(input_data['name'], input_data['genre'], input_data['score'], input_data['vote']):
        linedata = {}
        if name in dic:
            continue
        else:
            dic[name] = score
            linedata['name'] = name
            linedata['genre'] = genre
            linedata['score'] = score
            linedata['vote'] = vote
            csvData.append(linedata)


    csvwriter = csv.writer(output_file)
    csvwriter.writerow(csvData[0].keys())
    for line in csvData:
        csvwriter.writerow(line.values())