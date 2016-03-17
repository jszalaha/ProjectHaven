package hFramework;

import hEntity.SpellEffects;
import hEntity.Zone;
import hFoundation.Game;
import hFoundation.Framework;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class World {
    
    private Zone testZone;
    private Zone cottage;
    public Zone currentZone;
    
    public static SpellEffects spellEffects;
    
    private BufferedImage testTerrainImg;
    
    public World() {}
    
    private void BuildGraph() {
        testZone.Adj.add(cottage);
        cottage.Adj.add(testZone);
    }
    
    public void LoadContent() {
        // Load Terrain Image //////////////////////////////////////////////
        // Load any assets shared by mutliple zones ////////////////////////
        
    }
    
    public void Initialize() {
        spellEffects = new SpellEffects();
        spellEffects.LoadContent();
        spellEffects.Initialize();
        
        testZone = new hZoneBox.TestZone();
        testZone.LoadContent();
        testZone.Initialize();
        
        cottage = new hZoneBox.Cottage();
        cottage.LoadContent();
        cottage.Initialize();
        
        this.BuildGraph();
        
        currentZone = testZone;
        Game.hero.tileX = ((Framework.frameWidth / 2 - 20)-currentZone.x_coor)/Tile.TILE_WIDTH;
        Game.hero.tileY = ((Framework.frameHeight / 2 - 28)-currentZone.y_coor)/Tile.TILE_HEIGHT+1;
    }
    
    // NEED TO WRITE FUNCTION TO HANDLE ADDING A SPELL EFFECT TO CURRENTZONE'S
    // DRAWLIST BASED ON X AND Y POSITION DETERMINED BY KEYEVENT HANDLED IN 
    // PLAYERCHARACTER CLASS (or handle event here and do separately*)
    
    public void addEffect(SpellEffects.Type type, int x, int y){ 
        currentZone.insertEntity(spellEffects.newEffect(type, x, y));
        switch(type){
            case FIRE:
                currentZone.tileSet.tile.get(Game.hero.tileX).get(Game.hero.tileY-1).status = Tile.TileStatus.BURNING;
                break;
            default:
                break;
        }
    }
    
    private void Update() {
        int port = currentZone.tileSet.tile.get(Game.hero.tileX).get(Game.hero.tileY).portal;
        if(port >= 0) {
            currentZone.setReturnCoord(port);
            currentZone = currentZone.Adj.get(port);
            Game.hero.tileX = ((Framework.frameWidth / 2 - 20)-currentZone.x_coor)/Tile.TILE_WIDTH;
            Game.hero.tileY = ((Framework.frameHeight / 2 - 28)-currentZone.y_coor)/Tile.TILE_HEIGHT+1;
        }
        
        
    }
    
    public void Draw(Graphics2D g2d) {
        this.Update();
        currentZone.Draw(g2d);
    }
    
}
