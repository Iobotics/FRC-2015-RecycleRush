package org.iolani.frc.commands;

/**
 *
 */
public class JogElevatorToteHeight extends CommandBase {
	
	private final int _jogValue;

    public JogElevatorToteHeight(int jogValue) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    	_jogValue = jogValue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	boolean up = _jogValue > 0 ? true : false;
		elevator.setHeightSetpointInches(elevator.getNearestToteHeight(up));
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
    }
}