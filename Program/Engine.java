package imageClipper;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * アプリケーションの動作のためのエンジン
 */
public class Engine {
  static final int WIDTH=600;
  static final int HEIGHT=800;
  
  /**
   * テスト用メインクラス
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Engine engine = new Engine();
    try {
      engine.execute();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * メインとなるフレームを作成し、ImageFactoryに作らせたImagePanelを追加する。
   * @throws InterruptedException
   */
  private void execute() throws InterruptedException{
    //ファイルを読み込んでImagePanelを作らせる
    ImageFileReader reader = new ImageFileReader();
    System.out.print("Loading codes...");
    ArrayList<String> codeList=(ArrayList<String>) reader.readCodes();
    System.out.println("done!");
    System.out.print("Generating ImagePanel...");
    ArrayList<ImagePanel> imagePanelList= new ArrayList<ImagePanel>();
    for(String code:codeList){
      ImageFactory factory = new ImageFactory(code,imagePanelList);
      //imagePanelList.add(factory.createImagePanel());
      factory.start();
    }
    
    //ImagePanelの生成スレッドがすべて終了したら先に進む
    int length=0;
    while(true){
     Thread.sleep(100);
     length=imagePanelList.size();
     if(length==codeList.size()){
       break;
     }
    }

    System.out.println("Done!");
    System.out.print("Showing Images...");
    //画像表示に必要な高さを得る
    int imageHeight = (imagePanelList.size()/4)*130;
    
    //メインフレームの作成
    JFrame mainFrame;
    mainFrame = new JFrame("ImageClipper");
    mainFrame.setMinimumSize(new Dimension(WIDTH, 500));
    mainFrame.setMaximumSize(new Dimension(WIDTH, 600));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setLocation(50,50);
   
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
    mainFrame.getContentPane().add(scrollpane);
    
    //メインフレームを可視化する
    mainFrame.setVisible(true);
    System.out.println("Done!");
  }

}
