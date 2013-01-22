import pyodbc

cnxn = pyodbc.connect('DSN=pbvaje;UID=pb;PWD=pbvaje')
cursor = cnxn.cursor()
updater= cnxn.cursor()
try:
    cursor.execute("DROP TABLE gostota")
except pyodbc.DatabaseError:
    pass
cursor.execute("CREATE TABLE gostota (id INT NOT NULL AUTO_INCREMENT, xmin INT NULL, xmax INT NULL, ymin INT NULL, ymax INT NULL, gPopulacija DECIMAL(10,2) NULL, gIgralci DECIMAL(10,2) NULL, PRIMARY KEY(id))")
cnxn.commit()
for i in range(-400,391,10):
    for j in range(-400,391,10):
        cursor.execute("select sum(sumPopulation)/100 as gPopulacija, count(sumPopulation)/100 as gIgralci from (select sum(population) as sumPopulation from naselje where (x between ? AND ?) AND (y between ? AND ?) group by pid) T1", i, i+10, j, j+10)
        row = cursor.fetchone()
        if row.gPopulacija != None:
            updater.execute("INSERT INTO gostota (xmin, xmax, ymin, ymax, gPopulacija, gIgralci) VALUES (?, ?, ?, ?, ?, ?)", i, i+10, j, j+10, row.gPopulacija, row.gIgralci)
cnxn.commit()