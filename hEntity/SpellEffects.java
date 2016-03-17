package hEntity;

import hFoundation.Framework;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class SpellEffects {
    public static enum Type {
        FIRE
    }
    
    private static Animation Fire_Ground;
    private static Sprite Burnt_Ground;    
    
    private BufferedImage groundFireImg;
    private BufferedImage burntGroundImg;  
    
    public SpellEffects(){
        Fire_Ground = null;
        Burnt_Ground = null;
    }
    
    public Entity newEffect(Type type, int x, int y) {
        Entity effect = null;
        switch(type){
            case FIRE:
                effect = new Entity(Fire_Ground,Burnt_Ground,x,y,null,false);
                break;
            default:
                break;              
        }
        return effect;
    }
    public void Initialize() {
        Fire_Ground = new Animation(groundFireImg,
            40, 
            56, 
            4, 
            83,
            83,
            true, 
            0, 
            0, 
            0);
            
        Burnt_Ground = new Sprite(burntGroundImg,
            40, 
            28, 
            0,
            0, 
            true, 
            0);
    }
    
    public void LoadContent() {
        try {
            
            URL groundFireImg_Url = this.getClass().getResource(
                "/resources/assets/groundFire_anim.png");
            groundFireImg = ImageIO.read(groundFireImg_Url);
            
            URL burntGroundImg_Url = this.getClass().getResource(
                "/resources/assets/scorchedGround.png");
            burntGroundImg = ImageIO.read(burntGroundImg_Url);
            
        }catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);}
    }
}
