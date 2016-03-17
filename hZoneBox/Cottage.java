package hZoneBox;

import hEntity.Zone;
import hFramework.Tile;
import hFramework.TileSet;

import hFoundation.Framework;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Cottage extends Zone {
    
    private BufferedImage backgroundImg;
    
    public Cottage() 
    {
        super();
    }
    
    @Override
    public void initTileSet() {
        int j;
        
        try{
        this.tileSet = new TileSet(this.Background.frameWidth/Tile.TILE_WIDTH+1,
                                   this.Background.frameHeight/Tile.TILE_HEIGHT+1);
        }catch(NullPointerException ex){}
        
        for(int i = 0; i < this.Background.frameWidth/Tile.TILE_WIDTH+1; i++){
            this.tileSet.tile.get(i).get(0).blocked = true;
            this.tileSet.tile.get(i).get(1).blocked = true;
            this.tileSet.tile.get(i).get(2).blocked = true;
            this.tileSet.tile.get(i).get(3).blocked = true;
            if(i == 0 || i == 11){
                j = 0;
                while (j<14){
                    this.tileSet.tile.get(i).get(j).blocked = true;
                    j++;
                }
            }
            if(i <= 3 || i >= 5) {
                this.tileSet.tile.get(i).get(13).blocked = true;
            }
            if(i == 5 || i == 6){
                this.tileSet.tile.get(i).get(4).blocked = true;
                this.tileSet.tile.get(i).get(5).blocked = true;
            }
            if(i == 7 || i == 8) {
                this.tileSet.tile.get(i).get(4).blocked = true;
            }
            if(i == 4){
                this.tileSet.tile.get(i).get(13).portal = 0;
            }
        }
    }
    
    @Override
    public void Populate(){
        
    }
    
    @Override
    public void setReturnCoord(int port) {
        this.x_coor = 280;
        this.y_coor = -56;
        this.Jump(x_coor, y_coor);
    }
    
    @Override
    public void Initialize() {
        this.initBackground(backgroundImg);
        this.initTileSet();        
        this.setReturnCoord(0);
    }
    
    @Override
    public void LoadContent() {
        try{
            URL background_Url = this.getClass().getResource(
                    "/resources/assets/cottage_firstfloor.png");
            backgroundImg = ImageIO.read(background_Url);
        }catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
