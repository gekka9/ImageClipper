package imageClipper;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    File codeFile = null;
    String fileTitle=null;
    
    //FileChooserの作成
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
    
    //選択されたファイルの読み取り
    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      codeFile=chooser.getSelectedFile();
      fileTitle=codeFile.getName();
      fileTitle=fileTitle.replaceAll(".txt", "");
    }else{
      return;
    }
    //ファイルを読み込んでImagePanelを作らせる
    System.out.print("Loading codes...");
    ArrayList<String> codeList=(ArrayList<String>) ImageFileReader.readCodes(codeFile);
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
    tab.add(fileTitle,scrollpane);
    tab.add(fileTitle,scrollpane2);
    
    mainFrame.getContentPane().add(tab);
    //メインフレームを可視化する
    mainFrame.setVisible(true);
    System.out.println("Done!");
  }

}
