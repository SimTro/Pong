
package pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Ball implements Entity {

    Point location = new Point(0, 0);
    int x = 0, y = 0;

    public Ball() {
    }

    @Override
    public Dimension getSize() {
        return new Dimension(20, 20);
    }

    @Override
    public Point getLocation() {
        return new Point(location);

    }

    @Override
    public void setLocation(Point p) {
        location = p;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(getLocation().x, getLocation().y, getSize().width, getSize().height);
    }

}
