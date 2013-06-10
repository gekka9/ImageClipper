package imageClipper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFileReader {
  
  ImageFileReader(){
    System.out.println("read");
  }
  
  public List<String> readCodes(){
    ArrayList<String> list = new ArrayList<String>();
    //FileChooserの作成
    JFileChooser chooser = new JFileChooser();
    chooser.setFileFilter(new FileNameExtensionFilter("*.txt","txt"));
    
    //選択されたファイルの読み取り
    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      System.out.print("Loading source code...");
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
