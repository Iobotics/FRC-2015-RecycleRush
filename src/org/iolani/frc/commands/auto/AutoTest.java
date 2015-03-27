package org.iolani.frc.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.iolani.frc.commands.*;

/**
 *
 */
public class AutoTest extends CommandGroup {
    
    public AutoTest() {
    	addSequential(new SetGrabberGrabbed(false, true));
    	addSequential(new SetIntakePower(1.0, 1.0, true));
    	addSequential(new AutoDriveStraight(24)); //Assume we start 2 feet away
    	addSequential(new SetGrabberGrabbed(true, true));
    	addSequential(new SetIntakePower(0.0, 0.0, true));
    	addSequential(new AutoTurn(45));
    	addSequential(new AutoDriveStraight(-60)); //Assume we need to back up 5 feet
    }
}
