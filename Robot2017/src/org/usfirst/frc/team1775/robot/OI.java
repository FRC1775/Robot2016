package org.usfirst.frc.team1775.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

import org.usfirst.frc.team1775.robot.commands.autonomous.DriveFromBackWallAndShoot;
import org.usfirst.frc.team1775.robot.commands.drivetrain.AngleTune;
import org.usfirst.frc.team1775.robot.commands.drivetrain.ResetGyro;
import org.usfirst.frc.team1775.robot.commands.drivetrain.RotateByAngle;
import org.usfirst.frc.team1775.robot.commands.gearassembly.CloseGear;
import org.usfirst.frc.team1775.robot.commands.gearassembly.ReleaseGear;
import org.usfirst.frc.team1775.robot.commands.shooter.AdjustShooterSpeed;
import org.usfirst.frc.team1775.robot.commands.shooter.AdjustShooterSpeed.ChangeDirection;
import org.usfirst.frc.team1775.robot.commands.shooter.Shoot;
import org.usfirst.frc.team1775.robot.commands.shooter.StopShooterSubsystem;
import org.usfirst.frc.team1775.robot.commands.winch.WindWinch;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public final static int DRIVER_JOYSTICK = 0;
	public final static int OPERATOR_JOYSTICK = 1;
	
	public final static int XBOX_A = 1;
	public final static int XBOX_B = 2;
	public final static int XBOX_X = 3;
	public final static int XBOX_Y = 4;
	public final static int XBOX_LEFT_BUMPER = 5;
	public final static int XBOX_RIGHT_BUMPER = 6;
	public final static int XBOX_BACK = 7;
	public final static int XBOX_START = 8;
	
	public final static int XBOX_POV_UP = 0;
	public final static int XBOX_POV_DOWN = 180;

	public final static int XBOX_LEFT_JOYSTICK_X_AXIS = 0;
	public final static int XBOX_LEFT_JOYSTICK_Y_AXIS = 1;
	public final static int XBOX_LEFT_TRIGGER = 2;
	public final static int XBOX_RIGHT_TRIGGER = 3;
	public final static int XBOX_RIGHT_JOYSTICK_X_AXIS = 4;
	public final static int XBOX_RIGHT_JOYSTICK_Y_AXIS = 5;

	public Joystick driverJoystick;
	public Joystick operatorJoystick;

	public JoystickButton driverAButton;
	public JoystickButton driverBButton;
	public JoystickButton driverXButton;
	public JoystickButton driverYButton;
	public JoystickButton driverLeftBumper;
	public JoystickButton driverRightBumper;
	public JoystickButton driverBackButton;
	public JoystickButton driverStartButton;
	public Trigger        driverLeftTrigger;
	public Trigger        driverRightTrigger;
	public Trigger        driverPovUp;
	public Trigger        driverPovDown;

	public JoystickButton operatorAButton;
	public JoystickButton operatorBButton;
	public JoystickButton operatorXButton;
	public JoystickButton operatorYButton;
	public JoystickButton operatorLeftBumper;
	public JoystickButton operatorRightBumper;
	public JoystickButton operatorBackButton;
	public JoystickButton operatorStartButton;
	public Trigger        operatorLeftTrigger;
	public Trigger        operatorRightTrigger;
	public Trigger        operatorPovUp;
	public Trigger        operatorPovDown;

	public OI() {
		initDriverJoystick();
		initOperatorJoystick();
	}
	
	private void initDriverJoystick() {
		driverJoystick = new Joystick(DRIVER_JOYSTICK);
		
		// A button
		driverAButton = new JoystickButton(driverJoystick, XBOX_A);
		driverAButton.whileHeld(new ReleaseGear());
		
		// B button
		driverBButton = new JoystickButton(driverJoystick, XBOX_B);
		
		// X button
		driverXButton = new JoystickButton(driverJoystick, XBOX_X);
		driverXButton.whenPressed(new ReleaseGear(true));
		
		// Y button
		driverYButton = new JoystickButton(driverJoystick, XBOX_Y);
		driverYButton.whenPressed(new CloseGear());

		// Left bumper
		driverLeftBumper = new JoystickButton(driverJoystick, XBOX_LEFT_BUMPER);
		driverLeftBumper.whenPressed(new RotateByAngle());
		
		// Right bumper
		driverRightBumper = new JoystickButton(driverJoystick, XBOX_RIGHT_BUMPER);
		
		// Back button
		driverBackButton = new JoystickButton(driverJoystick, XBOX_BACK);
		driverBackButton.whenPressed(new AngleTune());
		
		// Start button 
		driverStartButton = new JoystickButton(driverJoystick, XBOX_START);
		driverStartButton.whenPressed(new ResetGyro());

		// Left trigger
		driverLeftTrigger = new Trigger() {
			@Override
			public boolean get() {
				return driverJoystick.getRawAxis(XBOX_LEFT_TRIGGER) > 0;
			}
		};
		driverLeftTrigger.whenActive(new Shoot());
		driverLeftTrigger.whenInactive(new StopShooterSubsystem());
		
		// Right trigger
		driverRightTrigger = new Trigger() {
			@Override
			public boolean get() {
				return driverJoystick.getRawAxis(XBOX_RIGHT_TRIGGER) > 0;
			}
		};
		driverRightTrigger.whileActive(new WindWinch());
		
		// POV Up
		driverPovUp = new Trigger() {
			@Override
			public boolean get() {
				return driverJoystick.getPOV() == XBOX_POV_UP;
			}
		};
		driverPovUp.whenActive(new AdjustShooterSpeed(ChangeDirection.Up));
		
		// POV Down
		driverPovDown = new Trigger() {
			@Override
			public boolean get() {
				return driverJoystick.getPOV() == XBOX_POV_DOWN;
			}
		};
		driverPovDown.whenActive(new AdjustShooterSpeed(ChangeDirection.Down));
	}
	
	private void initOperatorJoystick() {
		operatorJoystick = new Joystick(OPERATOR_JOYSTICK);

		if (!hasOperatorJoystick()) {
			operatorJoystick = null;
			return;
		}
		
		// A button
		operatorAButton = new JoystickButton(operatorJoystick, XBOX_A);
		operatorAButton.whileHeld(new ReleaseGear());
		
		// B button
		operatorBButton = new JoystickButton(operatorJoystick, XBOX_B);
		//operatorBButton.whenPressed(new RotateByAngle(90));
		
		// X button
		operatorXButton = new JoystickButton(operatorJoystick, XBOX_X);
		
		// Y button
		operatorYButton = new JoystickButton(operatorJoystick, XBOX_Y);

		// Left bumper
		operatorLeftBumper = new JoystickButton(operatorJoystick, XBOX_LEFT_BUMPER);
		operatorLeftBumper.whenPressed(new RotateByAngle());
		
		// Right bumper
		operatorRightBumper = new JoystickButton(operatorJoystick, XBOX_RIGHT_BUMPER);
		
		// Back button
		operatorBackButton = new JoystickButton(operatorJoystick, XBOX_BACK);
		
		// Start button 
		operatorStartButton = new JoystickButton(operatorJoystick, XBOX_START);
		operatorStartButton.whenPressed(new DriveFromBackWallAndShoot());

		// Left trigger
		operatorLeftTrigger = new Trigger() {
			@Override
			public boolean get() {
				return operatorJoystick.getRawAxis(XBOX_LEFT_TRIGGER) > 0;
			}
		};
		operatorLeftTrigger.whenActive(new Shoot());
		operatorLeftTrigger.whenInactive(new StopShooterSubsystem());
		
		// Right trigger
		operatorRightTrigger = new Trigger() {
			@Override
			public boolean get() {
				return operatorJoystick.getRawAxis(XBOX_RIGHT_TRIGGER) > 0;
			}
		};
		operatorRightTrigger.whileActive(new WindWinch());
		
		// POV Up
		operatorPovUp = new Trigger() {
			@Override
			public boolean get() {
				return operatorJoystick.getPOV() == XBOX_POV_UP;
			}
		};
		operatorPovUp.whenActive(new AdjustShooterSpeed(ChangeDirection.Up));
		
		// POV Down
		operatorPovDown = new Trigger() {
			@Override
			public boolean get() {
				return operatorJoystick.getPOV() == XBOX_POV_DOWN;
			}
		};
		operatorPovDown.whenActive(new AdjustShooterSpeed(ChangeDirection.Down));
	}
	
	public boolean hasOperatorJoystick() {
		if (operatorJoystick == null) {
			return false;
		}
		
		try
		{
			operatorJoystick.getType();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void rumbleLeft(double value) {
		driverJoystick.setRumble(RumbleType.kLeftRumble, value);
		
		if (hasOperatorJoystick()) {
			operatorJoystick.setRumble(RumbleType.kLeftRumble, value);
		}
	}
	
	public void rumbleRight(double value) {
		driverJoystick.setRumble(RumbleType.kRightRumble, value);
		
		if (hasOperatorJoystick()) {
			operatorJoystick.setRumble(RumbleType.kRightRumble, value);
		}
	}
	
	public void rumbleBoth(double value) {
		rumbleLeft(value);
		rumbleRight(value);
	}
	
	public double getLeftTrigger() {
		if (!hasOperatorJoystick()) {
			return Robot.oi.driverJoystick.getRawAxis(OI.XBOX_LEFT_TRIGGER);
		}
		
		return Math.max(Robot.oi.driverJoystick.getRawAxis(OI.XBOX_LEFT_TRIGGER), Robot.oi.operatorJoystick.getRawAxis(OI.XBOX_LEFT_TRIGGER));
	}
	
	public double getRightTrigger() {
		if (!hasOperatorJoystick()) {
			return Robot.oi.driverJoystick.getRawAxis(OI.XBOX_RIGHT_TRIGGER);
		}
		
		return Math.max(Robot.oi.driverJoystick.getRawAxis(OI.XBOX_RIGHT_TRIGGER), Robot.oi.operatorJoystick.getRawAxis(OI.XBOX_RIGHT_TRIGGER));
	}
}
