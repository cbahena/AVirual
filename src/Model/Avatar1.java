/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author Carlos
 * Clase para la creacion de un robot a base de cilindros y esfera
 */
public class Avatar1 {

  Cylinder cylinder;
  Sphere sphere;
  TransformGroup TG;
  
  TransformGroup robotBody;
  TransformGroup RobotRigthArm;
  TransformGroup RobotLeftArm;
  TransformGroup robotHead;
  
  Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
  Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
  
  Vector3f Vector = new Vector3f();
  Transform3D Trans = new Transform3D();

  /**
 *
 * M?todo para crear el avatar
 */
  public TransformGroup createRobot() 
  {
    robotBody = new TransformGroup();

    Vector.set(0.0f, -1.5f, 0.0f);
    Trans.set(Vector);
    robotBody.setTransform(Trans);

    Material material = new Material(white, black, white, white, 64);
    Appearance appearance = new Appearance();
    appearance.setMaterial(material);

    TG = new TransformGroup();
    Vector.set(0.0f, 1.5f, 0.0f);
    Trans.set(Vector);
    TG.setTransform(Trans);
    
    cylinder = new Cylinder(0.65f, 3.0f, appearance);
    TG.addChild(cylinder);

    robotBody.addChild(TG);

    RobotRigthArm = new TransformGroup();
    RobotRigthArm.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    RobotRigthArm.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    Vector.set(-0.95f, 2.9f, -0.2f);
    Trans.set(Vector);
    RobotRigthArm.setTransform(Trans);

    sphere = new Sphere(0.20f, appearance);
    RobotRigthArm.addChild(sphere);

    TG = new TransformGroup();

    Vector.set(-0.0f, -0.75f, 0.0f);
    Trans.set(Vector);
    TG.setTransform(Trans);
    
    cylinder = new Cylinder(0.2f, 1.5f, appearance);
    TG.addChild(cylinder);

    RobotRigthArm.addChild(TG);

    robotBody.addChild(RobotRigthArm);

    RobotLeftArm = new TransformGroup();
    RobotLeftArm.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    RobotLeftArm.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    Vector.set(0.95f, 2.9f, -0.2f);
    Trans.set(Vector);
    RobotLeftArm.setTransform(Trans);

    sphere = new Sphere(0.20f, appearance);
        
    RobotLeftArm.addChild(sphere);

    TG = new TransformGroup();

    Vector.set(0.0f, -0.75f, 0.0f);
    Trans.set(Vector);
    TG.setTransform(Trans);
    
    cylinder = new Cylinder(0.2f, 1.5f, appearance);
    TG.addChild(cylinder);

    RobotLeftArm.addChild(TG);

    robotBody.addChild(RobotLeftArm);

    TG = new TransformGroup();
    Vector.set(0.0f, -0.5f, 0.0f);
    Trans.set(Vector);
    TG.setTransform(Trans);
    
    cylinder = new Cylinder(0.1f, 0.5f, appearance);
    TG.addChild(cylinder);

    robotHead = new TransformGroup();
    Vector.set(0.0f, 3.5f, 0.0f);
    Trans.set(Vector);
    robotHead.setTransform(Trans);

    sphere = new Sphere(0.5f, appearance);
    
    sphere.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    sphere.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

    robotHead.addChild(sphere);

    robotBody.addChild(robotHead);
    
    return robotBody;
  }
  
}
