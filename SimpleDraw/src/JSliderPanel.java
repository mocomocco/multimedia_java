import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.BorderLayout;

public class JSliderPanel extends JFrame implements ChangeListener{
    private static final long serialVersionUID = 42L;
    float level;
    JLabel label = new JLabel("スライダーの値");

    JSlider slider=new JSlider(0,15);
    SimpleDraw parent;

    private void setweigthslider(JSlider wslider){
        wslider.setMajorTickSpacing(5);
        wslider.setMinorTickSpacing(1);
        wslider.setPaintTicks(true);
        wslider.setLabelTable(wslider.createStandardLabels(5));
        wslider.setPaintLabels(true);
    }

    JSliderPanel(String title,SimpleDraw simpledraw){
        parent=simpledraw;
        this.setTitle(title);
        setBounds( 10, 10, 300, 200);
        this.setweigthslider(slider);
        slider.addChangeListener(this);

        JPanel p = new JPanel();
        p.add(slider);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(label, BorderLayout.PAGE_END);
    }


    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        float fps = (float)source.getValue();
        label.setText("値：" + fps);
        parent.valueofslider(fps);
    }
}
