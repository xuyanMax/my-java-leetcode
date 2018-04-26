package dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
 * 
 * @L length of matrixes chaining
 * @i starting matrix in the chain 
 * @k intermediate matrix 
 * @j last matrix 
 * 
 * 									j = i + L - 1
 *  We are filling the matrix diagonally. First for L=1 i.e multiplying matrix with itself,
 *  we fill M[i][i] diagonally as 0. After that multiplying two matrices L=2 e.x., A*B , 
 *  in matrix it will be M[1][2] so we start from i=1,so j must be 2 i.e j=1+2-1=2 
 *  and then we move i and j like window for multiplying all two adjacent matrix like B*C 
 *  and like that filling diagonally for just two matrix multiplication. After that we 
 *  multiply 3 matrices L = 3, again like 3 matrixes window starting from A, which will be 
 *  filled diagonally. In the end, we get our answer at the top right corner.
 *  
 *  
 */
public class MatrixMultiplicationCost {

    public void findCost(int[] p){
        
        /* For simplicity of the program, one extra row and one
        extra column are allocated in m[][].  0th row and 0th
        column of m[][] are not used */
    	
    	int n = p.length;
        int m[][] = new int[n][n];
 
        int i, j, k, L, q;
 
        /* m[i,j] = Minimum number of scalar multiplications needed
        to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where
        dimension of A[i] is p[i-1] x p[i] */
 
        // cost is zero when multiplying one matrix.
        for (i = 1; i < n; i++)
            m[i][i] = 0;
 
        // L is chain length.
        for (L=2; L<n; L++)
        {
            for (i=1; i<n-L+1; i++)
            {
                j = i+L-1;
//                if(j == n) continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k=i; k<=j-1; k++)// i<=k<j because, k is between i and J; m[i][j] was set to infinity
                {
                    // q = cost/scalar multiplications``
                	// first * middle * last
                	// [i-1] * [k] * [j]
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }
        for (int ii=0;ii<m.length;ii++) {
        	for (int jj=0; jj<m[0].length;jj++)
        		System.out.print(String.format("%4d", m[ii][jj]));
			System.out.println("");
        }
        // return m[1][n-1];
    }
    
    public static void main(String args[]){
        MatrixMultiplicationCost mmc = new MatrixMultiplicationCost();
        int arr[] = {2,3,6,4,5};
        mmc.findCost(arr);
        
    }
}