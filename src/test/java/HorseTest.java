import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void firstArgumentIsNullReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 2.0));
    }

    @Test
    void firstArgumentIsNullReturnStringNameCannotBeNull() {
        try {
            new Horse(null, 1.0, 2.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be null.", e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   ",
            "    "
    })
    void firstArgumentIsEmptyReturnIllegalArgumentException(String empty) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(empty, 1.0, 2.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "   ",
            "    "
    })
    void firstArgumentIsEmptyReturnStringNameCannotBeBlank(String empty) {
        try {
            new Horse(empty, 1.0, 2.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Name cannot be blank.", e.getMessage());
        }
    }

    @Test
    void secondArgumentIsNegativeNumberReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Comet", -1.0, 2.0));
    }

    @Test
    void secondArgumentIsNegativeNumberReturnStringSpeedCannotBeNegative() {
        try {
            new Horse("Comet", -1.0, 2.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Speed cannot be negative.", e.getMessage());
        }
    }

    @Test
    void thirdArgumentIsNegativeNumberReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Comet", 1.0, -2.0));
    }

    @Test
    void thirdArgumentIsNegativeNumberReturnStringDistanceCannotBeNegative() {
        try {
            new Horse("Comet", 1.0, -2.0);
        } catch (IllegalArgumentException e) {
            assertEquals("Distance cannot be negative.", e.getMessage());
        }
    }

    @Test
    void getName() {
        Horse horse = new Horse("Comet", 3, 3);
        assertEquals("Comet", horse.getName());
            }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Comet", 3, 3);
        assertEquals(3, horse.getSpeed());
    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Comet", 3, 3);
        assertEquals(3, horse.getDistance());
    }

    @Test
    public void moveUsedGetRandomDouble(){
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Comet", 1, 2);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }




    @ParameterizedTest
    @ValueSource(doubles = {
            0.2,
            0.3,
            0.4,
            0.5,
            0.6,
            0.7,
            0.8,
            0.9
    })
    void moveCorrectResult(double random) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            Horse horse = new Horse("Comet", 1, 2);
            horse.move();
            assertEquals(2 + 1 * random, horse.getDistance());
        }

    }
}