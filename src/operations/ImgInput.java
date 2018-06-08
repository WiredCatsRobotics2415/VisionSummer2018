package operations;

import org.opencv.core.Mat;

public class ImgInput extends MatOutput{
	private Mat output;
	
	public ImgInput() {
		super(null);
		this.output = null;
	}
	
	public void process(Mat img) {
		this.output = img;
	}
	
	public void process() {
		this.output = new Mat();
		runChildren();
	}
	
	public Mat getOutput() {
		return this.output;
	}
}
