package hFoundation;

import hEntity.PlayerCharacter;
import hFramework.World;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Game {
    
    public static PlayerCharacter hero;
    public static World world;
    
    public Game(){
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Load game files (images, sounds, ...)
                LoadContent();
                // Sets variables and objects for the game.
                Initialize();
                
                Framework.gameState = Framework.GameState.PLAYING;
            }
        };
        threadForInitGame.start();
    }
    
    public void Initialize(){
        
        hero = new PlayerCharacter();
        hero.LoadContent();
        hero.Initialize();
        
        world = new World();
        world.LoadContent();
        world.Initialize();
        
    }
    
    public void LoadContent(){ 
    
    }
    
    public void RestartGame(){
        
    }
    
    public void UpdateGame(long gameTime, Point mousePosition){
        
    }
    
    public void Draw(Graphics2D g2d, Point mousePosition){
        world.Draw(g2d);
        g2d.drawString("(" + mousePosition.x + ", " + mousePosition.y + ")",
                850, 15);
        g2d.drawString("(" + hero.tileX + ", " + hero.tileY + ")",
                850, 30);
    }
}
