package imageClipper;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel{

  //https://sites.google.com/site/gorogoronyan/Home/java-1/java-2
  //http://www.javadrive.jp/applet/image/index2.html
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private BufferedImage image =null;
  
  ImagePanel(BufferedImage image){
    super();
    AffineTransformOp ato = null;
    int width=120;
    int height = (int)(((double)width /image.getWidth()) * (double)image.getHeight());
    BufferedImage distImage = new BufferedImage(width, height, image.getType());
    ato = new AffineTransformOp(AffineTransform.getScaleInstance((double)width / image.getWidth(),(double) height / image.getHeight()),null);
    ato.filter(image, distImage);
    this.image=distImage;
  }
  
  public void paintComponent(Graphics g){
    g.drawImage(this.image, 0, 0, this);
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
