package imageClipper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * TwitPicのURL末尾のコードのリストファイルを読み込むクラス
 */
public class ImageFileReader {
  
  /**
   * コンストラクタ
   */
  ImageFileReader(){
  }
  
  /**
   * TwitPicのURL末尾のコードのリストファイルを読み込み、一行ずつを収めたリストを応答する。
   */
  public List<String> readCodes(){
    ArrayList<String> list = new ArrayList<String>();
    //FileChooserの作成
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
    
    //選択されたファイルの読み取り
    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      try {
        BufferedReader inb = new BufferedReader(new FileReader(chooser.getSelectedFile()));
        String line="";
        while( ( line = inb.readLine() ) != null ){
          list.add(line);
        }
        inb.close();
      }
      catch( IOException e ){
        e.printStackTrace();
      }
      return list;
    }
    return null;
  }

}
