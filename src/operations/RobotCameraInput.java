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
		System.out.println("grabbed");
		if(this.output.width() != 0) {
			super.runChildren();
		}
	}
	@Override
	public Mat getOutput() {
		return this.output;
	}
	@Override
	public void clearMemory() {
		this.output = new Mat();
	}
}