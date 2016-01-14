import java.util.*;

public class Cluster 
{
	public int ClusterIndex;
	
	public List<Point> Points;

	public Cluster()
	{
		Points = new ArrayList<Point>();
		
		ClusterIndex = -1;
	}

	/*
	 * Add point to cluster
	 */
	public void AddPoint(Point point) 
	{
		Points.add(point);
		
		point.SetCluster(this);
	}

	public void SetClusterIndex(int clusterIndex) 
	{
		ClusterIndex = clusterIndex;
	}
	
	public static double GetDistanceBetweenClusters(Cluster clusterI, Cluster clusterJ, DistanceCalculationMethod distanceCalculationMethod) 
	{
		if (distanceCalculationMethod == DistanceCalculationMethod.SingleLink)
		{
			return CalcSingleLinkDistance(clusterI, clusterJ);
		}
		else 
		{
			return CalcAverageLinkDistance(clusterI, clusterJ);
		}
	}

	/*
	 * Calculate the Average Link Distance: the average distance between 
	 * each pair of elements from the two clusters
	 */
	private static double CalcAverageLinkDistance(Cluster clusterI, Cluster clusterJ) 
	{
		int pairsCount = 0;
		double averageDistance = 0;
		
		for (Point pointI : clusterI.Points)
		{
			for (Point pointJ : clusterJ.Points)
			{
				averageDistance += CalculatePointsDistance(pointI, pointJ);
				pairsCount++;
			}
		}
		
		if (pairsCount == 0)
		{
			return 1;
		}
		
		return averageDistance/pairsCount;
	}

	/*
	 * Calculates the Single Link Distance: the shortest distance between 
	 * a pair of elements from the two clusters
	 */
	private static double CalcSingleLinkDistance(Cluster clusterI, Cluster clusterJ) 
	{
		double minDistance = CalculatePointsDistance(clusterI.Points.get(0), clusterJ.Points.get(0));
		double distance;

		for (Point pointI : clusterI.Points)
		{
			for (Point pointJ : clusterJ.Points)
			{
				distance = CalculatePointsDistance(pointI, pointJ);

				if (distance < minDistance)
				{
					minDistance = distance;
				}
			}
		}

		return minDistance;
	}
	
	/*
	 * Geometric distance between two points
	 */
	private static double CalculatePointsDistance(Point point1, Point point2)
	{
		return Math.sqrt((point1.X - point2.X)*(point1.X - point2.X) + (point1.Y - point2.Y)*(point1.Y - point2.Y)); 
	}
	

}
