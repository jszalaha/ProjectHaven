package hFoundation;

import javax.swing.*;

public class Window extends JFrame {
    
    private static int WINDOW_WIDTH = 926;
    private static int WINDOW_HEIGHT = 588;
    
    private Window(){
        
        this.setTitle("Project Haven");
        
        if(false) // Full screen mode
        {
            // Disables decorations for this frame.
            this.setUndecorated(true);
            // Puts the frame to full screen.
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
        else // Window mode
        {
            // Size of the frame.
            this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            // Puts frame to center of the screen.
            this.setLocationRelativeTo(null);
            // So that frame cannot be resizable by the user.
            this.setResizable(false);
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Framework());
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        // Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
