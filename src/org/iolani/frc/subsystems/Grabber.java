package org.iolani.frc.subsystems;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.SetGrabberGrabbed;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Grabber extends Subsystem {

    private Solenoid _valve;
    
    public void init() {
    	System.out.println("Grabber init start");
    	_valve = new Solenoid(RobotMap.grabberSolenoid);
    	System.out.println("Grabber init end");
    }
    
    /**
     * Get the grabbed state.
     * @return true if the grabber is grabbed
     */
    public boolean isGrabbed() {
    	return !_valve.get();
    }
    
    public void setGrabbed(boolean grabbed) {
    	_valve.set(!grabbed);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	this.setDefaultCommand(new SetGrabberGrabbed(true));
    }
    
    public void debug() {
    	SmartDashboard.putBoolean("grabber-valve", _valve.get());
    }
}

