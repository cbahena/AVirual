/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;


/**
 * Clase para crear el piso
 * @author Carlos
 */

  public class Floor extends Shape3D {
  /**
   * Constructor de la clase
   * @param A Punto para colocar el piso Point3f
   * @param B Punto para colocar el piso Point3f
   * @param C Punto para colocar el piso Point3f
   * @param D Punto para colocar el piso Point3f
   */
    Floor(Point3f A, Point3f B, Point3f C, Point3f D) {
      this.setGeometry(createGeometry(A, B, C, D));
      this.setAppearance(createAppearance());
    }

    Geometry createGeometry(Point3f A, Point3f B, Point3f C, Point3f D) {

      QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
          | GeometryArray.NORMALS);

      plane.setCoordinate(0, A);
      plane.setCoordinate(1, B);
      plane.setCoordinate(2, C);
      plane.setCoordinate(3, D);

      Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
      Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
      Vector3f n = new Vector3f();
      n.cross(b, a);

      n.normalize();

      plane.setNormal(0, n);
      plane.setNormal(1, n);
      plane.setNormal(2, n);
      plane.setNormal(3, n);

      return plane;
    }
  /**
   * Crea la apariencia del piso
   */
    Appearance createAppearance() {
      Appearance appear = new Appearance();
      Material material = new Material();
      appear.setMaterial(material);

      return appear;
    }

  }
