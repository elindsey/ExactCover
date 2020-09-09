
public class Node {
	public int size;
	private final String name;
	private Node columnHeader;
	public Node left, right, up, down;
	
	public Node(String name, int size, Node columnHeader) {
		this.size = size;
		this.name = name;
		this.columnHeader = columnHeader;
		
		left  = this;
		right = this;
		up    = this;
		down  = this;
	}
	
	public String getName()  { return name; }
	public Node getColumnHeader() { return columnHeader; }
	public String toString() { return name + ":" + size; }
}