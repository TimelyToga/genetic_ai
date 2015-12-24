# Genetic AI
AI that learns to play a game through a genetic evolution algorithm.

##Overview
The game itself is written using the Java library [Slick2D](http://slick.ninjacave.com/), which is a Java game engine, 
which is implemented using the [LWJGL](https://www.lwjgl.org/). So the classes to look at there:
* [EvolutionGame.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/EvolutionGame.java): contains main game loop
* [World.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/World.java): contains game world definition, and where a lot of action happens
* [Renderable.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/Renderable.java): contains `Renderable` abstract class, which is basis for all renderable objects
* [G.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/G.java): contains globals

###Game Design
So the game's design is simple enough:

