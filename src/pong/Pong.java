
package pong;

import javax.swing.JFrame;

/**
 *
 * @author Simon Trottberger
 */
public class Pong {

    
    public static int screenwidht = 0;
    public static int screenhight = 0;
    Start start;


    public static void main() {
        
        
        
       screenwidht = (Start.jtext1);
       screenhight = (Start.jtext2);
        
        JFrame frame = new JFrame();
        View view = new View();
        
        Model model = new Model(view);
        frame.setSize(screenwidht, screenhight);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(view);
        frame.setVisible(true);
        frame.setTitle("PONG");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model.paddle1.resetScore();
        model.paddle2.resetScore();
        
            
        
        
        
    }
    
}
