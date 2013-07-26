package imageClipper;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 画像を持つパネル
 */
public class ImagePanel extends JPanel{
  
  /**
   * 画像を持つフィールド
   */
  private BufferedImage image =null;
  
  /**
   * コンストラクタ
   */
  ImagePanel(BufferedImage image, int width){
    super();
    //画像の読み込みに失敗していたらnullを保持する
    if(image == null){
      this.image=null;
    }else{
      /**
       * 画像のリサイズを行う
       */
      AffineTransformOp ato = null;
      int height = (int)(((double)width /image.getWidth()) * (double)image.getHeight());
      BufferedImage distImage = new BufferedImage(width, height, image.getType());
      ato = new AffineTransformOp(AffineTransform.getScaleInstance((double)width / image.getWidth(),(double) height / image.getHeight()),null);
      ato.filter(image, distImage);
      this.image=distImage;
    }
  }
  
  /**
   * 画像を描画する
   */
  public void paintComponent(Graphics g){
    g.drawImage(this.image, 0, 0, this);
  }
  
  /**
   * テスト用クラス。未実装
   */
  public static void main(String[] args) {

  }
  
  /**
   * 画像を応答する
   */
  public BufferedImage getImage(){
    return this.image;
  }

}
