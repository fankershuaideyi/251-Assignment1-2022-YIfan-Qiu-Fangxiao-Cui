import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class searchTest {

    @Test
    void search_word() {
        search search= Mockito.mock(search.class);
        search.search_word();
        Mockito.verify(search).search_word();
    }
}