package cz.wz.marysidy.hippodrome;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainTest {
    @Test
    @Timeout(value = 22)
    @Disabled("Takes too long, enable only when need")
    void main_ShouldCompleteWithin22Seconds() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}