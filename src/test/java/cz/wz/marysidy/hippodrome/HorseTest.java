package cz.wz.marysidy.hippodrome;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    void constructor_NameIsNull_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 15.0, 5.0));
    }

    @Test
    void constructor_NameIsNull_ThrowsExceptionWithCorrectMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 15.0, 5.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void constructor_NameIsBlank_ThrowsIllegalArgumentException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 15.0, 5.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void constructor_NameIsBlank_ThrowsExceptionWithCorrectMessage(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 15.0, 5.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructor_NegativeSpeed_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -15.0, 5.0));
    }

    @Test
    void constructor_NegativeSpeed_ThrowsExceptionWithCorrectMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Bucephalus", -15.0, 5.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor_NegativeDistance_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 15.0, -5.0));
    }

    @Test
    void constructor_NegativeDistance_ThrowsExceptionWithCorrectMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Bucephalus", 15.0, -5.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ReturnsCorrectName() {
        Horse horse = new Horse("Bucephalus", 15.0, 5.0);
        assertEquals("Bucephalus", horse.getName());
    }

    @Test
    void getSpeed_ReturnsCorrectSpeed() {
        Horse horse = new Horse("Bucephalus", 15.0, 5.0);
        assertEquals(15.0, horse.getSpeed());
    }

    @Test
    void getDistance_ReturnsCorrectDistance() {
        Horse horse = new Horse("Bucephalus", 15.0, 5.0);
        assertEquals(5.0, horse.getDistance());
    }

    @Test
    void constructor_WithoutDistance_SetsZeroDistance() {
        Horse horse = new Horse("Bucephalus", 15.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    void move_ShouldCallGetRandomDoubleWithCorrectParameters() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Bucephalus", 15.0, 5.0);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "0.2, 8",     // 5 + 15 * 0.2 = 8
            "0.5, 12.5",  // 5 + 15 * 0.5 = 12.5
            "0.89, 18.35" // 5 + 15 * 0.89 = 18.35
    })
    void move_ShouldCalculateDistanceCorrectly(double randomValue, double expectedDistance) {

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Bucephalus", 15.0, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            horse.move();
            assertEquals(expectedDistance, horse.getDistance(), 0.0001);
        }
    }
}