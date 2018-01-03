import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 */

/**
 * @author g1620520
 *simple draw panel class povides drawLine method
 */
public class DrawPanel extends JPanel{
    private static final long serialVersionUID = 42L;


        Color currentColor = Color.black;
        Float currentwidthlevel=3.0f;
    public void setPenColor(Color newColor) {
        currentColor = newColor;
    }
    public void setPenWidth(Float newwidthlevel){
        currentwidthlevel=newwidthlevel;
    }
    public void drawLine(int x1,int y1,int x2,int y2){
        Graphics2D g = (Graphics2D)this.getGraphics();
        g.setColor(currentColor);
        BasicStroke wideStroke = new BasicStroke(currentwidthlevel);
        g.setStroke(wideStroke);
        g.drawLine(x1,y1-40,x2,y2-40);
    }

}

