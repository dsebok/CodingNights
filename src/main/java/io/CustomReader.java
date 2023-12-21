package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CustomReader {

    public static List<String> readFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            return Stream.generate(() -> SafeLineReader.readLine(bufferedReader))
                    .takeWhile(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static class SafeLineReader {

        private static Optional<String> readLine(BufferedReader bufferedReader) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    return Optional.of(line);
                }
            } catch (IOException e) {
                System.err.println("A line could not be read!:\n" + e.getMessage());
            }
            return Optional.empty();
        }
    }
}
