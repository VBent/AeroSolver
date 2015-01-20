package wing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.SimpleUniverse;

import naca_airfoil_series.*;
import static linguistics.NumberFormatter.*;
import static java.lang.Math.*;

public class WingSection {


String foilname ;	
	
double ch ;
double position ;

ArrayList<double[]> camberline, body ;	
ArrayList<Point3d[]> camberline3D, body3D ;


public WingSection ( Airfoil foil, double chord, double position)
{
foilname = foil.name;
ch=chord;
this.position = position;

camberline = new ArrayList<double[]>();
body  = new ArrayList<double[]>();
fill_camber_and_body(foil);	


}

	

private void fill_camber_and_body(Airfoil foil)
{int k = foil.camberline.size();
	
	for (int i=0; i<k;i++)	
	{
	double[] t = new double[6] ;
	t[0] =foil.camberline.get(i)[0];         // = t
	t[1] = ch*foil.camberline.get(i)[1];     // = xc
	t[2] =ch *foil.camberline.get(i)[2];     // = yc
	t[3] =position;                          // = zc
	t[4] = foil.camberline.get(i)[3];        // = è 
	t[5] = ch*foil.camberline.get(i)[3]/2d;  // = ä/2
		
 	this.camberline.add(t);
	
	
 	double[] u = new double[5];
 	u[0] = t[0];                             // = t
 	u[1] = ch*foil.body.get(i)[1];           // = x
 	u[2] = ch*foil.body.get(i)[2] ;          // = y
 	u[3] =  position;                        // = z
 	u[4] = t[5];                             // = ä/2
 	
 	this.body.add(u);
 		
 		
	}
	
	
	
}

	

public void extract_wingsection_data (String position_on_disk) throws FileNotFoundException, UnsupportedEncodingException
{
File archive = new File (position_on_disk); 
archive.mkdirs();	
	
PrintWriter writer = new PrintWriter(position_on_disk+"/"+foilname+" Data.wsec", "UTF-8");

writer.println("t" + "                 "+"xc"+"                "+"yc" +"                "+"zc"+"                "+"è"+"                 "+"x"+"                 "+"y"+"                 "+"z"+"                 "+"ä");
writer.println("=========================================================================================================================================================");
writer.println();

for (int j =0; j <body.size();j++)
{
	writer_print( writer, camberline.get(j)[0] ,camberline.get(j)[1],camberline.get(j)[2],camberline.get(j)[3],camberline.get(j)[4],
			body.get(j)[1], body.get(j)[2],body.get(j)[3],body.get(j)[4]) ;
}
	
writer.close();
}
	





	
	
	public static void main(String[] args) throws IOException {
		
WingSection wsec = new WingSection (new NACA_4("NACA_2412"),2, 0.3);


		wsec.extract_wingsection_data("C:/Airfoil Data/WingSections");
	}

}
