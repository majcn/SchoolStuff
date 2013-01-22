public class Vector3D
{
	private double[] m_vektor;
	
	public double[] vector() {
		return m_vektor;
	}
	
	public Vector3D(double x, double y, double z)
	{
		m_vektor = new double[3];
		m_vektor[0] = x;
		m_vektor[1] = y;
		m_vektor[2] = z;
	}
	
	public static double[] negate(double[] vektor)
	{
		double[] result = new double[vektor.length];
		for(int i=0; i<vektor.length; i++) 
			result[i] = -vektor[i];
		return result;
	}
	
	public static double[] add(double[] vektor1, double[] vektor2)
	{
		if(vektor1.length!=vektor2.length)
		{
			//error
			return null;
		}
		else
		{
		double[] result = new double[vektor1.length];			
		for(int i=0; i<vektor1.length; i++)
			result[i] = vektor1[i] + vektor2[i];
		return result;
		}
	}
	
	public static double[] scalarProduct(double[] vektor, double scalar)
	{
		double[] result = new double[vektor.length];				
		for(int i=0; i<vektor.length; i++) 
			result[i] = scalar * vektor[i];
		return result;
	}	
	
	public static double dotProduct(double[] vektor1, double[] vektor2)
	{
		if(vektor1.length!=vektor2.length)
		{
			//error
			return -1;
		}
		else
		{
			double result = 0.0;
			for(int i=0; i<vektor1.length; i++) 
				result += vektor1[i]*vektor2[i];
			return result;
		}
	}	
	
	public static double[] crossProduct(double[] vektor1, double[] vektor2)
	{
		if(vektor1.length!=vektor2.length)
		{
			//error
			return null;
		}
		else
		{
			double[] result = new double[vektor1.length];			
			result[0] = vektor1[1]*vektor2[2] - vektor1[2]*vektor2[1];
			result[1] = vektor1[2]*vektor2[0] - vektor1[0]*vektor2[2];
			result[2] = vektor1[0]*vektor2[1] - vektor1[1]*vektor2[0];
			return result;
		}		
	}
	
	public static int length(double[] vektor)
	{
		return vektor.length;
	}
	
	public static double norm(double[] vektor)
	{
		double result = 0;
		for(int i=0; i<vektor.length; i++)
		{
			result += Math.pow(vektor[i], 2);
		}
		return Math.sqrt(result);
	}
	
	public static double[] normalize(double[] vektor)
	{
		double[] result = new double[vektor.length];
		double vectorNorm = norm(vektor);
		for(int i=0; i<vektor.length; i++)
		{
			result[i] = result[i]/vectorNorm;
		}
		return result;
	}
	
	public static double cosPhi(double[] vektor1, double[] vektor2)
	{
		if(vektor1.length!=vektor2.length)
		{
			//error
			return -1;
		}
		else
		{
			return (dotProduct(vektor1, vektor2)/(norm(vektor1)*norm(vektor2)));
		}
	}
	
	public static double[] project(double[] vektor1, double[] vektor2)
	{
		if(vektor1.length!=vektor2.length)
		{
			//error
			return null;
		}
		else
		{		
			return scalarProduct(vektor1, norm(vektor2)*cosPhi(vektor1, vektor2)); 
		}		
	}
	
	
}