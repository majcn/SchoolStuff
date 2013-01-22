import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class main
{
	public static void main(String[] args) throws IOException
	{
		double[][] tockeC = Bezier.bezierCurve3(new double[][]{{1.0, 1.0, 1.0},{2.0, 1.0, 2.0},{1.0, 2.0, 2.0}, {3.0, 2.0, 2.0}}, 0.1);
		double[][] tockeP = Bezier.bezierPatch3(new double[][]{{1.0, 1.0, 1.0},{2.0, 1.0, 2.0},{1.0, 2.0, 2.0}, {3.0, 2.0, 2.0}, {2.0, 1.0, 1.0},{3.0, 1.0, 2.0},{2.0, 2.0, 2.0}, {4.0, 2.0, 2.0}, {3.0, 1.0, 1.0},{4.0, 1.0, 2.0},{3.0, 2.0, 2.0}, {5.0, 2.0, 2.0}, {4.0, 1.0, 1.0},{5.0, 1.0, 2.0},{4.0, 2.0, 2.0}, {6.0, 2.0, 2.0}}, 0.1, 0.1);
		double[][] barvneTockeC = Gouraud.colorInterpolation1(tockeC, 0.1, new float[][] {{1.0f, 0.0f, 0.0f},{0.0f, 1.0f, 0.0f}});
		double[][] barvneTockeP = Gouraud.colorInterpolation2(tockeP, 0.1, 0.1, new float[][] {{1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {1.0f, 1.0f, 0.0f}});
		FileWriter fstream;
        BufferedWriter out; 
        
        fstream = new FileWriter("testiranjeC.m");
        out = new BufferedWriter(fstream);
        out.write("x = [");
        for(int i=0; i<tockeC.length; i++)
        	out.write(String.valueOf(tockeC[i][0])+" ");
        out.write("];\n");
        out.write("y = [");
        for(int i=0; i<tockeC.length; i++)
        	out.write(String.valueOf(tockeC[i][1])+" ");
        out.write("];\n");
        out.write("z = [");
        for(int i=0; i<tockeC.length; i++)
        	out.write(String.valueOf(tockeC[i][2])+" ");
        out.write("];\n");
        out.write("plot3(x,y,z, '*', 'MarkerSize', 1);");
        out.close();
        
        fstream = new FileWriter("testiranjeP.m");
        out = new BufferedWriter(fstream);
        out.write("x = [");
        for(int i=0; i<tockeP.length; i++)
        	out.write(String.valueOf(tockeP[i][0])+" ");
        out.write("];\n");
        out.write("y = [");
        for(int i=0; i<tockeP.length; i++)
        	out.write(String.valueOf(tockeP[i][1])+" ");
        out.write("];\n");
        out.write("z = [");
        for(int i=0; i<tockeP.length; i++)
        	out.write(String.valueOf(tockeP[i][2])+" ");
        out.write("];\n");
        out.write("plot3(x,y,z, '*', 'MarkerSize', 1);");
        out.close();
        
        fstream = new FileWriter("testiranjeBC.m");
        out = new BufferedWriter(fstream);
        out.write("hold on;\n");
		out.write("x = [");
        for(int i=0; i<barvneTockeC.length; i++)
        	out.write(String.valueOf(barvneTockeC[i][0])+" ");
        out.write("];\n");
        out.write("y = [");
        for(int i=0; i<barvneTockeC.length; i++)
        	out.write(String.valueOf(barvneTockeC[i][1])+" ");
        out.write("];\n");
        out.write("z = [");
        for(int i=0; i<barvneTockeC.length; i++)
        	out.write(String.valueOf(barvneTockeC[i][2])+" ");
        out.write("];\n");
        out.write("R = [");
        for(int i=0; i<barvneTockeC.length; i++)
        	out.write(String.valueOf(barvneTockeC[i][3])+" ");
        out.write("];\n");
        out.write("G = [");
        for(int i=0; i<barvneTockeC.length; i++)
        	out.write(String.valueOf(barvneTockeC[i][4])+" ");
        out.write("];\n");
        out.write("B = [");
        for(int i=0; i<barvneTockeC.length; i++)
        	out.write(String.valueOf(barvneTockeC[i][5])+" ");
        out.write("];\n");
        out.write("for i=1:" + barvneTockeC.length);
        out.write("\n  plot3(x(i),y(i),z(i), '*', 'MarkerSize', 1, 'color', [R(i),G(i),B(i)]);\n");
        out.write("end");
        out.close();       
        
        fstream = new FileWriter("testiranjeBP.m");
        out = new BufferedWriter(fstream);
        out.write("hold on;\n");
        out.write("x = [");
        for(int i=0; i<barvneTockeP.length; i++)
        	out.write(String.valueOf(barvneTockeP[i][0])+" ");
        out.write("];\n");
        out.write("y = [");
        for(int i=0; i<barvneTockeP.length; i++)
        	out.write(String.valueOf(barvneTockeP[i][1])+" ");
        out.write("];\n");
        out.write("z = [");
        for(int i=0; i<barvneTockeP.length; i++)
        	out.write(String.valueOf(barvneTockeP[i][2])+" ");
        out.write("];\n");
        out.write("R = [");
        for(int i=0; i<barvneTockeP.length; i++)
        	out.write(String.valueOf(barvneTockeP[i][3])+" ");
        out.write("];\n");
        out.write("G = [");
        for(int i=0; i<barvneTockeP.length; i++)
        	out.write(String.valueOf(barvneTockeP[i][4])+" ");
        out.write("];\n");
        out.write("B = [");
        for(int i=0; i<barvneTockeP.length; i++)
        	out.write(String.valueOf(barvneTockeP[i][5])+" ");
        out.write("];\n");
        out.write("for i=1:" + barvneTockeP.length);
        out.write("\n  plot3(x(i),y(i),z(i), '*', 'MarkerSize', 1, 'color', [R(i),G(i),B(i)]);\n");
        out.write("end");
        out.close();  
	}
}