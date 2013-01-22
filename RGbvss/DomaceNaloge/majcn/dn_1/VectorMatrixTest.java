public class VectorMatrixTest
{

	public static void main(String[] args)
	{
		Vector3D a = new Vector3D(1, 5, 7);
		Vector3D b = new Vector3D(2, 4, 1);
		
		double[] cTemp = new double[3];
		cTemp = Vector3D.add(Vector3D.scalarProduct(Vector3D.negate(a.vector()), 1.0/2.0), Vector3D.scalarProduct(b.vector(), 2.0/3.0));
		Vector3D c = new Vector3D(cTemp[0], cTemp[1], cTemp[2]);
		
		double[] dTemp = new double[3];
		dTemp = Vector3D.scalarProduct(c.vector(), Vector3D.dotProduct(a.vector(), b.vector()));
		Vector3D d = new Vector3D(dTemp[0], dTemp[1], dTemp[2]);
		
		double[] eTemp = new double[3];
		eTemp = Vector3D.scalarProduct(Vector3D.crossProduct(a.vector(), b.vector()), 1.0/2.0);
		Vector3D e = new Vector3D(eTemp[0], eTemp[1], eTemp[2]);
		
		System.out.printf("Vector a: [%f, %f, %f]'\n", a.vector()[0], a.vector()[1], a.vector()[2]);
		System.out.printf("Vector b: [%f, %f, %f]'\n", b.vector()[0], b.vector()[1], b.vector()[2]);
		System.out.printf("Vector c: [%f, %f, %f]'\n", c.vector()[0], c.vector()[1], c.vector()[2]);
		System.out.printf("Vector d: [%f, %f, %f]'\n", d.vector()[0], d.vector()[1], d.vector()[2]);
		System.out.printf("Vector e: [%f, %f, %f]'\n", e.vector()[0], e.vector()[1], e.vector()[2]);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		double[][] matrixTemp = new double[4][2];
		matrixTemp[0][0] = 1;
		matrixTemp[0][1] = 0;
		matrixTemp[1][0] = 0;
		matrixTemp[1][1] = 1;
		matrixTemp[2][0] = 1;
		matrixTemp[2][1] = 0;
		matrixTemp[3][0] = 0;
		matrixTemp[3][1] = 1;	
		System.out.println("A matrika: ");
		Matrix A = null;
		try {
			A = new Matrix(matrixTemp);
			for(int i=0; i<A.matrica().length; i++)
			{
				for(int j=0; j<A.matrica()[i].length; j++)
					System.out.printf("%f ", A.matrica()[i][j]);
				System.out.println();
			}
		} catch(Exception e1){}
		System.out.println();

		
		
		matrixTemp = new double[2][3];
		matrixTemp[0][0] = 3;
		matrixTemp[0][1] = 1;
		matrixTemp[0][2] = 2;
		matrixTemp[1][0] = 7;
		matrixTemp[1][1] = 5;
		matrixTemp[1][2] = 1;	
		System.out.println("B matrika: ");
		Matrix B = null;
		try {
			B = new Matrix(matrixTemp);
			for(int i=0; i<B.matrica().length; i++)
			{
				for(int j=0; j<B.matrica()[i].length; j++)
					System.out.printf("%f ", B.matrica()[i][j]);
				System.out.println();
			}
		} catch(Exception e1){}
		System.out.println();
		
		System.out.println("C matrika: ");
		Matrix C = null;
		try {
			C = new Matrix(Matrix.multiply(A.matrica(), 2.0/3.0));
			for(int i=0; i<C.matrica().length; i++)
			{
				for(int j=0; j<C.matrica()[i].length; j++)
					System.out.printf("%f ", C.matrica()[i][j]);
				System.out.println();
			}
		} catch(Exception e1){}
		System.out.println();

		System.out.println("D matrika: ");
		Matrix D = null;
		try {
			D = new Matrix(Matrix.multiply(B.matrica(), 5.0/8.0));
			for(int i=0; i<D.matrica().length; i++)
			{
				for(int j=0; j<D.matrica()[i].length; j++)
					System.out.printf("%f ", D.matrica()[i][j]);
				System.out.println();
			}
		} catch(Exception e1){}
		System.out.println();
		
		System.out.println("E matrika: ");
	 	Matrix E = null;
		try {
			E = new Matrix(Matrix.add(Matrix.multiply(A.matrica(), 2.0), Matrix.multiply(Matrix.transpose(B.matrica()), 3.0)));
			for(int i=0; i<E.matrica().length; i++)
			{
				for(int j=0; j<E.matrica()[i].length; j++)
					System.out.printf("%f ", E.matrica()[i][j]);
				System.out.println();
			}
		} catch(Exception e1){}		
		System.out.println();
		
		System.out.println("F matrika: ");
		Matrix F = null;
		try {
			F = new Matrix(Matrix.multiply(Matrix.multiply(B.matrica(), Matrix.transpose(B.matrica())), 1.0/3.0));
			for(int i=0; i<F.matrica().length; i++)
			{
				for(int j=0; j<F.matrica()[i].length; j++)
					System.out.printf("%f ", F.matrica()[i][j]);
				System.out.println();
			}
		} catch(Exception e1){}
		System.out.println();		
	}
}
