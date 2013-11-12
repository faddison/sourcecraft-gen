package parser;

import java.util.ArrayList;

import metrics.AbstractMetrics;

public abstract class AbstractParser<T extends AbstractMetrics>
{

	public abstract ArrayList<T> parse(String sourceFilename);


}
