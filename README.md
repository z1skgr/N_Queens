# AI application
> Solving the problem of n queens using AI algorithms studying their performance.

## Table of contents
* [General Info](#general-information)
* [Implementation](#implementation)
* [Setup](#setup)
* [Screenshot](#screenshot)
* [Acknowledgements](#acknowledgements)

## General Information
On *NxN* chessboard, queens placed at random cells. The problem is solved after queens move to the desired state of the chessboard (no threatened queen). <br>For more information, check https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/. <br>
In our approach, we have 
1. Local search 
2. Constraint satisfaction

## Implementation
We use `Grid.Java` to represent queens in a board ( __NxN__ grid, queen as binary values)
Grid is randomly initialized with queens in any columns.


### Hill Climbing
* Available move is a position in which another queen can't intervene (inside the grid).
* Queens exposed to attacks reflect the conflicts.

For more information, see https://www.javatpoint.com/hill-climbing-algorithm-in-ai

### Constraint Satisfaction 
In our view, queen can threat diagonally, vertical and horizontaly (due to mutual collisions between two queens).

* Non-threatened queen by a predecessor (from left-sided queen), stays where she is.
* Otherwise, finds a row in which he is not bothered by the previous ones. 
    * No such row, then the algorithm will return with the position of the queen who has a problem.

For more info, see https://en.wikipedia.org/wiki/Constraint_satisfaction

### Performance Measures
* Average resolution time for N number of repetitions per number
queens
* Average initialization number of the grid to establish the solution.
* Reliability of the solution.
* Collision minimization

## Setup
Java Integrated Development Environment (Eclipse IDE)

## Screenshot
https://github.com/z1skgr/N_Queens/issues/1#issue-1143769173





## Acknowledgements
- This project was created for the requirements of the lesson Artificial Intelligence

