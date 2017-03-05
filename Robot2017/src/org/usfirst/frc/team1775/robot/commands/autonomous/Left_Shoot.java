package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.drivetrain.RotateByAngle;
import org.usfirst.frc.team1775.robot.commands.shooter.Shoot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Left_Shoot extends CommandGroup {

	public Left_Shoot() {
		addSequential(new DriveDistance(60));
		addSequential(new RotateByAngle(-110));
		addSequential(new Wait(200));
		addSequential(new RotateByAngle());
		addSequential(new Wait(200));
		addSequential(new RotateByAngle());
		//addSequential(new RotateByAngle());
		addSequential(new Shoot(), 3);
	}
}