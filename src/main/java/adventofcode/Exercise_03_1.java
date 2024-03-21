package adventofcode;

import io.CustomReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Exercise_03_1 {

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_03_1.txt");
        // findPartNumbers --> List<Long>
        // getSum
        // syso
    }

    private static List<Long> findPartNumbers(List<String> input) {
        List<Long> result = new ArrayList<>();
        Optional<PositionedNumber> foundNumber = findFirstNumber(input);
        while (foundNumber.isPresent()) {
            PositionedNumber number = foundNumber.get();
            if (checkIfNumberContactsASymbol(input, number)) {
                result.add(number.value);
            }
            foundNumber = findNextNumber(input, number);
        }
        return result;
    }

    private static Optional<PositionedNumber> findFirstNumber(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);
                if (Character.isDigit(ch)) {
                    int row = i;
                    int startIndex = j;
                    StringBuilder sb = new StringBuilder(Character.toString(ch));
                    int k = j;
                    while (k < line.length()) {
                        char endCh = line.charAt(k);
                        if (Character.isDigit(endCh)) {
                            sb.append(endCh);
                        } else {
                            break;
                        }
                        k++;
                    }
                    int endIndex = k - 1;
                    PositionedNumber p = new PositionedNumber(row, startIndex, endIndex, Long.parseLong(sb.toString()));
                    return Optional.of(p);
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<PositionedNumber> findNextNumber(List<String> input, PositionedNumber number) {
        // TODO: implement
        return Optional.empty();
    }

    private static boolean checkIfNumberContactsASymbol(List<String> input, PositionedNumber number) {
        // TODO: implement
        return false;
    }

    private record PositionedNumber(int row, int startIndex, int endIndex, long value) {
    }
}
