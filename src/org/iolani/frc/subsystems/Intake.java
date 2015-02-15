package org.iolani.frc.subsystems;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */

public class Intake extends Subsystem {

	private Talon _lIntake;
	private Talon _rIntake;
    
    public void init() {
    	_lIntake = new Talon(RobotMap.intakeLeftTalon);
    	_rIntake = new Talon(RobotMap.intakeRightTalon);
    }
    
    public void setPower(double leftPower, double rightPower) {
    	_lIntake.set(leftPower);
    	_rIntake.set(-rightPower);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new OperateIntake());
    }
}

