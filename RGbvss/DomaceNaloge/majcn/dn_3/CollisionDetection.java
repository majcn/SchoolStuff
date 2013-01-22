public class CollisionDetection
{
	public static int[][] getCollisions(double[][][] aabbs)
	{
		int[][] tempResult = new int[aabbs.length*aabbs.length/2][2]; //priblizno max mozno
		int[][] result = null;
		int stevec = 0;
		boolean trk = true;
		
		for(int i=0; i<aabbs.length; i++)
		{
			for(int j=0; j<aabbs.length; j++)
			{
				if(i<j)
				{
					for(int k=0; k<3; k++)
					{
						if((aabbs[i][1][k]<=aabbs[j][0][k]) || (aabbs[i][0][k]>=aabbs[j][1][k]))
							trk = false;
					}
					if(trk)
					{
						tempResult[stevec][0] = i;
						tempResult[stevec][1] = j;
						stevec++;
					}
					trk = true;
				}
			}
		}
		
		result = new int[stevec][2];
		for(int i=0; i<stevec; i++)
			for(int j=0; j<2; j++)
				result[i][j] = tempResult[i][j];
		tempResult = null;
		
		return result;
	}
}
