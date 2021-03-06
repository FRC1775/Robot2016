package org.usfirst.frc.team1775.robot.commands.autonomous;

import org.usfirst.frc.team1775.robot.commands.drivetrain.DriveDistance;
import org.usfirst.frc.team1775.robot.commands.drivetrain.RotateByAngle;
import org.usfirst.frc.team1775.robot.commands.drivetrain.StopDrive;
import org.usfirst.frc.team1775.robot.commands.shooter.StartRegulator;
import org.usfirst.frc.team1775.robot.commands.shooter.StartShooter;
import org.usfirst.frc.team1775.robot.commands.shooter.StartSingulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Red_Right_Shoot extends CommandGroup {

	public Red_Right_Shoot() {
		addSequential(new StartShooter());
		addSequential(new DriveDistance(30), 2);
		addParallel(new StopDrive());
		
		addSequential(new RotateByAngle(82), 3);
		addParallel(new StopDrive());
		
		addSequential(new WaitCommand(.3));
		addSequential(new StartSingulator());
		addSequential(new StartRegulator());
	}
}
