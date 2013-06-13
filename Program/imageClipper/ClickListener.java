package imageClipper;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 * ImagePanelがクリックされたとき処理を行うクラス
 *
 */
public class ClickListener implements MouseListener{

  private String URL=null;
  
  /**
   * コンストラクタ
   */
  ClickListener(String url){
    this.URL = url;
  }
  
  /**
   * テスト用メインメソッド<br>
   * 未実装
   */
  public static void main(String[] args) {
      
  }

  /**
   * 画像がクリックされたことを検知し、その画像のURLをクリップボードにコピーする。<br>
   * 将来的にダブルクリックに何らかの昨日を実装しようと考えているが未実装
   */
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() >= 2){
      //ダブルクリック時の処理
      //JOptionPane.showMessageDialog(null, "ダブルクリック");
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
