package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.drivetrain.RotateByAngle;
import org.usfirst.frc.team1775.robot.commands.shooter.AdjustShooterSpeed;
import org.usfirst.frc.team1775.robot.commands.shooter.StartRegulator;
import org.usfirst.frc.team1775.robot.commands.shooter.StartShooter;
import org.usfirst.frc.team1775.robot.commands.shooter.StartSingulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Blue_Left_PlaceGearThenShoot extends CommandGroup {

	public Blue_Left_PlaceGearThenShoot() {
		// Run Left_PlaceGear
		// Move to a shooting position
		// Shoot
		addSequential(new Blue_Left_PlaceGear(true));
		addSequential(new RotateByAngle(130), 3);
		addSequential(new StartShooter());
		addSequential(new Wait(400));
		addSequential(new DriveDistance(30), 2);
		addSequential(new Wait(200));
		addSequential(new RotateByAngle(), 1);
		addSequential(new Wait(200));
		addSequential(new RotateByAngle(), 0.5);
		addSequential(new Wait(1000));
		addSequential(new AdjustShooterSpeed());
		addSequential(new StartSingulator());
		addSequential(new ChangeCamera());
		addSequential(new Wait(200));
		addSequential(new StartRegulator());
	}
}