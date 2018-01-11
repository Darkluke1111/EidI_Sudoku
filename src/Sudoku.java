/**
 * Created by lg18 on 11.01.2018.
 */
public class Sudoku {

    private int[][] sudoku;
    private final int SIZE = 9;
    private final int BOX_SIZE = 3;

    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        System.out.println(s);
    }

    public Sudoku() {
        generateSudoku();
    }

    private void generateSudoku() {
        sudoku = new int[SIZE][SIZE];
        int xPos = 0;
        int yPos = 0;
        while(xPos < 9 && yPos < 9) {
            if(sudoku[yPos][xPos] == 9) {
                sudoku[yPos][xPos] = 0;
                xPos = (xPos + 8) % 9;
                if (xPos == 8) yPos--;
            } else {
                boolean possible = insertAndTest(xPos, yPos, sudoku[yPos][xPos] + 1);
                if (possible) {
                    xPos = (xPos + 1) % 9;
                    if (xPos == 0) yPos++;
                }
            }
        }
    }

    private boolean testRow(int xPos, int yPos) {
        if(sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        for(int i = 0 ; i < SIZE ; i++) {
            if(sudoku[yPos][xPos] == sudoku[yPos][i] && i != xPos) {
                return false;
            }
        }
        return true;
    }

    private boolean testColumn(int xPos, int yPos) {
        if(sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        for(int i = 0 ; i < SIZE ; i++) {
            if(sudoku[yPos][xPos] == sudoku[i][xPos] && i != yPos) {
                return false;
            }
        }
        return true;
    }

    private boolean testBox(int xPos, int yPos) {
        if(sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        int vNum = (yPos / BOX_SIZE) * BOX_SIZE;
        int hNum = (xPos / BOX_SIZE) * BOX_SIZE;
        for(int i = vNum ; i < vNum + BOX_SIZE ; i++) {
            for(int j = hNum ; j < hNum + BOX_SIZE ; j++) {
                if(sudoku[yPos][xPos] == sudoku[i][j] && (i != yPos || j != xPos)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean insertAndTest(int xPos, int yPos, int value) {
        //System.out.println("testing at " + xPos + " " + yPos + " with Value " + value);
        sudoku[yPos][xPos] = value;
        System.out.println(this.toString());
        boolean possible =
                testRow(xPos,yPos) &&
                testColumn(xPos,yPos) &&
                testBox(xPos,yPos);

        return possible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < SIZE ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
