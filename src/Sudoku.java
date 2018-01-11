import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lg18 on 11.01.2018.
 */
public class Sudoku {

    private volatile int[][] sudoku;
    private final int SIZE;
    private final int BOX_SIZE;
    volatile long steps = 0;
    private volatile int xPos = 0;
    private volatile int yPos = 0;
    private RunInspector ri;
    private Timer t;

    public static void main(String[] args) {
        Sudoku s = new Sudoku(1);
        System.out.println("Sudoku in Step " + s.steps);
        System.out.println(s);
    }

    public Sudoku() {
        this(9);
    }

    public Sudoku(int size) {
        int i;
        for (i = 1; i * i < size; i++) ;
        if (i * i == size) {
            BOX_SIZE = i;
            SIZE = size;
        } else {
            throw new IllegalArgumentException("Size must be a square but is " + size);
        }
        ri = new RunInspector(this);
        generateSudoku();
    }

    private void generateSudoku() {
        sudoku = new int[SIZE][SIZE];

        t = new Timer();
        t.scheduleAtFixedRate(ri, 1000, 1000);
        while (xPos < SIZE && yPos < SIZE) {
            steps++;
            if (sudoku[yPos][xPos] == SIZE) {
                sudoku[yPos][xPos] = 0;
                xPos = (xPos + SIZE - 1) % SIZE;
                if (xPos == SIZE - 1) yPos--;
            } else {
                boolean possible = insertAndTest(xPos, yPos, sudoku[yPos][xPos] + 1);
                if (possible) {
                    xPos = (xPos + 1) % SIZE;
                    if (xPos == 0) yPos++;
                }
            }
        }
        t.purge();
        t.cancel();
    }

    private boolean testRow(int xPos, int yPos) {
        if (sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[yPos][xPos] == sudoku[yPos][i] && i != xPos) {
                return false;
            }
        }
        return true;
    }

    private boolean testColumn(int xPos, int yPos) {
        if (sudoku[yPos][xPos] == 0) throw new IllegalArgumentException("Tested Cell is not filled with a value!");
        for (int i = 0; i < SIZE; i++) {
            if (sudoku[yPos][xPos] == sudoku[i][xPos] && i != yPos) {
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
                if (sudoku[yPos][xPos] == sudoku[i][j] && (i != yPos || j != xPos)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean insertAndTest(int xPos, int yPos, int value) {
        //System.out.println("testing at " + xPos + " " + yPos + " with Value " + value);
        sudoku[yPos][xPos] = value;
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

class RunInspector extends TimerTask {
    Sudoku sudoku;

    public RunInspector(Sudoku s) {
        this.sudoku = s;
    }

    @Override
    public void run() {
        System.out.println("Sudoku in Step " + sudoku.steps);
        System.out.println(sudoku);
    }
}
