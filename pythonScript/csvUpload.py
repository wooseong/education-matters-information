import sqlite3
import csv

con = sqlite3.connect("DB.sqlite3")
cursor = con.cursor()

f = open("2017-2학기 강의시간표.csv", 'r')

data = []

with open('2017-2학기 강의시간표.csv', newline='') as csvfile:
    reader = csv.reader(csvfile)
    for row in reader:
        data.append('\", \"'.join(row))

for query in data[1:]:
    cursor.execute("INSERT into Lecture VALUES (\"" + query + "\")")
    # print("INSERT into Lecture VALUES (\"" + query + "\")")

f.close()
con.commit()
con.close()
