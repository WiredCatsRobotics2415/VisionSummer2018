package operations;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Blur extends  MatOutput{
	private MatOutput input;
	private BlurType blurtype;
	private double radius;
	private Mat output;
	
	public Blur(MatOutput input, BlurType blurtype, double radius) {
		super(input);
		this.input = input;
		this.blurtype = blurtype;
		this.radius = radius;
		this.output = null;
	}
	
	public void process() {
		int radius = (int)(this.radius + 0.5);
		int kernelSize;
		switch(this.blurtype){
			case BOX:
				kernelSize = 2 * radius + 1;
				Imgproc.blur(this.input.getOutput(), this.output, new Size(kernelSize, kernelSize));
				break;
			case GAUSSIAN:
				kernelSize = 6 * radius + 1;
				Imgproc.GaussianBlur(this.input.getOutput(),this.output, new Size(kernelSize, kernelSize), radius);
				break;
			case MEDIAN:
				kernelSize = 2 * radius + 1;
				Imgproc.medianBlur(this.input.getOutput(), this.output, kernelSize);
				break;
			case BILATERAL:
				Imgproc.bilateralFilter(this.input.getOutput(), this.output, -1, radius, radius);
				break;
		}
		runChildren();
	}
	
	public Mat getOutput() {
		return this.output;
	}
	
	enum BlurType {
		BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
		BILATERAL("Bilateral Filter");

		private final String label;

		BlurType(String label) {
			this.label = label;
		}

		public static BlurType get(String type) {
			if (BILATERAL.label.equals(type)) {
				return BILATERAL;
			}
			else if (GAUSSIAN.label.equals(type)) {
				return GAUSSIAN;
			}
			else if (MEDIAN.label.equals(type)) {
				return MEDIAN;
			}
			else {
				return BOX;
			}
		}

		@Override
		public String toString() {
			return this.label;
		}
	}
}
