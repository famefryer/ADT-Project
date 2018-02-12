package adt;

import java.awt.Color;
import java.util.Scanner;
/**
 * The class that use for represent the L problem in GUI.
 * @author fame
 *
 */
public class LBoard {
	private static MiniBox[][] box;
	private static Color color = Color.BLUE;
	private static double wideBox;
/**
 * The method that use for draw the table from the input.
 * @param wide
 * @param xOfRed
 * @param yOfRed
 */
	public static void drawTable(int wide, int xOfRed, int yOfRed) {
		StdDraw.setPenColor(StdDraw.BLACK);
		double xPosition = 0;
		double yPosition = 1;
		box = new MiniBox[wide][wide];
		wideBox = 1 / (double) wide;
		for (int y = 0; y < wide; y++) {
			for (int x = 0; x < wide; x++) {
				if (x == xOfRed - 1 && y == yOfRed - 1) {
					StdDraw.setPenColor(StdDraw.BOOK_RED);
					StdDraw.filledSquare(xPosition, yPosition, wideBox / 2);
					box[x][y] = new MiniBox(xPosition, yPosition);
					box[x][y].setCheck(true);
					StdDraw.setPenColor(StdDraw.BLACK);
				} else {
					StdDraw.square(xPosition, yPosition, wideBox / 2);
					box[x][y] = new MiniBox(xPosition, yPosition);
				}
				xPosition += wideBox;
			}
			xPosition = 0;
			yPosition -= wideBox;
		}
		printPosition();
	}
/**
 * The method that use for draw the normal L.
 * @param x
 * @param y
 */
	public static void drawL(int x, int y) {
		MiniBox temp = box[x][y];
		randomColor();
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(temp.getX(), temp.getY(), wideBox / 2);
		box[x][y].setCheck(true);
		StdDraw.filledSquare(temp.getX() - wideBox, temp.getY(), wideBox / 2);
		box[x - 1][y].setCheck(true);
		StdDraw.filledSquare(temp.getX() - wideBox, temp.getY() + wideBox, wideBox / 2);
		box[x - 1][y - 1].setCheck(true);
	}
/**
 * The method that use for draw the three time clockwise 90 degree rotate L.
 * @param x
 * @param y
 */
	public static void drawL3(int x, int y) {
		MiniBox temp = box[x][y];
		randomColor();
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(temp.getX(), temp.getY(), wideBox / 2);
		box[x][y].setCheck(true);
		StdDraw.filledSquare(temp.getX() + wideBox, temp.getY(), wideBox / 2);
		box[x + 1][y].setCheck(true);
		StdDraw.filledSquare(temp.getX() + wideBox, temp.getY() + wideBox, wideBox / 2);
		box[x + 1][y - 1].setCheck(true);
	}
	/**
	 * The method that use for draw the one time clockwise 90 degree rotate L.
	 * @param x
	 * @param y
	 */
	public static void drawL1(int x, int y) {
		MiniBox temp = box[x][y];
		randomColor();
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(temp.getX(), temp.getY(), wideBox / 2);
		box[x][y].setCheck(true);
		StdDraw.filledSquare(temp.getX(), temp.getY() + wideBox, wideBox / 2);
		box[x][y - 1].setCheck(true);
		StdDraw.filledSquare(temp.getX() + wideBox, temp.getY() + wideBox, wideBox / 2);
		box[x + 1][y - 1].setCheck(true);
	}
	/**
	 * The method that use for draw the two time clockwise 90 degree rotate L.
	 * @param x
	 * @param y
	 */
	public static void drawL2(int x, int y) {
		MiniBox temp = box[x][y];
		randomColor();
		StdDraw.setPenColor(color);
		StdDraw.filledSquare(temp.getX(), temp.getY(), wideBox / 2);
		box[x][y].setCheck(true);
		StdDraw.filledSquare(temp.getX() + wideBox, temp.getY(), wideBox / 2);
		box[x + 1][y].setCheck(true);
		StdDraw.filledSquare(temp.getX() + wideBox, temp.getY() - wideBox, wideBox / 2);
		box[x + 1][y + 1].setCheck(true);
	}
/**
 * the method that use for random the color.
 */
	public static void randomColor() {
		color = new Color((int) (Math.random() * 200), (int) (Math.random() * 200), (int) (Math.random() * 200));
	}
/**
 * the method that use print the position of all box.
 */
	public static void printPosition() {
		for (int x = 0; x < box.length; x++) {
			for (int y = 0; y < box.length; y++) {
				System.out.print("(" + x + "," + y + ") at (" + box[x][y].getX() + "," + box[x][y].getY() + ")");
				if (box[x][y].getCheck()) {
					System.out.println(" Checked!!!");
				} else
					System.out.println();
			}
		}
	}
/**
 * The method that use for calculate the position and the form of L to draw.
 * @param range
 * @param startX
 * @param startY
 * @param endX
 * @param endY
 */
	public static void Input(int range, int startX, int startY, int endX, int endY) {
		int part = range / 2;
		MiniBox red = findRed(startX, startY, endX, endY);
		int middleX = (startX + endX) / 2;
		int middleY = (startY + endY) / 2;

		if (red.getXAxis() < (startX + endX) / 2 && red.getYAxis() < (startY + endY) / 2) {
			drawL3(middleX - 1, middleY);
		} else if (red.getXAxis() >= (startX + endX) / 2 && red.getYAxis() < (startY + endY) / 2) {
			drawL(middleX, middleY);
		} else if (red.getXAxis() < (startX + endX) / 2 && red.getYAxis() >= (startY + endY) / 2) {
			drawL2(middleX - 1, middleY - 1);
		} else {
			drawL1(middleX - 1, middleY);
		}
		StdDraw.show(100);
	}
/**
 * The method that use for find the not freely box in the range x,y of input
 * @param startX
 * @param startY
 * @param endX
 * @param endY
 * @return
 */
	public static MiniBox findRed(int startX, int startY, int endX, int endY) {
		int temp = startY;
		for (; startX < endX; startX++) {
			for (; startY < endY; startY++) {
				if (box[startX][startY].getCheck()) {
					box[startX][startY].setXAxis(startX);
					box[startX][startY].setYAxis(startY);
					return box[startX][startY];
				}
			}
			startY = temp;
		}
		return null;
	}
/**
 * The recursive method that use for filled the L on the box until the end.
 * @param range
 * @param startX
 * @param startY
 * @param endX
 * @param endY
 */
	public static void filledL(int range, int startX, int startY, int endX, int endY) {
		if (range == 1) {
			return;
		}
		int part = range / 2;
		Input(range, startX, startY, endX, endY);
		filledL(part, startX, startY, startX + part, startY + part);
		filledL(part, startX + part, startY, endX, startY + part);
		filledL(part, startX, startY + part, startX + part, endY);
		filledL(part, startX + part, startY + part, endX, endY);
	}

