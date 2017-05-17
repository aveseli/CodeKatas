/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.av;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author adnan
 */
public class DiamondTest {

	@Test
	public void testDiamondWithLetterA() {
		String[][] result = new Diamond('A').print();
		System.out.println("Testcase: testDiamondWithLetterA");
		printDiamondToStdOut(result);
		String[][] expected = {
			{"A"}
		};

		assertArrayEquals(expected, result);
	}

	@Test
	public void testDiamondWithLetterB() {
		String[][] result = new Diamond('B').print();
		System.out.println("Testcase: testDiamondWithLetterB");
		printDiamondToStdOut(result);
		String[][] expected = {
			{null, "A", null},
			{"B", null, "B"},
			{null, "A", null},};

		assertArrayEquals(expected, result);
	}

	@Test
	public void testDiamondWithLetterC() {
		String[][] result = new Diamond('C').print();

		System.out.println("Testcase: testDiamondWithLetterC");
		printDiamondToStdOut(result);
		String[][] expected = {
			{null, null, "A", null, null},
			{null, "B", null, "B", null},
			{"C", null, null, null, "C"},
			{null, "B", null, "B", null},
			{null, null, "A", null, null},};

		assertArrayEquals(expected, result);
	}

	private void printDiamondToStdOut(String[][] diamond) {
		for (int row = 0; row < diamond.length; row++) {
			for (int col = 0; col < diamond[row].length; col++) {
				String toPrint = diamond[row][col];
				if (toPrint == null) {
					toPrint = ".";
				}
				System.out.print(toPrint);
			}
			System.out.println("");
		}
	}

	private static class Diamond {

		private final char givenChar;
		private final int middle;
		private final int diamondLength;

		public Diamond(char aGivenChar) {
			givenChar = aGivenChar;
			middle = (givenChar - 'A');
			diamondLength = 2 * middle + 1;
		}

		public String[][] print() {
			final String[][] diamond =  new String[diamondLength][diamondLength];

			for (int row = 0; row <= middle; row++) {
				String charToPrint = evaluateCharacterFor(row);

				assignTopRightHalf(diamond, row, charToPrint);
				assignTopLeftHalf(diamond, row, charToPrint);
				assignBottomRightHalf(diamond, row, charToPrint);
				assignBottomLeftHalf(diamond, row, charToPrint);
			}
			return diamond;
		}

		private String evaluateCharacterFor(int row) {
			return String.valueOf((char) ('A' + row));
		}

		private void assignTopRightHalf(String[][] diamond, int row, String charToPrint) {
			diamond[row][middle + row] = charToPrint;
		}

		private void assignTopLeftHalf(String[][] diamond, int row, String charToPrint) {
			diamond[row][middle - row] = charToPrint;
		}

		private void assignBottomRightHalf(String[][] diamond, int row, String charToPrint) {
			diamond[2 * middle - row][middle + row] = charToPrint;
		}

		private void assignBottomLeftHalf(String[][] diamond, int row, String charToPrint) {
			diamond[2 * middle - row][middle - row] = charToPrint;
		}
	}
}
