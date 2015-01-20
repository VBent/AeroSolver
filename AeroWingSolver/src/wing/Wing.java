package wing;

import java.util.ArrayList;

public class Wing {

	
ArrayList<WingSection> body ;

ArrayList<WingSection> blueprint;



public Wing (WingSection... ws)
{
	
blueprint = new ArrayList<WingSection>();

for (int i =0;i<ws.length; i++)
{
blueprint.add(ws[i]);	
}


	
	
}





	
	
}
