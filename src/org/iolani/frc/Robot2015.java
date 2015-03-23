
package org.iolani.frc;

import org.iolani.frc.commands.CommandBase;
import org.iolani.frc.commands.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.iolani.frc.commands.*;
//import org.iolani.frc.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot2015 extends IterativeRobot {

    //Command autonomousCommand;
	PowerDistributionPanel _pdp;
	Compressor             _compressor;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	_pdp = new PowerDistributionPanel();
    	_compressor = new Compressor();
    	
    	_pdp.clearStickyFaults();
    	_compressor.clearAllPCMStickyFaults();
    	_compressor.start();
    	
        // instantiate the command used for the autonomous period
        //autonomousCommand = new ExampleCommand();
		CommandBase.init();
		new HomeElevator().start();
		new CalibrateNavigationSensor().start();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	System.out.println("hello");
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        SmartDashboard.putData(Scheduler.getInstance());
        CommandBase.elevator.debug();
        CommandBase.grabber.debug();
        CommandBase.drivetrain.debug();
        CommandBase.navsensor.debug();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
