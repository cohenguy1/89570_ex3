import java.util.*;

public class ClusterArranger 
{
	DistanceCalculationMethod DistanceCalculationMethod;

	public ClusterArranger(DistanceCalculationMethod distanceCalculationMethod)
	{
		this.DistanceCalculationMethod = distanceCalculationMethod;
	}

	public void ArrangeClusters(PointsPack pointsPack) 
	{
		List<Cluster> clusters = CreateClustersAndAddPoints(pointsPack.Points);

		while (clusters.size() > pointsPack.NumOfClusters)
		{
			List<Cluster> clustersToMerge = FindClustersToMerge(clusters);

			MergeClusters(clustersToMerge, clusters);
		}
	}

	private void MergeClusters(List<Cluster> clustersToMerge, List<Cluster> clusters) 
	{
		Cluster clusterToMerge = clustersToMerge.get(0);
		Cluster clusterToRemove = clustersToMerge.get(1);

		for (Point point : clusterToRemove.Points)
		{
			clusterToMerge.AddPoint(point);
			
			point.SetCluster(clusterToMerge.ClusterIndex);
		}

		// remove empty cluster
		clusters.remove(clusterToRemove);
	}

	private List<Cluster> FindClustersToMerge(List<Cluster> clusters) 
	{
		int indexIToMerge = 0;
		int indexJToMerge = 1;

		double minDistance = GetDistanceBetweenClusters(clusters, 0, 1);

		for (int clusterI = 0; clusterI < clusters.size() - 1; clusterI++)
		{
			for (int clusterJ = clusterI + 1; clusterJ < clusters.size(); clusterJ++)
			{
				double distance = GetDistanceBetweenClusters(clusters, clusterI, clusterJ);

				if (distance < minDistance)
				{
					minDistance = distance;
					indexIToMerge = clusterI;
					indexJToMerge = clusterJ;
				}
			}
		}

		List<Cluster> clustersToMerge = new ArrayList<Cluster>();
		clustersToMerge.add(clusters.get(indexIToMerge));
		clustersToMerge.add(clusters.get(indexJToMerge));

		return clustersToMerge;
	}

	private double GetDistanceBetweenClusters(List<Cluster> clusters, int i, int j) 
	{
		Cluster clusterI = clusters.get(i);
		Cluster clusterJ = clusters.get(j);

		return GetDistanceBetweenClusters(clusterI, clusterJ);
	}

	private double GetDistanceBetweenClusters(Cluster clusterI, Cluster clusterJ) 
	{
		if (DistanceCalculationMethod == DistanceCalculationMethod.SingleLink)
		{
			return CalcSingleLinkDistance(clusterI, clusterJ);
		}
		else 
		{
			return 0;
			//return CalcAverageLinkDistance(clusterI, clusterJ);
		}
	}

	private double CalcSingleLinkDistance(Cluster clusterI, Cluster clusterJ) 
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

	private double CalculatePointsDistance(Point point1, Point point2)
	{
		return Math.sqrt((point1.X - point2.X)*(point1.X - point2.X) + (point1.Y - point2.Y)*(point1.Y - point2.Y)); 
	}
	
	private List<Cluster> CreateClustersAndAddPoints(List<Point> points)
	{
		List<Cluster> clusters = new ArrayList<Cluster>();
		
		for (Point point : points)
		{
			Cluster newCluster = new Cluster();

			newCluster.AddPoint(point);
			
			point.SetCluster(newCluster.ClusterIndex);
			
			clusters.add(newCluster);
		}

		return clusters;
	}
}
