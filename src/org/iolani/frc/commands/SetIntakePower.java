package org.iolani.frc.commands;

/**
 *
 */
public class SetIntakePower extends CommandBase {
	private double _leftPower, _rightPower;

    public SetIntakePower() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(false, false);
    }
    
    public SetIntakePower(boolean leftOn, boolean rightOn) {
    	requires(intake);
    	_leftPower = leftOn ? 1.0 : 0.0;
    	_rightPower = rightOn ? -1.0 : 0.0;
    }
    
    public SetIntakePower(double power) {
    	this(power, power);
    }
    
    public SetIntakePower(double leftPower, double rightPower) {
    	requires(intake);
    	_leftPower = leftPower;
    	_rightPower = -rightPower;
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
        return false;
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
