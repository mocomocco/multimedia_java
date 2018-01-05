import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */

/**
 * @author g1620520
 *simple draw panel class povides drawLine method
 */
public class DrawPanel extends JPanel{
    private static final long serialVersionUID = 42L;
    BufferedImage bufferImage=null;
    Graphics2D bufferGraphics=null;

    private void createBuffer(int width, int height) {
        //バッファ用のImageとGraphicsを用意する
        bufferImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
        bufferGraphics=bufferImage.createGraphics(); //getGraphicsと似ているが、戻り値がGraphics2D。
        bufferGraphics.setBackground(Color.white);
        bufferGraphics.clearRect(0, 0, width, height); //バッファクリア
    }

        Color currentColor = Color.black;
        Float currentwidthlevel=3.0f;
    public void setPenColor(Color newColor) {
        currentColor = newColor;
    }
    public void setPenWidth(Float newwidthlevel){
        currentwidthlevel=newwidthlevel;
    }

    public void drawLine(int x1, int y1, int x2, int y2){
        if(null==bufferGraphics) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int w = screenSize.width;
            int h = screenSize.height;
            this.createBuffer(w,h);  //バッファをまだ作ってなければ作る
        }
        bufferGraphics.setColor(currentColor);
        BasicStroke wideStroke = new BasicStroke(currentwidthlevel);
        bufferGraphics.setStroke(wideStroke);
        bufferGraphics.drawLine(x1, y1-40, x2, y2-40); // バッファに描画する
        repaint();//再描画するためpaintComponentを呼び出す。
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);//他に描画するものがあるかもしれないので親を呼んでおく
        if(null!=bufferImage) g.drawImage(bufferImage, 0,0,this);//バッファを表示する
    }
}

