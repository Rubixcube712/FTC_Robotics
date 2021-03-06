/* Copyright (c) 2016 Robert Atkinson
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

import android.graphics.Color;
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

//Basic driver controlled

//@Disabled
public class PushbotTeleopTank_Iterative extends OpMode{

    //Op Mode is using the HardwarePushbot from the samples. Not from the teamcode

    /* Declare OpMode members. */
    org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot robot       = new HardwarePushbot(); // use the class created to define a Pushbot's hardware
    // could also use HardwarePushbotMatrix class.

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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

//        if (gamepad1.x) {
//            robot.frontRight.setPower(0.7);
//            telemetry.addData("Front right ", "moving forward");
//            telemetry.update();
//        }
//        else if (gamepad1.y) {
//            robot.backRight.setPower(0.7);
//            telemetry.addData("Back right ", "moving forward");
//            telemetry.update();
//        }
//        else if (gamepad1.a) {
//            robot.frontLeft.setPower(0.7);
//            telemetry.addData("Front left ", "moving forward");
//            telemetry.update();
//        }
//        else if (gamepad1.b) {
//            robot.backLeft.setPower(0.7);
//            telemetry.addData("Back left ", "moving forward");
//            telemetry.update();
//        }


        //Drive forward

        if (gamepad1.dpad_up) {
            moveStraight(0.7);
        }

        //Backward

        else if (gamepad1.dpad_down) {
            moveStraight(-0.7);

        }

        //Turn Right

        else if (gamepad1.dpad_right) {
            turnRight(0.7);

        }

        //Turn Left

        else if (gamepad1.dpad_left) {
            //robot.leftMotor.setDirection(DcMotor.Direction.FORWARD);
            turnLeft(0.7);

        }
        else {
            moveStop();
        }

        float xstickvalue = gamepad1.left_stick_x;
        float ystickvalue = gamepad1.left_stick_y;

//        telemetry.addData("X Axis is at:", xstickvalue);    //
//        telemetry.addData("Y Axis is at:", ystickvalue);    //


        //arm code
//        if (gamepad1.x) {
//            robot.armservo.setPosition(robot.MID_SERVO - 0.02);
//        } else if (gamepad1.b) {
//            robot.armservo.setPosition(robot.MID_SERVO + 0.02);
//        } else {
//            robot.armservo.setPosition(0.05);
//        }


        //Color sensor code
       //if (SensorMRColor.colorSensor.blue() > 150 && SensorMRColor.colorSensor.red() < 150) {
            //telemetry.addData("The thing is ", "Blue");    //
       //}

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

    @Override
    public void stop() {
    }

    private void turnLeft(double power) {
        moveMotors(-power, power);
    }

    private void turnRight(double power) {
        moveMotors(power, -power);
    }

    private void moveStraight(double power) {
        moveMotors(power, power);
    }

    private void moveStop() {
        moveMotors(0.0, 0.0);
    }

    private void moveMotors(double left, double right) {
        robot.frontLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backLeft.setPower(left);
        robot.backRight.setPower(right);
    }
}


