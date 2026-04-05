# Wave Function Collapse Exercise

A Scala application that implements the Wave Function Collapse algorithm to generate procedural 2D maps featuring landmasses and water bodies.

## Description

This project demonstrates the Wave Function Collapse (WFC) algorithm, a procedural generation technique that creates coherent tile-based patterns from a set of input tiles. The application generates random land and sea configurations using a constraint-based approach.

## Features

- Procedural generation of 2D tile maps
- Interactive GUI for generating new worlds
- Constraint-based tile placement algorithm
- Support for custom tile sets

## Installation

### Prerequisites

- Scala 3.1.3 or higher
- SBT (Scala Build Tool)

### Building the Project

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd wave-function-collapse-exercise
   ```

2. Compile the project:
   ```bash
   sbt compile
   ```

3. Run the application:
   ```bash
   sbt run
   ```

## Usage

The application provides a graphical interface where you can:

- View the generated world map
- Generate new random configurations using the restart button
- Observe the wave function collapse algorithm in action

## Project Structure

```
wave-function-collapse-exercise/
├── lib/                    # Library dependencies
├── src/
│   ├── main/
│   │   └── scala/
│   │       ├── WaveFunctionCollapse.scala  # Core WFC algorithm
│   │       ├── Square.scala               # Tile representation
│   │       ├── AllTiles.scala             # Tile definitions
│   │       └── Main.scala                 # GUI application
│   └── test/                              # Test files
├── Tiles/                  # Tile image assets
├── build.sbt              # Build configuration
└── README.md              # This file
```

## Dependencies

- Scala Swing (for GUI)
- O1 Library (for graphics utilities)

## Credits

- Tile art by [Hellchains](https://hellchains.itch.io/water-cliff-tileset)
- Wave Function Collapse algorithm implementation

## License

This project is for educational purposes.
