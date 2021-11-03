// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.StartDriving;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Chassis chassis;

  // public static XboxController xbox;
  public static Joystick leftStick;
  public static Joystick rightStick;

  //instantiating buttons
  //assign controller, then button on the controller (ex. 0 for now)
  public static JoystickButton exampleButton;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    chassis = new Chassis();

    configureButtonBindings();

    chassis.setDefaultCommand(new StartDriving());

    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // xbox = new XboxController(0);

    leftStick = new Joystick(0);
    rightStick = new Joystick(1);

    exampleButton = new JoystickButton(leftStick, 0);

    //when pressed queue command into scheduler (?)
    //can be done in configureButtonBindings, can also be done in a diff method, which is then called in constructor
    //in old 6070 code  
    exampleButton.whenPressed(new StartDriving());

    

  }

  

  public static double getYLeft(){
    double kleft = leftStick.getY();
    if(Math.abs(kleft) <= 0.1){
      return 0;
    } else {
      return kleft*Math.abs(kleft); //Math.abs to preserve sign
    }
  }

  public static double getYRight(){
    double kright = rightStick.getY();
    if(Math.abs(kright) <= 0.1){
      return 0;
    } else {
      return kright*Math.abs(kright); //Math.abs to preserve sign
    }
  }

  // public static double getYLeft(){
  //   double kleft = xbox.getY(Hand.kLeft);
  //   if(Math.abs(kleft) <= 0.1){
  //     return 0;
  //   } else {
  //     return kleft*Math.abs(kleft); //Math.abs to preserve sign
  //   }
  // }

  // public static double getYRight(){
  //   double kright = xbox.getY(Hand.kRight);
  //   if(Math.abs(kright) <= 0.1){
  //     return 0;
  //   } else {
  //     return kright*Math.abs(kright); //Math.abs to preserve sign
  //   }
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new StartDriving();
  }
}
