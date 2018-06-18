package operations;

import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ContourToMat extends MatOutput {
	ContourOutput input;
	Mat output;
	public ContourToMat(ContourOutput parent) {
		super(parent);
		this.input = parent;
		this.output = new Mat();
	}
	@Override
	public Mat getOutput() {
		return this.output;
	}

	@Override
	public void process() {
		this.output = new Mat(new Size(640,480), CvType.CV_8UC3, new Scalar(0,0,0));
		try {
			Imgproc.drawContours(this.output, this.input.getContours(), -1, new Scalar(255,255,255));
		} catch(CvException e) {
			System.out.println(e);
		}
		System.out.println("contourToMat");
		super.runChildren();
	}
	@Override
	public void clearMemory() {
		this.output = new Mat();
	}
}
