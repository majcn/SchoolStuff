public class AABB
{
	public static double[][] getAABB(double[][] points)
	{
		double[][] result = new double[2][3];
		for(int i=0; i<result.length; i++)
			for(int j=0; j<result[i].length; j++)
				result[i][j] = points[0][j];

		for(int i=1; i<points.length; i++)
		{
			for(int j=0; j<3; j++)
				if(points[i][j]<result[0][j])
					result[0][j] = points[i][j];
			for(int j=0; j<3; j++)
				if(points[i][j]>result[1][j])
					result[1][j] = points[i][j];
		}
			
		return result;
	}
}
