package main;

import java.util.ArrayList;
import java.util.List;

public class GeneratorEngine {
    private static final String[] alphabet = {"A", "C", "G", "T"};
    private static final int digits = 6;

    private List<Integer> combinationPool = new ArrayList<>();
    private List<String> response = new ArrayList<>();

    /**
     * Generate sequence of dna
     *
     * @return The list of dna sequence
     */
    public List<String> generate() {
        generateCombinationPool();
        while (true) {
            StringBuilder sb = new StringBuilder();

            int tmp = combinationPool.get(Helper.generateNumber(combinationPool.size() / 2));
            combinationPool.remove((Object) tmp);

            for (int i = 0; i < digits; i++) {
                sb.append(alphabet[((tmp - 1) % 10)]);
                tmp = tmp / 10;
            }
            if (Helper.checkList(response, sb.toString())) {
                response.add(sb.toString());
            }
            if (response.size() == 96) {
                break;
            }
        }
        return response;
    }

    /**
     * Generate all combination of sequence as a number
     *
     * @return The list of combination
     */
    private List<Integer> generateCombinationPool() {
        for (int i = ((int) Math.pow(10, (digits - 1))); i < ((int) Math.pow(10, digits)); i++) {
            boolean doAdd = true;
            int number = i;
            int tmpNumber = i;
            while (number != 0) {
                if (number % 10 > 4 || number % 10 == 0) {
                    doAdd = false;
                }
                number = number / 10;
            }
            if (doAdd)
                combinationPool.add(tmpNumber);
        }
        return combinationPool;
    }

    /**
     * Print sequence matrix to console
     */
    public void printResponseMatrix2Console() {
        String[][] matrix = new String[response.size() + 1][response.size() + 1];
        for (int row = 0; row < response.size() + 1; row++) {
            for (int col = 0; col < response.size() + 1; col++) {
                if (row == 0 && col == 0) {
                    matrix[row][col] = "******";
                } else if (row == 0) {
                    matrix[row][col] = response.get(col - 1);
                } else if (col == 0) {
                    matrix[row][col] = response.get(row - 1);
                } else {
                    matrix[row][col] = String.valueOf(Helper.computeDiff(response.get(col - 1), response.get(row - 1)));
                }
            }
        }
        //System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n"));
        for (int row = 0; row < (response.size() + 1); row++) {
            for (int col = 0; col < (response.size() + 1); col++) {
                System.out.print(String.format("%" + (digits + 1) + "s", matrix[row][col] + " "));
            }
            System.out.println();
        }
    }
}
