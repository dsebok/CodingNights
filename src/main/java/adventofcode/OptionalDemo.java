package adventofcode;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {
        String textNumber1 = "12345";
        String textNumber2 = "abcde12345";
        Optional<Integer> result1 = getResult(textNumber1);
        Optional<Integer> result2 = getResult(textNumber2);


        String resultMessage1 = result1.map(number -> String.format("This result is: %d.", number)).orElse("The was no result for this attempt!");
        String resultMessage2 = result2.map(number -> String.format("This result is: %d.", number)).orElse("The was no result for this attempt!");
        System.out.println(resultMessage1);
        System.out.println(resultMessage2);
    }

    private static Optional<Integer> getResult(String textNumber) {
        try {
            Integer number = Integer.parseInt(textNumber);
            return Optional.of(number);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
