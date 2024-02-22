package adventofcode;

import io.CustomReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise_02_1 {

    private static final int RED_BASES = 12;
    private static final int GREEN_BASES = 13;
    private static final int BLUE_BASES = 14;

    public static void main(String[] args) {
        List<String> input = CustomReader.readFile("resources/input_02_1.txt");
        List<Game> games = input.stream()
                .map(Exercise_02_1::parse)
                .toList();
        long result1 = games.stream()
                .filter(g -> g.arePossibleBases(GREEN_BASES, RED_BASES, BLUE_BASES))
                .mapToLong(Game::getId)
                .sum();
        long result2 = games.stream()
                .map(Game::findMinimumSet)
                .mapToLong(Round::getPowerOfBalls)
                .sum();
        System.out.println(result2);
    }

    private static Game parse(String s) {
        // Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        String[] parts = s.split(":");
        String gameText = parts[0];
        int indexOfFirstSpace = gameText.indexOf(' ');
        long gameId = Long.parseLong(gameText.substring(indexOfFirstSpace + 1));
        String[] roundsText = parts[1].split(";");
        List<Round> rounds = Arrays.stream(roundsText)
                .map(text -> {
                    List<Integer> reds = countColor(text, "red");
                    int red = reds.isEmpty() ? 0 : reds.get(0);
                    List<Integer> greens = countColor(text, "green");
                    int green = greens.isEmpty() ? 0 : greens.get(0);
                    List<Integer> blues = countColor(text, "blue");
                    int blue = blues.isEmpty() ? 0 : blues.get(0);
                    return new Round(red, green, blue);
                })
                .toList();
        return new Game(gameId, rounds);
    }

    private static List<Integer> countColor(String line, String colorText) {
        Pattern serialMatcher = Pattern.compile("(\\d+) " + colorText);
        Matcher matcher = serialMatcher.matcher(line);
        List<Integer> drawnNumbers = new ArrayList<>();
        while (matcher.find()) {
            drawnNumbers.add(Integer.parseInt(matcher.group(1)));
        }
        return drawnNumbers;
    }

    private static class Game {

        private final long id;
        private final List<Round> rounds;

        public Game(long id, List<Round> rounds) {
            this.id = id;
            this.rounds = rounds;
        }

        public long getId() {
            return id;
        }

        public boolean arePossibleBases(int redBases, int greenBases, int blueBases) {
            return rounds.stream()
                    .allMatch(r -> r.getRedBalls() <= redBases && r.getGreenBalls() <= greenBases && r.getBlueBalls() <= blueBases);
        }

        public Round findMinimumSet() {
            int minRedBalls = findMaxBalls(Round::getRedBalls);
            int minGreenBalls = findMaxBalls(Round::getGreenBalls);
            int minBlueBalls = findMaxBalls(Round::getBlueBalls);
            return new Round(minRedBalls, minGreenBalls, minBlueBalls);
        }

        private int findMaxBalls(ToIntFunction<Round> ballSupplier) {
            return rounds.stream()
                    .mapToInt(ballSupplier)
                    .max()
                    .orElse(0);
        }
    }

    private static class Round {

        private final int redBalls;
        private final int greenBalls;
        private final int blueBalls;

        public Round(int redBalls, int greenBalls, int blueBalls) {
            this.redBalls = redBalls;
            this.greenBalls = greenBalls;
            this.blueBalls = blueBalls;
        }

        public int getRedBalls() {
            return redBalls;
        }

        public int getGreenBalls() {
            return greenBalls;
        }

        public int getBlueBalls() {
            return blueBalls;
        }

        public long getPowerOfBalls() {
            return (long) redBalls * greenBalls * blueBalls;
        }
    }
}
