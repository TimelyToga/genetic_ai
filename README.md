# Genetic AI
AI that learns to play a game through a genetic evolution algorithm. There is a defined game where small circles attempt to live as long as possible. They burn energy at an individual rate, and every action burns energy. They can gain energy by consuming a food object that is randomly spawned into the game.

The best of the actors are then skimmed off, randomly paired with other top actors, with whom their parameters (genes), are mixed (genetic crossover) to create a model for the next generation. The next generation is then created by randomly perturbing each of the actor's parameters. This process is repeated for as long as defined in the simulation.

## Game Design
The game itself is written using the Java library [Slick2D](http://slick.ninjacave.com/), which is a Java game engine,
which is implemented using the [LWJGL](https://www.lwjgl.org/).

All active units are represented as independent actors with various traits that are then fed into common engine code to allow the simulation to be run.

#### Classes of Note:
* [EvolutionGame.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/EvolutionGame.java): contains main game loop
* [World.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/World.java): contains game world definition, and where a lot of action happens
* [Renderable.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/Renderable.java): contains `Renderable` abstract class, which is basis for all renderable objects
* [G.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/genetic_ai/G.java): contains globals


## Simulation Design
The simulation is implemented with Scrum as the actors, which are attempting to collect Food objects.


#### Classes of Note:
* [Scrum.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/units/Scrum.java): class that defines the independent actors
* [Food.java](https://github.com/TimelyToga/genetic_ai/blob/master/src/units/Food.java): Food objects

## Discussion of AI

The [AI](https://github.com/TimelyToga/genetic_ai/blob/master/src/ai_methods/AI.java) interface allows several AI implementations to be constructed and used in the simulation. I am starting with [NaiveAI](https://github.com/TimelyToga/genetic_ai/blob/master/src/ai_methods/NaiveAI.java) that is merely, if you sense food, move towards it. But I plan to use the Java Neural Network Library [Neuroph](http://neuroph.sourceforge.net/) to create more complicated movement behavior. The AI method call produces an [Action](https://github.com/TimelyToga/genetic_ai/blob/master/src/ai_methods/Action.java) object which is essentially a wrapper for a vector that describes the velocity (direction and magnitude, mind you) for the Scrum's movement until the next frame.
