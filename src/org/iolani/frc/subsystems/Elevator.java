package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.HoldElevatorPosition;
import org.iolani.frc.util.Utility;

/**
 *
 */
public class Elevator extends Subsystem implements PIDOutput {
	// physical constants //
	private static final double MM_PER_INCH = 25.4;
	// pulley system constants //
	private static final int    DRIVER_PULLEY_TEETH = 15;
	private static final int    DRIVEN_PULLEY_TEETH = 40;
	private static final int    BELT_PITCH_MM       = 5;
	private static final double DRIVEN_INCH_PER_REV = DRIVEN_PULLEY_TEETH * BELT_PITCH_MM / MM_PER_INCH;
	private static final double DRIVER_INCH_PER_REV = DRIVEN_INCH_PER_REV * DRIVER_PULLEY_TEETH / DRIVEN_PULLEY_TEETH;
	// encoder constants //
	private static final int    ENCODER_TICKS_PER_REV = 360;
	private static final double ENCODER_INCH_PER_TICK = DRIVER_INCH_PER_REV / ENCODER_TICKS_PER_REV;
	
	// physical constants //
	public static final double HEIGHT_INCHES_MAX       = 65.0;
	public static final double HEIGHT_INCHES_MIN       = 0.0;
	public static final double HEIGHT_INCHES_TOLERANCE = 0.50;
	public static final double POWER_UP_MAX            = 1.0;
	public static final double POWER_DOWN_MAX          = 0.5;
	public static final double CLEARANCE_HEIGHT_INCHES = 14.5;
	public static final double TOTE_HEIGHT_INCHES      = 11.25;
	
	public static final double[] TOTE_HEIGHTS = new double[5];
	static {
		for(int i = 0; i < TOTE_HEIGHTS.length; i++) {
			TOTE_HEIGHTS[i] = CLEARANCE_HEIGHT_INCHES + i * TOTE_HEIGHT_INCHES;
		}
	}
	
	// PID control constants //
	private static final double kP = 0.25;
	private static final double kI = 0.0;
	private static final double kD = 0.3;
	    
	// hardware //
	private CANTalon      _lTalon;
	private CANTalon      _rTalon;
	private Encoder       _encoder;
	private DigitalInput  _limitLower;
    private PIDController _pid;
    
    private double _desiredPosition;
	
	public void init() {
		_lTalon     = new CANTalon(RobotMap.elevatorLeftTalon);
    	_rTalon     = new CANTalon(RobotMap.elevatorRightTalon);
    	_encoder    = new Encoder(RobotMap.elevatorEncoderA, RobotMap.elevatorEncoderB);
    	_limitLower = new DigitalInput(RobotMap.elevatorLimitLower);
    	
    	_lTalon.enableBrakeMode(true);
    	_rTalon.enableBrakeMode(true);
    	_encoder.setIndexSource(_limitLower, Encoder.IndexingType.kResetWhileLow);
    	_encoder.setDistancePerPulse(ENCODER_INCH_PER_TICK);
    	
    	_pid = new PIDController(kP, kI, kD, _encoder, this);
    	_pid.setInputRange(HEIGHT_INCHES_MIN, HEIGHT_INCHES_MAX);
        _pid.setOutputRange(-POWER_DOWN_MAX, POWER_UP_MAX);
        _pid.setAbsoluteTolerance(HEIGHT_INCHES_TOLERANCE);
        
        _desiredPosition = 0.0;
	}
	
	/**
	 * Positive is up.
	 * @param power
	 */
	public void setPower(double power) {
		power = Utility.window(power, -POWER_DOWN_MAX, POWER_UP_MAX);
		this.setPower(power, true);
	}
	
	private void setPower(double power, boolean stopPID) {
		if(stopPID) _pid.disable();
		if(this.isLowerLimit() && power < 0) {
			power = 0;
		}
		_lTalon.set(-power);
		_rTalon.set(power);
	}
	
	public boolean setPIDEnabled(boolean enabled) {
		boolean old = _pid.isEnable();
		if(enabled) {
			_pid.enable();
		} else {
			_pid.disable();
		}
		return old;
	}
	
	public double setHeightSetpointInches(double inches) {
		double old = _pid.getSetpoint();
		this.setPIDEnabled(true);
		_pid.setSetpoint(inches);
		return old;
	}
	
	public double getDesiredPosition() {
		return _desiredPosition;
	}
	
	// if the value is negative, there is no desired position //
	public double setDesiredPosition(double value) {
		double old = _desiredPosition;
		_desiredPosition = value;
		return old;
	}
	
	/**
	 * must be an integer from 0 to TOTE_HEIGHTS.length
	 * @param totePosition
	 */
	public void setToteHeight(int totePosition) {
		if(totePosition < 0 || totePosition >= TOTE_HEIGHTS.length) {
			throw new IllegalArgumentException("Invalid tote position: " + totePosition);
		}
		setHeightSetpointInches(TOTE_HEIGHTS[totePosition]);
	}
	
	/**
	 * 
	 * @param above
	 * @return nearest tote height
	 */
	public double getNearestToteHeight(boolean above) {
		double height = _pid.getSetpoint();
		if(above) {
			for(int i = 0; i < TOTE_HEIGHTS.length; i++) {
				if(height < TOTE_HEIGHTS[i]) {
					return TOTE_HEIGHTS[i];
				}
			}
			return TOTE_HEIGHTS[TOTE_HEIGHTS.length - 1];
		} else {
			for(int i = TOTE_HEIGHTS.length - 1; i >= 0; i--) {
				if(height > TOTE_HEIGHTS[i]) {
					return TOTE_HEIGHTS[i];
				}
			}
			return 0.0;
		}
	}
	
	public boolean isLowerLimit() {
		return !_limitLower.get();
	}
	
	public boolean isOnTarget() {
		return _pid.onTarget();
	}
	
	public int getEncoderTicks() {
		return _encoder.get();
	}
	
	public double getHeightInches() {
		return _encoder.getDistance();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new HoldElevatorPosition());
    }
    
    public void debug() {
    	SmartDashboard.putData("elevator-encoder", _encoder);
    	SmartDashboard.putData("elevator-lower-limit", _limitLower);
    	SmartDashboard.putNumber("height-inches", this.getHeightInches());
    	SmartDashboard.putData("elevator-pid", _pid);
    	SmartDashboard.putBoolean("elevator-on-target", this.isOnTarget());
    	SmartDashboard.putNumber("elevator-power", _rTalon.get());
    	SmartDashboard.putNumber("elevator-next-above-tote", this.getNearestToteHeight(true));
    	SmartDashboard.putNumber("elevator-next-below-tote", this.getNearestToteHeight(false));
    	//System.out.println(this.getEncoderTicks() + ", " + this.getHeightInches() + ", " + this.getLowerLimitSwitch());
    }
    
    // implement PIDOutput //
    public void pidWrite(double value) {
    	this.setPower(value, false);
    }
}

