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
		/*
		for (int row = 0; row < map.Size; row++)
		{
			for (int column = 0; column < map.Size; column++)
			{
				// No policy exists in W and G locations
				if (map.Map[row][column] != 'W' && map.Map[row][column] != 'G')
				{
					if (!firstLine)
					{
						writer.newLine();
					}
					
					WritePolicyForLocation(writer, row, column, policy);
					firstLine = false;
				}
			}
		}
		*/
		writer.close();
	}
	
	/*
	private void WritePolicyForLocation(BufferedWriter writer, int row, int column, Policy policy) throws IOException 
	{
		writer.write(row + "," + column + "," + GetStepString(policy.Action[row][column]));
	}

	private String GetStepString(StepDirection step)
	{
		switch (step)
		{
		case R:
			return "R";
		case RD:
			return "RD";
		case D:
			return "D";
		case LD:
			return "LD";
		case L:
			return "L";
		case LU:
			return "LU";
		case U:
			return "U";
		case RU:
			return "RU";
		case NotAvailable:
			return "";
		}
		
		return "";
	}*/
}
