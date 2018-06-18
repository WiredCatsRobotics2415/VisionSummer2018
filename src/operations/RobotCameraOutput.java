package operations;

import java.util.List;

import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;

public class RobotCameraOutput extends Operation {
	public static final int DEFAULT_WIDTH = 680;
	public static final int DEFAULT_HEIGHT = 480;
	MatOutput input;
	CvSource cvSource;
	ContourOutput cInput;
	
	public RobotCameraOutput(MatOutput parent) {
		super(parent);
		input = parent;
		this.cInput = null;
		this.cvSource = CameraServer.getInstance().putVideo("Output", DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	public RobotCameraOutput(MatOutput parent, String name) {
		super(parent);
		input = parent;
		this.cInput = null;
		this.cvSource = CameraServer.getInstance().putVideo(name, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	public RobotCameraOutput(MatOutput parent, String name, int width, int height) {
		super(parent);
		input = parent;
		this.cInput = null;
		this.cvSource = CameraServer.getInstance().putVideo(name, width, height);
	}
	public RobotCameraOutput(MatOutput parent, String name, int width, int height, ContourOutput cInput) {
		this(parent,name,width,height);
		this.cInput = cInput;
	}
	
	public void process() {
		Mat img = this.input.getOutput();
		Rect bb;
		if(this.cInput != null) {
			try {
				for(MatOfPoint contour: this.cInput.getContours()) {
					bb = Imgproc.boundingRect(contour);
					Imgproc.rectangle(img,new Point(bb.x,bb.y), new Point(bb.x+bb.width, bb.y+bb.height), new Scalar(255,0,0));
				}
			} catch(CvException e) {
				System.out.println(e);
			}
		}
		if(!this.input.getOutput().empty()) { 
			this.cvSource.putFrame(this.input.getOutput());
		} else {
			System.out.println("Empty image"+this.cvSource.getName());
		}
		System.out.println("pushed " + this.cvSource.getName());
	}
}
