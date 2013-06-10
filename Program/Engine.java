package imageClipper;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class Engine {
  static final int WIDTH=600;
  static final int HEIGHT=800;
  
  /**
   * @param args
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
    aWindow = new JFrame("test");
    //aWindow.getContentPane().add(aView);
    aWindow.setMinimumSize(new Dimension(400, 300));
    aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    aWindow.setSize(WIDTH, HEIGHT);
    aWindow.setLocation(200,200);
    aWindow.setVisible(true);
    JPanel mainPanel = new JPanel();
    mainPanel.setBounds(0, 0, WIDTH, HEIGHT);
    aWindow.add(mainPanel);
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setPreferredSize(new Dimension(400,400));
    mainPanel.add(scrollPane);
    JViewport view = scrollPane.getViewport();
    int x = 20;
    int y=20;
    
    for(ImagePanel panel:imagePanelList){
      panel.setBounds(x, y, 120, 120);
      view.add(panel);
      x+=140;
      if(x>WIDTH-130){
        x=20;
        y+=130;
      }
    }
    //view.add(new JPanel());
    scrollPane.setViewport(view);
    //aWindow.add(scrollPane);
    aWindow.repaint();
  }

}
