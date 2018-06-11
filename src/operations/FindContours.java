package operations;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
/**
 * @author matthewpropp
 */
public class FindContours extends ContourOutput{
	/**
	 * previous operation layer to get image input
	 */
	private MatOutput input;
	/*
	 * the contour output
	 */
	private List<MatOfPoint> contours;
	/*
	 * if only external contours should be counted
	 */
	private boolean externalOnly;
	/**
	 * Constructor for FindCountours
	 * @param input input layer (must output an image)
	 * @param externalOnly (if only external contours should be counted
	 */
	public FindContours(MatOutput input,boolean externalOnly) {
		super(input);
		this.input = input;
		this.externalOnly = externalOnly;
		this.contours = new ArrayList<MatOfPoint>();
	}
	/**
	 * finds the contours in the image and stores them in a list in the class. The
	 * contours can be accessed by using the getContours() method.
	 */
	@Override
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
	/**
	 * returns the list of contours from the last iamge
	 * @return list of contours from the last image
	 */
	@Override
	public List<MatOfPoint> getContours() {
		return this.contours;
	}
}
