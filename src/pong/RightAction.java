
package pong;
/**
 *
 * @author Melek Ayaz und Simon Trottberger 
 */

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
    
    
public class RightAction extends AbstractAction {
    Direction dir;
    boolean pressed;

    public RightAction(Direction dir, boolean pressed) {
        this.dir = dir;
        this.pressed = pressed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (pressed) {
            Model.keys.add(dir);
        } else {
            Model.keys.remove(dir);
        }
    }

    
}
