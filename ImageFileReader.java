package imageClipper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFileReader {

  private final String URL = "/Users/otaki/Dropbox/Public/codes.txt";
  
  ImageFileReader(){
    System.out.println("read");
  }
  
  public List<String> readCodes(){
    ArrayList<String> list = new ArrayList<String>();
    try {
      //java.net.URL url = new java.net.URL(this.URL);
      //InputStream strm = url.openStream();
      
      //InputStreamReader in = new InputStreamReader(strm);
      FileReader in = new FileReader(new File(this.URL));
      BufferedReader inb = new BufferedReader(in);
      String line="";
      while( ( line = inb.readLine() ) != null ){
          list.add(line);
      }
      inb.close();
      in.close();
    }
    catch( IOException e ){
      e.printStackTrace();
    }
    //System.out.println(result);
    /*
    for (String line : list){
      System.out.println(line);
    }
    */
    return list;
  }

}
