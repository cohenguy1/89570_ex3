import java.io.*;
import java.util.*;

/*
 * Class for handling I/O of files.
 */
public class FileHandler 
{
	private final String SingleLink = "single link";
	private final String AverageLink = "average link";
	
	public PointsPack ParseFile(String inputFileName) throws IOException
	{
		FileReader fileReader;
		
		fileReader = new FileReader(inputFileName);
		
		BufferedReader reader = new BufferedReader(fileReader);
				
		// read line of distance calculation method
		DistanceCalculationMethod distanceCalculationMethod = GetDistanceCalculationMethod(reader);
		
		// get num of clusters
		int numOfClusters = GetNumOfClusters(reader);
		
		// create the appropriate data structure to hold the input data
		PointsPack pointsPack = new PointsPack(distanceCalculationMethod, numOfClusters);
		
		ReadPoints(reader, pointsPack);
		
		reader.close();
		
		return pointsPack;
	}
	
	/*
	 * Returns the Distance Calculation Method as read from the input file
	 */
	private DistanceCalculationMethod GetDistanceCalculationMethod(BufferedReader reader) throws IOException
	{
		String method = reader.readLine().toLowerCase();
		
		if (method.equals(SingleLink))
		{
			return DistanceCalculationMethod.SingleLink;
		}
		else if (method.equals(AverageLink))
		{
			return DistanceCalculationMethod.AverageLink;
		}
		
		// default return
		return DistanceCalculationMethod.SingleLink;
	}
	
	/*
	 * Returns the number of clusters as read from the input file
	 */
	private int GetNumOfClusters(BufferedReader reader) throws IOException
	{
		String numOfClusters = reader.readLine();
		
		return Integer.parseInt(numOfClusters);
	}
	
	/*
	 * Reads the points from the input file, one by one
	 * insert them into a list
	 */
	private void ReadPoints(BufferedReader reader, PointsPack pointsPack) throws IOException
	{		
		String line = reader.readLine();
		
		// read until EOF
		while (line != null)
		{
			// split X and Y by the ','
			String[] numbers = line.split(",");
			
			double x = Double.parseDouble(numbers[0]);
			double y = Double.parseDouble(numbers[1]);
			
			Point newPoint = new Point(x, y);
			
			pointsPack.Points.add(newPoint);
			
			// read the next line
			line = reader.readLine();
		}
	}

	/*
	 * Writes the policy of the map to the output file
	 */
	public void WriteResult(String outputFileName, PointsPack pointsPack) throws IOException
	{
		FileWriter fileWriter;
		
		fileWriter = new FileWriter(outputFileName);
		
		BufferedWriter writer = new BufferedWriter(fileWriter);
		
		boolean firstLine = true;
		
		for (Point point : pointsPack.Points)
		{
			if (!firstLine)
			{
				writer.newLine();
			}
			
			// write cluster index for the point
			writer.write(String.valueOf(point.GetCluster().ClusterIndex));
			
			firstLine = false;
		}
		
		writer.close();
	}
}
