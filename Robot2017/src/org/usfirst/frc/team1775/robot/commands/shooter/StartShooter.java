package org.usfirst.frc.team1775.robot.commands.shooter;

import org.usfirst.frc.team1775.robot.Cameras;
import org.usfirst.frc.team1775.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StartShooter extends Command {

	private int rpm;
	private boolean shouldWait;
	
	public StartShooter() {
		this(true);
	}
	
	public StartShooter(boolean shouldWait) {
		requires(Robot.shooter);
		this.shouldWait = shouldWait;
	}
	
	public static int getRpmFromCamera() {
		if (Cameras.distance == 0) {
			return 0;
		} else {
			//return (int)(294.73 * Cameras.distance * 0.025 + 1060 + 100); // Calculate rpms from distance in inches
			return (int) (11.239*Cameras.distance+646.7);
		}
	}
	
	public void initialize() {
		this.rpm = getRpmFromCamera();
	}

	protected void execute() {
		Robot.shooter.startShooter(rpm);
	}
	
	@Override
	protected boolean isFinished() {
		if (shouldWait) {
			return Robot.shooter.isShooterReady();
		} else {
			return true;
		}
	}
}
