package linguistics;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;





public class NumberFormatter {

static 	DecimalFormat formatter ;
static String pattern ;	

static 
{
    pattern = "000.00000";	
	formatter =  new DecimalFormat (pattern, new DecimalFormatSymbols (Locale.US));
}



public static double format ( double t)
{
		
	return (double)Double.parseDouble(formatter.format(t));
	
}
	
	
public static void vprint ( double... t )
{
	
	
	for (int i =0; i<t.length; i++)
	{
	System.out.println(formatter.format(t[i]));
	}
}

public static void hprint ( double... t )
{
	
	
	for (int i =0; i<t.length; i++)
	{   if (i ==0)
	{
		System.out.print(formatter.format(t[i])) ;
	}
		if (t[i] <0)
		{
	System.out.print("     "+formatter.format(t[i]));
		}
		
		else System.out.print("      "+formatter.format(t[i]));
	}
	System.out.println();
}


public static void writer_print ( PrintWriter writer, double... t ) 
{
	
	
	for (int i =0; i<t.length; i++)
	{   if (i ==0)
	{
		writer.print(formatter.format(t[i])) ;
	}
	else{
		if (t[i] <0)
		{
	writer.print("        "+formatter.format(t[i]));
		}
		
		else writer.print("         "+formatter.format(t[i]));
	}
	}
	writer.println();
}

public static String toString ( double t)
{
	if (t<0)
	{
	return formatter.format(t) ;
	}
	else return (" "+formatter.format(t));
}

public static void main (String[] args)
{
	String pattern = "000.00000";
	double y = 3872825367.8426833453 ;
	double i = 8237467.3465563;
	double p = 234.4455880980348 ;
	
	
	vprint( y,i,p);
System.out.println();
hprint(y,i,p);
	
	
}



}
