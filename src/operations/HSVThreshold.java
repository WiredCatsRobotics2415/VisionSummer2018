package operations;

import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class HSVThreshold extends MatOutput{
	private MatOutput input;
	private double[] hue, sat, val;
	private Mat output;
	
	public HSVThreshold(MatOutput input, double[] hue, double[] sat, double[] val) {
		super(input);
		this.input = input;
		this.hue = hue;
		this.sat = sat;
		this.val = val;
		this.output = new Mat();
	}
	
	public void process() {
		try {
			Imgproc.cvtColor(this.input.getOutput(), this.output, Imgproc.COLOR_BGR2HSV);
		} catch(CvException e) {
			System.out.println(e);
		}
		try {
			Core.inRange(this.output, new Scalar(hue[0], sat[0], val[0]), new Scalar(hue[1], sat[1], val[1]), this.output);
		} catch(CvException e) {
			System.out.println(e);
		}
		System.out.println("HSV");
		runChildren();
	}
	@Override
	public Mat getOutput() {
		return output;
	}
	@Override
	public void clearMemory() {
		this.output = new Mat();
	}
}
