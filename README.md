# GridGame

GridGame is a simple Java-based game where players can merge cells on a grid. The game features a graphical user interface built with Swing.

![Grid Game Screenshot](https://github.com/ludwig-dev/GridGame/blob/main/GridGame.png)
## Features

- Graphical User Interface using Swing
- Merge cells with the same number
- Score tracking
- Randomly generated grid

## Getting Started
### Prerequisites

- Java 21 or higher
- Maven

### Installing

1. Clone the repository:
    ```sh
    git clone https://github.com/ludwig-dev/gridgame.git
    cd .\gridgame\demo\
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

### Running the Game

To run the game, execute the `App` class:

```sh
mvn exec:java
```

## How to Play
1. Launch the game
2. Click on a cell to select it
3. Click on an adjacent cell with the same number to merge them
4. The merged cell will increase its number by 1, and the score will be updated.
5. When mering two cells with the number 5, new cells spawns at the top.
6. The game continues until no more merges are possible


