package imageClipper;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class ClickListener implements MouseListener{

  private String URL=null;
  
  ClickListener(String url){
    this.URL = url;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
      
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() >= 2){
      JOptionPane.showMessageDialog(null, "ダブルクリック");
   }else{
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    StringSelection selection = new StringSelection(this.URL);
    clipboard.setContents(selection, selection);
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
