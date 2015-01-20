package wing;

import java.io.IOException;

import javax.vecmath.*;
import javax.media.j3d.*;

import naca_airfoil_series.NACA_4;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import static java.lang.Math.*;

public class Tester {

	
public static void visualize(WingSection ws, Vector3f v)
	{
		SimpleUniverse uni = new SimpleUniverse();
	
	uni.addBranchGraph(createPOV (uni,v));	
		
		
		BranchGroup contGroup = new BranchGroup();
		
		
		
	}	
	
protected static BranchGroup createPOV (SimpleUniverse uni, Vector3f v)
{
	BranchGroup viewGroup = new BranchGroup();
	
	Transform3D pov = new Transform3D() ;
	pov.set(v);
	TransformGroup povGroup = new TransformGroup(pov);
	
	
	/**
	ViewPlatform vplat = new ViewPlatform();
	View myView = new View();
	PhysicalBody body = new PhysicalBody();
	PhysicalEnvironment env = new PhysicalEnvironment();
	myView.setPhysicalBody(body);
	myView.setPhysicalEnvironment(env);
	myView.addCanvas3D(c);
	myView.attachViewPlatform(vplat);
	
	povGroup.addChild(vplat);
	*/
	
	povGroup.addChild(uni.getViewingPlatform().getViewPlatform());
	viewGroup.addChild(povGroup);
		
	return viewGroup ;
	
	
}
	


protected static BranchGroup createObj ( WingSection wsec, Vector3d v)
{
BranchGroup cBranch = new BranchGroup();	
	
Transform3D move = new Transform3D();
move.set(v);
TransformGroup translate =new TransformGroup(move);

cBranch.addChild(translate);

int k = wsec.body.size();
int[] length = new int[] {k};
Point3d[] graph = new Point3d[k];

for (int i =0; i<k;i++)
{
	graph[i] = new Point3d(wsec.body.get(i)[1],wsec.body.get(i)[2],wsec.body.get(i)[3]);
	
}

LineStripArray line = new LineStripArray(k,GeometryArray.COORDINATES,length);
line.setCoordinates(0, graph);

Shape3D lineshape = new Shape3D (line);

translate.addChild(lineshape);
	
	return cBranch;
}

public static Shape3D WSect_body (WingSection wsec)
{
	
	int k = wsec.body.size();
	int[] length = new int[] {k};
	Point3d[] graph = new Point3d[k];

	for (int i =0; i<k;i++)
	{
		graph[i] = new Point3d(wsec.body.get(i)[1],wsec.body.get(i)[2],wsec.body.get(i)[3]);
		
	}

	LineStripArray line = new LineStripArray(k,GeometryArray.COORDINATES,length);
	line.setCoordinates(0, graph);

	
Appearance app = new Appearance();
	
	ColoringAttributes atts = new ColoringAttributes();
	atts.setColor(new Color3f(1.0f,0.0f,0.0f));
	app.setColoringAttributes(atts);
	return new Shape3D (line,app);

	
	
	
}
public static Shape3D WSect_camber (WingSection wsec)
{
	
	int k = wsec.camberline.size();
	int[] length = new int[] {k};
	Point3d[] graph = new Point3d[k];

	for (int i =0; i<k;i++)
	{
		graph[i] = new Point3d(wsec.camberline.get(i)[1],wsec.camberline.get(i)[2],wsec.camberline.get(i)[3]);
		
	}

	LineStripArray line = new LineStripArray(k,GeometryArray.COORDINATES,length);
	line.setCoordinates(0, graph);

	
Appearance app = new Appearance();
	
	ColoringAttributes atts = new ColoringAttributes();
	atts.setColor(new Color3f(0.0f,0.0f,1.0f));
	app.setColoringAttributes(atts);
	
	return new Shape3D (line,app);

	
	
	
}

public static Shape3D getCoordSystem(double r)
{
	Point3d[] matrix = new Point3d[]
			{new Point3d(0.0d,0.0d,0.0d),
			   new Point3d(r,0.0d,0.0d),
			   new Point3d(0.0d,0.0d,0.0d),
			   new Point3d(0.0d,r,0.0d),
			   new Point3d(0.0d,0.0d,0.0d),
			   new Point3d(0.0d,0.0d,r),
			};
	
	LineArray coords = new LineArray(matrix.length,GeometryArray.COORDINATES);
	coords.setCoordinates(0, matrix);
	
	Appearance app = new Appearance();
	
	ColoringAttributes atts = new ColoringAttributes();
	atts.setColor(new Color3f(0.0f,1.0f,0.0f));
	app.setColoringAttributes(atts);
	return new Shape3D(coords,app);
	
	
}

public static Shape3D createLine(double b,double r)
{
	Point3d[] matrix = new Point3d[]
			{
			   new Point3d(b,0.0d,0.0d),
			   new Point3d(b,0.0d,r),
			};
	
	LineArray coords = new LineArray(matrix.length,GeometryArray.COORDINATES);
	coords.setCoordinates(0, matrix);
	
	Appearance app = new Appearance();
	
	ColoringAttributes atts = new ColoringAttributes();
	atts.setColor(new Color3f(0.0f,1.0f,0.0f));
	app.setColoringAttributes(atts);
	return new Shape3D(coords,app);
}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

WingSection wsec0 = new WingSection(new NACA_4("NACA_3420"), 1.0,  0.0);
WingSection wsec1 = new WingSection(new NACA_4("NACA_2415"), 0.7,  0.4);
WingSection wsec2 = new WingSection(new NACA_4("NACA_2412"), 0.6,  0.5);
WingSection wsec3 = new WingSection(new NACA_4("NACA_2210"), 0.4,  0.8);
WingSection wsec4 = new WingSection(new NACA_4("NACA_2208"), 0.35, 0.9);
WingSection wsec5 = new WingSection(new NACA_4("NACA_2206"), 0.25, 1.0);

		
SimpleUniverse uni = new SimpleUniverse();
//uni.getViewingPlatform().setNominalViewingTransform();

BranchGroup group = new BranchGroup();
uni.getViewingPlatform().setNominalViewingTransform();
OrbitBehavior orbit = new OrbitBehavior(uni.getCanvas());
orbit.setSchedulingBounds(new BoundingSphere());
orbit.setRotXFactor(0.1);
orbit.setRotYFactor(0.1);
uni.getViewingPlatform().setViewPlatformBehavior(orbit);

View view = new View();
view.setPhysicalBody(new PhysicalBody());
view.setPhysicalEnvironment(new PhysicalEnvironment());

view.setProjectionPolicy(View.SCALE_EXPLICIT);
view.attachViewPlatform(uni.getViewingPlatform().getViewPlatform());



Shape3D bodyshape0 =WSect_body(wsec0); 
Shape3D cambershape0 = WSect_camber(wsec0);

Shape3D bodyshape1 =WSect_body(wsec1); 
Shape3D cambershape1 = WSect_camber(wsec1);

Shape3D bodyshape2 =WSect_body(wsec2); 
Shape3D cambershape2 = WSect_camber(wsec2);

Shape3D bodyshape3 =WSect_body(wsec3); 
Shape3D cambershape3 = WSect_camber(wsec3);

Shape3D bodyshape4 =WSect_body(wsec4); 
Shape3D cambershape4 = WSect_camber(wsec4);

Shape3D bodyshape5 =WSect_body(wsec5); 
Shape3D cambershape5 = WSect_camber(wsec5);


Shape3D cfour_line= createLine(0.2, 1d);
Shape3D newcfour_line= createLine(0.0, 1d);


Transform3D move = new Transform3D ();

Shape3D coords = getCoordSystem(1d);

move.set(new AxisAngle4d(0.0, 0.0, 0.0,0.25*PI));
move.setTranslation(new Vector3d(-0.0d,0.0d,0.0d ));








Transform3D rotate0 = new Transform3D();
rotate0.set(new AxisAngle4d(0.00, -0.00,1d,toRadians(0)));
rotate0.setTranslation(new Vector3d(-0.25d,0.0d,0.0d ));
TransformGroup rotgroup0 = new TransformGroup(rotate0);

Transform3D rotate1 = new Transform3D();
//rotate1.set(new AxisAngle4d(0.01, -0.01,0.00d,0.25*PI));
rotate1.set(new AxisAngle4d(0.00, -0.00,1d,toRadians(0)));
rotate1.setTranslation(new Vector3d(-0.7/4d,0.0d,0.0d ));
//rotate1.setTranslation(new Vector3d(-0.0d,0.0d,0.0d ));
TransformGroup rotgroup1 = new TransformGroup(rotate1);

Transform3D rotate2 = new Transform3D();
rotate2.set(new AxisAngle4d(0.00, -0.00,1d,toRadians(0)));
rotate2.setTranslation(new Vector3d(-0.6/4d,0.0d,0.0d ));
TransformGroup rotgroup2 = new TransformGroup(rotate2);

Transform3D rotate3 = new Transform3D();
rotate3.set(new AxisAngle4d(0.00, -0.00,1d,toRadians(0)));
rotate3.setTranslation(new Vector3d(-0.1d,0.0d,0.0d ));
TransformGroup rotgroup3 = new TransformGroup(rotate3);

Transform3D rotate4 = new Transform3D();
rotate4.set(new AxisAngle4d(0.00, 0.00,1,toRadians(0)));
rotate4.setTranslation(new Vector3d(-0.35/4d,0.0d,0.0d ));
TransformGroup rotgroup4 = new TransformGroup(rotate4);

Transform3D rotate5 = new Transform3D();
rotate5.set(new AxisAngle4d(0.00, 0.00,1,toRadians(0)));
rotate5.setTranslation(new Vector3d(-0.25/4d,0.0d,0.0d ));
TransformGroup rotgroup5 = new TransformGroup(rotate5);




TransformGroup tgroup = new TransformGroup();
tgroup.setTransform(move); 
/**
tgroup.addChild(bodyshape0); 
tgroup.addChild(cambershape0); 

tgroup.addChild(bodyshape1); 
tgroup.addChild(cambershape1); 

tgroup.addChild(bodyshape2); 
tgroup.addChild(cambershape2); 

tgroup.addChild(bodyshape3); 
tgroup.addChild(cambershape3); 

tgroup.addChild(bodyshape4); 
tgroup.addChild(cambershape4); 

tgroup.addChild(bodyshape5); 
tgroup.addChild(cambershape5); 

*/



rotgroup0.addChild(bodyshape0);
rotgroup0.addChild(cambershape0);
group.addChild(rotgroup0);


rotgroup1.addChild(bodyshape1);
rotgroup1.addChild(cambershape1);
group.addChild(rotgroup1);



rotgroup2.addChild(bodyshape2);
rotgroup2.addChild(cambershape2);
group.addChild(rotgroup2);


rotgroup3.addChild(bodyshape3);
rotgroup3.addChild(cambershape3);
group.addChild(rotgroup3);


rotgroup4.addChild(bodyshape4);
rotgroup4.addChild(cambershape4);
group.addChild(rotgroup4);


rotgroup5.addChild(bodyshape5);
rotgroup5.addChild(cambershape5);
group.addChild(rotgroup5);

tgroup.addChild(coords); 
//t2group.addChild(cfour_line);
tgroup.addChild(newcfour_line);

group.addChild(tgroup);

uni.addBranchGraph(group);














		
	}

}
