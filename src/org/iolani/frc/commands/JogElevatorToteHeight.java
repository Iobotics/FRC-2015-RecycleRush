package org.iolani.frc.commands;

/**
 *
 */
public class JogElevatorToteHeight extends CommandBase {
	
	private final boolean _up;
	private double _desired;

    public JogElevatorToteHeight(boolean up) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	_up = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_desired = elevator.getNearestToteHeight(_up); /*+ (_up? 0.75 : -0.5)*/
		elevator.setHeightSetpointInches(_desired);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevator.isOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	elevator.setPower(0.0);
    	elevator.setDesiredPosition(_desired);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
