import com.sun.istack.internal.Nullable;

import java.util.Timer;

/**
 * Created by lg18 on 11.01.2018.
 */
public class SudokuGenerator {

    private int[][] sudoku;
    private final int SIZE;
    private final int BOX_SIZE;
    volatile long steps = 0;
    private int xPos = 0;
    private int yPos = 0;
    private GenerationVisualisation gv;

    public static void main(String[] args) {
        SudokuGenerator s = new SudokuGenerator(9, null);
        s.generateSudoku();
        System.out.println("SudokuGenerator in Step " + s.steps);
        System.out.println(s);
    }

    public SudokuGenerator() {
        this(9, null);
    }

    public SudokuGenerator(int size, @Nullable GenerationVisualisation gv) {
        int i;
        for (i = 1; i * i < size; i++) ;
        if (i * i == size) {
            BOX_SIZE = i;
            SIZE = size;
        } else {
            throw new IllegalArgumentException("Size must be a square but is " + size);
        }
        if(gv == null) {
            this.gv = new DefaultGenerationVisualisation();
        } else {
            this.gv = gv;
        }
    }

    public int[][] generateSudoku() {
        sudoku = new int[SIZE][SIZE];

        while (xPos < SIZE && yPos < SIZE) {
            steps++;
            if (sudoku[yPos][xPos] == SIZE) {
                sudoku[yPos][xPos] = 0;
                gv.visualizeDeleteCell(xPos,yPos,steps);
                decrementPos();
            } else {
                boolean possible = insertAndTest(xPos, yPos, sudoku[yPos][xPos] + 1);
                if (possible) {
                    incrementPos();
                }
            }
        }
        return sudoku;
    }

    private void decrementPos() {
        xPos = (xPos + SIZE - 1) % SIZE;
        if (xPos == SIZE - 1) yPos--;
    }

    private void incrementPos() {
        xPos = (xPos + 1) % SIZE;
        if (xPos == 0) yPos++;
    }

    private boolean testRow(int xPos, int yPos) {
        if (sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        for (int i = 0; i < SIZE; i++) {
            boolean possible = !(sudoku[yPos][xPos] == sudoku[yPos][i] && i != xPos);
            gv.visualizeTest(xPos, yPos, i, yPos, possible);
            if (!possible) {
                return false;
            }
        }
        return true;
    }

    private boolean testColumn(int xPos, int yPos) {
        if (sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        for (int i = 0; i < SIZE; i++) {
            boolean possible = !(sudoku[yPos][xPos] == sudoku[i][xPos] && i != yPos);
            gv.visualizeTest(xPos, yPos, xPos, i, possible);
            if(!possible) {
                return false;
            }
        }
        return true;
    }

    private boolean testBox(int xPos, int yPos) {
        if (sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        int vNum = (yPos / BOX_SIZE) * BOX_SIZE;
        int hNum = (xPos / BOX_SIZE) * BOX_SIZE;
        for (int i = vNum; i < vNum + BOX_SIZE; i++) {
            for (int j = hNum; j < hNum + BOX_SIZE; j++) {
                boolean possible = !(sudoku[yPos][xPos] == sudoku[i][j] && (i != yPos || j != xPos));
                gv.visualizeTest(xPos, yPos, j, i, possible);
                if (!possible) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean insertAndTest(int xPos, int yPos, int value) {
        //System.out.println("testing at " + xPos + " " + yPos + " with Value " + value);
        sudoku[yPos][xPos] = value;
        gv.visualizeFillCell(xPos, yPos, sudoku[yPos][xPos], steps);
        //System.out.println(this.toString());
        boolean possible = testRow(xPos, yPos) &&
                        testColumn(xPos, yPos) &&
                        testBox(xPos, yPos);

        return possible;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            if (i % BOX_SIZE == 0) {

                for (int j = 0; j < SIZE; j++) {
                    if (j % BOX_SIZE == 0) {
                        sb.append("+");
                    }
                    sb.append(" - ");
                }
                sb.append("\n");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % BOX_SIZE == 0) {
                    sb.append("|");
                }
                sb.append(String.format("%2d", sudoku[i][j])).append(" ");
            }

            sb.append("\n");
        }
        return sb.toString();
    }
}
