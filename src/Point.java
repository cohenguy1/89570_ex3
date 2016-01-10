
public class Point 
{
	public double X;
	public double Y;

	private int _clusterIndex;
	
	public Point(double x, double y) 
	{
		X = x;
		Y = y;
	}
	
	public void SetCluster(int clusterIndex)
	{
		_clusterIndex = clusterIndex;
	}

	public int GetClusterIndex() 
	{
		return _clusterIndex;
	}
}
