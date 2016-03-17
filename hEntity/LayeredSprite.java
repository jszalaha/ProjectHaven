package hEntity;

import hFoundation.Canvas;
import hFoundation.Framework;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LayeredSprite {
    
    protected ArrayList<Sprite> spriteLayer;
    public Sprite shadow;
    
    public LayeredSprite(
            BufferedImage shadowImg,
            BufferedImage l1,
            BufferedImage l2,
            BufferedImage l3,
            BufferedImage l4,
            BufferedImage l5,
            BufferedImage l6,
            BufferedImage l7,
            BufferedImage l8,
            int fw, 
            int fh, 
            int x,
            int y, 
            boolean vis, 
            long del)
    {        
        spriteLayer = new ArrayList<>();
        try{
            shadow = new Sprite(shadowImg,
                    shadowImg.getWidth(),
                    shadowImg.getHeight(),
                    x,
                    y+fh-shadowImg.getHeight(),
                    true,0);
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l1,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l2,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l3,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l4,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l5,fw,fh,x,y-8,true,0));
        }   catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l6,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l7,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
        try{
            spriteLayer.add(new Sprite(l8,fw,fh,x,y-8,true,0));
        }catch(NullPointerException ex){}
    }
    
    public void Update() {
    }

    public void Draw(Graphics2D g2d,Entity.Facing face,int row,int col){       
        
        switch(face){
            
            case BACK:
                try{
                    shadow.Draw(g2d, 1, 1);
                }catch(NullPointerException e){}
                for(int i = 0; i < spriteLayer.size(); i++){
                    try {
                        spriteLayer.get(i).Draw(g2d,row,col);
                    } catch(NullPointerException e){}
                }
                break;
            case FORWARD:
                try{
                    shadow.Draw(g2d, 1, 1);
                }catch(NullPointerException e){}
                for(int i = 0; i < spriteLayer.size(); i++){
                    try {
                        spriteLayer.get(i).Draw(g2d,row,col);
                    } catch(NullPointerException e){}
                }
                break;
            case LEFT:
                try{
                    shadow.Draw(g2d, 1, 1);
                }catch(NullPointerException e){}
                for(int i = 0; i < spriteLayer.size(); i++){
                    try {
                        spriteLayer.get(i).Draw(g2d,row,col);
                    } catch(NullPointerException e){}
                }
                break;
            case RIGHT:
                try{
                    shadow.Draw(g2d, 1, 1);
                }catch(NullPointerException e){}
                for(int i = 0; i < spriteLayer.size(); i++){
                    try {
                        spriteLayer.get(i).Draw(g2d,row,col);
                    } catch(NullPointerException e){}
                }
                break;
            default:
                try{
                    shadow.Draw(g2d, 1, 1);
                }catch(NullPointerException e){}
                for(int i = 0; i < spriteLayer.size(); i++){
                    try {
                        spriteLayer.get(i).Draw(g2d,row,col);
                    } catch(NullPointerException e){}
                }
                break;
                
        }
        
    }
        
}
