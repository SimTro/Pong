
package pong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;


public interface Entity {
    
    public Dimension getSize();

    public Point getLocation();

    public void setLocation(Point p);

    public void paint(Graphics g);
    
}
