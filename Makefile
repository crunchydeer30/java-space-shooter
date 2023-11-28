JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	game/GameScreen.java \
	game/KeyboardManager.java \
	game/Background.java \
	game/Bullet.java \
	game/Entity.java \
	game/StateManager.java \
	game/LevelManager.java \
	game/BackgroundManager.java \
	game/Utils.java \
	player/Player.java \
	player/PlayerLight.java \
	player/PlayerHeavy.java \
	player/PlayerManager.java \
	sounds/SoundManager.java \
	sounds/SoundEffect.java \
	sounds/Music.java \
	enemies/Enemy.java \
	enemies/Grunt.java \
	enemies/Officer.java \
	enemies/Boss.java \
	levels/Level.java \
	levels/Level1.java \
	levels/Level2.java \
	levels/Level3.java \
	levels/Level4.java \
	ui/Menu.java \
	ui/TitleScreen.java \
	ui/MenuItem.java \
	ui/Options.java

default: classes
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
