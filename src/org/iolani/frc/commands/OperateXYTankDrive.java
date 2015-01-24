/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.util.PowerScaler;

/**
 *
 * @author iobotics
 */
public class OperateXYTankDrive extends CommandBase {
    
    //private static final double DEADBAND = 0.05;  
    
    public OperateXYTankDrive() {
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
        PowerScaler scale = oi.getDriveScaler();
        if(scale != null) {
            left = scale.get(left);
            right = scale.get(right);
        }

        System.out.println("joysticks: " + left + ", " + right);
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
