package org.iolani.frc.commands;

import org.iolani.frc.commands.CommandBase;

/**
 *
 */
public class SetElevatorHeight extends CommandBase {
	private final double _heightInches;
	
    public SetElevatorHeight(double inches) {
    	_heightInches = inches;
        // Use requires() here to declare subsystem dependencies
        this.requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.setHeightSetpointInches(_heightInches);
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
    	elevator.setPIDEnabled(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
