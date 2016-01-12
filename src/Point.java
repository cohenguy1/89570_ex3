
public class Point 
{
	public double X;
	public double Y;

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
