/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick driverStick = new Joystick(RobotMap.driverPort);
  public static XboxController operatorStick = new XboxController(RobotMap.operatorPort);
  
  public static Button toggleChassisFront = new JoystickButton(driverStick, RobotMap.XboxRightShoulder);
  public static Button toggleChassisRear = new JoystickButton(driverStick, RobotMap.XboxLeftShoulder);

  Button DropEndEffector = new JoystickButton(operatorStick, 2);
	Button RaiseEndEffector = new JoystickButton(operatorStick, 3);
	Button ExtendHatchGrabber = new JoystickButton(operatorStick, 1);
  Button RetractHatchGrabber = new JoystickButton(operatorStick, 4);
  Button LeanLiftForward = new JoystickButton(operatorStick, 0);//TODO: Map Button
  Button LeanLiftBack = new JoystickButton(operatorStick, 0);//TODO: Map Button

  public static Button target = new JoystickButton(driverStick, RobotMap.XboxYButton);

  public OI(){

    //Climber
    toggleChassisFront.whenPressed(new raiseFront(Value.kForward));
    toggleChassisFront.whenReleased(new raiseFront(Value.kReverse));
    toggleChassisRear.whenPressed(new raiseRear(Value.kForward));
    toggleChassisRear.whenReleased(new raiseRear(Value.kReverse));

    //End Effector
    DropEndEffector.whileHeld(new DropEndEffector());
		RaiseEndEffector.whenPressed(new RaiseEndEffector());
		ExtendHatchGrabber.whenPressed(new ExtendHatchGrabber());
    RetractHatchGrabber.whenPressed(new RetractHatchGrabber());
    
    //Lift
    LeanLiftForward.whenPressed(new leanLiftForward());
    LeanLiftBack.whenPressed(new leanLiftBack());
  }//end constructor

  public static double deadband(double tolerance, double value){
    return (Math.abs(value) <= tolerance) ? 0 : value;
  }//end deadband
}//end OI
