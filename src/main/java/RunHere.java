import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class RunHere {
    public static int width;
    public static int height ;
    public static void main(String[] args) throws IOException {
        Toolkit tit = Toolkit.getDefaultToolkit();
        Dimension screenSize = tit.getScreenSize();
        //Get screen length and height
        width = screenSize.width;
        height = screenSize.height;
        new Window();
    }
}
