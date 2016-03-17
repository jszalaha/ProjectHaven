package hEntity;

import hFramework.TileSet;
import hFoundation.Game;
import hFoundation.Canvas;
import hFramework.Tile;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;

public abstract class Zone {
    
    public static final int SQUARE_WIDTH = 40;
    public static final int SQUARE_HEIGHT = 28;
    
    public static boolean movingUp;
    public static boolean movingDown;
    public static boolean movingLeft;
    public static boolean movingRight;
    public static boolean moveActionInProgress;
    protected static int distanceMoved;
    
    protected int Width;
    protected int Height;
    public int x_coor;
    public int y_coor;
    
    protected Sprite Background;  
    
    public TileSet tileSet;
    
    public LinkedList<Entity> drawList;
    
    public LinkedList<Zone> Adj;
    
    public Zone()
    {
       drawList = new LinkedList<>();
       Adj = new LinkedList<>();
    }
    
    // ABSTRACTS //////////////////////////////////
    public abstract void setReturnCoord(int port);
    
    public abstract void Initialize();
    
    public abstract void LoadContent();
    
    public abstract void Populate();
    
    // INIT HELPTER FUNCTS //////////////////////////
    public void initTileSet() {
        try{
        this.tileSet = new TileSet(this.Background.frameWidth/Tile.TILE_WIDTH+1,
                                   this.Background.frameHeight/Tile.TILE_HEIGHT+1);
        }catch(NullPointerException ex){}
    }
    
    public void initBackground(BufferedImage backgroundImg) {
        this.Background = new Sprite(backgroundImg, 
                backgroundImg.getWidth(), 
                backgroundImg.getHeight(), 
                0, 
                0, 
                true, 
                0);
    }
    
    // SUB-ROUTINES ////////////////////////////////////////////
    
    // Updates all static entities' positions
    // relative to the nex x, y coordinates for
    // the background image
    protected void Shift(int x, int y){
        if(this.Background.x_coor == x && this.Background.y_coor == y){}
        else{
            //this.tileSet.clear();
            int oldx,oldy,newx,newy;
            oldx = this.Background.x_coor;
            oldy = this.Background.y_coor;
            this.Background.changeCoordinates(x, y);
            
            if(x%40==0){
                if(oldx > x)
                    Game.hero.tileX++;
                else if(oldx < x)
                    Game.hero.tileX--;
            }
            if(y%28 ==0){
                if(oldy > y)
                    Game.hero.tileY++;
                else if(oldy < y)
                    Game.hero.tileY--;
            }
            
            //Walls.changeCoordinates(x, y);
            try{
                for(int i = 0; i < this.drawList.size(); i++){
                    newx = this.drawList.get(i).x_coor - oldx + x;
                    newy = this.drawList.get(i).y_coor - oldy + y;
                    this.drawList.get(i).changeCoordinates(newx, newy);
                    //this.tileSet.block(newx, newy, 
                       // this.drawList.get(i).shadow.frameWidth, 
                       // this.drawList.get(i).shadow.frameHeight);
                }
            }catch(NullPointerException ex){}
        }
    }
    
    protected void Jump(int x, int y){
        if(this.Background.x_coor == x && this.Background.y_coor == y){}
        else{
            //this.tileSet.clear();
            int oldx,oldy,newx,newy;
            oldx = this.Background.x_coor;
            oldy = this.Background.y_coor;
            this.Background.changeCoordinates(x, y);
            
            //Walls.changeCoordinates(x, y);
            try{
                for(int i = 0; i < this.drawList.size(); i++){
                    newx = this.drawList.get(i).x_coor - oldx + x;
                    newy = this.drawList.get(i).y_coor - oldy + y;
                    this.drawList.get(i).changeCoordinates(newx, newy);
                    //this.tileSet.block(newx, newy, 
                       // this.drawList.get(i).shadow.frameWidth, 
                       // this.drawList.get(i).shadow.frameHeight);
                }
            }catch(NullPointerException ex){}
        }
    }
    
    public void insertEntity(Entity e){
        int i = 0;
        while(i < this.drawList.size() && this.drawList.get(i).y_coor <= e.y_coor){
            i++;
        }
        if(i == this.drawList.size()){ 
            this.drawList.addLast(e);
        } else {
            this.drawList.add(i-1,e);
        }
        e.parentZone = this;
    }
    
