package Model;


/* Clase para el manejo del viewport usando las teclas direccionales
*/

import java.awt.AWTEvent;
import java.awt.event.*;
import java.util.Enumeration;

import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.vp.*;


/**
 *
 * @author Carlos
 * Clase para el comportamiento del viewport usando las teclas direccionales del teclado
 */
public class KeyBehavior extends ViewPlatformBehavior
{
  private static final double ROT_AMT = Math.PI / 36.0; 
  private static final double MOVE_STEP = 0.2;

  // hardwired movement vectors
  private static final Vector3d FWD = new Vector3d(0,0,-MOVE_STEP);
  private static final Vector3d BACK = new Vector3d(0,0,MOVE_STEP);
  private static final Vector3d LEFT = new Vector3d(-MOVE_STEP,0,0);
  private static final Vector3d RIGHT = new Vector3d(MOVE_STEP,0,0);
  private static final Vector3d UP = new Vector3d(0,MOVE_STEP,0);
  private static final Vector3d DOWN = new Vector3d(0,-MOVE_STEP,0);

  // key names
  private int forwardKey = KeyEvent.VK_UP;
  private int backKey = KeyEvent.VK_DOWN;
  private int leftKey = KeyEvent.VK_LEFT;
  private int rightKey = KeyEvent.VK_RIGHT;

  private WakeupCondition keyPress;

  // for repeated calcs
  private Transform3D t3d = new Transform3D();
  private Transform3D toMove = new Transform3D();
  private Transform3D toRot = new Transform3D();


  private int upMoves = 0;

/**
   * Constructor de la clase
   */
  public KeyBehavior()
  { keyPress = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);  } 

/**
   * Inicializaci?n de la clase
   */
  public void initialize()
  {  wakeupOn( keyPress );  }

/**
   * Creaci?n del estimulo por cada vez que el usuario presione una tecla
   * @param criteria criterio
   */
  public void processStimulus(Enumeration criteria)
  {
    WakeupCriterion wakeup;
    AWTEvent[] event;

    while( criteria.hasMoreElements() ) {
      wakeup = (WakeupCriterion) criteria.nextElement();
      if( wakeup instanceof WakeupOnAWTEvent ) {
        event = ((WakeupOnAWTEvent)wakeup).getAWTEvent();
        for( int i = 0; i < event.length; i++ ) {
          if( event[i].getID() == KeyEvent.KEY_PRESSED )
            processKeyEvent((KeyEvent)event[i]);
        }
      }
    }
    wakeupOn( keyPress );
  } 

/**
   * Evento de presionar una tecla
   * @param eventKey evento
   */
  private void processKeyEvent(KeyEvent eventKey)
  {
    int keyCode = eventKey.getKeyCode();
    if(eventKey.isAltDown())
      altMove(keyCode);
    else
      standardMove(keyCode);
  } 

/**
   * Generacion del movimiento en el ambiente virtual
   * @param keycode codigo de tecla
   */
  private void standardMove(int keycode)
  { if(keycode == forwardKey)
      doMove(FWD);
    else if(keycode == backKey)
      doMove(BACK);
    else if(keycode == leftKey)
      rotateY(ROT_AMT);
    else if(keycode == rightKey)
      rotateY(-ROT_AMT);
  } 

/**
   * Movimiento hacia adelante o hacia atr?s
   * @param keycode c?digo de la tecla
   */
  private void altMove(int keycode)
  { if(keycode == forwardKey) {
      upMoves++;
      doMove(UP);
    }
    else if(keycode == backKey) {
      if (upMoves > 0) { 
        upMoves--;
        doMove(DOWN);
      }
    }
    else if(keycode == leftKey)
      doMove(LEFT);
    else if(keycode == rightKey)
      doMove(RIGHT);
  }  

/**
   * rotaci?n sobre el eje Y
   * @param radians radio de giro
   */
  private void rotateY(double radians)
  { targetTG.getTransform(t3d);  
    toRot.rotY(radians);
    t3d.mul(toRot);
    targetTG.setTransform(t3d);
  } 

/**
   * Movimiento en el plano
   * @param theMove movimiento a efectuar
   */
  private void doMove(Vector3d theMove)
  { targetTG.getTransform(t3d);
    toMove.setTranslation(theMove);
    t3d.mul(toMove);
    targetTG.setTransform(t3d);
  }


} 
