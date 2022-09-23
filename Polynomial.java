public class Polynomial
{
	double[] coefficients;
	
	public Polynomial()
	{
		this.coefficients = new double[1];
	}
	
	public Polynomial(double[] array)
	{
		this.coefficients = new double[array.length];
		for(int i = 0; i < array.length; i++)
		{
			this.coefficients[i] = array[i];
		}
	}
	
	public Polynomial add(Polynomial p)
	{
		int max = Math.max(this.coefficients.length, p.coefficients.length);
		int min = Math.min(this.coefficients.length, p.coefficients.length);
		double[] result = new double[max];
		
		for(int i = 0; i < max; i++)
		{
			if(i < min)											//need to add
				result[i] = coefficients[i] + p.coefficients[i];
			else if(i >= min && max == coefficients.length)			//if p is smaller
				result[i] = coefficients[i];
			else													//if calling is smaller
				result[i] = p.coefficients[i];		
		}
		
		//Polynomial p2 = new Polynomial(result);
		
		return new Polynomial(result);
		
	}
	
	public double evaluate(double d)
	{
		double answer = 0.0;
		
		for(int i = 0; i < coefficients.length; i++)
		{
			answer += coefficients[i]*(Math.pow(d,i));
		}
			
		return answer;
	
	}
	
	public boolean hasRoot(double root)
	{
		if(this.evaluate(root)==0)
			return true;
		return false;
	}
	
}