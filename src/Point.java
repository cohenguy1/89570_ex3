
public class Point 
{
	public double X;
	public double Y;

	// The cluster that holds the point
	private Cluster _cluster;
	
	public Point(double x, double y) 
	{
		X = x;
		Y = y;
	}
	
	public void SetCluster(Cluster cluster)
	{
		_cluster = cluster;
	}

	public Cluster GetCluster() 
	{
		return _cluster;
	}
}
