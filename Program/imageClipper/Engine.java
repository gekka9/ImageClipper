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
    //カレントディレクトリのテキストファイルを読み込んでリストに追加
    File[] textFiles = new File(".").listFiles();
    ArrayList<File> fileList=new ArrayList<File>(); 
    for (File aFile : textFiles){
      if(aFile.getAbsolutePath().endsWith(".txt")){
        fileList.add(aFile);
        System.out.println(aFile.getName());
      }
    }
    
    //もしテキストファイルがなければ、ユーザに選ばせる
    if(fileList.size()==0){
      //FileChooserの作成
      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
      
      //選択されたファイルの読み取り
      if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        fileList.add(chooser.getSelectedFile());
      }else{
        return;
      }
    }

    //メインフレームの作成
    this.mainFrame = new JFrame("ImageClipper");
    this.mainFrame.setMinimumSize(new Dimension(WIDTH, 500));
    this.mainFrame.setMaximumSize(new Dimension(WIDTH, 600));
    this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mainFrame.pack();
    this.mainFrame.setLocation(50,50);
    DnDListener listener = new DnDListener(this);
    new DropTarget(this.mainFrame, listener);
    
    this.scrollPaneList = new ArrayList<JScrollPane>();
    
    //タブを作成しする
    this.tabs = new JTabbedPane(); 
    this.tabs.setBounds(0, 0, WIDTH, HEIGHT);
    
    //ファイルのリストからスクロールペインを生成し、タブに追加
    for(File aFile:fileList){
      ArrayList<String> codeList = (ArrayList<String>) ImageFileReader.readCodes(aFile);
      JScrollPane scrollpane = GallaryFactory.createGallary(codeList);
      this.tabs.add(aFile.getName(),scrollpane);
    }
    
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
