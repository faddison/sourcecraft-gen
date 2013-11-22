package planner;

import java.io.PrintWriter;

import core.BlockData;
import core.BlockEntity;
import core.BuildingData;
import core.BuildingEntity;
import core.Point3D;

public class BlockWriter
{
        public void placeSigns(PrintWriter writer, BuildingEntity building, int xOffset, int yOffset, int zOffset, String className)
        {
                
                for (BlockEntity blockEntity: building.getBlockEntries())
                {
                        
                        if (isSignLocation(building.getBuildingData(), blockEntity.getPoint()))
                        {
                                Point3D p = blockEntity.getPoint().translate(xOffset, 1, zOffset);
                                BlockData data = new BlockData(63);
                                placeBlock(writer, new BlockEntity(p, data), className);
                                System.out.println(String.format("%d %d %d %d %s", data.getId(), p.getX(), p.getY(), p.getZ(), className));
                        }
                                Point3D p = blockEntity.getPoint().translate(xOffset, yOffset, zOffset);
                                //placeBlock(writer, className, new BlockEntity(p, blockEntity.getBlockData()));        
                }
        }
        
        public void placeBlock(PrintWriter writer, BlockEntity blockEntity, String className)
        {
                Point3D p = blockEntity.getPoint();
                writer.print(String.format("%d %d %d %d %s\n", blockEntity.getBlockData().getId(), p.getX(), p.getY(), p.getZ(), className));        
        }
        
        private boolean isSignLocation(BuildingData data, Point3D p)
        {
                return (p.getX() == Math.ceil(data.getLength()/2) && p.getZ() == Math.ceil(data.getLength()/2) && p.getY() == data.getHeight() - 1);
                
        }
}