public class Driver 
{ 
	public static void main(String [] args) 
	{ 
	 	double[] a = {5.0, 3.0};
	 	int[] b = {0, 2};
		Polynomial p1 = new Polynomial(a, b);
		
		double[] c = {16.0, 4.0};
	 	int[] d = {5, 2};
		Polynomial p2 = new Polynomial(c, d);
		
		Polynomial p3 = p1.multiply(p2); 
		for(int i = 0; i < p3.coefficients.length; i++)
		{
			System.out.println(p3.coefficients[i]);
			System.out.println(p3.exponents[i]);
		}
		
		Polynomial p4 = new Polynomial(TesterDoc.txt);
		
		for(int i = 0; i < p4.coefficients.length; i++)
		{
			System.out.println(p4.coefficients[i]);
			System.out.println(p4.exponents[i]);
		}
		
	} 
} 