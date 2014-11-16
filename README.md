Mouse in Maze
=============

To run the code:
- run maze.solver.main.MazeSolver.java

Board Example:
================================================
| Gap  |  Gap  |  Gap  |  333  |  Gap  |  Gap  |  
| 444  |  444  |  Gap  |  333  |  Gap  |  Gap  |  
| Rex  |  Rex  |  Gap  |  Gap  |  Gap  |  Gap  |  
| Gap  |  555  |  555  |  Gap  |  Gap  |  Gap  |  
| Gap  |  Gap  |  Gap  |  222  |  Gap  |  Gap  |  
| Gap  |  Gap  |  Gap  |  222  |  Gap  |  Gap  |  
================================================
Each block is represented by unique ID, ex: 333 represents 
a vertical block of size 2 and pivotPosition=(0,3).

Rex represents the mouse

===========================#===========================
Tree Node:
- Status: whole board
- Operators: <blockPivot,actions> 
- path_cost: numberOfMovesToCurrentState
- depth_cost: depthInTree
- Parent_node: to trace back the list of actions to reach current state
- children nodes: pointer to children 

Search Problem:
- initial state
- operators
- path cost
- goal test
- states space

/* Algorithm:
 * nodes <- MAKE-Q(MAKE-NODE(INIT-STATE(problem)))
 * loop do If nodes is empty 
 * then return failure 
 * node <- REMOVE-FRONT(nodes) 
 * If GOAL-TEST(problem)(STATE(node)) 
 * then return node nodes <- QING-FUN(nodes,EXPAND(node, OPER(problem)))
 * end
 */

BreadthFirst:
- takes Root Node
- Insert first level nodes into the queue
- recurse on dequeue

Note:
PivotID = 6x+y
0 0 0 0 0 0 
1 0 0 0 0 0
1 0 0 0 0 0
0 0 0 0 0 0

Deprecated Cost Function:
cost=numberOfBlocks-numberOfMovableBlocks
cost=numberOfMovableBlocks

Used Cost Function:
cost=3:
 




 
