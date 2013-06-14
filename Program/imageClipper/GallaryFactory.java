package imageClipper;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 * コードのリストを受け取り、ImagePanelの集合からJScrollPaneを生成し、応答する
 *
 */
public class GallaryFactory {
  static final int WIDTH=600;
  static final int HEIGHT=800;
  
  /**
   * コードのリストを受け取り、ImagePanelの集合からJScrollPaneを生成し、応答する
   */
  public static JScrollPane createGallary(List<String> codeList){
    ArrayList<ImagePanel> imagePanelList= new ArrayList<ImagePanel>();
    for(String code:codeList){
      ImageFactory factory = new ImageFactory(code,imagePanelList);
      //imagePanelList.add(factory.createImagePanel());
      factory.start();
    }
    
    //ImagePanelの生成スレッドがすべて終了したら先に進む
    int length=0;
    while(true){
     try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
     length=imagePanelList.size();
     if(length==codeList.size()){
       break;
     }
    }

    System.out.println("Done!");
    System.out.print("Showing Images...");
    //画像表示に必要な高さを得る
    int imageHeight = (int) (Math.ceil(imagePanelList.size()/4)*130);
    
    //画像を並べるためのパネルを作成
    JPanel allImage = new JPanel();
    allImage.setBackground(Color.gray);
    allImage.setPreferredSize(new Dimension(WIDTH-50, imageHeight));
    
    //パネルに画像を並べていく
    int x = 20;
    int y = 20;
    for(ImagePanel panel:imagePanelList){
      //もしImagePanelがBufferedImageにnullを持っていれば読み込みに失敗しているため、無視する
      if(panel.getImage() !=null){
        panel.setPreferredSize(new Dimension(120, 120));
        allImage.add(panel);
        panel.setBounds(x, y, 120, 120);
        //折り返しの処理
        x+=140;
        if(x>WIDTH-130){
          x=20;
          y+=130; 
        } 
      }
    }
    
    //スクロールペインの設定
    JScrollPane scrollpane = new JScrollPane(allImage);
    scrollpane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    
    return scrollpane;
  }

}
