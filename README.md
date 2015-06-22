#Labyrinth
=========

Simple 2D game where adventurer has to solve a maze

###Progress

####Currently working:
* maze generation with some of the doors locked with keys
* monsters exist
* boss creature exists
* critical path (at least certain number of rooms have to be opened for boss creature to be reached)
* monsters and players have skills
* monsters' level variation
* basic weapons
* basic weapon and armour mechanics
* teleportation
* inventory
* health works
* food works
* simple loot mechanics
* and some more things...

####Todo:
* player levelup
* variation in room types
* room object implementation
* win condition ¯\\(º_o)/¯
* way to spend money
* ...

###Playing

If you want to try game out, keys are written in Config.java, but here's overview:
* move with WASD
* open doors with Shift (if room is 'locked', first Shift hit merely unveils the content, second moves player inside). To open the door and enter room, player must be adjacent to the door
* Q drops temporary teleport stone (gatestone) and E teleports you to it. you can always reposition this stone
* 1 drops 'permanent' teleport stone and 2 teleports to it. To drop that gatestone, you must currently hold it (i.e. gatestone is not currently laying around in the labyrinth)
* Space picks up items, keys and permanent gatestone
* Can attack creatures and pick up items with mouse (leftclick shows list of possible actions)
* H teleports back to starting room (takes slightly longer to cast, can be interrupted)


![Picture](https://raw.github.com/henri5/Labyrinth/master/screenshot.png)
