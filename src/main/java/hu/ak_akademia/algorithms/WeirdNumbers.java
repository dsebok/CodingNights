package hu.ak_akademia.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

/**
 * Furcsa számok: https://hu.wikipedia.org/wiki/Furcsa_sz%C3%A1mok
 *
 * A legkisebb furcsa szám a 70. Valódi osztói 1, 2, 5, 7, 10, 14 és 35; ezek összege 74, de nem adhatók össze úgy, hogy 70-et adjanak.
 * A 12-es szám például bővelkedő, de nem furcsa szám; valódi osztói 1, 2, 3, 4 és 6, melyek összege 16; ugyanakkor 2+4+6 = 12.
 *
 * Az első néhány furcsa szám:
 *
 * 70, 836, 4030, 5830, 7192, 7912, 9272, 10430, 10570, 10792, 10990, 11410, 11690, 12110, 12530, 12670, 13370, 13510, 13790, 13930, 14770, ...
 *
 * Feladat: írj egy metódust, amelynek tetszőleges long paramétert (limit) megadva visszaadja az addig megtalálható összes furcsa számot
 * (a határt is beleértve).
 */
public class WeirdNumbers {

    public static void main(String[] args) {
        System.out.println(getWeirdNumbers(1000));
    }

    public static List<Long> getWeirdNumbers(long limit) {
        // csak pozitív, egész számokat fogadunk el
        List<Long> weirdNumbers = new ArrayList<>();
        // osztók megkeresése
        for (long n = 1; n <= limit; n++) {
            List<Long> divisors = getProperDivisors(n);
            long abundantPart = getAbundantPart(n, divisors);
            if (abundantPart > 0 && !isPseudoPerfectNumber(divisors, abundantPart)) {
                weirdNumbers.add(n);
            }
        }
        return weirdNumbers;
    }

    private static boolean isPseudoPerfectNumber(List<Long> properDivisors, long abundantPart) {
        List<Long> reducedDivisors = properDivisors.stream()
                .filter(divisor -> divisor <= abundantPart)
                .toList();
        return isPseudoPerfectNumber(abundantPart, 0, reducedDivisors);
    }

    private static boolean isPseudoPerfectNumber(long n, long sum, List<Long> divisors) {
        if (divisors.isEmpty()) {
            return sum == n;
        } else {
            ArrayList<Long> copy = new ArrayList<>(divisors);
            long divisor = copy.remove(0);
            boolean branchOneResult = isPseudoPerfectNumber(n, sum + divisor, copy);
            boolean branchTwoResult = isPseudoPerfectNumber(n, sum, copy);
            return branchOneResult || branchTwoResult;
        }
    }

    private static List<Long> getProperDivisors(long n) {
        //TODO refactor it with streams
        List<Long> divisors = new ArrayList<>();
        long i = 1;
        long squareRoot = Math.round(Math.sqrt(n));
        while (i <= squareRoot) {
            if (n % i == 0) {
                divisors.add(i);
                long divisorPair = n / i;
                // n is not a proper divisor of itself
                // the divisor pair of the square root is the divisor itself
                if (divisorPair != n && divisorPair != i) {
                    divisors.add(divisorPair);
                }
            }
            i++;
        }
        return divisors;
    }

    private static List<Long> getProperDivisors2(long n) {
        return LongStream.rangeClosed(1, n / 2)
                .filter(i -> (n % i == 0))
                .boxed()
                .toList();
    }

    private static long getAbundantPart(long n, List<Long> properDivisors) {
        long divisorSum = properDivisors.stream()
                .mapToLong(a -> a)
                .sum();
        return divisorSum - n;
    }

    private static boolean isAbundantNumberV2(long n, List<Long> properDivisors) {
        return properDivisors.stream()
                .reduce((a, b) -> a + b)
                .orElse(0L) > n;
    }
}
