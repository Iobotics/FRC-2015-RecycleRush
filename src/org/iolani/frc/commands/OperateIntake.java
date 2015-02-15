package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperateIntake extends CommandBase {

    public OperateIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	intake.setPower(oi.getIntakeLeftButton().get()? 1.0: 0.0, 
    					oi.getIntakeRightButton().get()? 1.0: 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	intake.setPower(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
