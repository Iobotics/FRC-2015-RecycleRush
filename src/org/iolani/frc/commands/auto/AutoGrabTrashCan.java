package org.iolani.frc.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import org.iolani.frc.commands.*;

/**
 *
 */
public class AutoGrabTrashCan extends CommandGroup {
    
	public static final int kLEFT  = 1;
	public static final int kRIGHT = 2;
	
    public AutoGrabTrashCan() { //Assumes "left" orientation (as we'd been doing it)
    	this(kLEFT);
    }
    
    public AutoGrabTrashCan(int side) {
    	addSequential(new SetGrabberGrabbed(false, true));
    	addSequential(new SetIntakePower(1.0, 1.0, true));
    	addSequential(new AutoDriveStraight(24)); //Assume we start 2 feet away
    	addSequential(new SetGrabberGrabbed(true, true));
    	addSequential(new SetIntakePower(0.0, 0.0, true));
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SetElevatorHeight(14.0));
    	addSequential(new AutoTurn(side == kLEFT? 45 : -45));
    	addSequential(new AutoDriveStraight(-120)); //Assume we need to back up 5 feet
    }
}
