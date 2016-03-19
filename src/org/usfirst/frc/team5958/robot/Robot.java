
package org.usfirst.frc.team5958.robot;

import org.usfirst.frc.owatonnarobotics.input.XboxController;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

  private Victor frontLeft;
  private Victor backLeft;
  private VictorSP frontRight;
  private VictorSP backRight;
  private Spark ballPickup;
  private Victor arm;

  private Joystick leftStick;
  private Joystick rightStick;
  private XboxController xbox;

  private RobotDrive drive;

  private int angle;

  private final int FRONTLEFT = 0;
  private final int BACKLEFT = 1;
  private final int FRONTRIGHT = 2;
  private final int BACKRIGHT = 3;
  private final int SPARK = 4;
  private final int ARM = 5;

  private final int LEFTSTICK = 0;
  private final int RIGHTSTICK = 1;
  private final int XBOX = 2;
  private int autoLoopCounter;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    this.backLeft = new Victor(this.BACKLEFT);
    this.backLeft.setInverted(true);
    this.backRight = new VictorSP(this.BACKRIGHT);
    this.backRight.setInverted(true);
    this.frontLeft = new Victor(this.FRONTLEFT);
    this.frontLeft.setInverted(true);
    this.frontRight = new VictorSP(this.FRONTRIGHT);
    this.frontRight.setInverted(true);
    this.ballPickup = new Spark(this.SPARK);
    this.arm = new Victor(this.ARM);
    this.arm.setInverted(true);

    this.leftStick = new Joystick(this.LEFTSTICK);
    this.rightStick = new Joystick(this.RIGHTSTICK);
    this.xbox = new XboxController(this.XBOX);

    this.drive = new RobotDrive(this.frontLeft, this.backLeft, this.frontRight,
        this.backRight);
  }

  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {

  }

  @Override
  public void autonomousInit() {
    this.autoLoopCounter = 0;
    this.angle = 0;
  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    if (this.autoLoopCounter < 1000) {
      this.drive.drive(-0.7D, 0.0D);
      this.autoLoopCounter += 1;
    } else {
      this.drive.drive(0.0D, 0.0D);
    }
  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    this.drive.tankDrive(this.leftStick, this.rightStick);
    if (this.xbox.y.get() && this.angle >= 0) {
      this.arm.set(1);
      this.angle -= 2;
    } else if (this.xbox.a.get() && this.angle < 80) {
      this.arm.set(-0.5);
      this.angle++;
    } else if (this.xbox.x.get() && this.xbox.b.get()) {
      this.arm.set(1);
      this.angle = 0;
    } else {
      this.arm.set(0);
    }

    if (this.xbox.lb.get() || this.xbox.rb.get()) {
      this.ballPickup.set(0.5);
    } else if (this.xbox.lt.get() || this.xbox.rt.get()) {
      this.ballPickup.set(-0.5);
    } else {
      this.ballPickup.set(0);
    }
  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {

  }

}
