package imageClipper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * モデル。
 */
public class Model {
  static final int WIDTH=600;
  static final int HEIGHT=800;
  private List<File> fileList;
  
  /**
   * 同じディレクトリにある.txtファイルを読み込み、ビューを生成する
   * @throws InterruptedException
   */
  public void execute() throws InterruptedException{
    System.out.println("model");
    //カレントディレクトリのテキストファイルを読み込んでリストに追加
    File[] textFiles = new File(".").listFiles();
    this.fileList=new ArrayList<File>(); 
    for (File aFile : textFiles){
      if(aFile.getAbsolutePath().endsWith(".dat") ){
        if(ImageFileReader.check(aFile)){
          fileList.add(aFile);
          System.out.println(aFile.getName());
        }
      }
    }
    
    //もしテキストファイルがなければ、ユーザに選ばせる
    if(fileList.size()==0){
      //FileChooserの作成
      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(new FileNameExtensionFilter("*.dat","dat"));
      
      //選択されたファイルの読み取り
      if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        fileList.add(chooser.getSelectedFile());
      }else{
        System.out.println("ファイル選択がキャンセルされました。終了します。");
        System.exit(1);
      }
    }
    View view = new View(this);
  }
  
  public List<File> getFileList(){
    return this.fileList;
  }
}
