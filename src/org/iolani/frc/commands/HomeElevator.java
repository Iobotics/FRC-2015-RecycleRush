package org.iolani.frc.commands;

/**
 *
 */
public class HomeElevator extends CommandBase {

	private static final double TIMEOUT = 10;
	
    public HomeElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	this.setInterruptible(false);
    	this.setRunWhenDisabled(true);
    	this.setTimeout(TIMEOUT);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.setPower(-0.25);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevator.isLowerLimit() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevator.setPower(0.0);
    	elevator.setDesiredPosition(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
