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

    int lastx=0,lasty=0,newx,newy;
    DrawPanel panel=new DrawPanel();

    Color backgroundcolor=Color.white;

    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        newx=arg0.getX();
        newy=arg0.getY();
        panel.drawLine(lastx, lasty, newx, newy);
        lastx=newx;
        lasty=newy;
    }


    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
        lastx=arg0.getX();
        lasty=arg0.getY();
    }


    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }


    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }


    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }


    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private void AddItemToMenu(JMenu menu,String itemlabel,String command){
        JMenuItem item = new JMenuItem(itemlabel);
        item.addActionListener(this);
        item.setActionCommand(command);
        menu.add(item);
    }

    private void AddSubitemToItem(JMenuItem item,String subitemlabel,String command){
        JMenuItem subitem1 = new JMenuItem(subitemlabel);
        subitem1.addActionListener(this);
        subitem1.setActionCommand(command);
        item.add(item);
    }

    private void setcolormenu(JMenuItem pen){
        AddSubitemToItem(colormenu,"red","color_255000000");
        AddSubitemToItem(colormenu,"blue","color_000000255");
        AddSubitemToItem(colormenu,"green","color_000255000");
        AddSubitemToItem(colormenu,"yellow","color_255255000");
        AddSubitemToItem(colormenu,"black","color_000000000");
        AddSubitemToItem(colormenu,"others","color_other");
        menubar.add(colormenu);
}

    private void settoolmenu(JMenuBar menubar){
        JMenu toolmenu = new JMenu("tool" );
        AddItemToMenu(toolmenu,"pen","tool_pen");
        AddItemToMenu(toolmenu,"eraser","tool_eraser");
        menubar.add(toolmenu);
    }

    private void setweightmenu(JMenuBar menubar){
        JMenu weigthmenu = new JMenu("line weight");

        AddItemToMenu(weigthmenu,"thin","weigth_0.5");
        AddItemToMenu(weigthmenu,"standard","weigth_3");
        AddItemToMenu(weigthmenu,"thick","weigth_10");
        AddItemToMenu(weigthmenu,"others","weigth_other");

        menubar.add(weigthmenu);
    }

    private void init(){
        this.setTitle("SimplDraw");
        this.setSize(300,200);

        JMenuBar menubar = new JMenuBar();
        this.settoolmenu(menubar);
        this.setcolormenu(menubar);
        this.setweightmenu(menubar);

        setJMenuBar(menubar);

        panel.setBackground(backgroundcolor);
        this.addMouseMotionListener(this);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void colorset(String command) {
        if (command.equals("other")) {
            JColorChooser colorchooser = new JColorChooser();
            Color color = colorchooser.showDialog(this, "choose a color", Color.blue);
            panel.setPenColor(color);
        } else {
            int R = Integer.parseInt(command.substring(0, 3));
            int G = Integer.parseInt(command.substring(3, 6));
            int B = Integer.parseInt(command.substring(6, 9));
            Color color = new Color(R, G, B);
            panel.setPenColor(color);
        }
    }

    JSliderPanel weigthframe=new JSliderPanel("weigth level",this);
    Float widthlevel;
    private void callweigthslider(){
        weigthframe.setVisible(true);

    }

    public void valueofslider(float fps){
        panel.setPenWidth(fps);
    }

    private void weigthset(String command){
        if (command.equals("other")) {
            this.callweigthslider();
        } else {
            widthlevel = Float.parseFloat(command);
            panel.setPenWidth(widthlevel);
        }
    }
    private void toolset(String command){
        if (command.equals("eraser")) {
            panel.setPenColor(backgroundcolor);
        } /*else if{

        }*/
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.substring(0,5).equals("color")){
            colorset(command.substring(6));
        }else if(command.substring(0,6).equals("weigth")){
            weigthset(command.substring(7));
        }else if(command.substring(0,4).equals("tool")){
            toolset(command.substring(5));
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
