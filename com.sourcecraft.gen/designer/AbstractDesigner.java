package designer;

import java.util.List;

import core.BlockEntry;

import metrics.AbstractMetrics;

public abstract class AbstractDesigner<T extends AbstractMetrics> {
	
	public abstract List<List<BlockEntry>> design(T metrics);

}
