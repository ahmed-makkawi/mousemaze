mousemaze
=========

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
cost=1:

Heuristic = 0->{REX,row2} | 1->else
Prefer To move away from {row2} if vertical
	If block==Row2 && block==Vertical && block=canMove
		h = 0
	else if block==REX && block=canMove
		h=0
		else h = 1
Prefer To move REX 

Heuristic = 0->{REX, row1, row2, row3} | 1->else
Prefer To move away from {row1, row2, row3} if vertical 




 
