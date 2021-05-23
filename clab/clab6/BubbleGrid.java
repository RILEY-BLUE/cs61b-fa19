public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] gridCopy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            gridCopy[i] = grid[i].clone();
        }

        /* Obtain the final bubbles after throwing all darts. */
        for (int[] dart : darts) {
            gridCopy[dart[0]][dart[1]] = 0;
        }

        UnionFind uf = new UnionFind(rows * cols + 1);
        /* Use unionfind data structure to build bubbles'
         * relations by union them when:
         * 1. i = 0, i.e.: bubble on top level
         * 2. any bubble's top bubble is firm
         * 3. any bubble's left bubble is firm
         * All bubbles that are firm after throwing all darts are connected.
         */
        for (int i = 0; i < rows; i += 1) {
            for (int j = 0; j < cols; j += 1) {
                int index = i * cols + j;
                if (i == 0) {
                    uf.union(index, rows * cols);
                }
                if (i > 0 && gridCopy[i - 1][j] == 1) {
                    uf.union(index, index - cols);
                }
                if (j > 0 && gridCopy[i][j - 1] == 1) {
                    uf.union(index, index - 1);
                }
            }
        }

        int[] res = new int[darts.length];
        /* Then we go backwards to restore the bubbles step by step.
         * In each step, the falling bubbles are obtained by comparing
         * the size of the firm bubbles' set before and after restoring.
         */
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        int step = darts.length - 1;
        while (step >= 0) {
            int preSize = uf.sizeOf(rows * cols);
            int row = darts[step][0];
            int col = darts[step][1];
            if (grid[row][col] == 0) {
                step -= 1;
            } else {
                int index = row * cols + col;
                for (int i = 0; i < 4; i += 1) {
                    int newRow = row + dr[i];
                    int newCol = col + dc[i];
                    // Union neighbouring bubbles if firm and in bounds.
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && gridCopy[newRow][newCol] == 1) {
                        uf.union(index, newRow * cols + newCol);
                    }
                }
                // Corner case: darts on bubbles on top, if not specially dealt, it will not be restored.
                if (row == 0) {
                    uf.union(index, rows * cols);
                }
            }
            int postSize = uf.sizeOf(rows * cols);
            res[step] = Math.max(0, postSize - preSize - 1);
            step -= 1;
        }
        return res;
    }
}
