package de.av.calc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author adnan
 */
public class StringCalculatorTest {

    public StringCalculatorTest() {
    }

    @Test
    public void testEmptyString() {
        int result = StringCalculator.add("");
        assertEquals(0, result);
    }

    @Test
    public void testOneNumer() {
        int result = StringCalculator.add("1");
        assertEquals(1, result);
    }

    @Test
    public void testTwoNumbers() {
        int result = StringCalculator.add("1,2");
        assertEquals(3, result);
    }

    @Test
    public void testTenNumbers() {
        int actualResult = StringCalculator.add("1,2,3,4,5,6,7,8,9,10");
        assertEquals(55, actualResult);
    }

    @Test
    public void testNewLineSepparator() {
        int actualResult = StringCalculator.add("1\n2");
        assertEquals(3, actualResult);
    }

    @Test
    public void testDifferentDelimiters() {
        int actualResult = StringCalculator.add("//;\n1;2");
        assertEquals(3, actualResult);
    }

    @Test
    public void testThatNumbersBiggerThan1000AreIgnored() {
        int actualResult = StringCalculator.add("5,1000");
        assertEquals(5, actualResult);
    }

    @Test
    public void testOneCustomDelimiterWithAnyLength() {
        int actualResult = StringCalculator.add("//[aaaaa]\n1aaaaa2");
        assertEquals(3, actualResult);
    }

    @Test
    public void testMultipleCustomDelimiters() {
        int actualResult = StringCalculator.add("//[a][b]\n1a2b3");
        assertEquals(6, actualResult);
    }

    @Test
    public void testNonNegativeNumbers() {
        try {
            StringCalculator.add("-1,1");
            fail("expected an exception.");
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed. [-1]", e.getMessage());
        }
    }

}
