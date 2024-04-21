package adventofcode;

import io.CustomReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercise_03_1 {

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_03.txt");
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
        List<Character> contactChars = getContactChars(input, number);
        return contactChars.stream()
                .anyMatch(ch -> !Character.isDigit(ch) && ch != '.');
    }

    private static List<Character> getContactChars(List<String> input, PositionedNumber number) {
        List<Character> result = new ArrayList<>();
        result.addAll(getLeftSide(input, number.startIndex, number.lineIndex));
        result.addAll(getRightSide(input, number.endIndex, number.lineIndex));
        result.addAll(getTopSide(input, number.startIndex, number.endIndex, number.lineIndex));
        result.addAll(getBottomSide(input, number.startIndex, number.endIndex, number.lineIndex));
        return result;
    }

    private static List<Character> getLeftSide(List<String> input, int startIndex, int lineIndex) {
        int colIndex = startIndex - 1;
        if (colIndex < 0) {
            return List.of();
        }
        List<Character> result = new ArrayList<>();
        if (lineIndex > 0) {
            result.add(input.get(lineIndex - 1).charAt(colIndex));
        }
        result.add(input.get(lineIndex).charAt(colIndex));
        if (lineIndex + 1 < input.size()) {
            result.add(input.get(lineIndex + 1).charAt(colIndex));
        }
        return result;
    }

    private static List<Character> getRightSide(List<String> input, int endIndex, int lineIndex) {
        int colIndex = endIndex + 1;
        if (colIndex >= input.get(lineIndex).length()) {
            return List.of();
        }
        List<Character> result = new ArrayList<>();
        if (lineIndex > 0) {
            result.add(input.get(lineIndex - 1).charAt(colIndex));
        }
        result.add(input.get(lineIndex).charAt(colIndex));
        if (lineIndex + 1 < input.size()) {
            result.add(input.get(lineIndex + 1).charAt(colIndex));
        }
        return result;
    }

    private static List<Character> getTopSide(List<String> input, int startIndex, int endIndex, int lineIndex) {
        int topLineIndex = lineIndex - 1;
        if (topLineIndex < 0) {
            return List.of();
        }
        String topLine = input.get(topLineIndex);
        return IntStream.rangeClosed(startIndex, endIndex)
                .mapToObj(topLine::charAt)
                .toList();
    }

    private static List<Character> getBottomSide(List<String> input, int startIndex, int endIndex, int lineIndex) {
        int bottomLineIndex = lineIndex + 1;
        if (bottomLineIndex >= input.size()) {
            return List.of();
        }
        String bottomLine = input.get(bottomLineIndex);
        return IntStream.rangeClosed(startIndex, endIndex)
                .mapToObj(bottomLine::charAt)
                .toList();
    }

    private record PositionedNumber(int lineIndex, int startIndex, int endIndex, long value) {}
}
