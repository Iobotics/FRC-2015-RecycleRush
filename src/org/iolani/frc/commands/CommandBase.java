package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.iolani.frc.OI;
import org.iolani.frc.subsystems.DriveTrain;
import org.iolani.frc.subsystems.Grabber;
import org.iolani.frc.subsystems.Intake;
import org.iolani.frc.subsystems.Elevator;
import org.iolani.frc.subsystems.NavigationSensor;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static final DriveTrain drivetrain = new DriveTrain();
    public static final Intake intake = new Intake();
    public static final Elevator elevator = new Elevator();
    public static final NavigationSensor navsensor = new NavigationSensor();
    public static final Grabber grabber = new Grabber();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        navsensor.init();
        drivetrain.init();
        intake.init();
        elevator.init();
        grabber.init();
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
