package hEntity;

import hFramework.Tile;
import java.lang.String;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    
    public static enum Facing {
        FORWARD,
        BACK,
        LEFT,
        RIGHT
    }
    
    public Sprite sprite;
    public Animation animation;
    public Sprite shadow;
    public Sprite smallSprite;
    public String label;
    public String description;
    
    // positional coordinates relative to the zone
    // in which they are being drawn
    public int x_coor, y_coor;
    
    public boolean block;
    
    public Zone parentZone;
    
    public Entity(Sprite sprite, Sprite shadow, Sprite smallSprite, int x, int y, Zone parent, boolean block) {
        try {
            this.sprite = sprite;
            this.shadow = shadow;
            this.smallSprite = smallSprite;
            this.x_coor = x;
            this.y_coor = y;
            this.block = block; 
            this.animation = null;
            this.parentZone = parent;
            
        } catch (NullPointerException e){}
        
        // if a parent zone is specified when the entity
        // is created then it will automatically add it
        // to the zone at the specified x and y coords
        if(parentZone != null) {
           parentZone.drawList.add(this);
           this.block(this.block);
        }
        
        this.label = "";
        this.description = "";
    }
    public Entity(Animation anim, Sprite base, int x, int y, Zone parent, boolean block) {
        try {
            this.animation = anim;
            this.shadow = base;
            this.smallSprite = null;
            this.x_coor = x;
            this.y_coor = y;
            this.block = block; 
            this.parentZone = parent;
            this.sprite = null;
        } catch (NullPointerException e){}
        
        // if a parent zone is specified when the entity
        // is created then it will automatically be added
        // to the zone at the specified x and y coords
        if(parentZone != null) {
           parentZone.drawList.add(this);
           this.block(this.block);
        }
        
        this.label = "";
        this.description = "";
    }
    
    public void block(boolean block) {
        this.block = block;
        if(this.block) {
                parentZone.tileSet.block(this.x_coor/Tile.TILE_WIDTH, this.y_coor/Tile.TILE_HEIGHT,
                    this.shadow.frameWidth, this.shadow.frameHeight);
        } else {
            parentZone.tileSet.unblock(this.x_coor/Tile.TILE_WIDTH, this.y_coor/Tile.TILE_HEIGHT,
                    this.shadow.frameWidth, this.shadow.frameHeight);
        }
    }
    
    public void changeCoordinates(int x, int y){
        this.x_coor = x;
        this.y_coor = y;
    }
    
    public Rectangle getBounds(){
        Rectangle box;
        
        box = new Rectangle(
                x_coor,
                y_coor,
                this.shadow.frameWidth,
                this.shadow.frameHeight);
        
        return box;
    }
    
    public void DrawSprite(Graphics2D g2d, int i, int j){
        if(this.shadow != null){
        this.shadow.DrawXY(g2d, 
                x_coor, 
                y_coor, 
                i, j);
        }
        if(this.sprite != null){
            this.sprite.DrawXY(g2d, 
                    x_coor-(this.sprite.frameWidth-this.shadow.frameWidth)/2,
                    y_coor-(this.sprite.frameHeight-this.shadow.frameHeight), i, j);
        }
        if(this.animation != null){
            this.animation.DrawXY(g2d,
                    x_coor-(this.animation.frameWidth-this.shadow.frameWidth)/2,
                    y_coor-(this.animation.frameHeight-this.shadow.frameHeight),
                    1);
        }
    }
    public void DrawTile(){
        
    }
}
