import java.util.Random;

class MatrixOperations implements Runnable {
  static final int MATRIX_SIZE = 10;
  static final int MAX_INTEGER = 1000;
  static int[][] numMatrix;
  int rowNumber;

  public MatrixOperations(int aRowNumber) {
    rowNumber = aRowNumber;
  }

  public static void populateMatrix() {
    numMatrix = new int[10][10];
    Random rn = new Random();
    for(int i = 0; i < MATRIX_SIZE; i++) {
      for(int j = 0; j < MATRIX_SIZE; j++) {
        numMatrix[i][j] = rn.nextInt(MAX_INTEGER);
      }
    }
  }

  public static int findLargestPerRow(int rowNumber) {
    int highestNumber = 0;
    for(int i = 0; i < MATRIX_SIZE; i++) {
      if(highestNumber < numMatrix[rowNumber][i]) highestNumber = numMatrix[rowNumber][i];
    }
    return highestNumber;
  }

  public static int findSmallestPerRow(int rowNumber) {
    int lowestNumber = Integer.MAX_VALUE;
    for(int i = 0; i < MATRIX_SIZE; i++) {
      if(lowestNumber > numMatrix[rowNumber][i]) lowestNumber = numMatrix[rowNumber][i];
    }
    return lowestNumber;
  }

  public static double findAverageOfRow(int rowNumber) {
    double average = 0;
    for(int i = 0; i < MATRIX_SIZE; i++) {
      average += numMatrix[rowNumber][i];
    }
    average = average / MATRIX_SIZE;
    return average;
  }

  public void run() {
    printRow(rowNumber);
    System.out.println(" -> (" + findLargestPerRow(rowNumber) + ", " + findSmallestPerRow(rowNumber) + ", " + findAverageOfRow(rowNumber) + ")");
  }

  public static void printRow(int rowNumber) {
    System.out.print("[");
    for(int i = 0; i < MATRIX_SIZE; i++) {
      System.out.print(String.valueOf(numMatrix[rowNumber][i]));
      if(i != (MATRIX_SIZE - 1)) System.out.print(", ");
    }
    System.out.print("]");
  }

  public static void main(String[] args) {
    populateMatrix();
    System.out.println("Matrix returns in the following format: (Largest Number, Smallest Number, Average)");
    for(int i = 0; i < MATRIX_SIZE; i++) {
      Thread th = new Thread(new MatrixOperations(i));
      th.start();
      try {
        th.join();
      } catch(InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
