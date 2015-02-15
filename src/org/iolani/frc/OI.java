
package org.iolani.frc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.iolani.frc.util.PowerScaler;
import org.iolani.frc.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    
    private final JoystickButton _runLeftIntakeButton = new JoystickButton(_lStick, 5);
    private final JoystickButton _runRightIntakeButton = new JoystickButton(_rStick, 4);
    
    private final JoystickButton _elevatorUpButton   = new JoystickButton(_rStick, 3);
    private final JoystickButton _elevatorDownButton = new JoystickButton(_lStick, 3);
    
    private final JoystickButton _elevatorTestButton1 = new JoystickButton(_rStick, 6);
    private final JoystickButton _elevatorTestButton2 = new JoystickButton(_rStick, 7);
    
    private final PowerScaler _tankScaler;
    private final PowerScaler _arcadeScaler;
    
    public OI() {
        
        // use power scaling from traction mode on 2014 bot //
        _tankScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.0, 0.0),
                new PowerScaler.PowerPoint(0.05, 0.0),
                new PowerScaler.PowerPoint(0.75, 0.5),
                new PowerScaler.PowerPoint(0.90, 1.0)
            });
        
        _arcadeScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
        		new PowerScaler.PowerPoint(0.0, 0.0),
        		new PowerScaler.PowerPoint(0.05, 0.0),
        		new PowerScaler.PowerPoint(0.5, 0.3),
        		new PowerScaler.PowerPoint(0.75, 0.5),
        		new PowerScaler.PowerPoint(0.9, 1.0)
        	});
        
        _runLeftIntakeButton.whileHeld(new SetIntakePower(true, false));
        _runRightIntakeButton.whileHeld(new SetIntakePower(false, true));
        
        _elevatorUpButton.whileHeld(new SetElevatorPower(1.0));
        _elevatorDownButton.whileHeld(new SetElevatorPower(-0.5));
        
        _elevatorTestButton1.whenPressed(new SetElevatorHeight(10.0));
        _elevatorTestButton2.whenPressed(new SetElevatorHeight(0.0));
    }
    
    public Joystick getLeftStick()  {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    public PowerScaler getTankScaler() {
        return _tankScaler;
    }
    
    public PowerScaler getArcadeScaler() {
    	return _arcadeScaler;
    }
    
    public double getVariableLeftIntakePower() {
    	return _lStick.getTwist();
    }
    
    public double getVariableRightIntakePower() {
    	return _rStick.getTwist();
    }
    
}