package org.usfirst.frc.team5958.robot;

import org.usfirst.frc.owatonnarobotics.input.XboxController;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

public class RobotOld extends IterativeRobot {
  RobotDrive myRobot;
  Joystick leftStick;
  Joystick rightStick;
  XboxController xbox;
  int autoLoopCounter;
  DoubleSolenoid festo0;
  XboxController.DirectionalPad liftButton;
  Compressor compressor;

  @Override
  public void robotInit() {
    this.festo0 = new DoubleSolenoid(1, 0);
    int FRONT_LEFT = 0;
    int BACK_LEFT = 1;
    int FRONT_RIGHT = 2;
    int BACK_RIGHT = 3;
    Victor FRONT_LEFT_VIC = new Victor(0);
    Victor BACK_LEFT_VIC = new Victor(1);
    VictorSP FRONT_RIGHT_VIC = new VictorSP(2);
    VictorSP BACK_RIGHT_VIC = new VictorSP(3);
    FRONT_RIGHT_VIC.setInverted(true);
    FRONT_LEFT_VIC.setInverted(true);
    BACK_LEFT_VIC.setInverted(true);
    BACK_RIGHT_VIC.setInverted(true);
    this.myRobot = new RobotDrive(FRONT_LEFT_VIC, BACK_LEFT_VIC,
        FRONT_RIGHT_VIC, BACK_RIGHT_VIC);
    this.leftStick = new Joystick(0);

    this.xbox = new XboxController(1);
    this.liftButton = this.xbox.dPad;
    this.compressor = new Compressor(0);
    this.compressor.setClosedLoopControl(false);
  }

  @Override
  public void disabledPeriodic() {
    while (isDisabled()) {
    }
  }

  @Override
  public void autonomousInit() {
    this.autoLoopCounter = 0;
  }

  @Override
  public void autonomousPeriodic() {
    if (this.autoLoopCounter < 100) {
      this.myRobot.drive(-0.5D, 0.0D);
      this.autoLoopCounter += 1;
    } else {
      this.myRobot.drive(0.0D, 0.0D);
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    if (this.rightStick == null) {
      this.myRobot.arcadeDrive(this.leftStick);
    } else {
      this.myRobot.tankDrive(this.leftStick, this.rightStick);
    }
    if (this.liftButton.getDirection() == XboxController.DPAD.DOWN) {
      this.festo0.set(DoubleSolenoid.Value.kReverse);
    } else if (this.liftButton.getDirection() == XboxController.DPAD.UP_LEFT
        || this.liftButton.getDirection() == XboxController.DPAD.UP_RIGHT) {
      this.festo0.set(DoubleSolenoid.Value.kForward);
    } else {
      this.festo0.set(DoubleSolenoid.Value.kOff);
    }
    if (this.leftStick.getRawButton(7) && this.leftStick.getRawButton(8)) {
      this.compressor.setClosedLoopControl(true);
    } else {
      this.compressor.setClosedLoopControl(false);
    }
  }

  @Override
  public void testPeriodic() {
  }
}
