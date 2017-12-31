import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 */

/**
 * @author g1620520
 *
 */
public class SimpleDraw extends JFrame implements MouseListener, MouseMotionListener,ActionListener {
    private static final long serialVersionUID = 42L;
    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    int lastx=0,lasty=0,newx,newy;
    DrawPanel panel=new DrawPanel();
    JColorChooser colorchooser=new JColorChooser();

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        newx=arg0.getX();
        newy=arg0.getY();
        panel.drawLine(lastx, lasty, newx, newy);
        lastx=newx;
        lasty=newy;
    }


    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
        lastx=arg0.getX();
        lasty=arg0.getY();
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    //@Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    //@Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    //@Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    //@Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    //@Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    private void init(){
        this.setTitle("SimplDraw");
        this.setSize(300,200);
        JMenuBar menubar = new JMenuBar();
        JMenu colormenu = new JMenu("color");

        JMenuItem item1 = new JMenuItem("red");
        item1.addActionListener(this);
        item1.setActionCommand("red");

        colormenu.add(item1);

        JMenuItem item2 = new JMenuItem("blue");
        item2.addActionListener(this);
        item2.setActionCommand("blue");

        colormenu.add(item2);

        JMenuItem item3 = new JMenuItem("green");
        item3.addActionListener(this);
        item3.setActionCommand("green");

        colormenu.add(item3);

        JMenuItem item4 = new JMenuItem("yellow");
        item4.addActionListener(this);
        item4.setActionCommand("yellow");

        colormenu.add(item4);

        JMenuItem item5 = new JMenuItem("black");
        item5.addActionListener(this);
        item5.setActionCommand("black");

        colormenu.add(item5);

        JMenuItem item6 = new JMenuItem("others");
        item6.addActionListener(this);
        item6.setActionCommand("other");

        /*getContentPane().add(colorchooser, BorderLayout.CENTER);
        getContentPane().add(colorLabel, BorderLayout.PAGE_START);
        getContentPane().add(selectPanel, BorderLayout.PAGE_END);
        JMenuItem subitem1 = new JMenuItem("Iced Coffee");
        subitem1.addActionListener(this);
        subitem1.setActionCommand("Iced Coffee");
        JMenuItem subitem2 = new JMenuItem("Iced Tea");
        subitem2.addActionListener(this);
        subitem2.setActionCommand("Iced Tea");
        JMenuItem subitem3 = new JMenuItem("Orange juice");
        subitem3.addActionListener(this);
        subitem3.setActionCommand("Orange juice");

        item3.add(subitem1);
        item3.add(subitem2);
        item3.add(subitem3);
*/
        colormenu.add(item6);

        menubar.add(colormenu);

        setJMenuBar(menubar);

        this.addMouseMotionListener(this);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command == "red"){
            panel.setPenColor(Color.red);
        }else if(command =="blue"){
            panel.setPenColor(Color.blue);
        }else if(command =="green"){
            panel.setPenColor(Color.green);
        }else if(command =="yellow"){
            panel.setPenColor(Color.yellow);
        }else if(command =="black"){
            panel.setPenColor(Color.black);
        }else if(command =="other") {
            JColorChooser colorchooser = new JColorChooser();
            Color color=colorchooser.showDialog(this,"choose a color",Color.blue);
            panel.setPenColor(color);
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SimpleDraw frame=new SimpleDraw();
        frame.init();
    }

}
