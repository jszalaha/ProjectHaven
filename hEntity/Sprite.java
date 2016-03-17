package hEntity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Sprite {
    
    protected BufferedImage background;
    public int frameWidth;
    public int frameHeight;
    protected int frame_row;
    protected int frame_col;
    public int x_coor;
    public int y_coor;
    public boolean visible;
    protected long creationTime;
    protected long showDelay;
    
    public Sprite(
            BufferedImage bkgd, 
            int fWidth, 
            int fHeight, 
            int x,
            int y, 
            boolean visible, 
            long delay) 
    {
        this.background = bkgd;
        this.frameWidth = fWidth;
        this.frameHeight = fHeight;
        this.frame_row = 0;
        this.frame_col = 0;
        this.x_coor = x;
        this.y_coor = y;
        this.visible = visible;
        this.showDelay = delay;
        this.creationTime = System.currentTimeMillis();
    }
    
    public void changeCoordinates(int x, int y)
    {
        this.x_coor = x;
        this.y_coor = y;
    }
    
    public void setSprite(int row, int col){
        frame_row = row-1;
        frame_col = col-1;
    }
    
    public void Draw(Graphics2D g2d, int i, int j)
    {   
        // ROW DETERMINES DIRECTION FACING
        this.frame_row = i-1;
        // COLUMN IS ALWAYS FIRST COLUMN WHEN USING ANIMATION SHEET
        this.frame_col = j-1;
        
        if(this.creationTime + this.showDelay <= System.currentTimeMillis()){
            g2d.drawImage(background, 
                    x_coor, 
                    y_coor,
                    x_coor+frameWidth,
                    y_coor+frameHeight,
                    frameWidth*frame_col,
                    frameHeight*frame_row,
                    frameWidth*frame_col+frameWidth,
                    frameHeight*frame_row+frameHeight,
                    null);
        }
    }
    public void DrawXY(Graphics2D g2d, int x, int y, int i, int j){
        // ROW DETERMINES DIRECTION FACING
        this.frame_row = i-1;
        // COLUMN IS ALWAYS FIRST COLUMN WHEN USING ANIMATION SHEET
        this.frame_col = j-1;
        
        if(this.creationTime + this.showDelay <= System.currentTimeMillis()){
            g2d.drawImage(background, 
                    x, 
                    y,
                    x+frameWidth,
                    y+frameHeight,
                    frameWidth*frame_col,
                    frameHeight*frame_row,
                    frameWidth*frame_col+frameWidth,
                    frameHeight*frame_row+frameHeight,
                    null);
        }
    }
}