public class Transformation {
	
	private double[][] m_tMatrika;
	public Transformation() {
		m_tMatrika = new double[4][4];
		setDefault();
	}
	
	private void setDefault()
	{
		for(int i=0; i<m_tMatrika.length; i++)
			for(int j=0; j<m_tMatrika[i].length; j++)
				if(i==j)
					m_tMatrika[i][j] = 1;
				else
					m_tMatrika[i][j] = 0;
	}
	
	public void translate(double tx, double ty, double tz)
	{
		setDefault();
		m_tMatrika[0][3] = tx;
		m_tMatrika[1][3] = ty;
		m_tMatrika[2][3] = tz;
	}
	
	public void scale(double sx, double sy, double sz)
	{
		setDefault();
		m_tMatrika[0][0] = sx;
		m_tMatrika[1][1] = sy;
		m_tMatrika[2][2] = sz;
	}
	
	public void rotateX(double fi)
	{
		setDefault();
		m_tMatrika[1][1] = Math.cos(fi);
		m_tMatrika[1][2] = -Math.sin(fi);
		m_tMatrika[2][1] = Math.sin(fi);
		m_tMatrika[2][2] = Math.cos(fi);
	}
	
	public void rotateY(double fi)
	{
		setDefault();
		m_tMatrika[0][0] = Math.cos(fi);
		m_tMatrika[0][2] = Math.sin(fi);
		m_tMatrika[2][0] = -Math.sin(fi);
		m_tMatrika[2][2] = Math.cos(fi);
	}
	
	public void rotateZ(double fi)
	{
		setDefault();
		m_tMatrika[0][0] = Math.cos(fi);
		m_tMatrika[0][1] = -Math.sin(fi);
		m_tMatrika[1][0] = Math.sin(fi);
		m_tMatrika[1][1] = Math.cos(fi);
	}
	
	public double[] transformPoint(double[] tocka)
	{		
		double[] resultH = new double[tocka.length+1];
		double[] tockaH = new double[tocka.length+1];
		for(int i=0; i<tocka.length; i++)
			tockaH[i] = tocka[i];
		tockaH[tocka.length] = 1;
		
	    for(int i = 0; i < m_tMatrika.length; i++)
	    {
	    	for(int k = 0; k < tockaH.length; k++)
	        {
	    		resultH[i] += m_tMatrika[i][k]*tockaH[k];
	        }
	    }
	    double[] result = new double[tocka.length];
	    for(int i=0; i<result.length; i++)
	    	result[i] = resultH[i];
		return result;
	}
}
