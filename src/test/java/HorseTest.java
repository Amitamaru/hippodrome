import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void checkNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0, 0));
    }

    @Test
    void checkMsgExceptionNameIsNull() {
        try {
            new Horse(null, 0, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n"})
    void checkEmptyName(String arg) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 0, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n"})
    void checkMsgExceptionNameIsBlank(String arg) {
        try {
            new Horse(arg, 0, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -3, -4})
    void checkNegativeSpeed(double arg) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("plotva", arg, 0));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -3, -4})
    void checkMsgExceptionNegativeSpeed(double arg) {
        try {
            new Horse("plotva", arg, 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -3, -4})
    void checkNegativeDistance(double arg) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("plotva", 0, arg));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -2, -3, -4})
    void checkMsgExceptionNegativeDistance(double arg) {
        try {
            new Horse("plotva", 0, arg);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }


    @Test
    void getName() {
        String expectedName = "plotva";
        Horse horse = new Horse(expectedName, 0, 0);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    void getSpeed() {
        double expectedSpeed = 123.4;
        Horse horse = new Horse("plotva", expectedSpeed, 0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    void getDistance() {
        double expectedDistance = 123.4;
        Horse horseThreeParams = new Horse("plotva", 0, expectedDistance);
        assertEquals(expectedDistance, horseThreeParams.getDistance());
    }

    @Test
    void getZeroDistance() {
        Horse horseTwoParams = new Horse("plotva", 0);
        assertEquals(0, horseTwoParams.getDistance());
    }

    @Test
    void moveGetRandom() {
        try (MockedStatic<Horse> utility = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("plotva", 1, 1);
            horse.move();

            utility.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.1, 0.3, 1.2",
            "0.5, 0.2, 2.2",
            "4.3, 3.2, 1.8"})
    void moveSetDistance(double speed, double distance, double imitatingRandom) {
        try (MockedStatic<Horse> utility = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("plotva", speed, distance);
            utility.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(imitatingRandom);
            horse.move();

            assertEquals(distance + speed * imitatingRandom, horse.getDistance());
        }
    }


}