package core;

public class BlockEntity 
{
	public BlockEntity(Point3D point, BlockData blockData) {
		super();
		this.point = point;
		this.blockData = blockData;
	}
	private Point3D point;
	private BlockData blockData;
	
	public Point3D getPoint() {
		return point;
	}
	public void setPoint(Point3D point) {
		this.point = point;
	}
	public BlockData getBlockData() {
		return blockData;
	}
	public void setBlockData(BlockData blockData) {
		this.blockData = blockData;
	}
}