    public boolean Collision() {
        boolean flag = false;
        if(Canvas.keyboardKeyState(KeyEvent.VK_W)){
            if(this.tileSet.tile.get(Game.hero.tileX).get(Game.hero.tileY-1).portal < 0)
                flag = this.tileSet.tile.get(Game.hero.tileX).get(Game.hero.tileY-1).blocked;
        }
        if(Canvas.keyboardKeyState(KeyEvent.VK_S)){
            if(this.tileSet.tile.get(Game.hero.tileX).get(Game.hero.tileY+1).portal < 0)
                flag = this.tileSet.tile.get(Game.hero.tileX).get(Game.hero.tileY+1).blocked;
        }
        if(Canvas.keyboardKeyState(KeyEvent.VK_A)){
            if(this.tileSet.tile.get(Game.hero.tileX-1).get(Game.hero.tileY).portal < 0)
                flag = this.tileSet.tile.get(Game.hero.tileX-1).get(Game.hero.tileY).blocked;
        }
        if(Canvas.keyboardKeyState(KeyEvent.VK_D)){
            if(this.tileSet.tile.get(Game.hero.tileX+1).get(Game.hero.tileY).portal < 0)
                flag = this.tileSet.tile.get(Game.hero.tileX+1).get(Game.hero.tileY).blocked;
        }
        return flag;
    }
    
    // UPDATE AND DRAW /////////////////////////////////////////////////
    private void Update(){
        
            // Calculating speed for moving up
            if(Canvas.keyboardKeyState(KeyEvent.VK_W)){
                if(!movingUp && !this.Collision()){
                    movingUp = moveActionInProgress = true;
                    y_coor += 2;
                    distanceMoved +=2;
                } else if(distanceMoved%SQUARE_HEIGHT != 0){
                    y_coor += 2;
                    distanceMoved +=2;
                } else { movingUp = moveActionInProgress = false; distanceMoved = 0; }
            } else if(movingUp){
                if(distanceMoved%SQUARE_HEIGHT != 0){
                    y_coor += 2;
                    distanceMoved += 2;
                } else { movingUp = moveActionInProgress = false; distanceMoved = 0; }
            }

            // Calculating speed for moving down
            if(Canvas.keyboardKeyState(KeyEvent.VK_S)){
                if(!movingDown && !this.Collision()){
                    movingDown = moveActionInProgress = true;
                    y_coor -= 2;
                    distanceMoved +=2;
                } else if(distanceMoved%SQUARE_HEIGHT != 0){
                    y_coor -= 2;
                    distanceMoved +=2;
                } else { movingDown = moveActionInProgress = false; distanceMoved = 0; }
            } else if(movingDown){
                if(distanceMoved%SQUARE_HEIGHT != 0){
                    y_coor -= 2;
                    distanceMoved += 2;
                } else { movingDown = moveActionInProgress = false; distanceMoved = 0; }
            }

            // Calculating speed for moving to the left
            if(Canvas.keyboardKeyState(KeyEvent.VK_A)){
                if(!movingLeft && !this.Collision()){
                    movingLeft = moveActionInProgress = true;
                    x_coor += 2;
                    distanceMoved +=2;
                } else if(distanceMoved%SQUARE_WIDTH != 0){
                    x_coor += 2;
                    distanceMoved +=2;
                } else { movingLeft = moveActionInProgress = false; distanceMoved = 0; }
            } else if(movingLeft){
                if(distanceMoved%SQUARE_WIDTH != 0){
                    x_coor += 2;
                    distanceMoved += 2;
                } else { movingLeft = moveActionInProgress = false; distanceMoved = 0; }
            }

            // Calculating speed for moving to the right
            if(Canvas.keyboardKeyState(KeyEvent.VK_D)){
                if(!movingRight && !this.Collision()){
                    movingRight = moveActionInProgress = true;
                    x_coor -= 2;
                    distanceMoved +=2;
                } else if(distanceMoved%SQUARE_WIDTH != 0){
                    x_coor -= 2;
                    distanceMoved +=2;
                } else { movingRight = moveActionInProgress = false; distanceMoved = 0; }
            } else if(movingRight){
                if(distanceMoved%SQUARE_WIDTH != 0){
                    x_coor -= 2;
                    distanceMoved += 2;
                } else { movingRight = moveActionInProgress = false; distanceMoved = 0; }
            }
        
        Shift(this.x_coor,this.y_coor);
    }

    public void Draw(Graphics2D g2d){
        this.Update();
        
        Background.Draw(g2d, 1, 1);
        int i = 0;
        try {
            while (i < this.drawList.size() && 
                    this.drawList.get(i).y_coor <= Game.hero.y_coor){
                this.drawList.get(i).DrawSprite(g2d,1,1); 
                i++;
            }
        }catch(NullPointerException ex){}
        Game.hero.Draw(g2d);
        try {
            while(i < this.drawList.size())
            {
                this.drawList.get(i).DrawSprite(g2d, 1, 1);
                i++;
            }
        }catch(NullPointerException ex){}
    }
}
