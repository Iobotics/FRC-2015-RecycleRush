
package org.iolani.frc;

import com.kauailabs.nav6.frc.IMUAdvanced;
import edu.wpi.first.wpilibj.SerialPort;
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
    
    private final SerialPort _imuSerial= new SerialPort(57600, SerialPort.Port.kMXP);
    private final IMUAdvanced _imu = new IMUAdvanced(_imuSerial, (byte) 50);
    
    private final JoystickButton _pauseLeftIntakeButton = new JoystickButton(_lStick, 5);
    private final JoystickButton _pauseRightIntakeButton = new JoystickButton(_rStick, 4);
    
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
        
        _pauseLeftIntakeButton.whileHeld(new SetIntakePower(false, true));
        _pauseRightIntakeButton.whileHeld(new SetIntakePower(true, false));
        
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
    
    public IMUAdvanced getIMUSensor() {
    	return _imu;
    }
    
}