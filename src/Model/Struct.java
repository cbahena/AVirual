/*
 * Proyecto para ambientes virtuales del posgrado en computacion de la ULA
 * Elaborado por Carlos Bahena V16611908
 */

package Model;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.*;
import javax.swing.JOptionPane;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
/**
 * Clase para el manejo de JAVA 3D
 * @author carlos
 * @version 1.0
 */
public final class Struct extends javax.swing.JFrame implements KeyListener{
    
    private SimpleUniverse univ = null;
    private BranchGroup scene = null;
    
    private java.net.URL bgTopeMesa = null;
    private java.net.URL bgAluminio = null;
    private java.net.URL bgFamilia = null;
    private java.net.URL bgPiso = null;
    private java.net.URL bgLadrillo = null;
    private java.net.URL bgPaisajeVentana = null;
    private java.net.URL bgBurbujas = null;
    private java.net.URL bgMadera = null;
    private java.net.URL bgFondo = null;
    
    private final String DIR = "src/resources/images/";
    private final static double DY = 1;
     
    private final BoundingSphere limites = new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
    
    private TransformGroup avatar;
    private TransformGroup grupoEsfera;
    
    private int xAvatar = 10;
    private int yAvatar = -1;
    private int zAvatar = -4;
    
    private final float yPelota = 1.0f;
    private final float zPelota= 10.0f;
    
    protected Shape3D cubo;
    protected Shape3D esfera;

 /**
 * Metodo para crear la escena
 * @author carlos
 * @version 1.0
 * @return BranchGroup
 */
    public BranchGroup crearEscena() {

    BranchGroup grupo = new BranchGroup();

    TransformGroup objScale = new TransformGroup();
    Transform3D t3d = new Transform3D();
    t3d.setScale(0);
    objScale.setTransform(t3d);
    grupo.addChild(objScale);
        
    // Parametros de direccion de luz y color
    Color3f lColor1 = new Color3f(0.7f, 0.7f, 0.7f);
    Vector3f lDir1  = new Vector3f(-1.0f, -1.0f, -1.0f);
    Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);

    AmbientLight luzAmbiente = new AmbientLight(alColor);
    luzAmbiente.setInfluencingBounds(limites);
    DirectionalLight luz1 = new DirectionalLight(lColor1, lDir1);
    luz1.setInfluencingBounds(limites);
    
    grupo.addChild(luzAmbiente);
    grupo.addChild(luz1);
        
    // Configuracion
    TransformGroup objTrans = new TransformGroup();
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    objTrans.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
    objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
    grupo.addChild(objTrans);
    
    TransformGroup objeto = new TransformGroup();

