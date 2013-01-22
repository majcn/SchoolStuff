public class Bezier
{
	public static double[][] bezierCurve3(double[][] points, double dt)
	{
		int maxT = (int)((1.0f/dt)+1);
		double[][] result = new double[maxT][points[0].length];
		double tempBezier;
		double tempT;
		int stevec = 0;
		int n = 3;
		for(int t=0; t<maxT; t++)
		{
			for(int i=0; i<=n; i++)
			{
				tempT = (double)t*dt;
				if(tempT > 1)
					tempT = 1;
				tempBezier = b(tempT, n, i);
				for(int k=0; k<points[0].length; k++)
				{
					result[stevec][k] += points[i][k] * tempBezier;
				}
			}
			stevec++;
		}
		return result;
	}
	
	public static double[][] bezierPatch3(double[][] points, double du, double dv)
	{
		int maxU = (int)((1.0f/du)+1);
		int maxV = (int)((1.0f/dv)+1);
		double[][] result = new double[maxU*maxV][points[0].length];
		double tempBezierU, tempBezierV;
		double tempU, tempV;
		int stevec = 0;
		int n = 3;
		for(int u=0; u<maxU; u++)
		{
			tempU = (double)u*du;
			if(tempU > 1)
				tempU = 1;
			for(int v=0; v<maxV; v++)
			{
				tempV = (double)v*dv;
				if(tempV > 1)
					tempV = 1;
				for(int i=0; i<=n; i++)
				{
					for(int j=0; j<=n; j++)
					{
						tempBezierU = b(tempU, n, i);
						tempBezierV = b(tempV, n, j);
						for(int k=0; k<points[0].length; k++)
						{
							result[stevec][k] += points[i*(n+1)+j][k] * tempBezierU * tempBezierV;
						}
					}
				}
				stevec++;
			}
		}
		return result;
	}
	
	private static double binomialCoefficient(double n, double k)
	{
		double c = 1;
		if(k > n-k)
			k = n-k;
		for(int i=0; i<k; i++)
		{
			c *= n-i;
			c /= i+1;
		}
		return c;
	}

	private static double b(double t, double n, double i)
	{
		return binomialCoefficient(n, i) * Math.pow(1-t, n-i) * Math.pow(t, i);
	}
}