package adventofcode;

import io.CustomReader;

import java.util.List;

public class Exercise_01 {

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_01.txt");
        long result = input.stream()
                .mapToInt(Exercise_01::readCalibrationValue)
                .sum();
        System.out.println(result);
    }

    private static int readCalibrationValue(String line) {
        List<Character> digits = line.chars()
                .filter(ch -> ch > 47 && ch < 58)
                .mapToObj(ch -> (char) ch)
                .toList();
        char first = digits.get(0);
        char last = digits.get(digits.size() - 1);
        return Integer.parseInt("" + first + last);
    }
}
