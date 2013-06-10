package imageClipper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;


/**
 * コードを1つ受け取り、ImagePanelを生成する。
 *
 */
public class ImageFactory extends Thread{

  private String code=null;
  private List<ImagePanel> list=null;
  private final String FULL = "http://twitpic.com/show/full/";
  private final String URL = "http://twitpic.com/";
  
  ImageFactory(String code){
    this.code=code;
  }
  ImageFactory(String code,List<ImagePanel> list){
    this.code=code;
    this.list=list;
  }
  
  public void run(){
    java.net.URL url;
    ImagePanel panel=null;
    try {
      System.out.println(this.URL+code);
      url = new java.net.URL(this.FULL+code);
      ClickListener listener = new ClickListener(this.URL+code);
      BufferedImage image;
      image = ImageIO.read(url);
      panel = new ImagePanel(image);
      panel.addMouseListener(listener);
      synchronized (list) {
      System.out.println(this.list.add(panel));
      System.out.println("add");
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public ImagePanel createImagePanel(){
    java.net.URL url;
    ImagePanel panel=null;
    try {
      System.out.println(this.URL+code);
      url = new java.net.URL(this.URL+code);
      BufferedImage image;
      image = ImageIO.read(url);
      panel = new ImagePanel(image);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
    return panel;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {

  }

}
