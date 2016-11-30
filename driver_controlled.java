package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the HardwarePushbot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gamepad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Pushbot: Teleop Tank", group="Pushbot")
//@Disabled
public class PushbotTeleopTank_Iterative extends OpMode{

    /* Declare OpMode members. */
    org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot robot       = new HardwarePushbot(); // use the class created to define a Pushbot's hardware
                                                         // could also use HardwarePushbotMatrix class.
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
     
        //Drive forward

        if (gamepad1.dpad_up) {
            robot.backRight.setPower(1.0);
            robot.frontRight.setPower(1.0);

            robot.frontLeft.setPower(-1 * 1.0);
            robot.backLeft.setPower(-1 * 1.0);
        }
        else if (gamepad1.b) {
            robot.backRight.setPower(0.5*1.0);
            robot.frontRight.setPower(0.5*1.0);

            robot.frontLeft.setPower(-1 * 0.5);
            robot.backLeft.setPower(-1 * 0.5);
        }
        else {
            robot.backLeft.setPower(0.0);
            robot.backRight.setPower(0.0);

            robot.frontLeft.setPower(0.0);
            robot.frontRight.setPower(0.0);
        }


        //Drive backward

        if (gamepad1.dpad_down) {
            robot.backRight.setPower(-1 * 1.0);
            robot.frontRight.setPower(-1 * 1.0);

            robot.frontLeft.setPower(1.0);
            robot.backLeft.setPower(1.0);

        }
        else if (gamepad1.x) {
            robot.backRight.setPower(-1 * 0.5);
            robot.frontRight.setPower(-1 * 0.5);

            robot.frontLeft.setPower(0.5*1.0);
            robot.backLeft.setPower(0.5*1.0);

        }
        else {
            robot.backLeft.setPower(0.0);
            robot.backRight.setPower(0.0);
            robot.frontLeft.setPower(0.0);
            robot.frontRight.setPower(0.0);
        }

        //Turn Right

        if (gamepad1.dpad_right) {
            robot.backLeft.setPower(1.0);
            robot.frontLeft.setPower(1.0);

            robot.backRight.setPower(1.0);
            robot.frontRight.setPower(1.0);

        }
        else if (gamepad1.a) {
            robot.backLeft.setPower(0.5);
            robot.frontLeft.setPower(0.5);

            robot.frontRight.setPower(0.5);
            robot.backRight.setPower(0.5);
        }
        else {
            robot.backLeft.setPower(0.0);
            robot.backRight.setPower(0.0);
            robot.frontLeft.setPower(0.0);
            robot.frontRight.setPower(0.0);
        }

        //Turn Left

        if (gamepad1.dpad_left) {
            robot.backLeft.setPower(-1*1.0);
            robot.frontLeft.setPower(-1*1.0);

            robot.backRight.setPower(-1*1.0);
            robot.frontRight.setPower(-1*1.0);

        }
        else if (gamepad1.y) {
            robot.backLeft.setPower(-1*0.5);
            robot.frontLeft.setPower(-1*0.5);

            robot.frontRight.setPower(-1*0.5);
            robot.backRight.setPower(-1*0.5);
        }
        else {
            robot.backLeft.setPower(0.0);
            robot.backRight.setPower(0.0);
            robot.frontLeft.setPower(0.0);
            robot.frontRight.setPower(0.0);
        }
        
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
