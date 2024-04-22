### Turing Machine Simulator

- Implementation of a non-deterministic Turing machine interpreter in Java language for single-tape acceptors.
- **Input File Structure**:
    - The file is divided into four parts:
        1. **"tr"**: Transition function provided first, with transitions listed one per line.
        2. **"acc"**: Lists acceptance states, one per line.
        3. **"max"**: Indicates the maximum number of steps for a single computation to prevent non-terminating machines.
        4. **"run"**: Lists strings for the machine to process, one per line.
- **Expected Output**:
    - A file containing `0` for non-accepted strings and `1` for accepted ones. If the step limit is reached without acceptance, the result can be `U` for undefined.

#### Usage Conventions
- For simplicity, tape symbols are characters (`char`), and states are integers (`int`).
- The machine always starts from state `0` and the first character of the input string.
- The tape is assumed to be unlimited in both directions and initially filled with the "blank" symbol (`_`).
- Characters "L", "R", "S" are used for head movement as usual.
- Input is provided through standard input, and output is through standard output.
