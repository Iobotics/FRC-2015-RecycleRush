package org.iolani.frc.commands;


/**
 *
 */
public class SetGrabberGrabbed extends CommandBase {
	
	private final boolean _value;

    public SetGrabberGrabbed(boolean value) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	_value = value;
    	requires(grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	grabber.setGrabbed(_value);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
