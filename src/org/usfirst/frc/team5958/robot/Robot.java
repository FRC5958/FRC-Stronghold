
package org.usfirst.frc.team5958.robot;

import org.usfirst.frc.owatonnarobotics.input.XboxController;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
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

  private Joystick leftStick;
  private Joystick rightStick;
  private XboxController xbox;

  private RobotDrive drive;

  private final int FRONTLEFT = 0;
  private final int BACKLEFT = 1;
  private final int FRONTRIGHT = 2;
  private final int BACKRIGHT = 3;

  private final int LEFTSTICK = 0;
  private final int RIGHTSTICK = 1;
  private final int XBOX = 2;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    this.backLeft = new Victor(this.BACKLEFT);
    this.backRight = new VictorSP(this.BACKRIGHT);
    this.frontLeft = new Victor(this.FRONTLEFT);
    this.frontRight = new VictorSP(this.FRONTRIGHT);

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

  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {

  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    this.drive.tankDrive(this.leftStick, this.rightStick);
  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {

  }

}
