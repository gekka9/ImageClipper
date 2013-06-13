package imageClipper;

import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
  private JFrame mainFrame;
  private JTabbedPane tabs;
  private List<JScrollPane> scrollPaneList;
  
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
   * メインとなるフレームを作成し、ImageFactoryに作らせたImagePanelをタブに追加する。
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
    //ファイルを読み込ませる
    System.out.print("Loading codes...");
    ArrayList<String> codeList=(ArrayList<String>) ImageFileReader.readCodes(codeFile);
    if(codeList == null){
      System.out.println(System.getProperty("line.separator")+"canceled");
      return;
    }
    System.out.println("done!");
    System.out.print("Generating ImagePanel...");

    //メインフレームの作成
    this.mainFrame = new JFrame("ImageClipper");
    this.mainFrame.setMinimumSize(new Dimension(WIDTH, 500));
    this.mainFrame.setMaximumSize(new Dimension(WIDTH, 600));
    this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mainFrame.pack();
    this.mainFrame.setLocation(50,50);
    DnDListener listener = new DnDListener(this);
    new DropTarget(this.mainFrame, listener);
    
    //スクロールペインを作らせ、リストに追加する
    this.scrollPaneList = new ArrayList<JScrollPane>();
    JScrollPane scrollpane = GallaryFactory.createGallary(codeList);
    this.scrollPaneList.add(scrollpane);
    
    //タブを作成し、スクロールペインを追加する
    this.tabs = new JTabbedPane(); 
    this.tabs.setBounds(0, 0, WIDTH, HEIGHT);
    this.tabs.add(fileTitle,scrollpane);
    //tab.add(fileTitle,scrollpane2);
    
    //メインフレームにタブを追加する
    this.mainFrame.getContentPane().add(tabs);
    //メインフレームを可視化する
    this.mainFrame.setVisible(true);
    System.out.println("Done!");
  }
  
  /**
   * スクロールペインを追加し、再描画する
   */
  public void addScrollPane(String title,JScrollPane scrollPane){
    this.scrollPaneList.add(scrollPane);
    this.tabs.add(title,scrollPane);
    this.mainFrame.repaint();
  }

}
