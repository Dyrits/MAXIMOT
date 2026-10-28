# Maximot

A Java word game: from a random draw of letters, find the longest possible word.

## About The Project

A console game built from scratch as a project for the ENI course "La Programmation Orientée Objet (POO) avec Java" (Module 1 - Les bases du Java). The original assignment (in French) is available in the repository: [Module 01 - Enoncé TP - Maximot 1.pdf](https://github.com/Dyrits/MAXIMOT/blob/Maximot-1/Module%2001%20-%20Enonc%C3%A9%20TP%20-%20Maximot%201.pdf).

How a round works, from the course assignment:

- A word is drawn at random from the dictionary;
- Its letters are shuffled randomly and shown to the player;
- The player enters a proposal;
- The proposal is checked: only the drawn letters may be used, and the word must exist in the dictionary;
- The word drawn at random is then revealed to the player.

The `dictionnaire.txt` dictionary file was provided with the assignment. A path to another word file can be passed as a program argument to use a different dictionary.

### Versions

From the author's notes:

- Maximot 1 - Completed (V1.1.01): this branch. V1.1.01 added support for reading an external word file, passed as a program argument.
- Maximot 2 - Completed (V1.2.0): [`Maximot-2`](https://github.com/Dyrits/MAXIMOT/tree/Maximot-2) branch.
- Maximot 3 - Completed (V1.3.0): [`Maximot-3`](https://github.com/Dyrits/MAXIMOT/tree/Maximot-3) branch.

## Built With

- Java

## Getting Started

### Prerequisites

- A Java Development Kit (JDK)

### Running the game

- Compile the sources in the `src/` directory and run the program. The main class is not documented in the repository.
- Optionally, pass the path to a word file as an argument to replace the default `dictionnaire.txt` dictionary.

## License

No license file is present in this repository.

## Contact

Dylan J. Gerrits - [GitHub](https://github.com/Dyrits)
