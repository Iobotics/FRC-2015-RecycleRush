package org.iolani.frc.subsystems;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */

public class Intake extends Subsystem {

	private CANTalon _lIntake;
	private CANTalon _rIntake;
    
    public void init() {
    	_lIntake = new CANTalon(RobotMap.intakeLeftTalon);
    	_rIntake = new CANTalon(RobotMap.intakeRightTalon);
    	
    	_lIntake.enableBrakeMode(true);
    	_rIntake.enableBrakeMode(true);
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

