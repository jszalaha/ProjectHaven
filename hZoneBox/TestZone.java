package hZoneBox;

import hEntity.*;
import hFramework.Tile;

import hFoundation.Framework;

import java.awt.image.BufferedImage;


import java.io.IOException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TestZone extends Zone {
    
    private BufferedImage backgroundImg;
    private BufferedImage treeImg;
    private BufferedImage treeShadowImg;
    private BufferedImage cottageImg;
    private BufferedImage foundationImg;
    private Sprite tree;
    private Sprite treeShadow;
    private Sprite cottage;
    private Sprite cottage_foundation;
    
    public TestZone() 
    {
        super();
    }
    
    @Override
    public void setReturnCoord(int port) {
        switch(port){
            case 0:
                this.x_coor = -Zone.SQUARE_WIDTH*4;
                this.y_coor = -Zone.SQUARE_HEIGHT*41;
                this.Jump(x_coor, y_coor);
            break;
            default:
            break;    
        }
    }
    
    @Override
    public void Populate() {
        int treeWidth = Zone.SQUARE_WIDTH*2;
        int treeHeight = Zone.SQUARE_HEIGHT*2;
        
        // Place five rows of trees along the top
        // of the zone
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < this.Background.frameWidth/treeWidth+1; j++){
                // Place a new tree in zone
                new Entity(
                        /*sprite*/ tree,
                        /*shadow*/ treeShadow,
                        /*animation*/ null,
                        /*x coord*/ j*treeWidth,
                        /*y coord*/ i*treeHeight,
                        /*set this as parentZone and add it*/ this, 
                        /*block occupied tiles in zone or not*/ true
                );
            }
        }
       
        for(int i = 5; i < 30; i++){
            for(int j = 0; j < (this.Background.frameWidth)/4/treeWidth; j++){
                // Place a new tree in zone
                new Entity(
                        /*sprite*/ tree,
                        /*shadow*/ treeShadow,
                        /*animation*/ null,
                        /*x coord*/ j*treeWidth,
                        /*y coord*/ i*treeHeight,
                        /*set this as parentZone and add it*/ this, 
                        /*block occupied tiles in zone or not*/ true
                );
            }
            for(int j = (((this.Background.frameWidth)/4)*3)/treeWidth+1; 
                    j < (this.Background.frameWidth)/treeWidth+1; j++){
                // Place a new tree in zone
                new Entity(
                        /*sprite*/ tree,
                        /*shadow*/ treeShadow,
                        /*animation*/ null,
                        /*x coord*/ j*treeWidth,
                        /*y coord*/ i*treeHeight,
                        /*set this as parentZone and add it*/ this, 
                        /*block occupied tiles in zone or not*/ true
                );
            }
        }
        
        // Place five rows of trees along the top
        // of the zone
        for(int i = 30; i < 35; i++){
            for(int j = 0; j < this.Background.frameWidth/treeWidth+1; j++){
                // Place a new tree in zone
                new Entity(
                        /*sprite*/ tree,
                        /*shadow*/ treeShadow,
                        /*animation*/ null,
                        /*x coord*/ j*treeWidth,
                        /*y coord*/ i*treeHeight,
                        /*set this as parentZone and add it*/ this, 
                        /*block occupied tiles in zone or not*/ true
                );
            }
        }
        
        // create a cottage and add it to the zone
        Entity Cottage = new Entity(
                        /*sprite*/cottage,
                        /*shadow*/cottage_foundation,
                        /*anim*/null,
                        /*x*/560,
                        /*y*/1316,
                        /*parent*/null,
                        /*block*/true
        );
        this.insertEntity(Cottage);
        Cottage.block(true);
    }
    
    @Override
    public void Initialize() {        
        tree = new Sprite(treeImg,96,112,0,0,true,0);
        treeShadow = new Sprite(treeShadowImg,80,56,0,0,true,0);
        cottage = new Sprite(cottageImg,160,175,0,0,true,0);
        cottage_foundation = new Sprite(foundationImg,160,112,0,0,true,0);  
        
        this.initBackground(backgroundImg);
        this.initTileSet();
        this.Populate();
        
        this.tileSet.tile.get(15).get(50).portal = 0;       
        
        this.x_coor = -240;
        this.y_coor = -364;
        this.Jump(this.x_coor,this.y_coor);
                
    }

    @Override
    public void LoadContent() {
        
        try{
            
            URL background_Url = this.getClass().getResource(
                    "/resources/assets/test/Test_Terrain2.png");
            backgroundImg = ImageIO.read(background_Url);
            
            URL treeImg_Url = this.getClass().getResource(
                    "/resources/assets/Tree.png");
            treeImg = ImageIO.read(treeImg_Url);
            
            URL treeShadowImg_Url = this.getClass().getResource(
                    "/resources/assets/Shadow_2x2.png");
            treeShadowImg = ImageIO.read(treeShadowImg_Url);
            
            URL cottageImg_Url = this.getClass().getResource(
                    "/resources/assets/Cottage.png");
            cottageImg = ImageIO.read(cottageImg_Url);
            
            URL foundationImg_Url = this.getClass().getResource(
                    "/resources/assets/Foundation_4x4.png");
            foundationImg = ImageIO.read(foundationImg_Url);
            
        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
