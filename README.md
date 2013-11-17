sourcecraft-gen
===============

blog:

www.thesourcecraft.wordpress.com

## Introduction ##

Sourcecraft-gen is the application that turns a code base into a structured file that minecraft transforms into a world of blocks.
The structured file is a list of coordinates and a block id. Such as:

1, 0, 1, 0
1, 0, 1, 1
1, 1, 1, 1
1, 1, 1, 0
1, 0, 2, 0
1, 0, 2, 1
1, 1, 2, 1
1, 1, 2, 0

This file would be run through the second part of the project, sourcecraft-mod, which would read the file and generate a 2x2 cube
in the world originating at the coordinate x:0, y:0, z:0 (which is ground level)

## Project Structure ##

### Folders ###

Cities
- Contains generated city files

Metrics
- Contains serialized metric classes
