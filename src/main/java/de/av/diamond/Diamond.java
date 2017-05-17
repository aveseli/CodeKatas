/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.av.diamond;

/**
 *
 * @author adnan
 */
class Diamond {
	
	private final char givenChar;
	private final int middle;
	private final int diamondLength;

	public Diamond(char aGivenChar) {
		givenChar = aGivenChar;
		middle = (givenChar - 'A');
		diamondLength = 2 * middle + 1;
	}

	public String[][] print() {
		final String[][] diamond = new String[diamondLength][diamondLength];
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
