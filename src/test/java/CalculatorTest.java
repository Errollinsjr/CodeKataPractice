import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void shouldReturnZeroOnEmptyString() {
        assertEquals(0, Calculator.add(""));
    }

    @Test
    public void shouldReturnNumberOnNumber() {
        assertEquals(1, Calculator.add("1"));
    }

    @Test
    public void shouldReturnSumOnTwoNumbersDelimitedByComma() {
        assertEquals(3, Calculator.add("1,2"));
    }

    @Test
    public void shouldReturnSumOnMultipleNumbers() {
        assertEquals(6, Calculator.add("1,2,3"));
    }

}