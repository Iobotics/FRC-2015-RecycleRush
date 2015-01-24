/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.OperateXYTankDrive;
import org.iolani.frc.util.Utility;

/**
 *
 * @author koluke
 */
public class DriveTrain extends Subsystem {
    private CANTalon    _lTalon1;
    private CANTalon    _lTalon2;
    private CANTalon    _lTalon3;
    private CANTalon    _rTalon1;
    private CANTalon    _rTalon2;
    private CANTalon    _rTalon3;
    
    public void init()  {
    	System.out.println("drive init start");
        _lTalon1 = new CANTalon(RobotMap.driveLeftTalon1);
        _lTalon2 = new CANTalon(RobotMap.driveLeftTalon2);
        _lTalon3 = new CANTalon(RobotMap.driveLeftTalon3);
        _rTalon1 = new CANTalon(RobotMap.driveRightTalon1);
        _rTalon2 = new CANTalon(RobotMap.driveRightTalon2);
        _rTalon3 = new CANTalon(RobotMap.driveRightTalon3);
        System.out.println("drive init end");
    }


    public void setTank(double left, double right) {
    	_lTalon1.set(-left);
    	_lTalon2.set(-left);
    	_lTalon3.set(-left);
    	_rTalon1.set(right);
    	_rTalon2.set(right);
    	_rTalon3.set(right);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new OperateXYTankDrive());
    }
    
    /**
     * Arcade drive implements single stick driving.
     * This function lets you directly provide joystick values from any source.
     * @param moveValue The value to use for forwards/backwards
     * @param rotateValue The value to use for the rotate right/left
     * @param squaredInputs If set, decreases the sensitivity at low speeds
     */
    public void setArcade(double moveValue, double rotateValue, boolean squaredInputs) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        moveValue   = Utility.limit(moveValue);
        rotateValue = Utility.limit(rotateValue);

        if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control while permitting full power
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        this.setTank(leftMotorSpeed, rightMotorSpeed);
    }

    /**
     * Arcade drive implements single stick driving.
     * This function lets you directly provide joystick values from any source.
     * @param moveValue The value to use for fowards/backwards
     * @param rotateValue The value to use for the rotate right/left
     */
    public void setArcade(double moveValue, double rotateValue) {
        this.setArcade(moveValue, rotateValue, false);
    }
    
}
