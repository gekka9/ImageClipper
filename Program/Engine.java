package imageClipper;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

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
    ImageFileReader reader = new ImageFileReader();
    ArrayList<String> codeList=(ArrayList<String>) reader.readCodes();
    ArrayList<ImagePanel> imagePanelList= new ArrayList<ImagePanel>();
    for(String code:codeList){
      ImageFactory factory = new ImageFactory(code,imagePanelList);
      //imagePanelList.add(factory.createImagePanel());
      factory.start();
    }
    int length=0;
    while(true){
     Thread.sleep(100);
     length=imagePanelList.size();
     if(length==codeList.size()){
       System.out.println("break");
       break;
     }
    }
    System.out.println("test");
    JFrame aWindow;
    aWindow = new JFrame("ImageClipper");
    //aWindow.getContentPane().add(aView);
    aWindow.setMinimumSize(new Dimension(400, 300));
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    aWindow.setSize(WIDTH, HEIGHT);
    aWindow.setLocation(200,200);
    aWindow.setVisible(true);
   
//    JPanel mainPanel = new JPanel();
//    mainPanel.setBounds(0, 0, WIDTH, HEIGHT);
//    mainPanel.setBackground(Color.black);
//    aWindow.add(mainPanel);
    
    int x = 20;
    int y=20;
    JPanel allImage = new JPanel();
    allImage.setPreferredSize(new Dimension(WIDTH, HEIGHT));
//    allImage.setBounds(0, 0, WIDTH, HEIGHT);
    
    //aWindow.add(new JPanel());
    for(ImagePanel panel:imagePanelList){
      panel.setPreferredSize(new Dimension(120, 120));
      allImage.add(panel);
      //aWindow.add(panel);
      panel.setBounds(x, y, 120, 120);
      x+=140;
      if(x>WIDTH-130){
        x=20;
        y+=130;
      }
//      panel.repaint();
    }
    JScrollPane scrollpane = new JScrollPane(allImage);
    scrollpane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    aWindow.getContentPane().add(scrollpane);
    
    
    
//    mainPanel.add(allImage);
    //aWindow.add(scrollPane);
//    aWindow.repaint();
  }

}
