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

### Examples

#### Non-deterministic Turing Machine for "ww<sup>R</sup>"
- **Acceptance States**: 7
- **Maximum Steps**: 800
- **Input Strings**:
    - `aababbabaa`
    - `aababbabaaaababbabaa`
    - `aababbabaaaababbabaab`
    - `aababbabaaaababbabaabbaababbabaaaababbabaa`
    - `aababbabbaaababbabaabbaababbabaaaababbabaa`
- **Transitions**:
    - `0 a a R 0`
    - `0 b b R 0`
    - `0 a c R 1`
    - `0 b c R 2`
    - `1 a c L 3`
    - `2 b c L 3`
    - `3 c c L 3`
    - `3 a c R 4`
    - `3 b c R 5`
    - `4 c c R 4`
    - `4 a c L 3`
    - `5 c c R 5`
    - `5 b c L 3`
    - `3 _ _ R 6`
    - `6 c c R 6`
    - `6 _ _ S 7`
- **Output**:
    - `1`
    - `1`
    - `0`
    - `U`
    - `0`

#### Turing Machine for "ww"
- **Acceptance States**: 11
- **Maximum Steps**: 2000
- **Input Strings**:
    - `aabaab`
    - `bbabbb`
    - `ababa`
    - `babaaababaaa`
    - `ababaabababbabbbbbabbbbabaaababaaaababaabababbabbbbbabbbbabaaababaaa`
- **Transitions**:
    - `0 a c R 1` 
    - `0 b c R 2`
    - `1 a a R 1` 
    - `1 a d L 3`
    - `1 b b R 1`
    - `2 a a R 2` 
    - `2 b b R 2` 
    - `2 b d L 3` 
    - `3 a a L 3`
    - `3 b b L 3`
    - `3 c c R 4`
    - `4 d d R 10`
    - `4 a c R 5`
    - `4 b c R 6`
    - `5 a a R 5`
    - `5 b b R 5`
    - `5 d d R 7`
    - `6 a a R 6`
    - `6 b b R 6`
    - `6 d d R 8`
    - `7 d d R 7`
    - `7 a d L 9`
    - `8 d d R 8`
    - `8 b d L 9`
    - `9 d d L 9`
    - `9 a a L 3`
    - `9 b b L 3`
    - `9 c c R 10`
    - `10 d d R 10`
    - `10 _ _ S 11`
- **Output**:
    - `1`
    - `0`
    - `0`
    - `1`
    - `U`
