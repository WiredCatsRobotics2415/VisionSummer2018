package operations;

public class OperationTree {
	private Operation root;
	
	public OperationTree(Operation root) {
		this.root = root;
	}
	
	public Operation getOperation(int[] path) { //path to the parent of the new operation
		return root.getChild(path);
	}
	
	public void run() {
		this.root.process();
	}
}