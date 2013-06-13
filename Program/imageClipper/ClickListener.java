package imageClipper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * ImagePanelがクリックされたとき処理を行うクラス
 *
 */
public class ClickListener implements MouseListener{

  private String URL=null;
  private BufferedImage image;
  private boolean doubleClickable=true;
  
  /**
   * コンストラクタ
   */
  ClickListener(String url,BufferedImage image){
    this.image = image;
  }
  
  ClickListener(String url,BufferedImage image,Boolean doubleClickable){
    this.image = image;
    this.doubleClickable=doubleClickable;
  }
  
  /**
   * テスト用メインメソッド<br>
   * 未実装
   */
  public static void main(String[] args) {
      
  }

  /**
   * 画像がクリックされたことを検知し、その画像のURLをクリップボードにコピーする。<br>
   * ダブルクリックがされると画像を拡大表示する
   */
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() >= 2 && this.doubleClickable){
      //ダブルクリック時の処理
      ImagePanel panel = new ImagePanel(this.image,400);
      panel.addMouseListener(new ClickListener(this.URL,this.image,false));
      JFrame mainFrame = new JFrame("ImageClipper");
      int height = panel.getImage().getHeight();
      mainFrame.setMinimumSize(new Dimension(400, height));
      mainFrame.setMaximumSize(new Dimension(400, height));
      //mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.pack();
      mainFrame.setLocation(100,100);
      
      mainFrame.add(panel);
      panel.setBounds(0,0,400,height);
      mainFrame.setVisible(true);
      
   }else{
    //クリップボードにURLをコピーする
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    StringSelection selection = new StringSelection(this.URL);
    clipboard.setContents(selection, selection);
    
    //アラートを出す
    //JOptionPane.showMessageDialog(null, "URLをクリップボードにコピーしました。");
   }
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {
    
  }

  @Override
  public void mouseExited(MouseEvent arg0) {
    
  }

  @Override
  public void mousePressed(MouseEvent arg0) {
    
  }

  @Override
  public void mouseReleased(MouseEvent arg0) {
    
  }

}
