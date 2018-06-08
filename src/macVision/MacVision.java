package macVision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import operations.ImgInput;
import operations.OperationTree;


public class MacVision {
	public static void main(String[] args) {
		//System.load("/users/matthewpropp/wpilib/java/current/lib/opencv.jar");
		for(int i = 0; i < 1000; i++) {
			//VideoCapture cam = new VideoCapture(0);
			DisplayPanal dp = new DisplayPanal();
			Mat img = new Mat();
			ImgInput input = new ImgInput();
			OperationTree o = new OperationTree(input);
			/*if(cam.isOpened()) {
				cam.read(img);
				input.process(img);
			} else {
				input.process();
			}*/
			dp.MatToBufferedImage(img);
			dp.repaint();
		}
	}
}
