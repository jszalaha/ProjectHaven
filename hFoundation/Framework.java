package hFoundation;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Framework extends Canvas {
    
    // Game States /////////////////////////////////////////////////////////////
    public static enum GameState{
        STARTING, 
        VISUALIZING, 
        GAME_CONTENT_LOADING, 
        MAIN_MENU, 
        OPTIONS, 
        PLAYING, 
        GAMEOVER, 
        DESTROYED
    }    
    public static GameState gameState;
    
    // Mechanical Variables ////////////////////////////////////////////////////
    public static int frameWidth;
    public static int frameHeight;
    public static final long SEC_NANO = 1000000000L;
    public static final long MILI_NANO = 1000000L;
    private final int GAME_FPS = 60;
    private final long GAME_UPDATE_PERIOD = SEC_NANO / GAME_FPS;
    private long gameTime;
    private long lastTime;
    
    // Content Variables ///////////////////////////////////////////////////////
    private BufferedImage mainMenuImg;
    
    // Game Variable ///////////////////////////////////////////////////////////
    private Game game;
    
    // Constructor /////////////////////////////////////////////////////////////
    public Framework(){
        super();
        
        gameState = GameState.VISUALIZING;
        
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        gameThread.start();
    }
    
    // Private Methods /////////////////////////////////////////////////////////
    
    private void newGame(){
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game();
    }
    
    // calls game.RestartGame()
    private void restartGame(){
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        
        game.RestartGame();
        
        // We change game status so that the game can start.
        gameState = GameState.PLAYING;
    }
    
    private Point mousePosition(){
        
        try {
            Point mp = this.getMousePosition();
            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0,0);
        }
        catch (Exception e) {
            return new Point(0,0);
        }
    }
    
    // Public Methods //////////////////////////////////////////////////////////
    public void Initialize(){
        
    }
    
    public void LoadContent(){
        
        try
        {
            // Load Image for the Main Menu Here //
            URL mainMenuImgUrl = this.getClass().getResource(
                    "/resources/menus/MainMenu.jpg");
            mainMenuImg = ImageIO.read(mainMenuImgUrl);
            
        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // GAME LOOP ///////////////////////////////////////////////////////////////
    public void GameLoop(){
        
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        long beginTime = System.nanoTime(), timeTaken, timeLeft;
        
        // Loops every 16.6667 ms
        while(true){
            
            switch(gameState){
                case VISUALIZING:
                    if(this.getWidth() > 1 && visualizingTime > SEC_NANO)
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        gameState = GameState.STARTING;
                    }
                    else
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                    
                    break;
                case STARTING:
                    LoadContent();
                    Initialize();
                    
                    gameState = GameState.MAIN_MENU;
                    
                    break;
                case MAIN_MENU:
                    break;
                case OPTIONS:
                    break;
                case GAME_CONTENT_LOADING:
                    break;
                case PLAYING:
                    break;
                case GAMEOVER:
                    break;
                case DESTROYED:
                    break;
            }
            
            // canvas repainted every 16.6667 ms
            repaint();
            
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / MILI_NANO;
            if (timeLeft < 10)
                timeLeft = 10;
            try {
                Thread.sleep(timeLeft);
            } catch (InterruptedException ex) {}
        }
    }  
    ////////////////////////////////////////////////////////////////////////////
    
    // Canvas Overrides ////////////////////////////////////////////////////////
    @Override
    public void Draw(Graphics2D g2d){
        switch (gameState)
        {
            case PLAYING:
                game.Draw(g2d, mousePosition());
                
            break;
            case GAMEOVER:
                //...
            break;
            case MAIN_MENU:
                g2d.drawImage(mainMenuImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.white);
                g2d.drawString("Press any key to start the game.", 
                        frameWidth / 2 - 100,
                        frameHeight / 2 + 30);
            break;
            case OPTIONS:
                //...
            break;
            case GAME_CONTENT_LOADING:
                g2d.setColor(Color.white);
                g2d.drawString("GAME is LOADING", frameWidth / 2 - 50, frameHeight / 2);
            break;
        }
    }
    
    @Override
    public void keyReleasedFramework(KeyEvent e){
        switch (gameState)
        {
            case MAIN_MENU:
                newGame();
            break;
            case GAMEOVER:
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
                    restartGame();
            break;                
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
    }
}
