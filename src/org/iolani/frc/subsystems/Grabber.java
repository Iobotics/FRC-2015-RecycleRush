package org.iolani.frc.subsystems;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.SetGrabberPower;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabber extends Subsystem {
    
    private Talon _grabTalon;
    
    public void init() {
    	System.out.println("Grabber init start");
    	_grabTalon = new Talon(RobotMap.grabberTalon);
    	System.out.println("Grabber init end");
    }
    
    /**
     * Get the grabbed state.
     * @return true if the grabber is grabbed
     */
    public boolean isGrabbed() {
    	return false;
    }
    
    /*public void setGrabbed(boolean grabbed) {
    	_grabTalon.set(grabbed? 0.5 : -0.5);
    }*/
    
    public void setPower(double power) {
    	_grabTalon.set(power);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	this.setDefaultCommand(new SetGrabberPower(0.0));
    }
}

