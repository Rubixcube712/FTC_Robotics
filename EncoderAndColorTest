/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
//import org.firstinspires.ftc.teamcode.HardwarePushbot;

/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Test it out", group="Pushbot")

//Runs encoders forward, backward, left, and right to test stability

public class PushbotAutoDriveByEncoder_Linear_Test extends LinearOpMode {


    /* Declare OpMode members. */
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();


    //Set variables
    static final double     COUNTS_PER_MOTOR_REV    = 1514 ;    // Adjusted for Modern Robotics
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.56 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    static ColorSensor colorSensor;    // Hardware Device Object
    static ColorSensor colorSensor2;
    static ColorSensor colorSensorfloor;
    int count = 0;

    TouchSensor touchSensor;

    @Override
    public void runOpMode() {

        telemetry.addData("Program running", "other words");
        telemetry.update();

        colorSensor = hardwareMap.colorSensor.get("colorSensor1");
        colorSensor2 = hardwareMap.colorSensor.get("colorSensor2");
        colorSensorfloor = hardwareMap.colorSensor.get("colorsensorfloor");

        colorSensor.setI2cAddress(I2cAddr.create8bit(0x3e));
        colorSensor2.setI2cAddress(I2cAddr.create8bit(0x3a));

        // hsvValues is an array that will hold the hue, saturation, and value information.
        float hsvValues[] = {0F,0F,0F};

        // values is a reference to the hsvValues array.
        final float values[] = hsvValues;

        // get a reference to the RelativeLayout so we can change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);

        // bPrevState and bCurrState represent the previous and current state of the button.
        boolean bPrevState = false;
        boolean bCurrState = false;

        // bLedOn represents the state of the LED.
        boolean bLedOn = true;

        boolean step1 = true;
        boolean step2 = false;
        boolean step3 = false;
        boolean step4 = false;

        boolean touched = false;

        boolean greater = false;

        // Set the LED in the beginning
        colorSensor.enableLed(false);
        colorSensor2.enableLed(false);
        colorSensorfloor.enableLed(true);

        //sleep(1000);

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
       robot.init(hardwareMap);
        //sleep(1000);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                robot.frontLeft.getCurrentPosition(),
                robot.backLeft.getCurrentPosition(),
                robot.backRight.getCurrentPosition(),
                robot.frontRight.getCurrentPosition());
        //robot.backLeft.getCurrentPosition(),
        // robot.backLeft.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();


        //Set up the color sensors
        while (opModeIsActive()) {

            // check the status of the x button on either gamepad.

            // check for button state transitions.

                // button is transitioning to a pressed state. So Toggle LED


            // update previous state variable.
            bPrevState = bCurrState;

            // convert the RGB values to HSV values.
            //Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, hsvValues);
//Debugging code
           /* if (gamepad1.dpad_up) {
                // send the info back to driver station using telemetry function.
                count +=1;
                telemetry.addData("Count", count);
                telemetry.addData("LED", bLedOn ? "On" : "Off");
                telemetry.addData("Clear", colorSensor.alpha());
                telemetry.addData("Red on 1 ", colorSensor.red());
                telemetry.addData("Blue on 1 ", colorSensor.blue());
                telemetry.addData("Green on 1 ", colorSensor.green());

                telemetry.addData("Clear", colorSensor2.alpha());
                telemetry.addData("Red on 2 ", colorSensor2.red());
                telemetry.addData("Blue on 2 ", colorSensor2.blue());
                telemetry.addData("Green on 2 ", colorSensor2.green());

                telemetry.addData("Clear", colorSensorfloor.alpha());
                telemetry.addData("Red on floor ", colorSensorfloor.red());
                telemetry.addData("Blue on floor ", colorSensorfloor.blue());
                telemetry.addData("Green on floor ", colorSensorfloor.green());

                telemetry.addData("Hue", hsvValues[0]);
                telemetry.addData("argb", colorSensor.argb());
                // We have no clue what we are doing...

                telemetry.update();
            }

            //colorSensor.argb();
            if (gamepad1.dpad_down) {
                if (colorSensor.blue() > colorSensor.red()) {
                    telemetry.addData("Its blue", "colorSensor");
                    telemetry.update();
                }

                if (colorSensor.red() > colorSensor.blue() ) {
                    telemetry.addData("Its red", "colorSensor");
                    telemetry.update();
                }
            }

            if (gamepad1.dpad_right) {
                telemetry.addData("colorSensor", colorSensor.getI2cAddress());
                telemetry.addData("colorSensor", colorSensor2.getI2cAddress());
                telemetry.addData("colorSensor", colorSensorfloor.getI2cAddress());
                telemetry.update();


            }
        */



            //Port 0 - Floor
            //Port 1 - Left if front is Beacon Presser
            //Port 2 - Right if front is Beacon Presser

            //I2c Floor - 0x3c
            //I2c Left - 0x3a
            //I2c Right - 0x3e

            //colorSensor - Right
            //colorSensor2 - Left

            // change the background color to match the color detected by the RGB sensor.
            // pass a reference to the hue, saturation, and value array as an argument
            // to the HSVToColor method.
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            telemetry.update();

            telemetry.addData("Running on ", "8 -8 or Right");
            telemetry.update();
            encoderDrive(DRIVE_SPEED,  160,  -160, 8.0); //Back up, away from the wall

            telemetry.addData("Running on ", "-8 -8 or Backward");
            telemetry.update();
            encoderDrive(DRIVE_SPEED,  -160,  -160, 8.0); //Back up, away from the wall

            telemetry.addData("Running on ", "8 8 or Forward");
            telemetry.update();
            encoderDrive(DRIVE_SPEED,  160,  160, 8.0); //Back up, away from the wall


            telemetry.addData("Running on ", "-8 8 or Left");
            telemetry.update();
            encoderDrive(DRIVE_SPEED,  -160,  160, 10.0); //Back up, away from the wall


            if (runtime.seconds() > 29) {
                sleep(10000);
            }

            sleep(5000);

            telemetry.addData("sleep", "completed");
            telemetry.update();
        }

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newbackLeftTarget;
        int newbackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newbackLeftTarget = robot.backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newbackRightTarget = robot.backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


            robot.frontLeft.setTargetPosition(newLeftTarget);
            robot.backLeft.setTargetPosition(newbackLeftTarget);
            robot.frontRight.setTargetPosition(newRightTarget);
            robot.backRight.setTargetPosition(newbackRightTarget);


            // Turn On RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeft.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backRight.isBusy() && robot.backLeft.isBusy())) {

//                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget,  newbackLeftTarget,  newbackRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            robot.frontLeft.getCurrentPosition(),
                                            robot.backLeft.getCurrentPosition(),
                                            robot.backRight.getCurrentPosition(),
                                            robot.frontRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);
            robot.backLeft.setPower(0);

            robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Turn off RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
