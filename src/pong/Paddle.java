
package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Melek Ayaz und Simon Trottberger 
 */

public class Paddle implements Entity {

    int score = 0;
    int paddleNum;
    int paddleX = 100, paddleY = 0;
    View view;

    Point location = new Point(0, 0);

    public Paddle(int paddleNum) {
        this.paddleNum = paddleNum;
    }

    public void createBindings() {
        if (paddleNum == 1) {
            view.addKeyBinding("leftup.pressed", KeyEvent.VK_W, true, new LeftAction(Direction.LEFT_UP, true));
            view.addKeyBinding("leftup.released", KeyEvent.VK_W, false, new LeftAction(Direction.LEFT_UP, false));
            view.addKeyBinding("leftdown.pressed", KeyEvent.VK_S, true, new LeftAction(Direction.LEFT_DOWN, true));
            view.addKeyBinding("leftdown.released", KeyEvent.VK_S, false, new LeftAction(Direction.LEFT_DOWN, false));
        } else {
            System.out.println("paddleNum nicht 1");
            view.addKeyBinding("rightup.pressed", KeyEvent.VK_UP, true, new RightAction(Direction.RIGHT_UP, true));
            view.addKeyBinding("rightup.released", KeyEvent.VK_UP, false, new RightAction(Direction.RIGHT_UP, false));
            view.addKeyBinding("rightdown.pressed", KeyEvent.VK_DOWN, true,
                    new RightAction(Direction.RIGHT_DOWN, true));
            view.addKeyBinding("rightdown.released", KeyEvent.VK_DOWN, false,
                    new RightAction(Direction.RIGHT_DOWN, false));
        }
    }

    @Override
    public Dimension getSize() {
        return new Dimension(25, 100);
    }

    @Override
    public Point getLocation() {
        return new Point(location);
    }

    @Override
    public void setLocation(Point p) {
        location = p;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void resetScore() {
        score = 0;
    }

    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(getLocation().x, getLocation().y, getSize().width, getSize().height);
    }    
    
}
