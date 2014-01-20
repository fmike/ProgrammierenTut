/**
 * A class representing a Matrix of double values.
 * The index of the top-left value is always (0,0)
 * and that of the bottom-right corner is (m-1,n-1).
 * It supports selected manipulation functions and matrix operations.
 * @author Felix Hofmann
 * @version 1.0
 */
public class Matrix {
    /**
     * Stores the values of the matrix, such that
     * the first index represents the row and
     * the second index represents the column.
     */
    double[][] values;

    /**
     * Initializes the matrix with the given number of rows and columns.
     * Sets all values to zero.
     * (If m or n is smaller than 1, the Matrix will be initialized as a 1x1 Matrix.)
     * @param m the number of rows of the matrix (m >= 1)
     * @param n the number of columns of the matrix (n >= 1)
     */
    Matrix(int m, int n) {
        if (m > 1 && n > 1) {
            values = new double[m][n];
        } else {
            // you will learn about a better solution for cases like that soon (-> Exceptions)
            values = new double[1][1];
        }
    }

    /**
     * Sets the value of the matrix at the given position (if i and j are in bounds).
     * @param i identifies the <code>i</code>-th row
     * @param j identifies the <code>j</code>-th column
     * @param value the value to be set at row <code>i</code> and column <code>j</code>
     */
    void put(int i, int j, double value) {
        // check of boundaries is optional and would be better in an extra method
        if (getNumberOfRows() > i && i >= 0 && getNumberOfColumns() > j && j >= 0) {
            values[i][j] = value;
        }
        // you will learn about a better solution for out-of-boundaries soon (-> Exceptions)
    }

    /**
     * Returns the value of the matrix at the given position.
     * @param i identifies the <code>i</code>-th row
     * @param j identifies the <code>j</code>-th column
     * @return the value of the matrix at row i and column j (if they are in bounds, NaN otherwise).
     */
    double get(int i, int j) {
        // check of boundaries is optional and would be better in a extra method
        if (getNumberOfRows() > i && i >= 0 && getNumberOfColumns() > j && j >= 0) {
            return values[i][j];
        } else {
            // you will learn about a better solution for cases like that soon (-> Exceptions)
            return Double.NaN;
        }
    }

    /**
     * Returns the number of rows.
     * @return the height of the matrix
     */
    int getNumberOfRows() {
        return values.length;
    }

    /**
     * Returns the number of columns.
     * @return the width of the matrix
     */
    int getNumberOfColumns() {
        return values[0].length;
    }

    /**
     * Calculates the matrix-product of this matrix with the matrix <code>factor</code>.
     * If possible, i.e. if <code>this.getNumberOfColumns() == factor.getNumberOfRows()</code>,
     * this method returns the matrix-product, otherwise it returns <code>null</code>.
     * Neither this matrix nor the matrix <code>factor</code> are modified by this operation.
     * @param factor The second factor in the product
     * @return the product of this matrix and the matrix <code>factor</code> if it exits,
     * <code>null</code> otherwise
     */
    Matrix times(Matrix factor) {
        // Localize for better readability.
        // (not necessary, just because this is the exemplary solution)
        int m = getNumberOfRows();
        int n = getNumberOfColumns();
        int o = factor.getNumberOfColumns();

        // Check if multiplication is possible.
        if (factor.getNumberOfRows() != n) {
            return null;
        }

        // Actual Multiplication
        Matrix muliplied = new Matrix(m, o);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < o; j++) {
                double currentValue = 0.0;
                for (int k = 0; k < n; k++) {
                    currentValue += get(i, k) * factor.get(k, j);
                }
                muliplied.put(i, j, currentValue);
            }
        }

        return muliplied;
    }

    /**
     * Calculates and returns the transposed matrix of the current matrix.
     * This matrix is not modified by the operation.
     * @return this matrix transposed
     */
    Matrix transpose() {
        Matrix transposed = new Matrix(getNumberOfColumns(), getNumberOfRows());
        for (int i = 0; i < getNumberOfRows(); i++) {
            for (int j = 0; j < getNumberOfColumns(); j++) {
                transposed.put(j, i, get(i, j));
            }
        }
        return transposed;
    }
}
