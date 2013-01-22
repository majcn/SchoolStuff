public class Gouraud
{
	public static double[][] colorInterpolation1(double[][] points, double dt, float[][] colors)
	{
		int maxT = (int)((1.0f/dt)+1);
		double[][] result = new double[points.length][points[0].length + colors[0].length];
		for(int i=0; i<points.length; i++)
			for(int j=0; j<points[0].length; j++)
				result[i][j] = points[i][j];
				
		double tempT;
		int stevec = 0;
		for(int i=0; i<colors[0].length; i++)
		{
			stevec = 0;
			for(int t=0; t<maxT; t++)
			{
				tempT = (double)t*dt;
				if(tempT > 1)
					tempT = 1;
				result[stevec++][colors[0].length+i] = linearInterpolation(tempT, colors[0][i], colors[1][i]);
			}
		}
		return result;
	}
	
	public static double[][] colorInterpolation2(double[][] points, double du, double dv, float[][] colors)
	{
		int maxU = (int)((1.0f/du)+1);
		int maxV = (int)((1.0f/dv)+1);
		double[][] result = new double[points.length][points[0].length + colors[0].length];
		for(int i=0; i<points.length; i++)
			for(int j=0; j<points[0].length; j++)
				result[i][j] = points[i][j];
				
		double tempU, tempV;
		int stevec = 0;
		for(int i=0; i<colors[0].length; i++)
		{
			stevec = 0;
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
					result[stevec++][colors[0].length+i] = bilinearInterpolation(tempU, tempV, colors[0][i], colors[1][i], colors[2][i], colors[3][i]);
				}
			}
		}
		return result;
	}
	
	private static double linearInterpolation(double t, float p0, float p1)
	{
		return p0 + t*(p1-p0);
	}
	private static double bilinearInterpolation(double u, double v, float p00, float p01, float p10, float p11)
	{
		return (1-u)*(1-v)*p00 + u*(1-v)*p10 + (1-u)*v*p01 + u*v*p11;
	}
}
