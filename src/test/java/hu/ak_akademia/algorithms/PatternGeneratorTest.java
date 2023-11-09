package hu.ak_akademia.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class PatternGeneratorTest {

    @Test
    public void createSignSequence() {
        // GIVEN
        int n = 2;
        // WHEN
        String actual = PatternGenerator.createSignSequence(n);
        // THEN
        Assert.assertEquals("X ", actual);
    }

    @Test
    public void createSignSequence_whenNIsLessThen1() {
        // GIVEN
        int n = 0;
        // WHEN
        String actual = PatternGenerator.createSignSequence(n);
        // THEN
        Assert.assertEquals("", actual);
    }

    @Test
    public void createSignSequence_whenNIsBig() {
        // GIVEN
        int n = 6;
        // WHEN
        String actual = PatternGenerator.createSignSequence(n);
        // THEN
        Assert.assertEquals("X O X ", actual);
    }

    @Test
    public void createPadding() {
        // GIVEN
        int n = 2;
        // WHEN
        String actual = PatternGenerator.createPadding(n);
        // THEN
        Assert.assertEquals("  ", actual);
    }

    @Test
    public void createPadding_whenNIsLessThen1() {
        // GIVEN
        int n = 0;
        // WHEN
        String actual = PatternGenerator.createPadding(n);
        // THEN
        Assert.assertEquals("", actual);
    }

    @Test
    public void createPadding_whenNIsBig() {
        // GIVEN
        int n = 6;
        // WHEN
        String actual = PatternGenerator.createPadding(n);
        // THEN
        Assert.assertEquals("      ", actual);
    }
}
