package operations;

import java.util.*;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
/**
 * @author matthewpropp
 */
public class FilterContours extends ContourOutput{
	/**
	 * previous operation layer to get contour inputs
	 */
	private ContourOutput input;
	/**
	 * the contour output
	 */
	private List<MatOfPoint> output;
	private double[] ratio, fillRatio;
	/**
	 * Constructor for FilterContours
	 * @param input input layer (must output contours)
	 */
	public FilterContours(ContourOutput input, double[] ratio, double[] fillRatio) {
		super(input);
		this.input = input;
		this.output = new ArrayList<MatOfPoint>();
		this.ratio = ratio;
		this.fillRatio = fillRatio;
	}
	/**
	 * filters the contours
	 * <p>
	 * stores the contours as a varible in the class. The contours can be accesed by
	 * using the getContours() method
	 */
	@Override
	public void process() {
		List<MatOfPoint> inputContours = input.getContours();
		Rect bb;
		this.output.clear();
		for(int i = 0; i < inputContours.size();i++) {
			bb = Imgproc.boundingRect(inputContours.get(i));
			if(bb.width/bb.height < this.ratio[0] && bb.width/bb.height > this.ratio[1]) {
				continue;
			}
			if(bb.area()/Imgproc.contourArea(inputContours.get(i)) < this.fillRatio[0] &&
					bb.area()/Imgproc.contourArea(inputContours.get(i)) > this.fillRatio[1]) {
				continue;
			}
			this.output.add(inputContours.get(i));
		}
		System.out.println("Filtered C");
		super.runChildren();
	}
	/**
	 * returns the filtered list of contours
	 * @return list of filtered contours
	 */
	@Override
	public List<MatOfPoint> getContours() {
		return this.output;
	}
	@Override
	public void clearMemory() {
		this.output.clear();
	}
}
