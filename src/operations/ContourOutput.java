package operations;

import java.util.List;

import org.opencv.core.MatOfPoint;

public abstract class ContourOutput extends Operation{
	public ContourOutput(Operation parent) {
		super(parent);
	}
	public abstract List<MatOfPoint> getContours();
}
