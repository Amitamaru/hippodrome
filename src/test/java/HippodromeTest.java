import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void checkNullParameter() {
        assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
    }

    @Test
    void checkMsgNullParameter() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be null.", e.getMessage());
        }
    }

    @Test
    void checkEmptyListParameter() {
        List<Horse> emptyList = new ArrayList<>(0 );
        assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(emptyList));
    }

    @Test
    void checkMsgEmptyListParameter() {
        try {
            List<Horse> emptyList = new ArrayList<>(0 );
            new Hippodrome(emptyList);
        } catch (IllegalArgumentException e) {
            assertEquals("Horses cannot be empty.", e.getMessage());
        }
    }

}