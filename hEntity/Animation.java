package hEntity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {
    
    private Entity.Facing face;
    
    private BufferedImage animImage;
    public int frameWidth;
    public int frameHeight;
    private int frameCount;
    private int currentFrame;
    private long frameTimeLat;
    private long frameTimeVert;
    private long startFrameTime;
    private long nextFrameTimeLat;
    private long nextFrameTimeVert;
    private long showDelay;
    private long creationTime;
    
    public boolean loop;
    public boolean active;
    
    private int x_coor;
    private int y_coor;    
    private int start_frame_x;
    private int end_frame_x;
    private int start_frame_y;
    private int end_frame_y;
    
    public Animation(
            BufferedImage animImage, 
            int frameWidth, 
            int frameHeight, 
            int numberOfFrames, 
            long frameTimeLat,
            long frameTimeVert,
            boolean loop, 
            int x, 
            int y, 
            long showDelay)
    {
        this.animImage = animImage;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameCount = numberOfFrames;
        this.frameTimeLat = frameTimeLat;
        this.frameTimeVert = frameTimeVert;
        this.loop = loop;
        this.x_coor = x;
        this.y_coor = y;        
        this.showDelay = showDelay;
        this.creationTime = System.currentTimeMillis();
        this.start_frame_x = 0;
        this.end_frame_x = frameWidth;
        this.start_frame_y = 0;
        this.end_frame_y = frameHeight;
        this.startFrameTime = System.currentTimeMillis() + showDelay;
        this.nextFrameTimeLat = startFrameTime + this.frameTimeLat;
        this.nextFrameTimeVert = startFrameTime + this.frameTimeVert;
        this.currentFrame = 0;
        this.active = true;
    }
    
    public void changeCoordinates(int x, int y)
    {
        this.x_coor = x;
        this.y_coor = y;
    }
    
    public void setAnimation(int i){
        this.start_frame_y = this.frameHeight * (i-1);
    }
    
    private void Update(){
        if(face == Entity.Facing.LEFT || face == Entity.Facing.RIGHT){
            if(nextFrameTimeLat <= System.currentTimeMillis()){

                currentFrame++;

                if(currentFrame >= frameCount){

                    currentFrame = 0;

                    if(!loop){
                        active = false;
                    }
                }

                start_frame_x = currentFrame * frameWidth;
                end_frame_x = start_frame_x + frameWidth;
                end_frame_y = start_frame_y + frameHeight;

                startFrameTime = System.currentTimeMillis();
                nextFrameTimeLat = startFrameTime + frameTimeLat;
            }
        }
        else{
            if(nextFrameTimeVert <= System.currentTimeMillis()){

                currentFrame++;

                if(currentFrame >= frameCount){

                    currentFrame = 0;

                    if(!loop){
                        active = false;
                    }
                }

                start_frame_x = currentFrame * frameWidth;
                end_frame_x = start_frame_x + frameWidth;
                end_frame_y = start_frame_y + frameHeight;

                startFrameTime = System.currentTimeMillis();
                nextFrameTimeVert = startFrameTime + frameTimeVert;
            }
        }
    }
    
    public void Draw(Graphics2D g2d, int i){
        this.start_frame_y = this.frameHeight * (i-1);
        this.Update();
        
        if(this.creationTime + this.showDelay <= System.currentTimeMillis()){
            g2d.drawImage(
                    animImage, 
                    x_coor, 
                    y_coor, 
                    x_coor + frameWidth, 
                    y_coor + frameHeight, 
                    start_frame_x, 
                    start_frame_y, 
                    end_frame_x, 
                    end_frame_y, 
                    null);
        }
    }
    public void DrawXY(Graphics2D g2d, int x, int y, int i){
        this.start_frame_y = this.frameHeight * (i-1);
        this.Update();
        
        if(this.creationTime + this.showDelay <= System.currentTimeMillis()){
            g2d.drawImage(
                    animImage, 
                    x, 
                    y, 
                    x + frameWidth, 
                    y + frameHeight, 
                    start_frame_x, 
                    start_frame_y, 
                    end_frame_x, 
                    end_frame_y, 
                    null);
        }
    }
}
