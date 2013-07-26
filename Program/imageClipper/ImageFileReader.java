package imageClipper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  public static List<String> readCodes(){
    ArrayList<String> list = new ArrayList<String>();
    //FileChooserの作成
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("*.dat","dat"));
    
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
  static public List<String> readCodes(File codeFile){
    ArrayList<String> list = new ArrayList<String>();
      try {
        BufferedReader reader = new BufferedReader(new FileReader(codeFile));
        String line="";
        while( ( line = reader.readLine() ) != null ){
          list.add(line);
        }
        reader.close();
      }
      catch( IOException e ){
        e.printStackTrace();
      }
      return list;
  }
  
  public static boolean check(List<String> target){
    for(String aString: target){
      aString = aString.trim();
      System.out.println(aString);
      Pattern pattern = Pattern.compile("[0-9a-zA-Z]*");
      Matcher matcher = pattern.matcher(aString);
      System.out.println(!matcher.matches());
      if(aString.length()>6 || !matcher.matches()){
        System.out.println("不正なデータです");
        return false;
      }
    }
    return true;
  }
  
  public static boolean check(File target){
    ArrayList<String> list = (ArrayList<String>) readCodes(target);
    return check(list);
  }

}
