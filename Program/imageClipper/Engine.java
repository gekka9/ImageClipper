package imageClipper;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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
    if(codeList == null){
      System.out.println(System.getProperty("line.separator")+"canceled");
      return;
    }
    System.out.println("done!");
    System.out.print("Generating ImagePanel...");

    //メインフレームの作成
    JFrame mainFrame;
    mainFrame = new JFrame("ImageClipper");
    mainFrame.setMinimumSize(new Dimension(WIDTH, 500));
    mainFrame.setMaximumSize(new Dimension(WIDTH, 600));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setLocation(50,50);
    JScrollPane scrollpane = GallaryFactory.createGallary(codeList);
    JScrollPane scrollpane2 = GallaryFactory.createGallary(codeList);
    
    JTabbedPane tab = new JTabbedPane(); 
    tab.setBounds(0, 30, WIDTH, HEIGHT);
    tab.add("1",scrollpane);
    tab.add("2",scrollpane2);
    
    mainFrame.getContentPane().add(tab);
    //メインフレームを可視化する
    mainFrame.setVisible(true);
    System.out.println("Done!");
  }

}
