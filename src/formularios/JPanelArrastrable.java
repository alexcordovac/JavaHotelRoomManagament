/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alex
 */
public class JPanelArrastrable extends JPanel implements MouseMotionListener, MouseListener{

    public JPanelArrastrable() {
        super();
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    
    int xx, xy;
    @Override
    public void mousePressed(MouseEvent e) {
        xx = e.getX();
        xy = e.getY();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        Window frame = SwingUtilities.getWindowAncestor(this);
        frame.setLocation(x - xx, y - xy);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
