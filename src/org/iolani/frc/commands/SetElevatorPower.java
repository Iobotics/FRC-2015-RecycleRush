package org.iolani.frc.commands;

/**
 *
 */
public class SetElevatorPower extends CommandBase {
	private double _power;

    public SetElevatorPower(double power) {
        // Use requires() here to declare subsystem dependencies
        requires(elevator);
        _power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	elevator.setPower(_power);
    	elevator.setDesiredPosition(-1.0);
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
    	elevator.setPower(0.0);
    }
}
