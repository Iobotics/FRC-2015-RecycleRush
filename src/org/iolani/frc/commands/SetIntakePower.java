package org.iolani.frc.commands;

/**
 *
 */
public class SetIntakePower extends CommandBase {
	private final double _leftPower, _rightPower;
	private final boolean _terminate;
    
    public SetIntakePower(double power) {
    	this(power, power);
    }
    
    public SetIntakePower(double leftPower, double rightPower) {
    	this(leftPower, rightPower, false);
    }
    
    public SetIntakePower(double leftPower, double rightPower, boolean terminate) {
    	requires(intake);
    	_leftPower  = leftPower;
    	_rightPower = rightPower;
    	_terminate  = terminate;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intake.setPower(_leftPower, _rightPower);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//intake.setPower(_leftPower, _rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _terminate;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	intake.setPower(0.0, 0.0);
    }
}
