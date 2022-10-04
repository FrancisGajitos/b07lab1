import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.*;

public class Polynomial
{
	double[] coefficients;
	int[] exponents;
	
	public Polynomial()
	{
		coefficients = new double[1];
		exponents = new int[1];
	}
	
	public Polynomial(double[] array, int[] exp )
	{
		coefficients = new double[array.length];
		exponents = new int[exp.length];
		for(int i = 0; i < array.length; i++)
		{
			coefficients[i] = array[i];
			exponents[i] = exp[i];
			
		}
	}
	
	public Polynomial(File filename) throws FileNotFoundException, IOException
	{
		BufferedReader b = new BufferedReader(new FileReader("C:\\Users\\Franc\\b07lab1\\filename.txt"));
		String line = b.readLine();
		String[] terms = line.split(("+|-"));
		int length = terms.length;
		
		int k = 0;
		//fill both arrays
		for (String s: terms)
		{
			//one char (constant)
			if(!(s.contains("x")))
			{
				coefficients[k] = Double.parseDouble(s);
				exponents[k] = 0;
			}
			
			//two char(linear)
			if(s.lastIndexOf("x")==s.length()-1)
			{
				coefficients[k] = Double.parseDouble(s.substring(0, s.length()-1));
				exponents[k] = 1;
			}
			
			//three term(degree > 1)
			else
			{
				coefficients[k] = Double.parseDouble(s.substring(0, s.indexOf("x")));
				exponents[k] = Integer.parseInt(s.substring(s.indexOf("x"), s.length()));
			}
			
			k++;
		}
	 }
	
	
	public double[] coArray(Polynomial p)
	{
		int total_length = coefficients.length + p.coefficients.length;
		double[] result = new double[total_length];
		
		int k = 0;
		for(int i = 0; i < coefficients.length; i++)
		{
			result[k] = coefficients[i];
			k++;
		}
		
		for(int j = 0; j < p.coefficients.length; j++)
		{
			result[k] = p.coefficients[j];
			k++;
		}
		
		return result;
	}
	
	public int[] expArray(Polynomial p)
	{
		int total_length = exponents.length + p.exponents.length;
		int[] result = new int[total_length];
		
		int k = 0;
		for(int x1: exponents)
		{
			result[k] = x1;
			k++;
		}
		
		int a = k;
		for(int x2: p.exponents)
		{
			result[a] = x2;
			a++;
		}
		return result;
	}
	
	public boolean inArray(int[] e, int element)
	{
		for(int x: e)
			if(x == element)
				return true;
		return false;
	}
	
	public int[] newExponent(int[] e)
	{
		int[] counted = new int[e.length];
		for(int i = 0; i < counted.length; i++)
			counted[i] = -1;
		
		int place = 0;
		for(int a: e)
		{
			if(inArray(counted, a) == false)
			{
				counted[place] = a;
				place++;
			}
		}
		
		return counted;
	}
	
	public Polynomial add(Polynomial p)
	{
		double[] total_coefficients = coArray(p);
		int[] total_exponents = expArray(p);
		
		int[] final_exponents = newExponent(total_exponents);
		int final_length = final_exponents.length;
		
		double[] coResult = new double[final_length];
		int[] exResult = new int[final_length];
		
		int k = 0;
		for(int i: final_exponents)
		{
			if(i == -1)
				break;
			
			for(int j = 0; j < total_exponents.length; j++)
			{
				if(total_exponents[j] == i)
					coResult[k] += total_coefficients[j];
			}
			
			exResult[k] = i;
			k++;
			
		}
		
		return new Polynomial(coResult, exResult);
		
	}
	
	public double evaluate(double d)
	{
		double answer = 0.0;
		
		for(int i = 0; i < coefficients.length; i++)
		{
			answer += coefficients[i]*(Math.pow(d,exponents[i]));
		}
			
		return answer;
	
	}
	
	public boolean hasRoot(double root)
	{
		if(this.evaluate(root)==0)
			return true;
		return false;
	}
	
	public Polynomial multiply(Polynomial p)
	{
		
		Polynomial final_poly = new Polynomial();
		
		double[] total_coefficients = new double[p.coefficients.length*coefficients.length];
		int[] total_exponents = new int[p.coefficients.length*coefficients.length];
		
		int i = 0;
		int j = 0;
		while(i < coefficients.length)
		{
			j=0;
			while(j < p.coefficients.length)
			{
				total_coefficients[j] = coefficients[i]*p.coefficients[j];
				total_exponents[j] = exponents[i]+p.exponents[j];
				j++;			
			}
			
			Polynomial temp = new Polynomial(total_coefficients, total_exponents);
			final_poly = final_poly.add(temp);
			
			i++;
		}
		
		return final_poly;	
	}
	
	public void saveToFile(String filename) throws FileNotFoundException, IOException
	{
		String textPoly = "";
		
		for(int i = 0; i < exponents.length; i++)
		{	
			if(coefficients[i] > 0)
			{
				textPoly += "+";
				textPoly += coefficients[i];
				textPoly += "x";
				
				if(exponents[i] > 0)
					textPoly += exponents[i];
			}
			
			if(coefficients[i] < 0)
			{
				textPoly += "-";
				textPoly += coefficients[i];
				textPoly += "x";
				
				if(exponents[i] > 0)
					textPoly += exponents[i];
			}
		}
		
		File f = new File("output.txt");
		PrintStream ps = new PrintStream("C:\\Users\\user\\output.txt");
		ps.println(textPoly + '\n');
		ps.close();
		
	 }
	
	
	
}