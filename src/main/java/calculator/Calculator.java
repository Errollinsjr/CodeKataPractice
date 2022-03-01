package calculator;

import ch.lambdaj.function.convert.Converter;

import static ch.lambdaj.Lambda.*;

public class Calculator {
    public static int add(String text) {
       if (text.isEmpty()) {
           return 0;
       }

       else if (text.contains(",")) {
           String[] numberPosition = text.split(",");
           convert(numberPosition, new Converter<String, Integer>() {});
           return toInt(numberPosition[0]) + toInt(numberPosition[1]);
       }

       else {
           return toInt(text);
       }
    }

    private static int toInt(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }
}
