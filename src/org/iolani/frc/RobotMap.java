package org.iolani.frc;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    // drive //
    public static final int driveLeftTalon1    = 4;
    public static final int driveLeftTalon2    = 5;
    public static final int driveLeftTalon3    = 6;
    public static final int driveRightTalon1   = 1;
    public static final int driveRightTalon2   = 2;
    public static final int driveRightTalon3   = 3;
    
    // elevator //
    public static final int elevatorLeftTalon  = 7;
    public static final int elevatorRightTalon = 8;
    public static final int elevatorEncoderA   = 0;
    public static final int elevatorEncoderB   = 1;
    public static final int elevatorLimitLower = 2;
    
    // intake //
    public static final int intakeLeftTalon    = 10;
    public static final int intakeRightTalon   = 9;
    
    // grabber //
    public static final int grabberSolenoid = 0;
}
