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

    public void setArcade(double move, double rotate) {
        _drive.arcadeDrive(move, rotate);
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
}
