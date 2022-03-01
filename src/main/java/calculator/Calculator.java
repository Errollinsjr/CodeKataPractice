package calculator;

import ch.lambdaj.function.convert.Converter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.hamcrest.Matchers.*;

import static ch.lambdaj.Lambda.*;

public class Calculator {
    public static int add(String text) {
        String[] numberPosition = numberConverter(text);
        List<Integer> numbers = convert(numberPosition, toInt());
        ensureAllNegative(numbers);

        return sum(numbers).intValue();
    }

    private static void ensureAllNegative(List<Integer> numbers) {
        List<Integer> negatives = filter(lessThan(0), numbers);
        if(negatives.size() > 0) {
            throw new RuntimeException("Negatives not allowed: " + join(negatives));
        }
    }

    private static String[] numberConverter(String text) {
        if(text.isEmpty()) {
            return new String[0];
        }
        else if(usesCustomDelimiterSyntax(text)) {
            return splitUsingCustomDelimiterSyntax(text);
        } else {
            return splitUsingCommaOrNewLine(text);
        }
    }

    private static boolean usesCustomDelimiterSyntax(String text) {
        return text.startsWith("//");
    }

    private static String[] splitUsingCommaOrNewLine(String text) {
        String[] tokens = text.split(",|\n|;|/");

        return tokens;
    }

    private static String[] splitUsingCustomDelimiterSyntax(String text) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        m.matches();
        String customDelimiter = m.group(1);
        String numbers = m.group(2);

        return numbers.split(Pattern.quote(customDelimiter));
    }

    public static Converter<String, Integer> toInt() {
        return new Converter<String, Integer>() {

            public Integer convert(String from) {
                return toInt(from);
            }
        };
        //can be Calculator::toInt
    }

    private static int toInt(String text) throws NumberFormatException {
        return Integer.parseInt(text);
    }
}