    //fotografia
    objeto.addChild( crearObjeto(new Box(0.2f, 0.14f, 0.02f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgAluminio)), 0f, 0.25f, 0f));
    objeto.addChild( crearObjeto(new Box(0.04f, 0.15f, 0.02f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgAluminio)), 0f, 0.19f, -0.15f, 45));
    objeto.addChild( crearObjeto(new Box(0.18f, 0.12f, 0.001f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgFamilia)), 0f, 0.25f, 0.02f));
       
    //Patas de la mesa
    objeto.addChild( crearObjeto(new Box(0.03f, 0.5f, 0.03f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), -0.55f, -0.460f, 0.33f));
    objeto.addChild( crearObjeto(new Box(0.03f, 0.5f, 0.03f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), -0.55f, -0.460f, -0.36f));       
    objeto.addChild( crearObjeto(new Box(0.03f, 0.5f, 0.03f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), 0.55f, -0.460f, 0.33f));
    objeto.addChild( crearObjeto(new Box(0.03f, 0.5f, 0.03f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), 0.55f, -0.460f, -0.36f));
            
    // Parte superior de la mesa y gaveta
    objeto.addChild( crearObjeto(new Box(0.6f, 0.011f, 0.4f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgTopeMesa)), 0f, 0.1f, 0f));       
    objeto.addChild( crearObjeto(new Box(0.6f, 0.1f, 0.4f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), 0f, 0.01f, 0f));
    objeto.addChild( crearObjeto(new Box(0.64f, 0.11f, 0.04f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), 0f, 0.01f, 0.41f));
    //Manilla mesa
    objeto.addChild( crearObjeto(new Sphere(0.04f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgTopeMesa)), 0f, 0.01f, 0.460f));
     
    //Lampara
    objeto.addChild( crearObjeto(new Cylinder(0.03f, 1.8f, Box.GENERATE_NORMALS, crearAparienciaObjeto(2, this.bgAluminio)), 1f, 3.35f, -1f));
    objeto.addChild( crearObjeto(new Sphere(0.4f, crearAparienciaObjeto(1, null)), 1f, 2.1f, -1f));
        
    //piso
    objeto.addChild( crearObjeto(new Box(2.6f, 0.01f, 2.4f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), 0f, -0.96f, 0f));       
    
    //paredes
    objeto.addChild( crearObjeto(new Box(0.01f, 2.6f, 2.4f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgLadrillo)), 2.6f, 1.65f, 0f));       
    objeto.addChild( crearObjeto(new Box(0.01f, 2.6f, 2.4f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgLadrillo)), -2.6f, 1.65f, 0f));       
    objeto.addChild( crearObjeto(new Box(2.6f, 2.6f, 0.01f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgLadrillo)), 0f, 1.65f, -2.4f));       
    objeto.addChild( crearObjeto(new Box(2.6f, 2.6f, 0.01f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgLadrillo)), 0f, 1.65f, 2.4f));       
    
    //puerta
    objeto.addChild( crearObjeto(new Box(0.85f, 2.0f, 0.02f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgMadera)), 0f, 1.05f, 2.4f));       
    objeto.addChild( crearObjeto(new Sphere(0.1f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgTopeMesa)), 0.5f, 1.01f, 2.4f));
    
    //techo
    objeto.addChild( crearObjeto(new Box(2.6f, 0.01f, 2.4f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgTopeMesa)), 0f, 4.25f, 0f));       
    
    //silla
    Group silla = crearObjeto(new Cylinder(0.4f, 1.0f, Box.GENERATE_NORMALS, crearAparienciaObjeto(1, null)), -2.0f, -0.45f, 1.5f);
    BoundingSphere volSilla = new BoundingSphere(new Point3d(0.4,1.0,0.0), 100.0);
    silla.setCollisionBounds(volSilla);
    silla.setCollidable(true);
    objeto.addChild( silla );
    
    //burbujas
    objeto.addChild( crearObjeto(new Sphere(0.085f, crearAparienciaObjeto(3, null)), 0.5f, 0.3f, 0.5f));
    objeto.addChild( crearObjeto(new Sphere(0.080f, crearAparienciaObjeto(3, null)), 0.4f, 0.6f, 0.5f));
    objeto.addChild( crearObjeto(new Sphere(0.065f, crearAparienciaObjeto(3, null)), 0.5f, 0.5f, 0.5f));
    objeto.addChild( crearObjeto(new Sphere(0.065f, crearAparienciaObjeto(3, null)), 0.6f, 0.5f, 0.5f));
    objeto.addChild( crearObjeto(new Sphere(0.055f, crearAparienciaObjeto(3, null)), 0.5f, 0.45f, 0.3f));
    objeto.addChild( crearObjeto(new Sphere(0.055f, crearAparienciaObjeto(3, null)), 0.5f, 0.36f, 0.3f));
    
    //maquina hacer burbujas
    objeto.addChild( crearObjeto(new Cylinder(0.04f, 0.1f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgBurbujas)), 0.5f, 0.16f, 0.3f));
    objeto.addChild( crearObjeto(new Cylinder(0.03f, 0.05f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgBurbujas)), 0.5f, 0.22f, 0.3f));
    objeto.addChild( crearObjeto(new Cylinder(0.02f, 0.05f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgBurbujas)), 0.5f, 0.27f, 0.3f));
    objeto.addChild( crearObjeto(new Cylinder(0.01f, 0.05f, Box.GENERATE_TEXTURE_COORDS, crearAparienciaObjeto(2, this.bgBurbujas)), 0.5f, 0.30f, 0.3f));
     
    //Crea el cubo para las colisiones
    crearCubo();
    objeto.addChild(crearObjeto(cubo,-12.5f, 0.0f, 10.3f));
    
    //Crea esfera para que se mueva
    Sphere pelota =  new Sphere(1.0f, Box.GENERATE_NORMALS, crearAparienciaObjeto(4, null));
    grupoEsfera = crearObjeto2(pelota, 10.0f, yPelota, zPelota);
    grupoEsfera.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    grupoEsfera.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
    esfera = pelota.getShape();
    objeto.addChild( grupoEsfera );
    
    // agrega todos los objetos a la escena
    grupo.addChild(objeto);   
    
    return grupo;
   }
     /**
 * Metodo para crear un cubo
 * @author carlos
 * @version 1.0
 * @return void
 */
   private void crearCubo()
   {
    IndexedQuadArray dimensionesCubo = new IndexedQuadArray(8, IndexedQuadArray.COORDINATES | IndexedQuadArray.NORMALS, 24);
    
    Point3f[] cubeCoordinates = { new Point3f(1.0f, 1.0f, 1.0f),
        new Point3f(-1.0f, 1.0f, 1.0f),
        new Point3f(-1.0f, -1.0f, 1.0f),
        new Point3f(1.0f, -1.0f, 1.0f), 
        new Point3f(1.0f, 1.0f, -1.0f),
        new Point3f(-1.0f, 1.0f, -1.0f),
        new Point3f(-1.0f, -1.0f, -1.0f),
        new Point3f(1.0f, -1.0f, -1.0f) };
    
    Vector3f[] cubeNormals = { new Vector3f(0.0f, 0.0f, 1.0f),
        new Vector3f(0.0f, 0.0f, -1.0f),
        new Vector3f(1.0f, 0.0f, 0.0f),
        new Vector3f(-1.0f, 0.0f, 0.0f),
        new Vector3f(0.0f, 1.0f, 0.0f), new Vector3f(0.0f, -1.0f, 0.0f) };
    
    int coordenadasCubo[] = { 0, 1, 2, 3, 7, 6, 5, 4, 0, 3, 7, 4, 5, 6, 2,
        1, 0, 4, 5, 1, 6, 7, 3, 2 };
    
    int indicesCubo[] = { 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3,
        3, 3, 4, 4, 4, 4, 5, 5, 5, 5 };
    
    dimensionesCubo.setCoordinates(0, cubeCoordinates);
    dimensionesCubo.setNormals(0, cubeNormals);
    dimensionesCubo.setCoordinateIndices(0, coordenadasCubo);
    dimensionesCubo.setNormalIndices(0, indicesCubo);
        
    Material material = new Material(new Color3f(0.0f, 1.0f, 0.0f), new Color3f(0.0f, 0.0f, 0.0f), 
                        new Color3f(0.0f, 0.0f, 0.0f), new Color3f(0.0f, 0.0f, 0.0f), 64);
    
    Appearance apariencia = new Appearance();
    apariencia.setMaterial(material);
    
    //Para que permita modificar los colores
    apariencia.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_READ); 
    apariencia.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);
    apariencia.setCapability(Appearance.ALLOW_MATERIAL_READ); 
    apariencia.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
    
    cubo = new Shape3D(dimensionesCubo, apariencia);
    cubo.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
    cubo.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
   }
   
 /**
 * Metodo para crear el ambiente virtual a trav?s de un cubo con cada cara con textura
 * @author carlos
 * @param  tamPared tama?o de cada pared
 * @version 1.0
 * @return void
 */
   private void crearAmbiente(double tamPared)
  { 

    //Genera las esquinas
    Point3d p1 = new Point3d(-tamPared/2, -DY, tamPared/2); 
    Point3d p2 = new Point3d(tamPared/2, -DY, tamPared/2);
    Point3d p3 = new Point3d(tamPared/2, -DY, -tamPared/2);
    Point3d p4 = new Point3d(-tamPared/2, -DY, -tamPared/2);

    Point3d p5 = new Point3d(-tamPared/2, tamPared/4, tamPared/2);
    Point3d p6 = new Point3d(tamPared/2, tamPared/4, tamPared/2);
    Point3d p7 = new Point3d(tamPared/2, tamPared/4, -tamPared/2);
    Point3d p8 = new Point3d(-tamPared/2, tamPared/4, -tamPared/2);

    // piso
    scene.addChild( new TexPlane(p2, p3, p4, p1, DIR+"paisajePiso.jpg"));
    // pared del frente
    scene.addChild( new TexPlane(p4, p3, p7, p8, DIR+"2.jpg"));
    // pared derecha
    scene.addChild( new TexPlane(p3, p2, p6, p7, DIR+"3.jpg"));
    // pared trasera
    scene.addChild( new TexPlane(p2, p1, p5, p6, DIR+"4.jpg"));
    // pared izquierda
    scene.addChild( new TexPlane(p1, p4, p8, p5, DIR+"1.jpg"));
    // techo
    scene.addChild( new TexPlane(p5, p8, p7, p6, DIR+"cielo.jpg"));

  }
 /**
 * Metodo para crear los arboles usando .gif
 * @author carlos
 * @version 1.0
 * @return void
 */    
   private void crearArboles()
  {
    Transform3D t3d = new Transform3D();

    t3d.set( new Vector3d(12,-1,0)); 
    TransformGroup tg1 = new TransformGroup(t3d);
    tg1.addChild( new GroundShape(DIR+"arbol1.gif", 6) );
    scene.addChild(tg1);

    t3d.set( new Vector3d(-10,-1,0));
    TransformGroup tg2 = new TransformGroup(t3d);
    tg2.addChild( new GroundShape(DIR+"arbol2.gif", 8) );
    scene.addChild(tg2);

    t3d.set( new Vector3d(22,-1,-6));
    TransformGroup tg3 = new TransformGroup(t3d);
    tg3.addChild( new GroundShape(DIR+"arbol1.gif", 7) );
    scene.addChild(tg3);

    t3d.set( new Vector3d(19,-1,-4));
    TransformGroup tg4 = new TransformGroup(t3d);
    tg4.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg4);
    
    t3d.set( new Vector3d(1,-1,-4));
    TransformGroup tg5 = new TransformGroup(t3d);
    tg5.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg5);
    
    t3d.set( new Vector3d(19,-1,5));
    TransformGroup tg6 = new TransformGroup(t3d);
    tg6.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg6);
    
    t3d.set( new Vector3d(20,-1,4));
    TransformGroup tg7 = new TransformGroup(t3d);
    tg7.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg7);
    
    t3d.set( new Vector3d(20,-3,-6));
    TransformGroup tg8 = new TransformGroup(t3d);
    tg8.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg8);
    
    t3d.set( new Vector3d(15,-1,-2));
    TransformGroup tg9 = new TransformGroup(t3d);
    tg9.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg9);
    
    t3d.set( new Vector3d(10,-1,-4));
    TransformGroup tg10 = new TransformGroup(t3d);
    tg10.addChild( new GroundShape(DIR+"arbol1.gif") );
    scene.addChild(tg10);
  } 
 /**
 * Metodo para crear el avatar
 * @author carlos
 * @version 1.0
 * @return void
 */      
  private void crearAvatar()
  {
    //agrega avatar
    Avatar1 avatarObject = new Avatar1();
    
    avatar = avatarObject.createRobot();
    
    //Permite que se pueda modificar las caracteristicas del avatar
    avatar.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    
    Transform3D t3d = new Transform3D();
    t3d.set( new Vector3d(xAvatar, yAvatar, zAvatar));
    
    avatar.setTransform(t3d);
    scene.addChild(avatar);
  }
 /**
 * Metodo para crear un objeto
 * @author carlos
 * @version 1.0
 * @param node Nodo
 * @param xpos Posicion X en el plano
 * @param ypos Posicion Y en el plano
 * @param ypos Posicion Z en el plano
 */
    private Group crearObjeto(Node node, float xpos, float ypos, float zpos) {
      
      TransformGroup grupo = new TransformGroup();
      Transform3D transformacion = new Transform3D();
      
      Vector3f coordenadas = new Vector3f(xpos, ypos, zpos);
      transformacion.setTranslation(coordenadas);
      
      grupo.setTransform(transformacion);    
      grupo.addChild(node);
      
      return grupo;
    }
    
     /**
 * Metodo para crear un objeto
 * @author carlos
 * @version 1.0
 * @param node Nodo
 * @param xpos Posicion X en el plano
 * @param ypos Posicion Y en el plano
 * @param ypos Posicion Z en el plano
 */
    private TransformGroup crearObjeto2(Node node, float xpos, float ypos, float zpos) {
      
      TransformGroup grupo = new TransformGroup();
      Transform3D transformacion = new Transform3D();
      
      Vector3f coordenadas = new Vector3f(xpos, ypos, zpos);
      transformacion.setTranslation(coordenadas);
      
      grupo.setTransform(transformacion);    
      grupo.addChild(node);
      
      return grupo;
    }
 /**
 * Metodo para crear un objeto
 * @author carlos
 * @version 1.0
 * @param node Nodo
 * @param xpos Posicion X en el plano
 * @param ypos Posicion Y en el plano
 * @param rotX Grados de rotacion con respecto a X
 */
    private Group crearObjeto(Node node, float xpos, float ypos, float zpos, double rotX) {
      
      TransformGroup objetoTG = new TransformGroup();
      Transform3D transformacion = new Transform3D();
      transformacion.rotX(rotX);
      
      Vector3f coordenada = new Vector3f(xpos, ypos, zpos);
      transformacion.setTranslation(coordenada);
      
      objetoTG.setTransform(transformacion); 
      
      objetoTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
      objetoTG.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
    
      objetoTG.addChild(node);
      
      return objetoTG;
    }
 /**
 * Metodo para dar apariencia al objeto creado
 * @author carlos
 * @version 1.0
 * @param appType Tipo de apariencia: 0 solido sin luz; 1  Solido con luz; 2 Textura, 3 Transparente
 * @param texImage Ruta de la imagen para crear la textura
 */
     private Appearance crearAparienciaObjeto(int appType, java.net.URL texImage) {
	Appearance apariencia = new Appearance();
	// Colores
	Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
	Color3f white = new Color3f(0.0f, 0.0f, 0.0f);

	switch (appType) {
	// Solido con luz
	case 1:
	    {
		// Parametros del material
		Color3f objetoColor = new Color3f(1.2f, 0.0f, 0.0f);
		apariencia.setMaterial(new Material(objetoColor, white, objetoColor,
					     white, 80.0f));
		break;
	    }

	// Textura
	case 2:
	    {
		// Mapea la textura
		TextureLoader texura = new TextureLoader(texImage, this);
		apariencia.setTexture(texura.getTexture());

 		TextureAttributes atributosTextura = new TextureAttributes();
 		atributosTextura.setTextureMode(TextureAttributes.MODULATE);
 		apariencia.setTextureAttributes(atributosTextura);
 
		//Define el color
		apariencia.setMaterial(new Material(white, black, white, black, 1.0f));
		break;
	    }

	// Tranparente
	case 3:
	    {
		// Define las propiedades transparentes
		TransparencyAttributes atributos = new TransparencyAttributes();
		atributos.setTransparencyMode(TransparencyAttributes.BLENDED);
		atributos.setTransparency(0.8f);
		apariencia.setTransparencyAttributes(atributos);

		// Setea los atributos del poligono
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		apariencia.setPolygonAttributes(pa);

		// Define el color de transparencia (Blanco en este caso)
		Color3f colorObjeto = new Color3f(1.0f, 1.0f, 1.0f);
		apariencia.setMaterial(new Material(colorObjeto, white, colorObjeto,
					     black, 1.0f));
		break;
	    }
	default:
	    {
		ColoringAttributes atributos = new ColoringAttributes();
		atributos.setColor(new Color3f(1.4f, 1.4f, 0.4f));
		apariencia.setColoringAttributes(atributos);
	    }
	}

	return apariencia;
    }    
 /**
 * Metodo para crear el universo de la escena
 * @author carlos
 * @version 1.0
 */
    private Canvas3D crearUniverso() {
    
     // Obtiene la configuracion por defecto de la libreria
     GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
     
    // Crea el canvase segun la configuracion predeterminada
     Canvas3D canvas = new Canvas3D(config);

     // Crea el universo
     univ = new SimpleUniverse(canvas);

     univ.getViewingPlatform().setNominalViewingTransform();

     univ.getViewer().getView().setMinimumFrameCycleTime(10);
    
     //Para que se vea a la distancia en el viewport
     univ.getViewer().getView().setBackClipDistance(100.0);

     //Agrega los eventos key
     canvas.addKeyListener(this);
    
     ViewingPlatform viewingPlatform = univ.getViewingPlatform();
         
     TransformGroup viewTransform = viewingPlatform.getViewPlatformTransform();
     Transform3D t3d = new Transform3D();
     viewTransform.getTransform(t3d);
     t3d.lookAt(new Point3d(0,0,23), new Point3d(0,0,0), new Vector3d(0,1,0));
     t3d.invert();
     viewTransform.setTransform(t3d);
    
     // Permite que se pueda mover la escena con el mouse
     KeyBehavior keyBeh = new KeyBehavior();
     keyBeh.setSchedulingBounds(limites);
     viewingPlatform.setViewPlatformBehavior(keyBeh);  
    
      return canvas;
    }
 /**
 * Carga todas las imagenes de la escena
 * @author carlos
 * @version 1.0
 */
    private void cargaImagenes()
    {        
        if (bgMadera == null) {
            // Ruta de la imagen de la madera1
            bgMadera = Resources.getResource("../resources/images/madera1.jpg");
            if (bgMadera == null) {
                System.err.println("../resources/images/madera1.jpg not found");
                System.exit(1);
            }
        }
        if (bgTopeMesa == null) {
            // Ruta de la imagen de la madera2
            bgTopeMesa = Resources.getResource("../resources/images/madera2.jpg");
            if (bgTopeMesa == null) {
                System.err.println("../resources/images/madera2.jpg not found");
                System.exit(1);
            }
        }
        if (bgAluminio == null) {
            // Ruta de la imagen del aluminio
            bgAluminio = Resources.getResource("../resources/images/aluminio.jpg");
            if (bgAluminio == null) {
                System.err.println("../resources/images/aluminio.jpg not found");
                System.exit(1);
            }
        }
        if (bgFamilia == null) {
            // Ruta de la imagen de la familia
            bgFamilia = Resources.getResource("../resources/images/familia.jpg");
            if (bgFamilia == null) {
                System.err.println("../resources/images/familia.jpg not found");
                System.exit(1);
            }
        }
        if (bgPiso == null) {
            // Ruta de la imagen del piso
            bgPiso = Resources.getResource("../resources/images/piso.jpg");
            if (bgPiso == null) {
                System.err.println("../resources/images/piso.jpg not found");
                System.exit(1);
            }
        }
        if (bgLadrillo == null) {
            // Ruta de la imagen de los ladrillos ladrillos
            bgLadrillo = Resources.getResource("../resources/images/ladrillos.jpg");
            if (bgLadrillo == null) {
                System.err.println("../resources/images/ladrillos.jpg not found");
                System.exit(1);
            }
        }
        if (bgPaisajeVentana == null) {
            // Ruta de la imagen del paisaje           
            bgPaisajeVentana = Resources.getResource("../resources/images/paisaje.jpg");
            if (bgPaisajeVentana == null) {
                System.err.println("../resources/images/paisaje.jpg not found");
                System.exit(1);
            }
        }
        if (bgBurbujas == null) {
            // Ruta de la imagen de las burbujas
            bgBurbujas = Resources.getResource("../resources/images/burbujas.jpg");
            if (bgBurbujas == null) {
                System.err.println("../resources/images/burbujas.jpg not found");
                System.exit(1);
            }
        }
        if (bgFondo == null) {
            // Ruta de la imagen de las burbujas
            bgFondo = Resources.getResource("../resources/images/fondo.jpg");
            if (bgFondo == null) {
                System.err.println("../resources/images/fondo.jpg not found");
                System.exit(1);
            }
        }
    }
 /**
 * Metodo para crear la niebla en el ambiente
 * @author carlos
 * @version 1.0
 * @return void
 */     
  private void crearNiebla()
  {
      // Genera el color de la niebla
        ExponentialFog niebla = null;
        Color3f color = new Color3f(1.0f,1.0f,1.0f);
        float density = ((Float)0.04f).floatValue();
        
       // Genera la densidad y color de la niebla
        niebla = new ExponentialFog();
        niebla.setColor(color);
        niebla.setDensity(density);
        niebla.setCapability(Fog.ALLOW_COLOR_WRITE);
        niebla.setCapability(ExponentialFog.ALLOW_DENSITY_WRITE);
        niebla.setInfluencingBounds(limites);
        
        scene.addChild(niebla);
  }
  
  /**
 * Metodo para colocarle fondo al universo
 * @author carlos
 * @version 1.0
 * @return void
 */  
  private void crearFondo()
  {
    TextureLoader textureLoad = new TextureLoader(this.bgFondo,null);
    Background bgImage = new Background(textureLoad.getImage());
    bgImage.setApplicationBounds(limites);
    scene.addChild(bgImage);
  }
 /**
 * Metodo para asignar las interacciones sobre los objetos cubo y esfera
 * @author carlos
 * @version 1.0
 * @return void
 */   
  private void crearColision()
  {
    Collision colision = new Collision(cubo,cubo.getBounds());  
    scene.addChild(colision);
    
    CollisionMotion colisionMovimiento = new CollisionMotion(esfera, grupoEsfera, yPelota, zPelota); 
    
    scene.addChild(colisionMovimiento);
  }     
  /**
 * Constructor de la clase
 * @author carlos
 * @version 1.0
 */
    public Struct() {
        
        initComponents();
        
        //carga las imagenes de la escena
        cargaImagenes();
        
         // Crea la escena
	scene = crearEscena();
        
        //Agrega el entorno
        crearAmbiente(50);
        
        //Agrega los arboles
        crearArboles();
        
        //Genera el canvas
	Canvas3D c = crearUniverso();
        
        //Crea el Avatar
        crearAvatar();
       
        //Crea la colision con el cubo
        crearColision();
        
        //Crea la niebla
        crearNiebla();
        
        //Crea el fondo de la escena
        crearFondo();
	drawingPanel.add(c, java.awt.BorderLayout.CENTER);

        // Optimiza la escena
        scene.compile();
        
	univ.addBranchGraph(scene);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        drawingPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AMBIENTES VIRTUALES");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        drawingPanel.setPreferredSize(new java.awt.Dimension(700, 600));
        drawingPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(drawingPanel, java.awt.BorderLayout.CENTER);

        jMenu2.setText("Ayuda");

        jMenuItem1.setText("Acerca de");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         JOptionPane.showMessageDialog(this, "Elaborado por: Carlos Bahena V16611908 \nUsar las teclas direccionales para moverse en el ambiente virtual. \nUtilizar la combinacion de taclas alt + <<teclas direccionales>> para subir o bajar en el ambiente. \nUsar las teclas WASD para mover el avatar. \nTocar con el avatar los objetos cubo y esfera para interactuar con ellos.", "Acerca de", 1);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Struct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Struct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel drawingPanel;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        
    char key = e.getKeyChar();

    if (key == 'w') {//Arriba
      zAvatar--;
      Transform3D t3d = new Transform3D();
      t3d.setTranslation(new Vector3d(xAvatar,yAvatar,zAvatar));
      avatar.setTransform(t3d);  
    }
    else if (key == 's') {//Abajo
      zAvatar++;
      Transform3D t3d = new Transform3D();
      t3d.setTranslation(new Vector3d(xAvatar,yAvatar,zAvatar));
      avatar.setTransform(t3d);  
    }
    else if (key == 'a') {//Izquierda
      xAvatar--;
      Transform3D t3d = new Transform3D();
      t3d.setTranslation(new Vector3d(xAvatar,yAvatar,zAvatar));
      avatar.setTransform(t3d);  
    }
    else if (key == 'd') {//Derecha
      xAvatar++;
      Transform3D t3d = new Transform3D();
      t3d.setTranslation(new Vector3d(xAvatar,yAvatar,zAvatar));
      avatar.setTransform(t3d);  
    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
