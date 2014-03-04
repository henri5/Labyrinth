Labyrinth
=========

Simple 2D game where adventurer has to solve a maze

Started working on it on 9th Feb '14. I wanted to create this game for Android (and still do) but considered it easier first to figure out the difficult areas and possible problems via developing it for PC and later port to Android.

Idealistically, game would be multiplayer game with skills and leveling and monsters with different mechanics and various interesting weapons with unique effects.

Currently working:
* maze generation with doors locked with keys
* monsters exist
* boss creature exists
* critical path (at least certain number of rooms have to be opened for boss creature to be reached)
* monsters and players have skills
* monsters' level variation
* basic weapons
* basic weapon and armour mechanics
* teleportation
* inventory
* food works
* simple loot mechanics
* and some more things...

Todo:
* player levelup
* variation in room types
* room object implementation
* win condition ¯\\(º_o)/¯
* way to spend money
* ...

If you want to try game out, keys are written in Config.java, but here's overview:
* move with WASD
* open doors with Shift (if room is 'locked', first Shift hit merely unveils the content, second moves player inside). To open the door and enter room, player must be adjecent to the door
* Q drops temporary teleport stone (gatestone) and E teleports you to it. you can always reposition this stone
* 1 drops 'permanent' teleport stone and 2 teleports to it. To drop that gatestone, you must currently hold it (i.e. gatestone is not currently laying around in the labyrinth)
* Space picks up items, keys and permanent gatestone
* Can attack creatures and pick up items with mouse (leftclick shows list of possible actions)
* H teleports back to starting room (takes slightly longer to cast, can be interrupted)
