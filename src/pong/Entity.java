
package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Melek Ayaz und Simon Trottberger 
 */

public interface Entity {
    
    public Dimension getSize();

    public Point getLocation();

    public void setLocation(Point p);

    public void paint(Graphics g);
    
}
