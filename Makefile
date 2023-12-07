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
	game/Entity.java \
	game/BackgroundManager.java \
	game/Utils.java \
	stateManager/StateManager.java \
	stateManager/GameState.java \
	stateManager/StateGameplay.java \
	stateManager/StateMenu.java \
	stateManager/StateOptions.java \
	player/Player.java \
	player/PlayerLight.java \
	player/PlayerHeavy.java \
	player/PlayerManager.java \
	attacks/Attack.java \
	attacks/Sphere.java \
	attacks/HomingSphere.java \
	attacks/special/Beam.java \
	attacks/special/Beyblade.java \
	attacks/special/Pulse.java \
	sounds/SoundManager.java \
	sounds/SoundEffectPlayer.java \
	sounds/MusicPlayer.java \
	enemies/Enemy.java \
	enemies/Grunt.java \
	enemies/Officer.java \
	bosses/Phase.java \
	bosses/UFO/UFO.java \
	bosses/UFO/phases/Phase1.java \
	bosses/UFO/phases/Phase2.java \
	bosses/UFO/phases/Phase3.java \
	bosses/UFO/phases/Phase4.java \
	bosses/UFO/phases/Phase5.java \
	bosses/UFO/phases/Phase6.java \
	bosses/JET/Jet.java \
	bosses/JET/phases/Phase1.java \
	bosses/JET/phases/Phase2.java \
	levels/LevelManager.java \
	levels/Level.java \
	levels/Level1.java \
	levels/Level2.java \
	levels/Level3.java \
	levels/Level4.java \
	ui/Menu.java \
	ui/TitleScreen.java \
	ui/MenuItem.java \
	ui/Options.java \
	ui/PlayerMenuItem.java

default: classes
classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
