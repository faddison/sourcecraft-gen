package planner;

import java.io.PrintWriter;

import core.BlockEntity;
import core.BuildingEntity;
import core.Point3D;

public class BlockWriter
{
	public void placeBuildingBlocks(PrintWriter writer, BuildingEntity building, int xOffset, int yOffset, int zOffset)
	{
		for (BlockEntity blockEntity: building.getBlockEntries())
		{
			Point3D p = blockEntity.getPoint().translate(xOffset,  yOffset, zOffset);
			placeBlock(writer, new BlockEntity(p, blockEntity.getBlockData()));	
		}
	}
	
	public void placeBlock(PrintWriter writer, BlockEntity blockEntity)
	{
		Point3D p = blockEntity.getPoint();
		writer.print(String.format("%d %d %d %d\n", blockEntity.getBlockData().getId(), p.getX(), p.getY(), p.getZ()));	
	}
}
