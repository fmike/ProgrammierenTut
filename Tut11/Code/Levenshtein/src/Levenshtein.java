
/**
 * Calculates the levenshtein distance
 * 
 * @author Spacetoaster
 *
 */
public class Levenshtein {	
	/**
	 * Calculates the levenshtein distance with dynamic programming
	 * gets called on construction of objects
	 */
	public static void calculateDistance(String u, String v) {
		int n = u.length() + 1;
		int m = v.length() + 1;
		int[][] distance = new int[n][m];
		
		//fill in trivial values for D_i0
		for (int i=0; i<n; i++) {
			distance[i][0] = i;
		}
		// fill in trivial values for D_0j
		for (int j=0; j<m; j++) {
			distance[0][j] = j;
		}
		// can be optimized
		
		
		for (int i=1; i < n; i++) {
			for (int j=1; j < m; j++) {
				distance = cases(i, j, u, v, distance);
			}
		}
		System.out.println(distance[n-1][m-1]);
	}
	
	private static int[][] cases(int i, int j, String u, String v, int[][] distance) {
		String substr_u = u.substring(0, i);
		String substr_v = v.substring(0, j);
		int result = 0;
		
		int deleteCost = distance[i-1][j] + 1;
		int insertCost = distance[i][j-1] + 1;
		int replaceCost = distance[i-1][j-1] + 1;
		
		if (substr_u.charAt(i-1) == substr_v.charAt(j-1)) {
			result = distance[i-1][j-1];
		} else {
			result = Math.min(Math.min(deleteCost, insertCost), replaceCost);
		}
		
		distance[i][j] = result;
		
		return distance;
	}

	public static void main(String[] args) {
		Levenshtein.calculateDistance("Tor", "Tier");
	}

}
