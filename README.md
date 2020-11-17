# StringGenerator
A little tool to generate random or iterated strings.

## Usage
This command-line-based program generates strings for you. You can choose how to generate the strings, how many strings to generate, and a few other things.
These options are saved in *settings.txt* and can be configured in the program. Your strings will be saved in *strings.txt*.

## Settings
..* Generation type: *random* or *iteration*. Random means the chars will be generated randomly, iteration means the chars will start at the lowest order and subsequent strings will be incremented from the previous string (in other words, you'll get "aaa", "aab", "aac", and so on).
..* Quantity: The amount of strings to generate.
..* Length: The length of strings to generate.
..* Core: A substring to include in every generated string. Will be added at index 0 (the beginning of the string). In *settings.txt* the core will be saved with quotations (").
You can also specify the range of characters to generate from - letters, numbers, or both.
..* Letters: *on* or *off*.
..* Numbers: *on* or *off*.
Note: If the quantity is greater than the amount of permutations possible with the length and range of chars the program will generate duplicates of strings.

## Code hierarchy
..* StringGenerator: Contains *main()* and handles command-line inputs.
..* Settings: Handles the settings.
..* Generator: Handles string generation.

## Remarks
The implementation should be straightforward enough so that you can modify the settings to fit your needs, such as including capital letters.
The library used for randomization is psuedo-random so it is not safe for your encrypting needs. Not that you would use a stranger's code to encrypt anything, right?
Licensed under GPL-3.0.
