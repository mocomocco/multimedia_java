import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

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
    DrawPanel base=new DrawPanel();
    DrawPanel background=new DrawPanel();
    DrawPanel panel=new DrawPanel();
    DrawPanel layer=new DrawPanel();
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

    private JMenu AddItemWithSubitemToMenu(JMenu menu,String itemlabel,String command){
        JMenu item = new JMenu(itemlabel);
        menu.add(item);
        return item;
    }


    Float[] widthlevel=new Float[10];
    int currenttoolnum=0;
    JFileChooser fileChooser=new JFileChooser();
    Color currentcolor=Color.black;

    private void settoolmenu(JMenuBar menubar){
        JMenu toolmenu = new JMenu("tool" );
        JMenu penmenu=AddItemWithSubitemToMenu(toolmenu,"pen","tool_pen");
        JMenu erasermenu=AddItemWithSubitemToMenu(toolmenu,"eraser","tool_eraser");
        JMenu colormenu=AddItemWithSubitemToMenu(penmenu,"color","pen_color");
        setcolormenu(colormenu);
        JMenu penweigthmenu=AddItemWithSubitemToMenu(penmenu,"weigth","pen_weigth");
        setweigthmenu(penweigthmenu,0);
        JMenu eraserweigthmenu=AddItemWithSubitemToMenu(erasermenu,"weigth","eraser_weigth");
        setweigthmenu(eraserweigthmenu,1);

        menubar.add(toolmenu);
    }

    private void setcolormenu(JMenu colormenu){
        AddItemToMenu(colormenu,"red","color_255000000");
        AddItemToMenu(colormenu,"blue","color_000000255");
        AddItemToMenu(colormenu,"green","color_000255000");
        AddItemToMenu(colormenu,"yellow","color_255255000");
        AddItemToMenu(colormenu,"black","color_000000000");
        AddItemToMenu(colormenu,"others","color_other");
    }

    private void setweigthmenu(JMenu weigthmenu,int toolnum){
        AddItemToMenu(weigthmenu,"thin","weigth_"+toolnum+"_0.5");
        AddItemToMenu(weigthmenu,"standard","weigth_"+toolnum+"_3");
        AddItemToMenu(weigthmenu,"thick","weigth_"+toolnum+"_10");
        AddItemToMenu(weigthmenu,"others","weigth_"+toolnum+"_other");
    }

    private void setfilemenu(JMenuBar menubar){
        JMenu filemenu = new JMenu("File");

        AddItemToMenu(filemenu,"New","file_new");
        AddItemToMenu(filemenu,"Open","file_open");
        AddItemToMenu(filemenu,"Save","file_save");

        menubar.add(filemenu);
    }

    private void init(){
        this.setTitle("SimplDraw");
        this.setSize(300,200);

        JMenuBar menubar = new JMenuBar();
        this.settoolmenu(menubar);
        this.setfilemenu(menubar);

        setJMenuBar(menubar);

        widthlevel[0]=3.0f;
        widthlevel[1]=3.0f;

        panel.setBackground(Color.white);
        this.getContentPane().add(panel);

        //background.setSize(new Dimension(1296, 678));
        //background.setBackground(Color.blue);
        //panel.setBackground(new Color(0,0,0,0));

        //panel.setBackground(Color.pink);
        panel.addMouseMotionListener(this);
        //base.add(background);
       // panel.setPreferredSize(new Dimension(200, 678));
        //background.add(panel);
        //this.getContentPane().add(panel);;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void colorset(String command) {
        if (command.equals("other")) {
            JColorChooser colorchooser = new JColorChooser();
            currentcolor = colorchooser.showDialog(this, "choose a color", Color.blue);
            panel.setPenColor(currentcolor);
        } else {
            int R = Integer.parseInt(command.substring(0, 3));
            int G = Integer.parseInt(command.substring(3, 6));
            int B = Integer.parseInt(command.substring(6, 9));
            currentcolor = new Color(R, G, B);
            panel.setPenColor(currentcolor);
        }
    }

    JSliderPanel weigthframe=new JSliderPanel("weigth level",this);


    private void callweigthslider(){
        weigthframe.setVisible(true);

    }

    public void valueofslider(float fps){
        panel.setPenWidth(fps);
    }

    private void weigthset(String command,int toolnum){
        if(toolnum==0){
            panel.setPenColor(currentcolor);
        }else if (toolnum==1){
            currenttoolnum=1;
            panel.setPenWidth(widthlevel[currenttoolnum]);
            panel.setPenColor(backgroundcolor);

           // panel.openFile(new File("../../../Desktop/a.png"));//..(java)/..(Documents)/Desktop/a.jpg
        }

        if (command.equals("other")) {
            this.callweigthslider();
        } else {
            widthlevel[toolnum] = Float.parseFloat(command);
            panel.setPenWidth(widthlevel[toolnum]);
        }
    }

    private void aboutfile(String command){
        if(command.equals("new")){
            panel.newFile();
        }else if (command.equals("save")){
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                panel.saveFile(fileChooser.getSelectedFile());
            }
        }else if (command.equals("open")){
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    /*panel.setBackground(Color.pink);
                    panel.addMouseMotionListener(this);
                    this.getContentPane().add(background);
                    panel.setPreferredSize(new Dimension(1296, 678));
                    background.add(panel);*/
                    panel.setLayout(new BorderLayout());
                    layer.setBackground(Color.green);
                    layer.setPreferredSize(new Dimension(1296, 678));
                    panel.add(layer,BorderLayout.EAST);
                    layer.openFile(fileChooser.getSelectedFile());
                    this.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.substring(0,5).equals("color")){
            currenttoolnum=0;
            panel.setPenWidth(widthlevel[currenttoolnum]);
            colorset(command.substring(6));
            //File output=new File("output.jpg");
            //panel.saveFile(output);
        }else if(command.substring(0,6).equals("weigth")){
            weigthset(command.substring(9), Integer.parseInt(command.substring(7,8)));

        }else if(command.substring(0,4).equals("file")){
            aboutfile(command.substring(5));
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
