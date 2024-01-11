package adventofcode;

import io.CustomReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise_01_2 {

    private static final Map<String, String> DIGIT_TEXTS = new HashMap<>();

    static {
        DIGIT_TEXTS.put("one", "o1e");
        DIGIT_TEXTS.put("two", "t2o");
        DIGIT_TEXTS.put("three", "t3e");
        DIGIT_TEXTS.put("four", "f4r");
        DIGIT_TEXTS.put("five", "f5e");
        DIGIT_TEXTS.put("six", "s6x");
        DIGIT_TEXTS.put("seven", "s7n");
        DIGIT_TEXTS.put("eight", "e8t");
        DIGIT_TEXTS.put("nine", "n9e");
    }

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_01_2.txt");
        long result = input.stream()
                .mapToInt(Exercise_01_2::readCalibrationValue)
                .sum();
        System.out.println(result);
    }

    private static int readCalibrationValue(String line) {
        line = swapTextToDigit(line);
        List<Character> digits = line.chars()
                .filter(ch -> ch > 47 && ch < 58)
                .mapToObj(ch -> (char) ch)
                .toList();
        char first = digits.get(0);
        char last = digits.get(digits.size() - 1);
        return Integer.parseInt("" + first + last);
    }

    private static String swapTextToDigit(String text) {
        for (String key : DIGIT_TEXTS.keySet()) {
             text = text.replaceAll(key, DIGIT_TEXTS.get(key));
        }
        return text;
    }
}
