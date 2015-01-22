/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

//import org.iolani.frc.util.PowerScaler;

/**
 *
 * @author iobotics
 */
public class OperateTankDrive extends CommandBase {
    
    //private static final double DEADBAND = 0.05;  
    
    public OperateTankDrive() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double left  = oi.getLeftStick().getY();
        double right = oi.getRightStick().getY();
        
        //drivetrain.setHighGear(oi.getGearShiftButton().get());
        
        // signal conditioning //
        /*PowerScaler scale = oi.getTankDriveScaler();
        if(scale != null) {
            mag = scale.get(mag);
            rot = scale.get(rot);
        }*/
        //if(Math.abs(mag) < DEADBAND) { mag = 0.0; }
        //if(Math.abs(rot) < DEADBAND) { rot = 0.0; }
        
        drivetrain.setTank(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
