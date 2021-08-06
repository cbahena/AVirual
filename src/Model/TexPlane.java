package Model;


/* Crea un cubo con la textura
   Autor Original: Andrew Davison, September 2006, ad@fivedots.coe.psu.ac.th
   Modificaciones por Carlos Bahena
*/

import javax.vecmath.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.image.*;

/**
 *
 * @author Carlos
 * Clase para la creacion de el fondo de cada cara del cubo
 */
public class TexPlane extends Shape3D 
{
  private static final int NUM_VERTS = 4;
/**
   * Constructor de la clase
   * @param p1 poscion en el plano
   * @param p2 poscion en el plano
   * @param p3 poscion en el plano
   * @param p4 poscion en el plano
   * @param texFnm ubicacion del archivo imagen a leer
   */
  public TexPlane(Point3d p1, Point3d p2, Point3d p3, Point3d p4, String texFnm) 
  { 
    createGeometry(p1, p2, p3, p4);

    Texture2D tex = loadTexture(texFnm);
    Appearance app = new Appearance();
    app.setTexture(tex);      
    setAppearance(app);
  } 

/**
   * Creaci?n de la geometria del arbol
   * @param p1 poscion en el plano
   * @param p2 poscion en el plano
   * @param p3 poscion en el plano
   * @param p4 poscion en el plano
   */
  private void createGeometry(Point3d p1, Point3d p2, Point3d p3, Point3d p4)
  {
    QuadArray plane = new QuadArray(NUM_VERTS, GeometryArray.COORDINATES | GeometryArray.TEXTURE_COORDINATE_2 );

    plane.setCoordinate(0, p1);
    plane.setCoordinate(1, p2);
    plane.setCoordinate(2, p3);
    plane.setCoordinate(3, p4);

    TexCoord2f q = new TexCoord2f();
    q.set(0.0f, 0.0f);    
    plane.setTextureCoordinate(0, 0, q);
    q.set(1.0f, 0.0f);   
    plane.setTextureCoordinate(0, 1, q);
    q.set(1.0f, 1.0f);    
    plane.setTextureCoordinate(0, 2, q);
    q.set(0.0f, 1.0f);   
    plane.setTextureCoordinate(0, 3, q);  

    setGeometry(plane);
  }  // end of createGeometry()

/**
   * Creaci?n de la textura del plano
   * @param fn Ubicacion del archivo
   */
  private Texture2D loadTexture(String fn)
  {
    TextureLoader texLoader = new TextureLoader(fn, null);
    Texture2D texture = (Texture2D) texLoader.getTexture();
    if (texture == null)
      System.out.println("No es posible cargar la textura desde" + fn);
    else {
      System.out.println("Textura cargada desde" + fn);

      texture.setBoundaryModeS(Texture.CLAMP_TO_EDGE);
      texture.setBoundaryModeT(Texture.CLAMP_TO_EDGE);

      texture.setMinFilter(Texture2D.BASE_LEVEL_LINEAR); 
      texture.setMagFilter(Texture2D.BASE_LEVEL_LINEAR); 
      texture.setEnable(true);
    }
    return texture;
  } 

} 

