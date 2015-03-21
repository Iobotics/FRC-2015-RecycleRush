package org.iolani.frc.commands;

/**
 *	Hold at the desired position or current position if no desired position is set.
 */
public class HoldElevatorPosition extends CommandBase {

    public HoldElevatorPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double desired = elevator.getDesiredPosition();
    	elevator.setHeightSetpointInches(desired >= 0? desired: elevator.getHeightInches());
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
    	elevator.setPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
