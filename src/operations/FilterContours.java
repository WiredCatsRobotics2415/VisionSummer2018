package operations;

import java.util.*;

import org.opencv.core.MatOfPoint;
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
	private List<MatOfPoint> contours;
	/**
	 * Constructor for FilterContours
	 * @param input input layer (must output contours)
	 */
	public FilterContours(ContourOutput input) {
		super(input);
		this.input = input;
		this.contours = new ArrayList<MatOfPoint>();
	}
	/**
	 * filters the contours
	 * <p>
	 * stores the contours as a varible in the class. The contours can be accesed by
	 * using the getContours() method
	 */
	@Override
	public void process() {
		
	}
	/**
	 * returns the filtered list of contours
	 * @return list of filtered contours
	 */
	@Override
	public List<MatOfPoint> getContours() {
		return this.contours;
	}
}
