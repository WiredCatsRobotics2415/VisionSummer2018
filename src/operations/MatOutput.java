package operations;

import org.opencv.core.Mat;

public abstract class MatOutput extends Operation{
	public MatOutput(Operation parent) {
		super(parent);
	}
	public abstract Mat getOutput();
}
