package imageClipper;

import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DnDConstants;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * テキストファイルのドラッグアンドドロップを検知するクラス。
 */
public class DnDListener implements DropTargetListener, MouseListener {
  private Engine engine;
  /**
   * コンストラクタ
   */
  DnDListener(Engine engine){
    this.engine=engine;
  }
  
  /**
   * テキストファイルのドラッグアンドドロップを検知し、新たなJScrollPaneを作らせ、<br>
   * タブに追加させる
   */
  public void drop(DropTargetDropEvent dtde) {
    Transferable t = dtde.getTransferable();
      if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
          try {
            @SuppressWarnings("unchecked")
            List<File> fileList = (List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
            if(fileList.size() == 1 && (fileList.get(0).getAbsolutePath().endsWith(".txt"))) {
              dtde.dropComplete(true);
              File codeFile = fileList.get(0);
              engine.addScrollPane(codeFile.getName().replaceAll(".txt", ""),GallaryFactory.createGallary(ImageFileReader.readCodes(codeFile)));
            } else {
              dtde.dropComplete(false);
            }
          } catch (UnsupportedFlavorException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
          throw new RuntimeException(ex);
      }
    }
  }
  @Override
  public void mouseClicked(MouseEvent arg0) {
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
  @Override
  public void dragEnter(DropTargetDragEvent arg0) {
  }
  @Override
  public void dragExit(DropTargetEvent arg0) {
  }
  @Override
  public void dragOver(DropTargetDragEvent arg0) {
  }
  @Override
  public void dropActionChanged(DropTargetDragEvent arg0) {
  }
}