import java.io.IOException;

public class java_ex3
{
	
	public static void main(String[] args) 
	{
		try
		{
			String inputFilePath = "input.txt";
			
			FileHandler fileHandler = new FileHandler();
			PointsPack pointsPack = fileHandler.ParseFile(inputFilePath);
			
			ClusterArranger clusterArranger = new ClusterArranger();
			clusterArranger.ArrangeClusters(pointsPack);
			
			String outputFilePath = "output.txt";
			fileHandler.WriteResult(outputFilePath, pointsPack);
		}
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
