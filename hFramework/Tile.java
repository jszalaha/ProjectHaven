package hFramework;

public class Tile {
    
    public static final int TILE_WIDTH = 40;
    public static final int TILE_HEIGHT = 28;
    
    public static enum TileStatus {
        BURNING,
        BURNT,
        FROZEN,
        WET       
    }
    
    public boolean blocked;
    public TileStatus status;
    
    // corresponds to the index of
    // zone's adj list
    public int portal;
    
    public Tile() {       
        this.blocked = false;
        this.status = null;
        this.portal = -1;
    }
}
