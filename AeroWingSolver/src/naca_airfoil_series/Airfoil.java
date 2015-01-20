package naca_airfoil_series;

import java.util.ArrayList;

public abstract class Airfoil  {

	
public String name ;

public ArrayList<double[]> camberline, body;

abstract protected double yc (double xc, double P);

abstract protected double half_thick( double xc, double T);
	
	


}
