package operations;

public class OperationTree {
	private ImgInput root;
	
	public OperationTree(ImgInput root) {
		this.root = root;
	}
	
	public Operation getOperation(int[] path) { //path to the parent of the new operation
		return root.getChild(path);
	}
}
