package hFoundation;

import hEntity.Zone;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.util.*;

public abstract class Canvas extends JPanel implements KeyListener, MouseListener {
    
    private static boolean[] keyboardState = new boolean[525];
    private static boolean[] mouseState = new boolean[3];
    
    public Canvas() {
        
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setBackground(Color.black);
        
        if(false)
        {
            BufferedImage cursorImg = new BufferedImage(
                    16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    cursorImg, new Point(0, 0), null);
            this.setCursor(cursor);
        }
        
        this.addKeyListener(this);
        this.addMouseListener(this); 
        
    }
    
    public abstract void Draw(Graphics2D g2d);
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;        
        super.paintComponent(g2d);        
        Draw(g2d);
    }
    
    // Keyboard ////////////////////////////////////////////////////////////////
    public static boolean keyboardKeyState(int key)
    {
        return keyboardState[key];
    }
    
    // Methods of the keyboard listener.
    @Override
    public void keyPressed(KeyEvent e) 
    {
        // Limits movement input to one key at a time
        if(e.getKeyCode() == KeyEvent.VK_W ||
                e.getKeyCode() == KeyEvent.VK_S ||
                e.getKeyCode() == KeyEvent.VK_A ||
                e.getKeyCode() == KeyEvent.VK_D)
        {
            if(!Zone.moveActionInProgress){
                keyboardState[KeyEvent.VK_A] = false;
                keyboardState[KeyEvent.VK_S] = false;
                keyboardState[KeyEvent.VK_W] = false;
                keyboardState[KeyEvent.VK_D] = false;
                keyboardState[e.getKeyCode()] = true;
            }
        }
        else {
            if(!Zone.moveActionInProgress){
                keyboardState[e.getKeyCode()] = true;
            }
        }
    }
    
    @Override
    public synchronized void keyReleased(KeyEvent e)
    {           
        keyboardState[e.getKeyCode()] = false;
        keyReleasedFramework(e);
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    
    public abstract void keyReleasedFramework(KeyEvent e);
    
    // Mouse ///////////////////////////////////////////////////////////////////
    public static boolean mouseButtonState(int button)
    {
        return mouseState[button - 1];
    }
    
    // Sets mouse key status.
    private void mouseKeyStatus(MouseEvent e, boolean status)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
            mouseState[0] = status;
        else if(e.getButton() == MouseEvent.BUTTON2)
            mouseState[1] = status;
        else if(e.getButton() == MouseEvent.BUTTON3)
            mouseState[2] = status;
    }
    
    // Methods of the mouse listener.
    @Override
    public void mousePressed(MouseEvent e)
    {
        mouseKeyStatus(e, true);
    }
    
    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseKeyStatus(e, false);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { }
    
    @Override
    public void mouseEntered(MouseEvent e) { }
    
    @Override
    public void mouseExited(MouseEvent e) { }
}
