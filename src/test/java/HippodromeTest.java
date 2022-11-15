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

        assertEquals(horsesList, new Hippodrome(horsesList).getHorses());
    }

    @Test
    void moveTest() {
        Horse simulatedHorse = Mockito.mock(Horse.class);
        Mockito.doNothing().when(simulatedHorse).move();
        List<Horse> horsesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horsesList.add(simulatedHorse);
        }
        new Hippodrome(horsesList).move();

        Mockito.verify(simulatedHorse, times(50)).move();
    }

    @Test
    void getWinnerTest() {
        Horse someHorse = new Horse("plotva1", 1, 0.9);
        Horse expectedResult = new Horse("plotva2", 1, 2);
        Horse anotherHorse = new Horse("plotva3", 1, 1.2);
        Hippodrome hippodrome = new Hippodrome(List.of(someHorse, expectedResult, anotherHorse));

        assertEquals(expectedResult, hippodrome.getWinner());
    }

}