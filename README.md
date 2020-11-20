# Artificial Intelligence
 AI Algorithms

The programming exercise concerns the study of the results of solving the problem of N-Queens according to the algorithm of 1)hill climbing (hill climbing) and 2)constraint satisfaction (constraint satisfaction problem with early control).

Description of the problem
The exercise involves the study of the results of solving the problem of N-Queens according to the algorithm of hill climbing and restriction satisfaction (constraint satisfaction problem with early control). The problem of N-Queens is interpreted as a chessboard containing N number of queens on a dimensional board. The chessboard is filled by randomly placing the queens on the board. The structure of the problem is explained by the total number of sub-threat queens.

The chessboard is reprepesented as an array NxN elements.
The queens represented as values in the array.


Part A : Local Search
1)Find collision pairs between queens.
2)Find best available move so collision count is being optimally improved for a random Queen.
3)Update Grid.
4)Repeat until no Queen is threatened.

Part B : Constaint Satisfaction
1)Temporary setup every queen in the first row of each chessboard column.
2)Fill chessboard with all constraints.

Assumption:
Queens threaten only to the right horizontally and right diagonally. Constraints are not filled from the left and vertically, for the simple reason that threats are mutual and vertically no queen is threatened.

The solution arises after similar movements of queens for each algorithm,resulting in the desired condition of the chessboard.

How to run
Run main.java. A menu is appeared with 2 choices.
1)Executing algorithms
2)Exit program
First algorithm prepared to be executed is local search. The results are presented in console.
Second algorithm prepared to be executed is constraint satisfaction. The results are presented in console

The project has been created in java Eclipse

