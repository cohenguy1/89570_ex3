import java.util.ArrayList;
import java.util.List;

public class PointsPack 
{
	public List<Point> Points;

	DistanceCalculationMethod DistanceMethod;
	
	public int NumOfClusters;
	
	public PointsPack(DistanceCalculationMethod distanceMethod, int numOfClusters)
	{
		NumOfClusters = numOfClusters;
		DistanceMethod = distanceMethod;
		
		Points = new ArrayList<Point>();
	}
}
