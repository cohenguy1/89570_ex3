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
				
		// read line of map size
		DistanceCalculationMethod distanceCalculationMethod = GetDistanceCalculationMethod(reader);
		int numOfClusters = GetNumOfClusters(reader);
		
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
			// read each character in the line
			String[] numbers = line.split(",");
			
			int x = Integer.parseInt(numbers[0]);
			int y = Integer.parseInt(numbers[1]);
			
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
			
			writer.write(String.valueOf(point.GetClusterIndex()));
			firstLine = false;
		}
		
		writer.close();
	}
}
