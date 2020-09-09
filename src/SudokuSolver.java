import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class SudokuSolver extends DLXSolver {
	private final Set<String> initial;
	
	public SudokuSolver(Set<String> initial) {
		this.initial = initial;
		Node[][] nodeSmatrix = new Node[730][324];
		Map<String, Node> rowtag2node = new HashMap<String, Node>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new File("sudoku_matrix"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// initialize root
		Node root = new Node("root", -1, null);
		
		// initialize header nodes in nodeSmatrix
		for(int c = 0; c < nodeSmatrix[0].length; ++c) {
			nodeSmatrix[0][c] = new Node("C" + c, 4, null);
		}
		
		// initialize data nodes in nodeSmatrix from file
		int row = 1;
		while(sc.hasNext()) {
			String[] line = sc.nextLine().split(" ");
			if(line[0].startsWith("#"))
				continue;
			
			String tag = line[0];
			String bits = line[1];
			
			int col = 0;
			for(char c : bits.toCharArray()) {
				if(c == '1') {
					nodeSmatrix[row][col] = new Node(tag, -1, nodeSmatrix[0][col]);
					// store first node in each row so we can remove the initial positions later
					if(rowtag2node.get(tag) == null)
						rowtag2node.put(tag, nodeSmatrix[row][col]);
				}
				++col;
			}
			++row;
		}
		
		// set left, right, up, down links
		for(int r = 0; r < nodeSmatrix.length; ++r) {
			for(int c = 0; c < nodeSmatrix[r].length; ++c) {
				if(nodeSmatrix[r][c] == null) continue;
				
				//System.out.println("Considering: " + nodeSmatrix[r][c]);
				int tempr, tempc;
				
				//set left
				tempc = c;
				do {
					--tempc;
					if(tempc < 0) tempc = nodeSmatrix[r].length-1;
				} while(nodeSmatrix[r][tempc] == null);
				nodeSmatrix[r][c].left = nodeSmatrix[r][tempc];
				
				// set right
				tempc = c;
				do {
					tempc = (tempc + 1) % nodeSmatrix[r].length;
				} while(nodeSmatrix[r][tempc] == null);
				nodeSmatrix[r][c].right = nodeSmatrix[r][tempc];
				
				// set up
				tempr = r;
				do {
					tempr = (tempr + 1) % nodeSmatrix.length;
				} while(nodeSmatrix[tempr][c] == null);
				nodeSmatrix[r][c].up = nodeSmatrix[tempr][c];
				
				// set down
				tempr = r;
				do {
					--tempr;
					if(tempr < 0) tempr = nodeSmatrix.length-1;
				} while(nodeSmatrix[tempr][c] == null);
				nodeSmatrix[r][c].down = nodeSmatrix[tempr][c];
			}
		}
		
		// tie root into the node network
		root.right = nodeSmatrix[0][0];
		root.left  = nodeSmatrix[0][nodeSmatrix[0].length-1];
		nodeSmatrix[0][0].left = root;
		nodeSmatrix[0][nodeSmatrix[0].length-1].right = root;
		
		this.root = root;
		// account for initial entries by covering all columns whose constraints they satisfy
		for(String s : initial) {
			Node n = rowtag2node.get(s);
			coverColumn(n.getColumnHeader());
			for(Node r = n.right; r != n; r = r.right) {
				coverColumn(r.getColumnHeader());
			}
		}
	}
	
	protected void printSolution(int k) {
		int[][] solution = new int[9][9];
		System.out.println("Solution:: ");
		for(int i = 0; i < k; i++) {
			String name = path.get(i).getName();
			// names are in r_c_#_ format
			solution[name.charAt(1)-'0'-1][name.charAt(3)-'0'-1] = name.charAt(5)-'0';
		}
		for(String s : initial)
			solution[s.charAt(1)-'0'-1][s.charAt(3)-'0'-1] = s.charAt(5)-'0';
		
		for(int r = 0; r < solution.length; ++r) {
			System.out.print("|");
			for(int c = 0; c < solution[r].length; ++c) {
				System.out.print(solution[r][c] + "|");
			}
			System.out.println();
		}
	}
}
