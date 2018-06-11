package robotVision;

import operations.*;

public class VisionThread extends Thread {
	private OperationTree pipeline;
	public VisionThread(Operation root) {
		this.pipeline = new OperationTree(root);
	}
	@Override
	public void run() {
		while(!Thread.interrupted()) {
			this.pipeline.run();
		}
	}
	public OperationTree getPipeline() {
		return this.pipeline;
	}
	public Operation getOperation() {
		return this.pipeline.getOperation(new int[] {-1});
	}
	public Operation getOperation(int[] path) {
		return this.pipeline.getOperation(path);
	}
}
