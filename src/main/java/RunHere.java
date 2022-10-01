import java.awt.*;

public class RunHere {

    public static void main(String[] args) {
        Toolkit tit = Toolkit.getDefaultToolkit();
        Dimension screenSize = tit.getScreenSize();
        final int width = screenSize.width;
        final int height = screenSize.height;
        Window window = new Window(width,height);
    }
}
