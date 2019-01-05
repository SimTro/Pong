package pong;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
//import javax.swing.Timer;

public class Model {

    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    static boolean presstp = false;
    static Set<Direction> keys = new HashSet<Direction>(25);
    Timer timer;
    boolean first = false;
    boolean direction = false, axis = false;
    double ballX = 0, ballY = 0;
    double p1X = 0, p1Y = 0;
    double p2X = 0, p2Y = 0;
    double incline = -0.5;
    List<Entity> entities = new ArrayList<Entity>(20);
    View view;
    public static int counter = 1;
    public int timercounter = 0;

    public Model(View view) {

        this.view = view;
        view.setModel(this);
        update(view.getBounds());
        view.repaint();

        KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        kfm.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {

                if (presstp == false && ke.getModifiers() == InputEvent.ALT_MASK && ke.getKeyChar() == 'p' || presstp == false && ke.getKeyCode() == KeyEvent.VK_P && ke.getModifiers() == InputEvent.META_MASK && ke.getID() == KeyEvent.KEY_PRESSED) {
                    startTimer(counter);
                    presstp = true;
                    
                }

                return false;
            }
        });

    }

    public void startTimer(int time) {
    
       String speed = (String) Start.jComboBox1.getSelectedItem();
    int speed2 = Integer.valueOf(speed);
        System.out.println(speed2);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update(view.getBounds());
                view.repaint();
                timercounter++;
                

            }

        }, 0,speed2);

    }

    public void update(Rectangle bounds) {

        if (paddle1 == null || paddle2 == null || ball == null) {
            paddle1 = new Paddle(1);
            paddle2 = new Paddle(2);
            ball = new Ball();
            ballX = 0;
            ballY = 0;
            p1X = 30;
            p2X = (Pong.screenwidht - 60);
            p1Y = (View.bountshight - 60) / 2;
            p2Y = (View.bountshight - 60) / 2;
            paddle1.setView(view);
            paddle2.setView(view);
            paddle1.createBindings();
            paddle2.createBindings();
            entities.add(paddle1);
            entities.add(paddle2);
            entities.add(ball);

        }

        if (paddle1.getScore() > 7) {
            JOptionPane.showMessageDialog(view, "Player 1 has won!");
            presstp = false;
            paddle1.resetScore();
            paddle2.resetScore();
            timer.cancel();

        } else if (paddle2.getScore() > 7) {
            JOptionPane.showMessageDialog(view, "Player 2 has won!");
            presstp = false;
            paddle1.resetScore();
            paddle2.resetScore();
            timer.cancel();

        }

        setDirection(direction, incline);
        bounce();

        if (keys.contains(Direction.LEFT_UP)) {
            p1Y -= 2;
        } else if (keys.contains(Direction.LEFT_DOWN)) {
            p1Y += 2;
        }
        if (keys.contains(Direction.RIGHT_UP)) {
            p2Y -= 2;
        } else if (keys.contains(Direction.RIGHT_DOWN)) {
            p2Y += 2;
        }

        paddle1.setLocation(new Point((int) p1X, (int) p1Y));
        paddle2.setLocation(new Point((int) p2X, (int) p2Y));
        ball.setLocation(new Point((int) ballX, (int) ballY));

    }

    public Entity[] getEntities() {
        return entities.toArray(new Entity[0]);

    }

    public void bounce() {

        if (ballX < p1X + paddle1.getSize().width && ballY > p1Y && ballY < p1Y + paddle2.getSize().height) {
            direction = true;
        }

        if (ballX + ball.getSize().width > p2X && ballY > p2Y && ballY < p2Y + paddle1.getSize().height) {
            direction = false;
        }

        if (ballX < view.getBounds().x) {
            paddle2.increaseScore();

            direction = !direction;

            ballX = View.bountswidht / 2;
            ballY =  Math.random()*(View.bountshight -60);

        }
        if (ball.getLocation().x > view.getBounds().x + view.getBounds().width) {
            paddle1.increaseScore();

            direction = !direction;

            ballX = View.bountswidht / 2;
            ballY = Math.random()*(View.bountshight -60);

        }
        if (ball.getLocation().y < view.getBounds().y) {
            ballY++;
            incline *= -1;

        }
        if (ball.getLocation().y > view.getBounds().height) {
            ballY--;
            incline *= -1;
        }

        if (paddle1.getLocation().y < view.getBounds().y) {
            p1Y = view.getBounds().x - 1;
        }
        if (paddle1.getLocation().y + paddle1.getSize().height > view.getBounds().height + 22) {
            p1Y = view.getBounds().height - paddle1.getSize().height + 22;
        }

        if (paddle2.getLocation().y < view.getBounds().y) {
            p2Y = view.getBounds().x - 1;
        }
        if (paddle2.getLocation().y + paddle2.getSize().height > view.getBounds().height + 22) {
            p2Y = view.getBounds().height - paddle2.getSize().height + 22;
        }

    }

    public void setDirection(boolean Xdir, double inc) {
        ballY += inc;
        if (Xdir) {
            ballX++;
        } else if (!Xdir) {
            ballX--;
        }
    }

    public int getPaddleScore(int paddleNum) {
        if (paddleNum == 1) {
            return paddle1.getScore();
        } else {
            return paddle2.getScore();
        }
    }

}
