import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static java.lang.Double.max;
import static java.lang.Double.min;
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
    int w,h;
    public void drawLine(int x1, int y1, int x2, int y2){
        if(null==bufferGraphics) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            w = screenSize.width;
            h = screenSize.height;
            this.createBuffer(w,h);  //バッファをまだ作ってなければ作る
        }
        bufferGraphics.setColor(currentColor);
        BasicStroke wideStroke = new BasicStroke(currentwidthlevel);
        bufferGraphics.setStroke(wideStroke);
        bufferGraphics.drawLine(x1, y1-40, x2, y2-40); // バッファに描画する
        repaint();//再描画するためpaintComponentを呼び出す。
    }

    public static BufferedImage resizeImage(final BufferedImage image, final double scale) throws IOException {
        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        BufferedImage resizedImage = new BufferedImage(width, height, image.getType());

        // アフィン変換でリサイズ（画質優先）
        AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);
        op.filter(image, resizedImage);

        return resizedImage;
    }


    public void openFile(File file2open) throws IOException {
        BufferedImage pictureImage;
        try {
            pictureImage = ImageIO.read(file2open);
        } catch(Exception e){
            System.out.println("Error: reading file="+file2open.getName());
            return;
        }
        //画像に合わせたサイズでbufferImageとbufferGraphicsを作りなおして画像を読み込む
        //ImageIO.readの戻り値をbufferImageに代入するのでは駄目みたいです。
        int width=pictureImage.getWidth();
        int height=pictureImage.getHeight();
        this.createBuffer(width,height);
        double scale=Double.min(w/width,h/height);
        //BufferedImage destImage=resizeImage(pictureImage,scale);
        bufferGraphics.drawImage(pictureImage,0,0,this);
        repaint(); //画像を表示するためにpaintComponentを呼ぶ
    }


    public void saveFile(File file2save) {
        try {
            BufferedImage destImage = resizeImage(bufferImage,1);
            ImageIO.write(destImage, "jpg", file2save);
        } catch (Exception e) {
            System.out.println("Error: writing file=" + file2save.getName());
            return;
        }
    }

    public void newFile(){
        bufferImage=null;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);//他に描画するものがあるかもしれないので親を呼んでおく
        if(null!=bufferImage) g.drawImage(bufferImage, 0,0,this);//バッファを表示する
    }
}

