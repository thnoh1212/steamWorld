import csv
import pandas as pd

input_file_name = "./data/GameTagData.csv"
output_file_name = "./data/tag.csv"

with open(output_file_name, "w", encoding="UTF-8", newline="") as output_file:
    csvData = []
    dic = {}
    data = pd.read_csv(input_file_name, sep=',')

    for genres in data['genre']:
        genres = genres.replace('[', '').replace(']', '').replace(" '", "'").replace(' "', '"').replace('"', "").split(',')
        for genre in genres:
            if genre[0] == "'":
                genre = genre[1:]
            if genre[-1] == "'":
                genre = genre[:-1]
            if genre in dic:
                continue
            else:
                genre_name = {}
                dic[genre] = True
                genre_name["genre"] = genre
                print(genre)
                csvData.append(genre_name)
    
    csvwriter = csv.writer(output_file)
    csvwriter.writerow(csvData[0].keys())
    for line in csvData:
        csvwriter.writerow(line.values())