package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.SetElevatorPower;
import org.iolani.frc.util.Utility;

/**
 *
 */
public class Elevator extends Subsystem {
	private CANTalon _lTalon;
	private CANTalon _rTalon;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void init() {
		_lTalon = new CANTalon(RobotMap.elevatorLeftTalon);
    	_rTalon = new CANTalon(RobotMap.elevatorRightTalon);
    	
    	_lTalon.enableBrakeMode(true);
    	_rTalon.enableBrakeMode(true);
	}
	
	/**
	 * Positive is up.
	 * @param power
	 */
	public void setPower(double power) {
		_lTalon.set(-power);
		_rTalon.set(power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new SetElevatorPower(0));
    }
}

