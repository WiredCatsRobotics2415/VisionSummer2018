package operations;

import java.util.ArrayList;
import java.util.List;

public abstract class Operation {
	private List<Operation> children;
	public abstract void process();
	
	public Operation(Operation parent) {
		this.children = new ArrayList<Operation>();
		if(parent != null) {
			parent.addChild(this);
		}
	}
	public void addChild(Operation child) {
		this.children.add(child);
	}
	
	public Operation getChild(int[] path) {
		if(path.length == 0) {
			return this;
		} else {
			int[] newPath = new int[path.length-1];
			for(int i = 0; i < newPath.length; i++) {
				newPath[i] = path[i+1];
			}
			return children.get(path[0]).getChild(newPath);
		}
	}
	
	public void runChildren() {
		for(Operation child: children) {
			child.process();
		}
	}
}