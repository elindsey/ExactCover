import java.util.HashSet;
import java.util.Set;



public class ExactCover {

	public static void main(String[] args) {
		//DLX dlx = new DLX(test1());
		//dlx.findSolution();
		
		Set<String> initial = new HashSet<String>();
		initial.add("r1c2#7");
		initial.add("r1c3#5");
		initial.add("r1c5#9");
		initial.add("r1c9#6");
		
		initial.add("r2c2#2");
		initial.add("r2c3#3");
		initial.add("r2c5#8");
		initial.add("r2c8#4");
		
		initial.add("r3c1#8");
		initial.add("r3c6#3");
		initial.add("r3c9#1");
		
		initial.add("r4c1#5");
		initial.add("r4c4#7");
		initial.add("r4c6#2");
		
		initial.add("r5c2#4");
		initial.add("r5c4#8");
		initial.add("r5c6#6");
		initial.add("r5c8#2");
		
		initial.add("r6c4#9");
		initial.add("r6c6#1");
		initial.add("r6c9#3");
		
		initial.add("r7c1#9");
		initial.add("r7c4#4");
		initial.add("r7c9#7");
		
		initial.add("r8c2#6");
		initial.add("r8c5#7");
		initial.add("r8c7#5");
		initial.add("r8c8#8");
		
		initial.add("r9c1#7");
		initial.add("r9c5#1");
		initial.add("r9c7#3");
		initial.add("r9c8#9");
		SudokuSolver ss = new SudokuSolver(initial);
		ss.findSolution();
	}

	public static Node test1() {
		Node root = new Node("Root", -1, null);

		Node a = new Node("A", 2, null);
		Node b = new Node("B", 2, null);
		Node c = new Node("C", 2, null);
		Node d = new Node("D", 3, null);
		Node e = new Node("E", 2, null);
		Node f = new Node("F", 2, null);
		Node g = new Node("G", 3, null);

		Node a1 = new Node("A1", -1, a);
		Node a2 = new Node("A2", -1, a);
		Node b1 = new Node("B1", -1, b);
		Node b2 = new Node("B2", -1, b);
		Node c1 = new Node("C1", -1, c);
		Node c2 = new Node("C2", -1, c);
		Node d1 = new Node("D1", -1, d);
		Node d2 = new Node("D2", -1, d);
		Node d3 = new Node("D3", -1, d);
		Node e1 = new Node("E1", -1, e);
		Node e2 = new Node("E2", -1, e);
		Node f1 = new Node("F1", -1, f);
		Node f2 = new Node("F2", -1, f);
		Node g1 = new Node("G1", -1, g);
		Node g2 = new Node("G2", -1, g);
		Node g3 = new Node("G3", -1, g);

		root.right = a;
		root.left = g;

		a.up = a2;
		a.down = a1;
		a.left = root;
		a.right = b;

		b.up = b2;
		b.down = b1;
		b.left = a;
		b.right = c;

		c.up = c2;
		c.down = c1;
		c.left = b;
		c.right = d;

		d.up = d3;
		d.down = d1;
		d.left = c;
		d.right = e;

		e.up = e2;
		e.down = e1;
		e.left = d;
		e.right = f;

		f.up = f2;
		f.down = f1;
		f.left = e;
		f.right = g;

		g.up = g3;
		g.down = g1;
		g.left = f;
		g.right = root;

		a1.up = a;
		a1.down = a2;
		a1.left = g1;
		a1.right = d1;

		a2.up = a1;
		a2.down = a;
		a2.left = d2;
		a2.right = d2;

		b1.up = b;
		b1.down = b2;
		b1.left = f2;
		b1.right = c2;

		b2.up = b1;
		b2.down = b;
		b2.left = g2;
		b2.right = g2;

		c1.up = c;
		c1.down = c2;
		c1.left = f1;
		c1.right = e1;

		c2.up = c1;
		c2.down = c;
		c2.left = b1;
		c2.right = f2;

		d1.up = d;
		d1.down = d2;
		d1.left = a1;
		d1.right = g1;

		d2.up = d1;
		d2.down = d3;
		d2.left = a2;
		d2.right = a2;

		d3.up = d2;
		d3.down = d;
		d3.left = g3;
		d3.right = e2;

		e1.up = e;
		e1.down = e2;
		e1.left = c1;
		e1.right = f1;

		e2.up = e1;
		e2.down = e;
		e2.left = d3;
		e2.right = g3;

		f1.up = f;
		f1.down = f2;
		f1.left = e1;
		f1.right = c1;

		f2.up = f1;
		f2.down = f;
		f2.left = c2;
		f2.right = b1;

		g1.up = g;
		g1.down = g2;
		g1.left = d1;
		g1.right = a1;

		g2.up = g1;
		g2.down = g3;
		g2.left = b2;
		g2.right = b2;

		g3.up = g2;
		g3.down = g;
		g3.left = e2;
		g3.right = d3;

		return root;
	}
}
