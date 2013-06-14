package imageClipper;

public class Main {
  /**
   * テスト用メインメソッド
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Model model = new Model();
    try {
      model.execute();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
