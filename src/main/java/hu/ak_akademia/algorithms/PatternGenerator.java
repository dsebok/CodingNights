package hu.ak_akademia.algorithms;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Source: https://www.codewars.com/kata/598ab728062fc49a22000410/java
 *
 * parameter: 1, generate pattern:
 * x
 *
 * parameter: 2, generate pattern:
 *  x
 * x x
 *  x
 *
 * parameter: 3, generate pattern:
 *   x
 *    x
 * x o x
 *  x
 *   x
 *
 * parameter: 4, generate pattern:
 *    x
 *     x
 *    o x
 * x o o x
 *  x o
 *   x
 *    x
 *
 * parameter: 5, generate pattern:
 *     x
 *      x
 *     o x
 *      o x
 * x o x o x
 *  x o
 *   x o
 *    x
 *     x
 *
 * parameter: 6, generate pattern:
 *      x
 *       x
 *      o x
 *       o x
 *      x o x
 * x o x x o x
 *  x o x
 *   x o
 *    x o
 *     x
 *      x
 */
public class PatternGenerator {

    public static final char SIGN_1 = 'X';
    public static final char SIGN_2 = 'O';
    public static final char SEPARATOR = ' ';

    public static void main(String[] args) {
        printPattern(6);
    }

    public static void printPattern(int size) {
        printHalfPart(size, PatternGenerator::createUpperLine);
        System.out.println(createMiddleLine(size));
        printHalfPart(size, PatternGenerator::createLowerLine);
    }

    public static void printHalfPart(int size, BiFunction<Integer, Integer, String> lineCreator) {
        for (int i = 1; i < size; i++) {
            System.out.println(lineCreator.apply(size, i));
        }
    }

    public static String createMiddleLine(int size) {
        return createLowerLine(size, 0) + createReverseSignSequence(size - 1);
    }

    public static String createUpperLine(int size, int i) {
        String padding = createPadding(size - 1);
        String signSequence = createReverseSignSequence(i);
        return padding + signSequence;
    }

    public static String createLowerLine(int size, int i) {
        String padding = createPadding(i);
        String signSequence = createSignSequence(size - i);
        return padding + signSequence;
    }

    public static String createReverseSignSequence(int n) {
        return new StringBuilder(createSignSequence(n)).reverse().toString();
    }

    public static String createSignSequence(int n) {
        StringBuilder sequence = new StringBuilder();
        int i = 1;
        while (i <= n) {
            if (i % 2 == 0) {
                sequence.append(SEPARATOR);
            } else if (i % 4 == 1) {
                sequence.append(SIGN_1);
            } else {
                sequence.append(SIGN_2);
            }
            ++i;
        }
        return sequence.toString();
    }

    public static String createPadding(int n) {
        return Stream.iterate(SEPARATOR, i -> i)
                .limit(n)
                .map(String::valueOf)
                .collect(Collectors.joining());
//                .reduce((a, b) -> a + b)
//                .orElse("");
    }
}
