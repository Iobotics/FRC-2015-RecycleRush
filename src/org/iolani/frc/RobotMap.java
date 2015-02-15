package org.iolani.frc;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    // drive //
    public static final int driveLeftTalon1    = 1;
    public static final int driveLeftTalon2    = 2;
    public static final int driveLeftTalon3    = 3;
    public static final int driveRightTalon1   = 4;
    public static final int driveRightTalon2   = 5;
    public static final int driveRightTalon3   = 6;
    
    // elevator //
    public static final int elevatorLeftTalon  = 7;
    public static final int elevatorRightTalon = 8;
    public static final int elevatorEncoderA   = 0;
    public static final int elevatorEncoderB   = 1;
    public static final int elevatorLimitLower = 2;
    
    // intake //
    public static final int intakeLeftTalon  = 1;
    public static final int intakeRightTalon = 2;
    
    // pneumatics //
    public static final int pressureRegulatorDIO = 14;
    public static final int compressorRelay = 8;
    public static final int gearShiftValve = 1;
}
