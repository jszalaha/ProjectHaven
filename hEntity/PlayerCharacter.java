package hEntity;

import hEntity.LayeredAnimation;
import hEntity.LayeredSprite;
import hEntity.SpellEffects;
import hFramework.Tile;
import hFoundation.Game;
import hFoundation.Framework;
import hFoundation.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PlayerCharacter extends Entity{
    
    public static enum Anim {
        WALKING_UP,
        WALKING_DOWN,
        WALKING_LEFT,
        WALKING_RIGHT,
        CASTING_AOE,
        CASTING_TARGET,
        IDLE
    }
    
    public Facing faceState;
    protected Anim animState;
    
    public LayeredSprite idle;
    
    private LayeredAnimation walk;
    private LayeredAnimation castAOE;
    private LayeredAnimation castTarget;
    
    private long castLength;
    private long startCast;
    private long timeSpentCasting;
    
    public static int tileX,tileY;
    
    // PC Assets  //////////////////////////////////////////////////////////////
    private BufferedImage SIs; // shadow
    
    private BufferedImage ASb; // body
    
    
    private BufferedImage ASc; // clothes
    
    
    private BufferedImage ASh; // hair
    
    
    private BufferedImage ASbr; // beard
    
    
    // Constructor /////////////////////////////////////////////////////////////
    public PlayerCharacter(){
        
        super(
                /*sprite*/ null, 
                /*shadow*/ null, 
                /*animation*/ null, 
                /*x*/ 0, 
                /*y*/ 0, 
                /*parentZone*/ null, 
                /*block*/ false);
        
        idle = null;
        walk = null;
        
        faceState = Facing.FORWARD;
        
        x_coor = Framework.frameWidth / 2 - 20;
        y_coor = Framework.frameHeight / 2 - 28;
        tileX = x_coor/Tile.TILE_WIDTH;
        tileY = y_coor/Tile.TILE_HEIGHT-1;
        castLength = 1000;
        startCast = 0;
    }
    
    // Initialize Fields //
    public void Initialize()
    {        
        // Create Idle LayeredSprite //
        this.idle = new LayeredSprite(
                SIs,
                ASb,
                ASc,
                ASh,
                ASbr,
                null,null,null,null,
                40,
                48,
                this.x_coor,
                this.y_coor,
                true,
                0);
        
        
        int y_pos = this.y_coor - 8;
        
        // Create Animations for Each Layer //
        Animation bodyWalk = new Animation(ASb, 40, 48, 4, 83, 58, true, this.x_coor,
                y_pos, 0);
        Animation clothesWalk = new Animation(ASc, 40, 48, 4, 83, 58, true, this.x_coor,
                y_pos, 0);
        Animation hairWalk = new Animation(ASh, 40, 48, 4, 83, 58, true, this.x_coor,
                y_pos, 0);
        Animation beardWalk = new Animation(ASbr, 40, 48, 4, 83, 58, true, this.x_coor,
                y_pos, 0);
        
        // Create Layered Animation // 
        walk = new LayeredAnimation();
        walk.shadow = idle.shadow;
        walk.animSetList.add(bodyWalk);
        walk.animSetList.add(clothesWalk);
        walk.animSetList.add(hairWalk);
        walk.animSetList.add(beardWalk);
        
        // TO DO
        // INITIALIZE CASTING L-ANIMATION
    }
    
    // Load Images //
    public void LoadContent(){
        try {            
            // Load Image Sheets for PC ////////////////////////////////////////
            // Shadow
            URL Si1s_Url = this.getClass().getResource(
                    "/resources/assets/NPC/Shadow_1x1.png");
            SIs = ImageIO.read(Si1s_Url);
            // Body
            URL Ai1b_Url = this.getClass().getResource(
                    "/resources/assets/NPC/WalkAnimation/AnimSheet_Walk_BODY.png");
            ASb = ImageIO.read(Ai1b_Url);            
            
            // Clothes
            URL Ai1c_Url = this.getClass().getResource(
                    "/resources/assets/NPC/WalkAnimation/AnimSheet_Walk_CLOTHES.png");
            ASc = ImageIO.read(Ai1c_Url);            
            
            // Hair
            URL Ai1h_Url = this.getClass().getResource(
                    "/resources/assets/NPC/WalkAnimation/AnimSheet_Walk_HAIR.png");
            ASh = ImageIO.read(Ai1h_Url);
            
            // Beard
            URL Ai1br_Url = this.getClass().getResource(
                    "/resources/assets/NPC/WalkAnimation/AnimSheet_Walk_BEARD.png");
            ASbr = ImageIO.read(Ai1br_Url);       
            
        } catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Update(){
        if(!Zone.movingUp &&
                !Zone.movingDown &&
                !Zone.movingLeft &&
                !Zone.movingRight &&
                animState != Anim.CASTING_AOE)
        {
            animState = Anim.IDLE;
        }
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_E)){
            if(animState != Anim.CASTING_AOE){
                animState = Anim.CASTING_AOE;
                startCast = System.currentTimeMillis();
                timeSpentCasting = 0;
            }
            if(animState == Anim.CASTING_AOE) {
                timeSpentCasting += System.currentTimeMillis() - startCast;
            }
        }
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_W)){
            if(faceState != Facing.FORWARD){
                faceState = Facing.FORWARD;
            }
            animState = Anim.WALKING_UP;
        }
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_S)){
            if(faceState != Facing.BACK){
                faceState = Facing.BACK;
            }            
            animState = Anim.WALKING_DOWN;
        }
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_A)){
            if(faceState != Facing.LEFT){
                faceState = Facing.LEFT;
            }            
            animState = Anim.WALKING_LEFT;
        }
        
        if(Canvas.keyboardKeyState(KeyEvent.VK_D)){
            if(faceState != Facing.RIGHT){
                faceState = Facing.RIGHT;
            }            
            animState = Anim.WALKING_RIGHT;
        }
    }
    
    public void Draw(Graphics2D g2d){
        
        this.Update();
        try {
            switch(animState){
                case WALKING_UP:
                    for(int i = 0; i < walk.animSetList.size(); i++){
                        walk.animSetList.get(i).loop = true;                        
                    }
                    walk.Draw(g2d,Facing.FORWARD);
                    break;
                case WALKING_DOWN:
                    for(int i = 0; i < walk.animSetList.size(); i++){
                        walk.animSetList.get(i).loop = true;                        
                    }
                    walk.Draw(g2d,Facing.BACK);
                    break;
                case WALKING_LEFT:
                    for(int i = 0; i < walk.animSetList.size(); i++){
                        walk.animSetList.get(i).loop = true;                        
                    }
                    walk.Draw(g2d,Facing.LEFT);
                    break;
                case WALKING_RIGHT:
                    for(int i = 0; i < walk.animSetList.size(); i++){
                        walk.animSetList.get(i).loop = true;
                    }
                    walk.Draw(g2d,Facing.RIGHT);
                    break;
                case CASTING_AOE:
                    /*for(int i = 0; i < castAOE.animSetList.size(); i++){
                        castAOE.animSetList.get(i).loop = true;
                    }*/
                   if(timeSpentCasting > castLength) {
                       if(Game.world.currentZone.tileSet.tile
                                .get(tileX).get(tileY-1).status 
                        != Tile.TileStatus.BURNING){
                            Game.world.addEffect(SpellEffects.Type.FIRE, 
                                    this.x_coor, 
                                    this.y_coor - Tile.TILE_HEIGHT);
                       }
                        this.animState = Anim.IDLE;
                    }
                    break;
                case IDLE:
                    for(int i = 0; i < walk.animSetList.size(); i++){
                        walk.animSetList.get(i).loop = false;
                    }
                    try{
                    /*for(int i = 0; i < castAOE.animSetList.size(); i++){
                        castAOE.animSetList.get(i).loop = false;
                    }*/
                    }catch(NullPointerException e){}
                    switch(faceState){
                    case FORWARD:
                            idle.Draw(g2d,Facing.FORWARD,2,1);
                        break;
                    case BACK:
                            idle.Draw(g2d,Facing.BACK,1,1);
                        break;
                    case LEFT:
                            idle.Draw(g2d,Facing.LEFT,3,1);
                        break;
                    case RIGHT:
                            idle.Draw(g2d,Facing.RIGHT,4,1);
                        break;
                    default:
                            idle.Draw(g2d,Facing.BACK,2,1);
                        break;
                    }
                    break;
            }
        } catch (NullPointerException e){}
    }
}
