package operations;

import java.util.List;

import org.opencv.core.MatOfPoint;
/**
 * @author matthewpropp
 */
public abstract class ContourOutput extends Operation{
	/**
	 * Constructor for ContourOutput
	 * @param parent the class the operation will recieve its input from
	 */
	public ContourOutput(Operation parent) {
		super(parent);
	}
	/**
	 * will output the contours from the last processed image
	 * @return the contours from the last processed image
	 */
	public abstract List<MatOfPoint> getContours();
}
