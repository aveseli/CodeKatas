/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.av.calc;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author adnan
 */
public class StringCalculator {

	public static int add(String string) {
		if (string == null || string.length() == 0) {
			return 0;
		}
		String[] numbers = tokenize(string);
		ensureAllNonNegative(numbers);
		return Arrays.asList(numbers).stream().mapToInt(Integer::parseInt).filter((e) -> e < 1000).sum();
	}

	private static String[] tokenize(String text) {
		if (text.startsWith("//")) {
			return splitUsingCustomDelimiter(text);
		}
		return splitUsingCommaOrNewLineDelimiter(text);
	}

	private static String[] splitUsingCustomDelimiter(String text) {
		Matcher matcher = Pattern.compile("//(.*)\n(.*)").matcher(text);
		matcher.matches();
		String delimiter = matcher.group(1);
		if (delimiter.length() > 1) {
			delimiter = extractCustomMultipleDelimiters(delimiter);
		}
		return matcher.group(2).split(delimiter);
	}

	private static String extractCustomMultipleDelimiters(String delimiter) {
		// remove leading '[' and trailing ']'
		delimiter = delimiter.substring(1, delimiter.length() - 1);
		delimiter = delimiter.replace("][", "|");
		return delimiter;
	}

	private static String[] splitUsingCommaOrNewLineDelimiter(String text) {
		return text.split(",|\\n");
	}

	private static void ensureAllNonNegative(String[] numbers) throws IllegalArgumentException {
		List<String> belowZeroList = Arrays.asList(numbers).stream().map(Integer::parseInt).filter((i) -> (i < 0)).map(String::valueOf).collect(Collectors.toList());
		if (belowZeroList.size() > 0) {
			final String msg = String.format("negatives not allowed. %s", Arrays.toString(belowZeroList.toArray()));
			throw new IllegalArgumentException(msg);
		}
	}
	
}
