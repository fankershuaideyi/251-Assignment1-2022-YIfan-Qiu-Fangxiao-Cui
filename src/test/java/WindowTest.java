import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.swing.*;

class WindowTest {
    @Test
    void openFile() {
        Window window = Mockito.mock(Window.class);
        window.open();
        Mockito.verify(window).open();
    }

    @Test
    void savetest() {
       Window window = Mockito.mock(Window.class);
       window.saveAstxt();
        Mockito.verify(window).saveAstxt();
    }
}