import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void gerHorsesTest() {
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horsesList.add(new Horse("plotva" + i, 1));
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        assertEquals(horsesList, hippodrome.getHorses());
    }



    @Test
    void moveTest() {
        Horse plotva = Mockito.mock(Horse.class);
        Mockito.doNothing().when(plotva).move();
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horsesList.add(plotva);
        }
        Hippodrome hippodrome = new Hippodrome(horsesList);
        hippodrome.move();

        Mockito.verify(plotva, times(50)).move();
    }

}