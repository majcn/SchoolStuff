from PyQt4 import QtGui, QtCore
import sys
import Image, ImageDraw, ImageQt
import pyodbc

def translate(value, leftMin, leftMax, rightMin, rightMax):
    leftSpan = leftMax - leftMin
    rightSpan = rightMax - rightMin
    valueScaled = float(value - leftMin) / float(leftSpan)
    return rightMin + (valueScaled * rightSpan)

class QTravianStat(QtGui.QWidget):
    def __init__(self, parent=None):
        QtGui.QWidget.__init__(self, parent)

        self.setGeometry(200, 200, 800, 800)
        self.setWindowTitle('Travian Statistika')
        
        self.gPopulacija10 = QtGui.QPushButton('gostota populacije 10x10', self)
        self.gPopulacija10.setGeometry(10, 10, 60, 35)
        
        self.gIgralci10 = QtGui.QPushButton('gostota igralcev 10x10', self)
        self.gIgralci10.setGeometry(10, 10, 60, 35)
        
        self.gPopulacija = QtGui.QPushButton('gostota populacije 1x1', self)
        self.gPopulacija.setGeometry(10, 10, 60, 35)
        
        self.gIgralci = QtGui.QPushButton('gostota igralcev 1x1', self)
        self.gIgralci.setGeometry(10, 10, 60, 35)
        
        self.quit = QtGui.QPushButton('IZHOD', self)
        self.quit.setGeometry(10, 10, 60, 35)

        self.connect(self.quit, QtCore.SIGNAL('clicked()'), QtGui.qApp, QtCore.SLOT('quit()'))
        self.connect(self.gPopulacija10, QtCore.SIGNAL('clicked()'), self.prikaziSlikoPopulacija10)
        self.connect(self.gIgralci10, QtCore.SIGNAL('clicked()'), self.prikaziSlikoIgralci10)
        self.connect(self.gPopulacija, QtCore.SIGNAL('clicked()'), self.prikaziSlikoPopulacija)
        self.connect(self.gIgralci, QtCore.SIGNAL('clicked()'), self.prikaziSlikoIgralci)

        hbox = QtGui.QHBoxLayout()
        vbox = QtGui.QVBoxLayout()
        
        emptyPixmap = QtGui.QPixmap(800,800)
        emptyPixmap.fill()
        
        self.label = QtGui.QLabel(self)
        self.label.setPixmap(emptyPixmap)
        
        hbox.addWidget(self.label)
        hbox.addLayout(vbox)
        vbox.addWidget(self.gPopulacija10)
        vbox.addWidget(self.gIgralci10)
        vbox.addWidget(QtGui.QLabel())
        vbox.addWidget(self.gPopulacija)
        vbox.addWidget(self.gIgralci)
        vbox.addStretch(1)
        vbox.addWidget(self.quit)
        
        self.setLayout(hbox)
        
    def prikaziSlikoPopulacija10(self):
        cnxn = pyodbc.connect('DSN=pbvaje;UID=pb;PWD=pbvaje')
        cursor = cnxn.cursor()
        
        cursor.execute("SELECT MAX(gPopulacija) as maxPopulacija FROM gostota")
        row = cursor.fetchone()
        maxPopulacija = row.maxPopulacija
        
        slika = Image.new("RGB", (800, 800), "white")
        risar = ImageDraw.Draw(slika)
        
        cursor.execute("SELECT * FROM gostota")
        for r in cursor:
            barva = translate(r.gPopulacija, 0, maxPopulacija, 200, 0)
            risar.rectangle([r.xmin+400,r.ymin+400,r.xmax+400,r.ymax+400], (255, int(barva), int(barva)), None)

        risar.line([0, 400, 800, 400], "black", 1)
        risar.line([400, 0, 400, 800], "black", 1)
        self.label.setPixmap(QtGui.QPixmap.fromImage(ImageQt.ImageQt(slika).copy()))
        
    def prikaziSlikoIgralci10(self):
        cnxn = pyodbc.connect('DSN=pbvaje;UID=pb;PWD=pbvaje')
        cursor = cnxn.cursor()
        
        cursor.execute("SELECT MAX(gIgralci) as maxIgralci FROM gostota")
        row = cursor.fetchone()
        maxIgralci = row.maxIgralci
        
        slika = Image.new("RGB", (800, 800), "white")
        risar = ImageDraw.Draw(slika)
        
        cursor.execute("SELECT * FROM gostota")
        for r in cursor:
            barva = translate(r.gIgralci, 0, maxIgralci, 200, 0)
            risar.rectangle([r.xmin+400,r.ymin+400,r.xmax+400,r.ymax+400], (int(barva), int(barva), 255), None)

        risar.line([0, 400, 800, 400], "black", 1)
        risar.line([400, 0, 400, 800], "black", 1)
        self.label.setPixmap(QtGui.QPixmap.fromImage(ImageQt.ImageQt(slika).copy()))
        
    def prikaziSlikoPopulacija(self):
        cnxn = pyodbc.connect('DSN=pbvaje;UID=pb;PWD=pbvaje')
        cursor = cnxn.cursor()

        cursor.execute("SELECT MAX(population) as maxPopulacija FROM naselje")
        row = cursor.fetchone()
        maxPopulacija = row.maxPopulacija

        slika = Image.new("RGB", (800, 800), "white")
        risar = ImageDraw.Draw(slika)

        cursor.execute("SELECT * FROM naselje")
        for r in cursor:
            barva = translate(r.population, 0, maxPopulacija, 200, 0)
            risar.point([r.x+400, r.y+400], (255, int(barva), int(barva)))
                
        risar.line([0, 400, 800, 400], "black", 1)
        risar.line([400, 0, 400, 800], "black", 1)
        self.label.setPixmap(QtGui.QPixmap.fromImage(ImageQt.ImageQt(slika).copy()))

    def prikaziSlikoIgralci(self):
        cnxn = pyodbc.connect('DSN=pbvaje;UID=pb;PWD=pbvaje')
        cursor = cnxn.cursor()

        slika = Image.new("RGB", (800, 800), "white")
        risar = ImageDraw.Draw(slika)

        cursor.execute("SELECT * FROM naselje")
        for r in cursor:
            risar.point([r.x+400, r.y+400], (0, 0, 255))

        risar.line([0, 400, 800, 400], "black", 1)
        risar.line([400, 0, 400, 800], "black", 1)
        self.label.setPixmap(QtGui.QPixmap.fromImage(ImageQt.ImageQt(slika).copy()))


app = QtGui.QApplication(sys.argv)
qb = QTravianStat()
qb.show()
sys.exit(app.exec_())