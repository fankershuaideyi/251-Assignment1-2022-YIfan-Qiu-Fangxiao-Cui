import java.awt.*;

public class RunHere {
   public static int width;
   public static int height ;
    public static void main(String[] args) {
        Toolkit tit = Toolkit.getDefaultToolkit();
        Dimension screenSize = tit.getScreenSize();
        width = screenSize.width;
        height = screenSize.height;
        Window window = new Window();
    }
}
