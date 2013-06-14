package imageClipper;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * モデル。
 */
public class Model {
  static final int WIDTH=600;
  static final int HEIGHT=800;
  
  /**
   * 同じディレクトリにある.txtファイルを読み込み、ビューを生成する
   * @throws InterruptedException
   */
  public void execute() throws InterruptedException{
    System.out.println("model");
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
    View view = new View(fileList);
  }
}