	public static void main(String[] args) {
		int n = 64;
		drawTable(n, 9, 2);
		filledL(n, 0, 0, n, n);
	}

}
/**
 * The class that use for collect the detail of each box.
 * @author Triwith Mutitakul
 *
 */
class MiniBox {
	private double x;
	private double y;
	private int xAxis;
	private int yAxis;
	private boolean isCheck = false;
/**
 * The constructor
 * @param x
 * @param y
 */
	public MiniBox(double x, double y) {
		this.x = x;
		this.y = y;
	}
/**
 * The method that use for set the x position.
 * @param x
 */
	public void setX(double x) {
		this.x = x;
	}
/**
 * The method that use for set the y position.
 * @param y
 */
	public void setY(double y) {
		this.y = y;
	}
/*
 * The method that use for get the x position.
 */
	public double getX() {
		return this.x;
	}
/*
 * The method that use for get the y position.
 */
	public double getY() {
		return this.y;
	}
/**
 * The method that use for set the x position in coordinate system.
 * @param x
 */
	public void setXAxis(int x) {
		this.xAxis = x;
	}
/**
 * The method that use for set the y position in coordinate system.
 * @param y
 */
	public void setYAxis(int y) {
		this.yAxis = y;
	}
/**
 * The method that use for get the x position in coordinate system.
 * @return
 */
	public int getXAxis() {
		return this.xAxis;
	}
/**
 * The method that use for get the y position in coordinate system.
 * @return
 */
	public int getYAxis() {
		return this.yAxis;
	}
/**
 * The method that use for set the box to free or not.
 * @param check
 */
	public void setCheck(boolean check) {
		this.isCheck = check;
	}
/**
 * The method that use for get the status of the box.
 * @return
 */
	public boolean getCheck() {
		return isCheck;
	}

}
