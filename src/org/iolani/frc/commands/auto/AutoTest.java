package org.iolani.frc.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.iolani.frc.commands.*;

/**
 *
 */
public class AutoTest extends CommandGroup {
    
    public  AutoTest() {
    	
    	addSequential(new SetGrabberGrabbed(false));
    	addSequential(new SetIntakePower(true, true));
    	addSequential(new AutoDriveForward(24)); //Assume we start 2 feet away
    	addSequential(new SetGrabberGrabbed(true));
    	addSequential(new SetIntakePower(false, false));
    	addSequential(new AutoTurn(45));
    	addSequential(new AutoDriveForward(-60)); //Assume we need to back up 5 feet
    }
}
