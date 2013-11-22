package decorator;

import java.util.ArrayList;

import metrics.SimpleBugsMetrics;
import metrics.SimpleClassMetrics;

import core.BlockConstants;
import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.CityEntity;

public class BugDecorator extends AbstractDecorator {
        
        // USED FOR DEBUGGING
        private int decoratedBlocks = 0;
        private int decoratedMethods = 0;
        public static int methodsWithoutNames = 0;
        
        public static int bugReplaceWithWorse = 0;
        public static int bugEquallyBad = 0;
        public static int bugLessBad = 0;

        public BugDecorator() {
                
        }
        
        // Should decorate the entire city
        
        @Override
        public CityEntity decorateCity (CityEntity city, ArrayList<SimpleBugsMetrics> bugList) {
                
                createDecorationLists(city, bugList);
                ArrayList<BuildingEntity> buildingEntity = city.getBuildingEntries();
                
                for (BuildingEntity be : buildingEntity) {
                        BuildingData buildingData = be.getBuildingData();
                        ArrayList<BlockEntity> blocks = be.getBlockEntries();
                        ArrayList<BugData> decorateList = buildingData.getDecorateList();
                        
                        if (decorateList.isEmpty()) {
                                continue;
                        }
                        
                        // Starts decorating methods from the bottom of the building
                        for (int i = 0; i < decorateList.size(); i++) {
                                
                                int decorateHeight = findNextDecorationHeight(buildingData.getHeight(), decorateList.size(), i);
                                decorateMethod(blocks, decorateHeight, decorateList.get(i));
                                decoratedMethods++;
                        }
//                        System.out.println("");
                }
                System.out.println("");
                System.out.println("total decorated blocks: " + decoratedBlocks);
                System.out.println("total decorated methods: " + decoratedMethods);
                System.out.println("methods without names: " + methodsWithoutNames);
//                System.out.println("");
//                System.out.println("bugs replace with worse ones: " + bugReplaceWithWorse);
//                System.out.println("bugs equally bad: " + bugEquallyBad);
//                System.out.println("bug less bad: " + bugLessBad);
                
                return city;
        }
        
        // Creates a list of methods to decorate in each BuildingData object
        // If the same method has several bugs, only the worst bug is added to the list
        
        private void createDecorationLists (CityEntity city, ArrayList<SimpleBugsMetrics> bugList) {
                
                ArrayList<BuildingEntity> buildingEntries = city.getBuildingEntries();

                for(SimpleBugsMetrics bug : bugList) {
                        
                        for (BuildingEntity buildingEntity : buildingEntries) {
                                
                                SimpleClassMetrics scm = buildingEntity.getMetrics();
                                
                                // Match every bug to a BuildingEntity, and then add it the DecorateList
                                if (bug.getClassName().equals(scm.getClassName())) {
                                        BuildingData buildingData = buildingEntity.getBuildingData();
                                        
                                        int bugRating = BugConstants.setBugRating(bug.getCategory());
                                        BugData tempBug = new BugData(bugRating, bug.getPriority(), bug.getMethodName());
                                        
                                        buildingData.addToDecorateList(tempBug);
                                }
                        }
                }
        }

        
        // Changes the blockData of blocks that are in the height y
        
        private void decorateMethod (ArrayList<BlockEntity> blocks, int y, BugData bugData) {
                
                for (BlockEntity blockEntity : blocks) {
                        
                        if (blockEntity.getPoint().getY() == y) {
                                
                                if (bugData.getBugType() == BugConstants.STYLE) {
                                        BlockData newBlockData = new BlockData(BlockConstants.GOLD);
                                        blockEntity.setBlockData(newBlockData);
                                        decoratedBlocks++;
                                }
                                else if (bugData.getBugType() == BugConstants.BAD_PRACTICE) {
                                        BlockData newBlockData = new BlockData(BlockConstants.MUSHROOM);
                                        blockEntity.setBlockData(newBlockData);
                                        decoratedBlocks++;
                                }
                                else if (bugData.getBugType() == BugConstants.CORRECTNESS) {
                                        BlockData newBlockData = new BlockData(BlockConstants.DIAMOND);
                                        blockEntity.setBlockData(newBlockData);
                                        decoratedBlocks++;
                                }
                                else if (bugData.getBugType() == BugConstants.EXPERIMENTAL) {
                                        BlockData newBlockData = new BlockData(BlockConstants.COAL);
                                        blockEntity.setBlockData(newBlockData);
                                        decoratedBlocks++;
                                }
                                else if (bugData.getBugType() == BugConstants.MALICIOUS_CODE) {
                                        BlockData newBlockData = new BlockData(BlockConstants.EMERALD);
                                        blockEntity.setBlockData(newBlockData);
                                        decoratedBlocks++;
                                }
                                else if (bugData.getBugType() == BugConstants.PERFORMANCE) {
                                        BlockData newBlockData = new BlockData(BlockConstants.SNOW);
                                        blockEntity.setBlockData(newBlockData);
                                        decoratedBlocks++;
                                }
                        }
                }
        }
        private int findNextDecorationHeight (int height, int numberOfBugs, int currentBeingDecorated) {
                
                
                if (height == 1) {
                        return 0;
                }
                
                currentBeingDecorated++;
                int spacing = 1;
                int middleOfBuilding = 0;
                
                if (height % 2 == 1) {
                        middleOfBuilding = (height/2) + 1;
                }
                else {
                        middleOfBuilding = height/2;
                }
                
                if (height >= (numberOfBugs*2)) {
                        spacing = 2;
                }

                int next = currentBeingDecorated/2;
                if (numberOfBugs == 1) {
                        return middleOfBuilding-1;
                }
                else {
                        if (currentBeingDecorated % 2 == 0) {
                                return middleOfBuilding - (spacing*next);
                        }
                        else {
                                return middleOfBuilding + (spacing*next);
                        }
                }
        }


        
}