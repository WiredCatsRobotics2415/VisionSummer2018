/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2415.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import operations.*;
import operations.Blur.BlurType;
import robotVision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private VisionThread visionThread;
	private Thread thread;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);
//		camera.setBrightness(80);
		camera.setWhiteBalanceAuto();
		System.out.println(camera.getBrightness());
		camera.setExposureManual(15);
		VideoMode videoMode = camera.getVideoMode();
		this.visionThread = new VisionThread(new RobotCameraInput());
		//new Blur((MatOutput)(this.visionThread.getOperation()), BlurType.get("Box Blur"), 10);
		new HSVThreshold((MatOutput)(this.visionThread.getOperation()), new double[] {110,130}, new double[] {175,255}, new double[] {25,150});
		new FindContours((MatOutput)(this.visionThread.getOperation()), false);
		//new FilterContours((ContourOutput)(this.visionThread.getOperation()), new double[] {0,100}, new double[] {0,1});
		new RobotCameraOutput((MatOutput)(this.visionThread.getOperation(new int[] {0})), "HSV", videoMode.width, videoMode.height);
		new RobotCameraOutput((MatOutput)(this.visionThread.getOperation(new int[] {0})), "FindContours", videoMode.width, videoMode.height, (ContourOutput)(this.visionThread.getOperation(new int[] {0,0})));
		//new ContourToMat((ContourOutput)(this.visionThread.getOperation(new int[] {0,0,0})));
		//new RobotCameraOutput((MatOutput)(this.visionThread.getOperation(new int[] {0,0,0,0})), "FilterContours", videoMode.width, videoMode.height);
		this.visionThread.start();
		/*this.thread = new Thread(()-> {
			while(!Thread.interrupted()) {
				System.out.println(Runtime.getRuntime().freeMemory());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		this.thread.start();*/
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		this.visionThread.interrupt();
		//this.thread.interrupt();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
