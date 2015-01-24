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
public class OperateArcadeTankDrive extends CommandBase {
    
    public OperateArcadeTankDrive() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double mag  = oi.getRightStick().getY();
        double rot = oi.getLeftStick().getX();
        
        // signal conditioning //
        PowerScaler scale = oi.getDriveScaler();
        if(scale != null) {
            mag = scale.get(mag);
            rot = scale.get(rot);
        }
        System.out.println("joysticks: " + mag + ", " + rot);
        drivetrain.setTank(mag, rot);
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
