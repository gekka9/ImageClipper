package imageClipper;

import java.awt.Dimension;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * ビュー
 *
 */
public class View extends JFrame{
  private ArrayList<JScrollPane> scrollPaneList;
  private JTabbedPane tabs;
  private Model model;
  private List<File> fileList;
  
  /**
   * コンストラクタ
   */
  View(Model model){
    super();
    this.model=model;
    this.fileList=model.getFileList();
    //メインフレームの作成
    this.setMinimumSize(new Dimension(Model.WIDTH, 500));
    this.setMaximumSize(new Dimension(Model.WIDTH, 600));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setLocation(50,50);
    DnDListener listener = new DnDListener(this);
    new DropTarget(this, listener);
    this.setVisible(true);
    this.scrollPaneList = new ArrayList<JScrollPane>();
    
    //タブを作成する
    this.tabs = new JTabbedPane(); 
    this.tabs.setBounds(0, 0, Model.WIDTH, Model.HEIGHT);
    
    //ファイルのリストからスクロールペインを生成し、タブに追加
    for(File aFile:this.fileList){
      ArrayList<String> codeList = (ArrayList<String>) ImageFileReader.readCodes(aFile);
      JScrollPane scrollpane = GallaryFactory.createGallary(codeList);
      this.tabs.add(aFile.getName().replaceAll(".dat", ""),scrollpane);
    }
    
    //メインフレームにタブを追加する
    this.getContentPane().add(tabs);
    //メインフレームを可視化する
    this.repaint();
    System.out.println("Done!");
  }
  
  /**
   * スクロールペインを追加し、再描画する
   */
  public void addScrollPane(String title,JScrollPane scrollPane){
    this.scrollPaneList.add(scrollPane);
    this.tabs.add(title,scrollPane);
    this.repaint();
  }
}
