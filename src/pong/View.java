
package pong;

import java.awt.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import static pong.Pong.screenhight;
import static pong.Pong.screenwidht;

/**
 *
 * @author Melek Ayaz und Simon Trottberger 
 */

public class View extends JPanel {
    
    Model model;
    Rectangle bounds;
    
    public static int bountsx = 0;
    public static int bountsy = 0;
    public static int bountswidht = screenwidht - 20;
    public static int bountshight = screenhight - 90;

    public View() {
        setBackground(Color.BLACK);
        bounds = new Rectangle(bountsx, bountsy, bountswidht, bountshight); 
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void addKeyBinding(String name, int keyEvent, boolean pressed, AbstractAction action) {
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyEvent, 0, !pressed), name);
        actionMap.put(name, action);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics g2d = (Graphics2D) g;
        g.setColor(Color.white);
        for (int i=0; i<= (bountshight/20); i++){
            g.fillRect(View.bountswidht/2-5, i*20, 10, 10);
        }
        
        g.fillRect(0, 0, 10, bountshight+20);
        g.fillRect(bountswidht+10, 0, 10, bountshight+20);
        g.fillRect(0, 0, bountswidht+20, 10);
        g.fillRect(0, bountshight+10, bountswidht+20, 10);
        
        
        if (model.getEntities() != null) {
            for (Entity entity : model.getEntities()) {
                entity.paint(g);
            }
            
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", 1, 40));
            g.drawString(model.getPaddleScore(1) + " : " + model.getPaddleScore(2), (Pong.screenwidht/2)-50, Pong.screenhight-35);
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", 1, 20));
           
            g.drawString("s = downe ", 10, Pong.screenhight-30);
            g.drawString("w = up ", 10, Pong.screenhight-50);
            char c = '\u2193';
            g.drawString(c+" = downe ", Pong.screenwidht-100, Pong.screenhight-30);
            char p = '\u2191';
            g.drawString(p+" = up ", Pong.screenwidht-100, Pong.screenhight-50);
            g.setColor(Color.BLUE);
            g.drawString("Speed: "+ Model.ingamespeed ,Pong.screenwidht-200, Pong.screenhight-30);
            g.setFont(new Font("Arial", 1, 15));
            g.setColor(Color.MAGENTA);
            g.drawString("+ = Speed + : "+ Model.ingamespeed ,Pong.screenwidht-310, Pong.screenhight-45);
            g.drawString("- = Speed - : "+ Model.ingamespeed ,Pong.screenwidht-310, Pong.screenhight-25);
            
            if (Model.presstp == false){
                g.setColor(Color.RED);
            
            g.setFont(new Font("Arial", 1, 40));
            g.drawString("Press P for Start ",bountswidht/2-100, Pong.screenhight/4);
            }
            
        } else {
            System.out.println("Something is wrong with the entities...");
        }
    }

    @Override
    public Rectangle getBounds() {
        return bounds;

    }
    

        
}
