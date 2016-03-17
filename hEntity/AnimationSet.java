package hEntity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimationSet {
    
    public Animation animBack;
    public Animation animForward;
    public Animation animLeft;
    public Animation animRight;

    
    public AnimationSet(){
        animBack = null;
        animForward = null;
        animLeft = null;
        animRight = null;
    }
    
    public boolean setAnimation(Animation a, int animNum){
        
        boolean success = false;
        
        switch(animNum){
            case 1:
                animBack = a;
                success = true;
                break;
            case 2:
                animForward = a;
                success = true;
                break;
            case 3:
                animLeft = a;
                success = true;
                break;
            case 4:
                animRight = a;
                success = true;
                break;
            default:
                success = false;
                break;
        }
        
        return success;
    }
    
    public boolean createAnimation(
            BufferedImage animImage, 
            int fW, 
            int fH, 
            int numOfFr, 
            long fTimeL,
            long fTimeV,
            boolean loop, 
            int x, 
            int y, 
            long delay,
            int animNum) 
    {        
        boolean success = false;
        
        switch(animNum){
            case 1:
                animBack = new Animation(animImage,fW,fH,numOfFr,fTimeL,fTimeV,loop,x,y,delay);
                success = true;
                break;
            case 2:
                animForward = new Animation(animImage,fW,fH,numOfFr,fTimeL,fTimeV,loop,x,y,delay);
                success = true;
                break;
            case 3:
                animLeft = new Animation(animImage,fW,fH,numOfFr,fTimeL,fTimeV,loop,x,y,delay);
                success = true;
                break;
            case 4:
                animRight = new Animation(animImage,fW,fH,numOfFr,fTimeL,fTimeV,loop,x,y,delay);
                success = true;
                break;
            default:
                break;
        }
        
        return success;
    }
}
