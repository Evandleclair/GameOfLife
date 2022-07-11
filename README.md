
# Game Of Life

This project was intended to re-aquaint me with Java, and to practice multithreading.
It allows users to run multiple instances of Conway's Game of Life based on custom parameters, and to import and export these generated boards. 


## Authors

- [@evanleclair](https://github.com/Evandleclair)





## Requirements

This project was created using JDK 17, and so installing JDK 17 or newer or Java 8 or newer is recommended. 

https://www.java.com/en/download/

https://www.oracle.com/java/technologies/downloads/

### Notes on Logging System

The logging system will not function correctly on Mac OS based systems. The rest of the program should run correctly. 

## Features

- Supported by any platform that can run Java
- Set custom parameters and create boards that play out the game of life, with up to twenty simultaneous simulations possible. 
- Users can pause their simulations, click to create or kill cells, and then resume to watch the patterns play out. 
- Users can save interesting patterns and boards, and import them to be resumed later. 

# How to use the program:

## Part 1: the main window

When you start the program, you will be greeted by the main window.
We will go into these features in depth later. For now you simply need to know that the main window consists of:
1. A section where you can define the parameters for the simulation you want to run.
2. A section where you can see the current rules that are running the simulation, and choose your own.
3. A table listing all the current running games, which lists their name, their current generation, their generation at which they stop, their status, and the time between generations in miliseconds.
4. Two buttons:
  3A. The "Start Game" button creates a new Game of Life using the parameters and rules you defined in sections 1 and 2.
  3B. The "End All Games" button ends all running simulations. 

### Section 1: The parameters box.
### Section 2: The rules box.
### Section 3: The game table.
## Part 2: The Game Window
### Section 1: The menu bar.
  Every simulation window has a menu bar, which contains options on how to interact with your simulation. 
### Section 2: Editing a game in progress.
  Once a game has been paused using the menu bar, you can revive and kill cells by clicking on them. Clicking on a live cell kills it, and clicking on a dead cell revives it. While it it possible to do this when the game is not paused as well, unless the generation time is very long the cells will usually die before you can give them enough neighbors to support themselves. 
## Part 3: Importing and Exporting Games.
### Section 1: Exporting a Game
### Section 2: Importing a Game
