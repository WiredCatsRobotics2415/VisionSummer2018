package operations;

import org.opencv.core.Mat;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.CameraServer;

public class RobotCameraInput extends MatOutput{
	private CvSink cvSink;
	private Mat output;
	
	public RobotCameraInput() {
		super();
		this.cvSink = CameraServer.getInstance().getVideo();
		this.output = new Mat();
	}
	
	public void process() {
		this.cvSink.grabFrame(this.output);
	}
	
	public Mat getOutput() {
		return this.output;
	}
}