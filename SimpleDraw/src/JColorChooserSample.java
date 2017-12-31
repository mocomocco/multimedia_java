import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JColorChooserSample extends JFrame implements ActionListener{
    JButton button;

    JColorChooserSample(String title){
        setTitle(title);

        button= new JButton("chooser color");
        button.addActionListener(this);

        JPanel panel=new JPanel();

        panel.add(button);

        Container container=getContentPane();
        container.add(panel);
    };
    public void actionPerformed(ActionEvent e){
        JColorChooser colorchooser = new JColorChooser();

        Color color=colorchooser.showDialog(this,"choose a color",Color.blue);

        //button.setBackground(color);
    }

    public static void main(String[] args){
        JColorChooserSample sample=new JColorChooserSample("JColorChooserSample");

        sample.pack();
        sample.setVisible(true);
        sample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}