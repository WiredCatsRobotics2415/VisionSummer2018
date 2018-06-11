package operations;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
/**
 * @author matthewpropp
 */
public class Blur extends  MatOutput{
	/**
	 * previous operation layer to get image input
	 */
	private MatOutput input;
	/**
	 * the type of blur
	 */
	private BlurType blurtype;
	/**
	 * increases blur with a higher radius
	 */
	private double radius;
	/**
	 * the image output
	 */
	private Mat output;
	/**
	 * Constructor for Blur class
	 * @param input input layer (must output an image)
	 * @param blurtype the type of blur (use BlurType.get(type))
	 * @param radius increase for increased blur
	 */
	public Blur(MatOutput input, BlurType blurtype, double radius) {
		super(input);
		this.input = input;
		this.blurtype = blurtype;
		this.radius = radius;
		this.output = null;
	}
	/**
	 * applies a blur to the input image
	 * <p>
	 * stores the resulting image as a varible in the class. The blured image
	 * can be accessed using getOutput()
	 */
	@Override
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
	/**
	 * returns the last blurred image
	 * @return last blurred image
	 */
	public Mat getOutput() {
		return this.output;
	}
	/**
	 *Different Blurtypes including "Box Blur", "Gaussian Blur", "Median Filter", and "Bilateral Filter"
	 */
	public enum BlurType {
		BOX("Box Blur"), GAUSSIAN("Gaussian Blur"), MEDIAN("Median Filter"),
		BILATERAL("Bilateral Filter");
		/**
		 * holds the type of blur as a String
		 */
		private final String label;
		/*
		 * Constructor for BlurType
		 */
		BlurType(String label) {
			this.label = label;
		}
		/**
		 * Will return a Blurtype of the given type
		 * @param type the type of blur as a String
		 * @return the BlurType correlating with the String type 
		 */
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
