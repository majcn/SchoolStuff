public class Matrix
{
	private double[][] m_matrika;
	
	public double[][] matrica() {
		return m_matrika;
	}
	
	public Matrix(double[][] matrika)
	{
		m_matrika = matrika;
	}
	
	public static double[][] negate(double[][] matrika)
	{
		double[][] result = copyMatrix(matrika);
		for(int i=0; i<matrika.length; i++)
			for(int j=0; j<matrika[i].length; j++)
			{
				result[i][j] = -matrika[i][j];
			}
		return result;
	}
	
	public static double[][] add(double[][] matrika1, double[][] matrika2)
	{
		if(matrika1.length!=matrika2.length)
		{
			System.err.printf("Matriki nista enake oblike\n");
			return null;
		}
		else if(matrika1[0].length!=matrika2[0].length)
		{
			System.err.printf("Matriki nista enake oblike\n");
			return null;
		}
		else
		{
			double[][] result = copyMatrix(matrika1);
			for(int i=0; i<matrika1.length; i++)
				for(int j=0; j<matrika1[i].length; j++)
				{
					result[i][j] = matrika1[i][j]+matrika2[i][j];
				}
			return result;
		}
	}
	
	public static double[][] transpose(double[][] matrika)
	{
		double[][] result = new double[matrika[0].length][matrika.length];
		for(int i=0; i<matrika.length; i++)
			for(int j=0; j<matrika[0].length; j++)
			{
				result[j][i] = matrika[i][j];
			}
		return result;
	}
	
	public static double[][] multiply(double[][] matrika, double scalar)
	{
		double[][] result = copyMatrix(matrika);
		for(int i=0; i<matrika.length; i++)
			for(int j=0; j<matrika[0].length; j++)
			{
				result[i][j] = matrika[i][j] * scalar;
			}
		return result;
	}
	
	public static double[][] multiply(double[][] matrika1, double[][] matrika2)
	{
		if(matrika1[0].length!=matrika2.length)
		{
			System.err.printf("Matriki nista prave oblike\n");
			return null;
		}
		
		double[][] result = new double[matrika1.length][matrika2[0].length];
	    for(int i = 0; i < matrika1.length; i++)
	    {
	        for(int j = 0; j < matrika2[0].length; j++)
	        {
	          for(int k = 0; k < matrika2.length; k++)
	          {
	        	  result[i][j] += matrika1[i][k]*matrika2[k][j];
	          }
	        }
	    }
		return result;
	}
	
	private static double[][] copyMatrix(double[][] matrika)
	{
		double[][] result = new double[matrika.length][matrika[0].length];
		for(int i=0; i<matrika.length; i++)
			for(int j=0; j<matrika[i].length; j++)
				result[i][j] = matrika[i][j];
		return result;
	}
}