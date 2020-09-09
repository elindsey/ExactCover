import java.util.HashMap;
import java.util.Map;


public class DLXSolver {
	protected Node root;
	protected Map<Integer, Node>  path;
	
	public DLXSolver() {
		root = null;
		path = new HashMap<Integer, Node>();
	}

	public DLXSolver(Node root) {
		this.root = root;
		path = new HashMap<Integer, Node>();
	}

	public void findSolution() {
		search(0);
		System.out.println("Ending search");
	}

	private void search(int k) {
		if(root.right == root) {
			printSolution(k);
			return;
		}

		Node c = chooseColumn();
		coverColumn(c);
		for(Node d = c.down; d != c; d = d.down) {
			path.put(k, d);
			
			for(Node r = d.right; r != d; r = r.right) 
				coverColumn(r.getColumnHeader());

			search(k+1);
			d = path.get(k);
			c = d.getColumnHeader();
			
			for(Node l = d.left; l != d; l = l.left) 
				uncoverColumn(l.getColumnHeader());
		}
		uncoverColumn(c);
	}

	protected void coverColumn(Node n) {
		assert(n.getColumnHeader() == null);
		n.right.left = n.left;
		n.left.right = n.right;

		Node d = n.down;
		while(d != n) {
			Node r = d.right;
			while(r != d) {
				r.up.down = r.down;
				r.down.up = r.up;
				r.getColumnHeader().size--;
				r = r.right;
			}
			d = d.down;
		}
	}

	private void uncoverColumn(Node n) {
		assert(n.getColumnHeader() == null);

		for(Node u = n.up; u != n; u = u.up) {
			for(Node l = u.left; l != u; l = l.left) {
				l.getColumnHeader().size++;
				l.up.down = l;
				l.down.up = l;
			}
		}
		n.left.right = n;
		n.right.left = n;
	}

	private Node chooseColumn() {
		int currSize = Integer.MAX_VALUE;
		Node ret = null;

		for(Node currNode = root.right; currNode != root; currNode = currNode.right) {
			if(currNode.size < currSize) {
				currSize = currNode.size;
				ret = currNode;
			} 
		}

		return ret;
	}

	protected void printSolution(int k) {
		System.out.println("Solution:: ");
		for(int i = 0; i < k; i++)
			System.out.println(path.get(i).getColumnHeader());
	}
}
