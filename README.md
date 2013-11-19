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

### Folders ###

/cities
- Contains generated city files like the one shown above

/maps
- Contains 2d maps generated from the city files by the mapping application

/metrics
- Contains serialized java 'ClassMetrics' arrays

/sources
- Contains xml representations of the code base being visualized

/com.sourcecraft.gen
- The java application

### Packages ###

Core
 - Contains all the core datatypes used in the application such as Point3D and BuildingEntity, etc..

Metrics
 - A set of objects that encompass the statistics about a class in the source code being analyzed

Parser
 - Classes responsible for parsing the source code and translating them into 'Metric' objects

Designer
 - Classes responsible for constructing the basic building size and structure from the metrics object.

Decorator
 - Classes responsible for decorating buildings with certain elements like doors and windows after the basic design is completed by the designer

Planner
 - Classes responsible for determing the layout and location of buildings in the world and writing the blocks to the city file

Mapper
 - An additional application which translates the city file into a readable map of 1's and 0's to preview the layout by the planner

Templates
 - A set of classes which represent different building theme configurations

Runner
 - The main entry point to the application which executes different templates

Util
 - Some helper classes for mathematical and IO functions

Tests
 - Test suite



## Usage ##

## Extension ##
