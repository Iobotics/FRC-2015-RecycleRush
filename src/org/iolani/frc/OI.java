
package org.iolani.frc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.iolani.frc.util.PowerScaler;
import org.iolani.frc.commands.*;
import org.iolani.frc.commands.auto.AutoDriveStraight;
import org.iolani.frc.commands.auto.AutoGrabTrashCan;
import org.iolani.frc.commands.auto.AutoTurn;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    
    private final JoystickButton _intakeLeftButton       = new JoystickButton(_lStick, 1);
    private final JoystickButton _intakeRightButton      = new JoystickButton(_rStick, 1);
    
    private final JoystickButton _elevatorUpButton       = new JoystickButton(_rStick, 2);
    private final JoystickButton _elevatorDownButton     = new JoystickButton(_lStick, 2);
    
    private final JoystickButton _elevatorUpOneButton    = new JoystickButton(_rStick, 3);
    private final JoystickButton _elevatorDownOneButton  = new JoystickButton(_lStick, 4);
    
    private final JoystickButton _elevatorTestButton1    = new JoystickButton(_rStick, 6);
    private final JoystickButton _elevatorTestButton2    = new JoystickButton(_lStick, 3);
    
    private final JoystickButton _elevatorHomeButton     = new JoystickButton(_lStick, 6);
    
    private final JoystickButton _grabberCloseButton 	 = new JoystickButton(_lStick, 5);
    private final JoystickButton _grabberOpenButton		 = new JoystickButton(_rStick, 4);
    
    private final JoystickButton _navCalibrateButton     = new JoystickButton(_lStick, 7);
    
    private final JoystickButton _autoTestButton1        = new JoystickButton(_lStick, 8);
    private final JoystickButton _autoTestButton2        = new JoystickButton(_lStick, 9);
    private final JoystickButton _autoTestButton3        = new JoystickButton(_lStick, 10);
    private final JoystickButton _autoTestButton4        = new JoystickButton(_lStick, 11);
    
    private final PowerScaler _normalScaler;
    private final PowerScaler _wimpScaler;
    
    public OI() {
        
        // use power scaling from traction mode on 2014 bot //
        _normalScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.0, 0.0),
                new PowerScaler.PowerPoint(0.05, 0.0),
                new PowerScaler.PowerPoint(0.75, 0.5),
                new PowerScaler.PowerPoint(0.90, 1.0)
            });
        
        _wimpScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
        		new PowerScaler.PowerPoint(0.0, 0.0),
        		new PowerScaler.PowerPoint(0.05, 0.0),
        		new PowerScaler.PowerPoint(0.75, 0.2),
        		new PowerScaler.PowerPoint(0.95, 0.3)
        	});
        
        _elevatorUpOneButton.whenPressed(new JogElevatorToteHeight(true));
        _elevatorDownOneButton.whenPressed(new JogElevatorToteHeight(false));
        
        _elevatorUpButton.whileHeld(new SetElevatorPower(1.0));
        _elevatorDownButton.whileHeld(new SetElevatorPower(-0.5));
        
        _elevatorTestButton1.whenPressed(new SetElevatorHeight(10.0));
        _elevatorTestButton2.whenPressed(new SetElevatorHeight(0.0));
        
        _elevatorHomeButton.whenPressed(new HomeElevator());
        
        _grabberOpenButton.whenPressed(new SetGrabberGrabbed(false));
        _grabberCloseButton.whenPressed(new SetGrabberGrabbed(true));
        
        _navCalibrateButton.whenPressed(new CalibrateNavigationSensor());
        
        _autoTestButton1.whenPressed(new AutoDriveStraight(36));
        _autoTestButton2.whenPressed(new AutoDriveStraight(120));
        _autoTestButton3.whenPressed(new AutoTurn(90));
        _autoTestButton4.whenPressed(new AutoGrabTrashCan());
    }
    
    public Joystick getLeftStick()  {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    public PowerScaler getNormalScaler() {
        return _normalScaler;
    }
    
    public PowerScaler getWimpScaler() {
    	return _wimpScaler;
    }
    
    public Button getIntakeLeftButton() {
    	return _intakeLeftButton;
    }
    
    public double getLeftTwist() {
    	return _lStick.getTwist();
    }
    
    public Button getIntakeRightButton() {
    	return _intakeRightButton;
    }
    
    public double getRightTwist() {
    	return _rStick.getTwist();
    }
    
}