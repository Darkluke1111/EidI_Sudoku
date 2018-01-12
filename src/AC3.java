import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lg18 on 12.01.2018.
 */
public class AC3 {
    int variables;
    int rowLength;
    Set<Integer>[] domains;
    boolean[][] constraints;

    public AC3() {
        variables = 81;
        rowLength = 9;
        domains = new Set[variables];
        constraints = new boolean[variables][variables];

        fillDomains();
        fillConstraints();
        printConstraints();
    }

    private void fillConstraints() {
        //do for every variable
        for (int v1 = 0; v1 < variables; v1++) {

            //rowConstraints
            int lo = (v1 / rowLength) * rowLength;
            for (int i = lo; i < lo + rowLength; i++) {
                if (v1 != i) {
                    constraints[v1][i] = true;
                }
            }

            //columnConstraints

        }
    }

    private void fillDomains() {
        for (Set<Integer> domain : domains) {
            domain = new HashSet<>();
            domain.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        }
    }

    private void printConstraints() {
        for (int i = 0; i < variables; i++) {
            for (int j = 0; j < variables; j++) {
                if (constraints[i][j]) {
                    System.out.print(" 1");
                } else {
                    System.out.print(" 0");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AC3 ac3 = new AC3();
    }
}
