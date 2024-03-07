package adventofcode;

import io.CustomReader;

import java.util.ArrayList;
import java.util.List;

public class Exercise_03_1 {

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_03_1.txt");
        // findPartNumbers --> List<Long>
        // getSum
        // syso
    }

    private static List<Long> findPartNumbers(List<String> input) {
        List<Long> result = new ArrayList<>();
        // sequentially findNextNumber(row, i) --> row, i, size
        // check if number is (engine) part --> check all the surroundings of the number --> don't forget the boundaries
        // if checked --> add to result
        return result;
    }

    private static PositionedNumber findNextNumber(int row, int i) {
        // TODO: implement
        return null;
    }

    private static boolean checkIfNumberContactsASymbol() {
        // TODO: implement
        return false;
    }

    private static class PositionedNumber {
        private int row;
        private int startIndex;
        private int size;
    }
}
