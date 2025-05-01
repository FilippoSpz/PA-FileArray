# FileArray Project

## Overview

This project is a Java-based application designed to manage and manipulate arrays stored in binary files. It was
developed as part of a school project to demonstrate file handling, random number generation, and basic command-line
interface (CLI) operations in Java.

The program allows users to create a binary file containing an array of random integers, increment all values in the
array, and display the array in a formatted manner. The application is composed of two main classes: `Main` and
`FileArray`.

---

## Features

1. **Random Array Creation**:
    - Generates a binary file containing an array of random integers.
    - The size of the array is randomly chosen between 1 and \(2^5\) (32).

2. **Increment All Values**:
    - Increments all integers in the array by 1.

3. **Formatted Array Printing**:
    - Displays the array in a formatted table with indices and values aligned for readability.

4. **Command-Line Interface**:
    - Accepts commands via CLI to perform operations on the array.

---

## How It Works

### `Main` Class

The `Main` class is the entry point of the application. It:

- Accepts the file path and commands as arguments.
- Initializes the `FileArray` object.
- Processes commands (`i` for increment, `p` for print) to manipulate or display the array.

### `FileArray` Class

The `FileArray` class handles all file-related operations:

- **File Creation**: Creates a binary file with random integers if the file does not already exist.
- **Increment Operation**: Reads the integers from the file, increments them, and writes them back to the file.
- **Formatted Printing**: Reads the integers and displays them in a structured format.

---

## Error Handling

- If the file path is invalid or inaccessible, the program will display an error message.
- If an unrecognized command is provided, the program will print: unrecognized command.

---

## Key Concepts Demonstrated

1. **File I/O**:
    - Reading and writing binary files using `DataInputStream` and `DataOutputStream`.

2. **Random Number Generation**:
    - Generating random integers using `java.util.Random`.

3. **Command-Line Arguments**:
    - Parsing and processing user input from the command line.

4. **Error Handling**:
    - Managing exceptions such as `IOException`.

5. **Formatted Output**:
    - Using `System.out.printf` for structured and readable output.

---

## License

This project is intended for educational purposes only and is not meant for commercial use.

---

## Author

- **Name**: [Filippo Spazzali]
- **School**: [Università degli Studi di Trieste]
- **Course**: [Computer Science]
