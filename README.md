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


 
