package hFramework;

import java.util.ArrayList;

public class TileSet {
    
    public ArrayList<ArrayList<Tile>> tile;
    
    public TileSet(int tileX,int tileY){
        tile = new ArrayList<ArrayList<Tile>>();
        for(int i = 0; i < tileX; i++){
            tile.add(new ArrayList<Tile>());
            for(int j = 0; j < tileY; j++){
                tile.get(i).add(new Tile());
            }
        }
    }
    
    public void block(int x, int y, int width, int height){
        if(y >= 0){
            int w = width/Tile.TILE_WIDTH;
            int h = height/Tile.TILE_HEIGHT;
            int ht;

            for(int i = x; i < x+w; i++){
                for(int j = y; j < y+h; j++){
                    this.tile.get(i).get(j).blocked = true;
                }
            }
        }
    }
    
    public void unblock(int x, int y, int width, int height){
        if(y >= 0){
            int w = width/Tile.TILE_WIDTH;
            int h = height/Tile.TILE_HEIGHT;
            int ht;

            for(int i = x; i < x+w; i++){
                for(int j = y; j < y+h; j++){
                    this.tile.get(i).get(j).blocked = false;
                }
            }
        }
    }
}
