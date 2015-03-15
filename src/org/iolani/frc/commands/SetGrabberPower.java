package org.iolani.frc.commands;


/**
 *
 */
public class SetGrabberPower extends CommandBase {
	
	private final double _power;

    public SetGrabberPower(double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	_power = power;
    	requires(grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	grabber.setPower(_power);
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
    	grabber.setPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}