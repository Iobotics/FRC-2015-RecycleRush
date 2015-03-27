package org.iolani.frc.commands.auto;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

import com.kauailabs.nav6.frc.IMU;

/**
 *
 */
public class AutoTurn extends Command implements PIDOutput {

	private final double        _degrees;
    private final PIDController _pid;
	
	private static final double kP = 0.01;
	private static final double kI = 0.0;
	private static final double kD = 0.0;
	
    public AutoTurn(double degrees) { //Counterclockwise is positive
    	requires(drivetrain);
    	requires(navsensor);
    	_degrees = degrees;
    	
    	_pid = new PIDController(kP, kI, kD, navsensor, this);
    	_pid.setOutputRange(-0.35, 0.35);
    }

    public void pidWrite(double power) {
    	drivetrain.setTank(-power, power);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	navsensor.zeroGyro();
    	_pid.setSetpoint(_degrees);
    	_pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	_pid.disable();
    	drivetrain.setPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
