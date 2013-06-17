package imageClipper;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

/**
 * 画像を持つパネル
 */
public class ImagePanel extends JPanel{
  
  /**
   * 画像を持つフィールド
   */
  private BufferedImage image =null;
  
  /**
   * コンストラクタ
   */
  ImagePanel(BufferedImage image, int width){
    super();
    //画像の読み込みに失敗していたらnullを保持する
    if(image == null){
      this.image=null;
    }else{
      /**
       * 画像のリサイズを行う
       */
      AffineTransformOp ato = null;
      int height = (int)(((double)width /image.getWidth()) * (double)image.getHeight());
      BufferedImage distImage = new BufferedImage(width, height, image.getType());
      ato = new AffineTransformOp(AffineTransform.getScaleInstance((double)width / image.getWidth(),(double) height / image.getHeight()),null);
      ato.filter(image, distImage);
      this.image=distImage;
        this.setFile();
//        System.out.println("ok");
        this.setTransferHandler(new TransferHandler() {
            @Override public int getSourceActions(JComponent c) {
                return COPY_OR_MOVE;
            }
            @Override protected Transferable createTransferable(JComponent c) {
                File tmpfile = getFile();
                if(tmpfile==null) {
                    return null;
                }else{
                    return new TempFileTransferable(tmpfile);
                }
            }
            @Override protected void exportDone(JComponent c, Transferable data, int action) {
                cleanup(c, action == MOVE);
            }
            private void cleanup(JComponent c, boolean removeFile) {
                if(removeFile) {
//                    clearFile();
                    c.repaint();
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                //                System.out.println(e);
                JComponent c = (JComponent)e.getSource();
                c.getTransferHandler().exportAsDrag(c, e, TransferHandler.COPY);
            }
        });

    }
      
  }
    private File file = null;
    private File getFile() {
        return file;
    }
    private void setFile() {
        try{
            this.file = File.createTempFile("image",".jpg");
//            System.out.println(tmp.getAbsolutePath());
//            this.file = new File(tmp.getParent()+"/test.txt");
//            tmp.delete();
//            System.out.println(this.file.getAbsolutePath());
            this.file.deleteOnExit();
            //          File file = new File("c:¥¥tmp¥¥test.txt");
//            FileWriter filewriter = new FileWriter(this.file);
            ImageIO.write(this.image, "jpeg", this.file);
            
//            filewriter.write("Hello World");
//            filewriter.close();
        }catch(IOException e){
            System.out.println(e);
        }
        System.out.println(this.file);
        //        label.setIcon(i2);
//        label.setText("tmpfile#exists(): true(draggable)");
    }

  /**
   * 画像を描画する
   */
  public void paintComponent(Graphics g){
    g.drawImage(this.image, 0, 0, this);
  }
  
  /**
   * テスト用クラス。未実装
   */
  public static void main(String[] args) {

  }
  
  /**
   * 画像を応答する
   */
  public BufferedImage getImage(){
    return this.image;
  }

}

class TempFileTransferable implements Transferable{
    private final File file;
    public TempFileTransferable(File file) {
        this.file = file;
    }
    @Override public Object getTransferData(DataFlavor flavor) {
        return Arrays.asList(file);
    }
    @Override public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] { DataFlavor.javaFileListFlavor };
    }
    @Override public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.javaFileListFlavor);
    }
}

