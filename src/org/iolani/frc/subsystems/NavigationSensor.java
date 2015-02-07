package org.iolani.frc.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NavigationSensor extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private final SerialPort _imuSerial= new SerialPort(57600, SerialPort.Port.kMXP);
	private final IMUAdvanced _imu = new IMUAdvanced(_imuSerial, (byte) 50);
	
	public void init() {
		
	}
	
	public void zeroGyro() {
		_imu.zeroYaw();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

