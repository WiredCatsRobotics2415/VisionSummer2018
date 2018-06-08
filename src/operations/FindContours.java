package operations;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;

public class FindContours extends ContourOutput{
	private MatOutput input;
	private List<MatOfPoint> contours;
	private boolean externalOnly;
	
	public FindContours(MatOutput input,boolean externalOnly) {
		super(input);
		this.input = input;
		this.externalOnly = externalOnly;
		this.contours = new ArrayList<MatOfPoint>();
	}
	
	public void process() {
		Mat hierarchy = new Mat();
		this.contours.clear();
		int mode;
		if (this.externalOnly) {
			mode = Imgproc.RETR_EXTERNAL;
		}
		else {
			mode = Imgproc.RETR_LIST;
		}
		int method = Imgproc.CHAIN_APPROX_SIMPLE;
		Imgproc.findContours(this.input.getOutput(), this.contours, hierarchy, mode, method);
		runChildren();
	}
	
	public List<MatOfPoint> getContours() {
		return this.contours;
	}
}
