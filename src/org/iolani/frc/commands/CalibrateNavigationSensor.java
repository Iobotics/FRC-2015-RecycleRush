package org.iolani.frc.commands;

import org.iolani.frc.commands.CommandBase;
/**
 *
 */
public class CalibrateNavigationSensor extends CommandBase {

    public CalibrateNavigationSensor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(navsensor);
    	this.setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !navsensor.isCalibrating();
    }

    // Called once after isFinished returns true
    protected void end() {
    	navsensor.zeroGyro();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
