JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	game/GameScreen.java \
	game/KeyboardManager.java \
	game/Player.java \
	game/Background.java \
	game/Bullet.java \
	enemies/Enemy.java \
	enemies/Grunt.java \
	enemies/Officer.java \
	levels/Level.java \
	levels/Level1.java \
	levels/Level2.java \
	levels/Level3.java \
	ui/Menu.java \

default: classes
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
