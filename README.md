# First stage of recruitment for the position of Junior Java Developer at Horus
## Author: _Maryna Kamienieva_

_Below we present a task asking you to analyze the code below and independently implement the findFolderByName, findFolderBySize, count methods in the FolderCabinet class - preferably avoiding code duplication and placing all logic in the FolderCabinet class. Considering the MultiFolder interface in the analysis and implementation!_

## Used Design Patterns & Algorithms 🚀
# [Composite Design Pattern](https://refactoring.guru/design-patterns/composite)
- allows treating individual folders and composite folders uniformly. So, represent the hierarhy of the structure (as we have an interface MultiFolder which contain a method `getFolders`)
- in the task => MultiFolder interface extends Folder, allowing us to treat both folders and composite folders uniformly. This ensures that operations like searching(finding by name or size) and counting work seamlessly across all folder types
- also, enables recursive operations on the folder hierarchy
- was used for `findFoldersBySize` and `findSizeFolders` and `count` methods => on individual folders and composite folders
- give an opportunity to avoid code duplication in the FolderCabinet class and reduces coupling and avoid putting all the logic to the one class

# Generic Recursion
there was used recursion alorithm for a couple of reasons:
- recursive solutions are often more concise and easier to understand than iterative solutions for tree traversal
- better for this taks, because of hierarhy
- I used a single recursive method (foldersChecker) that takes a lambda function to define the operations (count, optional, list)

# Why not other Algorithms?
- Of cource, I can use `DFS` for this solution, but in our case `recursion` will be much more efficient then `DFS` (would require explicit stack or queue)
- also, was thinking about the `HashMap`, but memory efficient is better in `recursion` approach
