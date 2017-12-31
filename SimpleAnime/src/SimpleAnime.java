/**
 * Created by HINARI on 2017/12/25.
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Thread;

class Animator implements Runnable {
    Graphics g;
    Color CurrentColor;
    int CurrentSpeedlevel;
    public void setGraphics(Graphics animeG) {
        g=animeG;
    }

    public void getColor(Color newcolor){
        CurrentColor=newcolor;
    }

    public void getSpeed(int newspeedlevel){
        CurrentSpeedlevel=newspeedlevel;
    }

    public void run() {
        int x=0, xdelta=10;
        while(true) {
            g.setColor(CurrentColor);
            g.fillOval(x,80,50,50);
            try{Thread.sleep(50);}catch(Exception e){}
            g.clearRect(x, 80, 52,52);
            x+=xdelta*CurrentSpeedlevel;
            if(x>250) xdelta=-10;
            if(x<0) xdelta=10;
        }
    }
}

class SimpleAnime extends JFrame implements ActionListener{
    JPanel panel;
    Graphics g;
    JLabel label;
    Animator animator;

    private void init() {
        animator=new Animator();
        this.setTitle("SimpleAnime");
        JMenuBar menubar=new JMenuBar();
        JMenu colormenu = new JMenu("color");
        JMenuItem color1 =new JMenuItem("red");
        color1.addActionListener(this);
        color1.setActionCommand("red");

        JMenuItem color2 =new JMenuItem("blue");
        color2.addActionListener(this);
        color2.setActionCommand("blue");

        JMenuItem color3 =new JMenuItem("yellow");
        color3.addActionListener(this);
        color3.setActionCommand("yellow");

        colormenu.add(color1);
        colormenu.add(color2);
        colormenu.add(color3);

        menubar.add(colormenu);

        JMenu speedmenu = new JMenu("speed");
        JMenuItem speed1 =new JMenuItem("1");
        speed1.addActionListener(this);
        speed1.setActionCommand("1");

        JMenuItem speed2 =new JMenuItem("2");
        speed2.addActionListener(this);
        speed2.setActionCommand("2");

        JMenuItem speed3 =new JMenuItem("3");
        speed3.addActionListener(this);
        speed3.setActionCommand("3");

        speedmenu.add(speed1);
        speedmenu.add(speed2);
        speedmenu.add(speed3);

        menubar.add(speedmenu);
        setJMenuBar(menubar);

        this.setSize(300,200);
        panel = new JPanel();
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g=panel.getGraphics();
        g.setColor(Color.blue);
        animator.getColor(Color.blue);
        animator.getSpeed(2);

        animator.setGraphics(g);
        new Thread(animator).start();
    }
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command == "red"){
            animator.getColor(Color.red);
        }else if(command == "blue"){
            animator.getColor(Color.blue);
        }else if(command == "yellow"){
            animator.getColor(Color.yellow);
        }else if(command=="1") {
            animator.getSpeed(1);
        }else if(command=="2") {
            animator.getSpeed(2);
        }else if(command=="3") {
            animator.getSpeed(3);
        }
        }

    public static void main(String[] args) {
        SimpleAnime frame = new SimpleAnime();
        frame.init();

        for(int i=0;;i++) {
            System.out.println(i);
            try {Thread.sleep(500);}catch(Exception e){}
        }
    }
}
