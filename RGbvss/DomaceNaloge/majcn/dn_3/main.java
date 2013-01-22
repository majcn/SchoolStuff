
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[][] points = new double[][] {
				  {38.239610, 29.606060, 35.821997},
				  {-21.130914, -91.696339, 84.873352},
				  {66.823461, -1.319721, 50.163318},
				  {-43.653937, -54.724264, -0.081992},
				  {42.112934, -43.912555, -62.983937},
				  {71.221560, 79.633583, -4.749756},
				  {-87.746093, -6.527592, 8.223418},
				  {64.495473, -50.934735, -60.416286},
				  {-50.068297, -65.357794, 52.651769},
				  {87.504885, 48.041027, -77.645615}
				};

				double[][][] aabbs = new double[5][2][3];
				for (int i=0; i<aabbs.length; i++) 
				  aabbs[i] = AABB.getAABB(new double[][] {points[2*i], points[2*i+1]});

				int[][] indeksi = CollisionDetection.getCollisions(aabbs);
				for (int i=0; i<indeksi.length; i++)
				  System.out.println("trk: " + indeksi[i][0] + " in " + indeksi[i][1]);
}
}