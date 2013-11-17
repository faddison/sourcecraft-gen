sourcecraft-gen
===============

blog:

www.thesourcecraft.wordpress.com

## Introduction ##

Sourcecraft-gen is the application that turns a code base into a structured file that minecraft transforms into a world of blocks.
The structured file is a list of coordinates and a block id. Such as:

block_id, x, y, z

1, 0, 1, 0  
1, 0, 1, 1  
1, 1, 1, 1  
1, 1, 1, 0  
1, 0, 2, 0  
1, 0, 2, 1  
1, 1, 2, 1  
1, 1, 2, 0  

This file would be run through the second part of the project, sourcecraft-mod, which would read the file and generate a 2x2 cube
in the world originating at the coordinate x:0, y:1, z:0 (sitting on ground level)

## Project Structure ##

### Packages ###

### Folders ###

/cities
- Contains generated city files like the one shown above

/maps
- Contains 2d maps generated from the city files by the mapping application

/metrics
- Contains serialized java 'ClassMetrics' arrays

/sources
- Contains xml representations of the code base being visualized
