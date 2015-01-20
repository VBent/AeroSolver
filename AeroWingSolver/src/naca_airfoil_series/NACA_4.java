package naca_airfoil_series;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;




import static linguistics.NumberFormatter.*;
import static java.lang.Math.*;

public class NACA_4 extends Airfoil  {


double M,P,T ;


/**
 * Constructor of NACA series 4 airfoils 
 * @param name A String denoting the requested airfoil...Substring NACA and the numbers must be separated by an underscore character
 * Example :  NACA_2412
 * @throws IOException 
 */





public NACA_4 (String name) throws IOException
{
		
	if (name.length() == 9 && name.substring(0, 5).equals("NACA_") && Integer.parseInt( name.substring(5,9)) >=0 )
	{
			construct_by_name(name);
	}  // end if 1
	
	else if (name.substring(1,3).equals(":/"))
	{
		construct_by_file(name);
	}

	
}//constructor
	

@Override
protected double yc (double xc, double P) 
{
	if (xc >=P)
		return format( (M / pow((1-P),2)) * (1-2*P+2*P*xc-pow(xc,2)));
		
	else 
		{
			return format((M / pow(P,2)) * (2*P*xc-pow(xc,2)));
		}
	
}
@Override
protected double half_thick (double xc, double T) {
	
	return format(T * (1.485*sqrt(xc) -0.63*xc-1.758*pow(xc,2)+1.4215*pow(xc,3)-0.5185*pow(xc,4)        ));
}




/**
 * This method re-constructs the airfoil from given data file
 * @param position the location on disk of the data file
 * @throws IOException due to usage of a Bufferedreader
 */
private void construct_by_file (String position) throws IOException 
{
	File file = new File(position);
	
	
	BufferedReader reader = new BufferedReader(new FileReader(position));
	
	camberline = new ArrayList<double[]>();
	body = new ArrayList<double[]>();
	
	String[][] lines = new String[3608][7] ;
	
	for (int i =0; i < lines.length;i++)
	{
		String s = reader.readLine();
		if (i>=3 && i <=3604)
		{
			
			String[] lala =s.split("         ");
			for (int j =0; j<lala.length;j++)
			{
				lines[i][j]=lala[j];
				
			} //tou j-for
	
			camberline.add( new double[] {
					Double.parseDouble(lines[i][0]),
					Double.parseDouble(lines[i][1]),
					Double.parseDouble(lines[i][2]),
					Double.parseDouble(lines[i][3]),
					Double.parseDouble(lines[i][6]),
			});
			
			body.add( new double[] {
					Double.parseDouble(lines[i][0]),
					Double.parseDouble(lines[i][4]),
					Double.parseDouble(lines[i][5]),
					Double.parseDouble(lines[i][6]),
			});
			
		}//tou if i>=4	
			
			
		}//tou for
reader.close();		
	
	name = file.getName().substring(0, 10);
	M =Double.parseDouble(file.getName().substring(5, 6))/100d;
	P =Double.parseDouble(file.getName().substring(6, 7))/10d;
	T=Double.parseDouble(file.getName().substring(7, 9))/100d;

}//ths me8odou

/**
 * This method constructs the airfoil when the given string is its official name
 * @param name the official name of the airfoil
 */
private void construct_by_name (String name)
{
	this.name = name ;
	
	M = Double.parseDouble( name.substring(5,6))/100;
	P = Double.parseDouble( name.substring(6,7))/10;
	T = Double.parseDouble( name.substring(7,9))/100;
	
	
	
	camberline = new ArrayList<double[]>();
	body = new ArrayList<double[]>();
	

	
for (int i =0 ; i <= 3600; i++)   // prosoxi sto ison tou i
{
	double w = format(2*PI*i/3600) ;
	double xc = format(0.5* ( 1+cos(w)));
	//double half_thick = format(T * (1.485*sqrt(xc) -0.63*xc-1.758*pow(xc,2)+1.4215*pow(xc,3)-0.5185*pow(xc,4)        ));
	
	
	if (xc >=P)
	{
		//double yc =format( (M / pow((1-P),2)) * (1-2*P+2*P*xc-pow(xc,2)));
		double theta = format(atan( (2*M / pow((1-P),2))*(P-xc)      ));
		double[] camber_pos = new double[] {format(i/3600d),xc, yc(xc,P),toDegrees(theta),(2*half_thick(xc,T))}; 
		camberline.add(camber_pos);
		
		
		if (i <=1800)
		{
			double xl =format(xc+half_thick(xc,T)*sin(theta)); 
			double yl =format(yc(xc,P) -half_thick(xc,T)*cos(theta));
			double[] lpos = new double[] {format(i/3600d), xl,yl,(2*half_thick(xc,T))};
		    body.add(lpos);
		}
		
		else  // 1800 < i <3600
		{

			double xu =format(xc-half_thick(xc,T)*sin(theta)); 
			double yu = format(yc(xc,P) +half_thick(xc,T)*cos(theta));
			double[] upos = new double[] {format(i/3600d), xu,yu,(2*half_thick(xc,T))};
		    body.add(upos);
		    
		} //end else
	} //end if x>=P
	
	else  // x<P
	{
		{
			
			//double yc = format((M / pow(P,2)) * (2*P*xc-pow(xc,2)));
			double theta = format(atan( (2*M / pow(P,2))*(P-xc)      ));
			double[] camber_pos = new double[] {format(i/3600d),xc, yc(xc,P),toDegrees(theta),(2*half_thick(xc,T))}; 
			camberline.add(camber_pos);
			
			
			if (i <=1800)
			{
				double xl =format(xc+half_thick(xc,T)*sin(theta)); 
				double yl =format(yc(xc,P) -half_thick(xc,T)*cos(theta));
				double[] lpos = new double[] {format(i/3600d), xl,yl,(2*half_thick(xc,T))};
			    body.add(lpos);
			}
			
			else // 1800 < i <3600 
			{

				double xu =format(xc-half_thick(xc,T)*sin(theta)); 
				double yu =format(yc(xc,P) +half_thick(xc,T)*cos(theta));
				double[] upos = new double[] {format(i/3600d), xu,yu,(2*half_thick(xc,T))};
			    body.add(upos);	
			}
		
	}
	
	
}
	
	
}  //end for



}






/**
 * This method prints on a .txt file all the data needed for saving on the disk
 * @param position_on_disk  the location on which the data are to be saved
 * @throws FileNotFoundException
 * @throws UnsupportedEncodingException
 */
public void extract_airfoil_data (String position_on_disk) throws FileNotFoundException, UnsupportedEncodingException
{
File archive = new File (position_on_disk); 
archive.mkdirs();	
	
PrintWriter writer = new PrintWriter(position_on_disk+"/"+name+" Data.afl", "UTF-8");

writer.println("t" + "                 "+"xc"+"                "+"yc" +"                "+"è"+"                 "+"x"+"                 "+"y"+"                 "+"ä");
writer.println("=====================================================================================================================");
writer.println();

for (int j =0; j <body.size();j++)
{
	writer_print( writer, camberline.get(j)[0] ,camberline.get(j)[1],camberline.get(j)[2],camberline.get(j)[3],body.get(j)[1], body.get(j)[2],body.get(j)[3]) ;
}
	

writer.println();
writer.println("=====================================================================================================================");
writer.println();
writer.println("No of points used = " +body.size());
writer.close();
}


public static void main (String[] args) throws IOException
{
	
	NACA_4 foil = new NACA_4 ("NACA_2345");
	foil.extract_airfoil_data("C:/Airfoil Data/Airfoils/NACA_4");
	
}




	
}//ths class
