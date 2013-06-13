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

    //メインフレームの作成
    JFrame mainFrame;
    mainFrame = new JFrame("ImageClipper");
    mainFrame.setMinimumSize(new Dimension(WIDTH, 500));
    mainFrame.setMaximumSize(new Dimension(WIDTH, 600));
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.pack();
    mainFrame.setLocation(50,50);
    JScrollPane scrollpane = GallaryFactory.createGallary(codeList);
    mainFrame.getContentPane().add(scrollpane);
    
    //メインフレームを可視化する
    mainFrame.setVisible(true);
    System.out.println("Done!");
  }

}
