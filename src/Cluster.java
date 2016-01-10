import java.util.*;

public class Cluster 
{
	public static int ClustersCount = 0;
	public int ClusterIndex;
	
	public List<Point> Points;

	public Cluster()
	{
		Points = new ArrayList<Point>();
		
		ClustersCount++;
		
		ClusterIndex = ClustersCount;
	}

	public void AddPoint(Point point) 
	{
		Points.add(point);
		
	}

	public void RemovePoint(Point point) 
	{
		Points.remove(point);
	}
}
