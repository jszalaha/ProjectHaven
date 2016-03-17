package hFramework;

import hFoundation.Framework;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;

public class FieldMenu extends JPanel {
    
    protected OutputArea outputArea;
    GridBagConstraints gbC;
    
    public FieldMenu() {
        super(new GridBagLayout());
        gbC = new GridBagConstraints();
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setOpaque(false);
        this.outputArea = new OutputArea();        
    }
    
    public void LoadContent() {
        this.outputArea.loadContent();
    }
    
    public void Initialize() {
        this.outputArea.initialize();
        gbC.fill = GridBagConstraints.HORIZONTAL;
        gbC.gridwidth = 40;
        gbC.gridheight = 28;
        gbC.gridx = 1;
        gbC.gridy = 1;
        this.add(outputArea,gbC);
    }
    
    public void Draw(Graphics2D g2d){
        this.paintComponent(g2d);
        this.outputArea.Draw(g2d);
    }
    @Override
    public void paintComponents(Graphics g2d) {
        super.paintComponents(g2d);
    }
    
    protected class OutputArea extends JPanel {
        
        protected BufferedImage background;
        protected int x;
        protected int y;
        protected int width;
        protected int height;
        protected boolean visible;
        
        protected JTextArea output;
        
        protected OutputArea() {
            super();
            this.output = new JTextArea("TEST");
        }
        
        protected void initialize() {
            //this.setBackground(new Color(1,1,1,(float)0.01));
            this.width = this.background.getWidth();
            this.height = this.background.getHeight();
            Dimension d = new Dimension(width,height);
            this.setPreferredSize(d);
            this.output.setPreferredSize(d);
            this.add(output);
            this.setOpaque(true);
            this.output.setOpaque(true);
            x = 10;
            y = Framework.frameHeight - this.height - 10;            
            
            this.hideMenu();
        }
        
        protected void loadContent() {
            try {
                URL output_url = this.getClass().getResource(
                    "/resources/menus/Output.png");
                this.background = ImageIO.read(output_url);
            } catch (IOException ex) {
                Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        protected void hideMenu() {
            this.setVisible(false);
            this.visible = false;
        }
        protected void showMenu() {
            this.setVisible(true);
            this.visible = true;
        }
        
        protected void Draw(Graphics2D g2d) {
            if(this.visible){
                //g2d.drawImage(background, x, y, null);
                this.paintComponent(g2d);
            }
        }
        
        @Override
        protected void paintComponent(Graphics g2d) {
            super.paintComponents(g2d);
        }
    }
}
