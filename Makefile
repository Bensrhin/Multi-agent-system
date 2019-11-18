# Ensimag 2A POO - TP 2015/16
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     Pour un package (ici gui.jar), il est aussi dans bin.
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: Conway

BallsSimulator2:
	make testBallsSimulator2
	make exeBallsSimulator2

Conway:
	make testConway
	make exeConway

Immigration:
	make testImmigration
	make exeImmigration

Shelling:
	make testShelling
	make exeShelling

Boids:
	make testBoidsSimulator
	make exeBoidsSimulator

EventManager:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestEventManager.java
	java -classpath bin:bin/gui.jar TestEventManager


testBallsSimulator2:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBallsSimulator2.java

testGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestEventManager.java

testConway:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestConway.java

testImmigration:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestImmigration.java

testShelling:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestShelling.java

testBoidsSimulator:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestBoidsSimulator.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin TestGUI
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeIHM

exeBallsSimulator2:
	java -classpath bin:bin/gui.jar TestBallsSimulator2
exeConway:
	java -classpath bin:bin/gui.jar TestConway
exeImmigration:
	java -classpath bin:bin/gui.jar TestImmigration
exeShelling:
	java -classpath bin:bin/gui.jar TestShelling
exeBoidsSimulator:
	java -classpath bin:bin/gui.jar TestBoidsSimulator

clean:
	rm -rf bin/*.class
