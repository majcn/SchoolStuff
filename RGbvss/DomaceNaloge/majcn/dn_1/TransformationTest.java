public class TransformationTest
{

	public static void main(String[] args)
	{
		Transformation transformacije = new Transformation();
		double[] a = {1, 5, 7};
		double[] b = {2, 4 ,1};
		
		
		System.out.printf("a: [%f %f %f]'\n", a[0], a[1], a[2]);
		transformacije.scale(1, 1, 0.5);
		a = transformacije.transformPoint(a);
		System.out.printf("a: scaleZ by 0.5: [%f %f %f]'\n", a[0], a[1], a[2]);
		transformacije.translate(2, 0, 0);
		a = transformacije.transformPoint(a);
		System.out.printf("a: translateX by 2: [%f %f %f]'\n", a[0], a[1], a[2]);
		
		
		System.out.printf("b: [%f %f %f]'\n", b[0], b[1], b[2]);
		transformacije.scale(1, 1, 0.5);
		b = transformacije.transformPoint(b);
		System.out.printf("b: scaleZ by 0.5: [%f %f %f]'\n", b[0], b[1], b[2]);
		transformacije.rotateZ(Math.PI/3.0);
		b = transformacije.transformPoint(b);
		System.out.printf("b: rotateZ by PI/3: [%f %f %f]'\n", b[0], b[1], b[2]);
		transformacije.translate(0, 0, 2);
		b = transformacije.transformPoint(b);
		System.out.printf("b: translateZ by 2: [%f %f %f]'\n", b[0], b[1], b[2]);
		transformacije.rotateX(Math.toRadians(45));
		b = transformacije.transformPoint(b);
		System.out.printf("b: rotateX by 45°: [%f %f %f]'\n", b[0], b[1], b[2]);
	}
}
