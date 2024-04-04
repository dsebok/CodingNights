package adventofcode;

import io.CustomReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Exercise_03_1 {

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_03_1.txt");
        List<Long> partNumbers = findPartNumbers(input);
        long sum = partNumbers.stream()
                .mapToLong(a -> a)
                .sum();
        System.out.printf("The sum of the engine part numbers is: %d%n", sum);
    }

    private static List<Long> findPartNumbers(List<String> input) {
        List<Long> result = new ArrayList<>();
        Optional<PositionedNumber> optionalNumber = findNextNumber(input, 0 ,0);
        while (optionalNumber.isPresent()) {
            PositionedNumber number = optionalNumber.get();
            if (checkIfNumberContactsASymbol(input, number)) {
                result.add(number.value);
            }
            int searchStartIndex = number.endIndex + 1;
            int currentLineIndex = number.lineIndex;
            if (searchStartIndex >= input.get(currentLineIndex).length()) {
                currentLineIndex++;
                searchStartIndex = 0;
            }
            optionalNumber = findNextNumber(input, currentLineIndex, searchStartIndex);
        }
        return result;
    }

    /**
     *
     * @param input
     * @param startLineIndex
     * @param startIndex
     * @return
     */
    private static Optional<PositionedNumber> findNextNumber(List<String> input, int startLineIndex, int startIndex) {
        int currentStartIndex = startIndex;
        for (int lineIndex = startLineIndex; lineIndex < input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            for (int index = currentStartIndex; index < line.length(); index++) {
                char ch = line.charAt(index);
                if (Character.isDigit(ch)) {
                    PositionedNumber number = readNumber(line, lineIndex, index);
                    return Optional.of(number);
                }
            }
            currentStartIndex = 0;
        }
        return Optional.empty();
    }

    /**
     *
     * @param line
     * @param lineIndex
     * @param startIndex
     * @return
     */
    private static PositionedNumber readNumber(String line, int lineIndex, int startIndex) {
        String linePart = line.substring(startIndex);
        String textNumber = linePart.chars()
                .takeWhile(Character::isDigit)
                .mapToObj(codePoint -> String.valueOf((char) codePoint))
                .collect(Collectors.joining());
        int endIndex = startIndex + textNumber.length() - 1;
        return new PositionedNumber(lineIndex, startIndex, endIndex, Long.parseLong(textNumber));
    }

    private static boolean checkIfNumberContactsASymbol(List<String> input, PositionedNumber number) {
        // TODO: implement
        // symbol: if not digit AND not '.' char
        return false;
    }

    private record PositionedNumber(int lineIndex, int startIndex, int endIndex, long value) {}
}
