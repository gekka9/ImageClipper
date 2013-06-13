package imageClipper;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;


/**
 * コードを1つとListを受け取り、ImagePanelのインスタンスを生成し、Listに追加する。
 */
public class ImageFactory extends Thread{

  /**
   * コードを保持する
   */
  private String code=null;
  
  /**
   * 生成したImagePanelを追加するためのListを保持する
   */
  private List<ImagePanel> list=null;
  
  /**
   * フルサイズの画像を取得するためのURLのコード以前の部分を持つ定数
   */
  private final String FULL = "http://twitpic.com/show/full/";
  
  /**
   * TwitPicのページのコード以前の部分を持つ定数
   */
  private final String URL = "http://twitpic.com/";
  
  /**
   * コンストラクタ
   */
  ImageFactory(String code,List<ImagePanel> list){
    this.code=code;
    this.list=list;
  }
  
  /**
   * ImagePanelのインスタンスを生成し、リストに追加する
   */
  public void run(){
    java.net.URL url;
    ImagePanel panel=null;
    try {
      url = new java.net.URL(this.FULL+code);
      BufferedImage image;
      image = ImageIO.read(url);
      ClickListener listener = new ClickListener(this.URL+code,image);
      panel = new ImagePanel(image,120);
      panel.addMouseListener(listener);
      synchronized (list) {
        this.list.add(panel);
        //System.out.println(this.list.add(panel));
        //System.out.println("add");
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * ImagePanelのインスタンスを作成し、応答する
   */
  public ImagePanel createImagePanel(){
    java.net.URL url;
    ImagePanel panel=null;
    try {
      System.out.println(this.URL+code);
      url = new java.net.URL(this.URL+code);
      BufferedImage image;
      image = ImageIO.read(url);
      panel = new ImagePanel(image,120);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
    return panel;
  }
  
  /**
   * テスト用クラス。未実装。
   */
  public static void main(String[] args) {

  }

}
