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
		// Create the clusters (Tree top-down)
		List<Cluster> clusters = CreateClustersAndAddPoints(pointsPack.Points);

		// Repeat until requested number of clusters reached
		while (clusters.size() > pointsPack.NumOfClusters)
		{
			// find two clusters to merge
			List<Cluster> clustersToMerge = FindClustersToMerge(clusters);

			MergeClusters(clustersToMerge, clusters);
		}
		
		// Set the clusters indices
		SetClustersIndices(pointsPack, clusters);
	}

	private void SetClustersIndices(PointsPack pointsPack, List<Cluster> clusters) 
	{
		// counter for indices
		int clusterIndex = 1;
		
		for (Point point : pointsPack.Points)
		{
			Cluster pointCluster = point.GetCluster(); 
			
			// if the cluster doesn't have an index, set it to the next index
			if (pointCluster.ClusterIndex == -1)
			{
				pointCluster.SetClusterIndex(clusterIndex);
				
				clusterIndex++;
			}
		}
	}

	/*
	 * Merge two clusters
	 */
	private void MergeClusters(List<Cluster> clustersToMerge, List<Cluster> clusters) 
	{
		// merge the second cluster to the first
		Cluster clusterToMerge = clustersToMerge.get(0);
		Cluster clusterToRemove = clustersToMerge.get(1);

		for (Point point : clusterToRemove.Points)
		{
			// add the point from the second cluster to the first
			clusterToMerge.AddPoint(point);
		}

		// remove empty cluster
		clusters.remove(clusterToRemove);
	}

	/*
	 * find two clusters to remove
	 */
	private List<Cluster> FindClustersToMerge(List<Cluster> clusters) 
	{
		int indexIToMerge = 0;
		int indexJToMerge = 1;

		double minDistance = GetDistanceBetweenClusters(clusters, 0, 1);

		// iterate over all cluster pairs and find the two clusters with minimal distance
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

		// return the two clusters to merge
		List<Cluster> clustersToMerge = new ArrayList<Cluster>();
		clustersToMerge.add(clusters.get(indexIToMerge));
		clustersToMerge.add(clusters.get(indexJToMerge));

		return clustersToMerge;
	}

	/*
	 * Get the distance between to clusters
	 */
	private double GetDistanceBetweenClusters(List<Cluster> clusters, int i, int j) 
	{
		Cluster clusterI = clusters.get(i);
		Cluster clusterJ = clusters.get(j);

		return Cluster.GetDistanceBetweenClusters(clusterI, clusterJ, DistanceCalculationMethod);
	}

	/*
	 * Create the clusters by the list of points
	 */
	private List<Cluster> CreateClustersAndAddPoints(List<Point> points)
	{
		List<Cluster> clusters = new ArrayList<Cluster>();
		
		for (Point point : points)
		{
			// Create a cluster for each point
			Cluster newCluster = new Cluster();

			newCluster.AddPoint(point);
			
			clusters.add(newCluster);
		}

		return clusters;
	}
}
