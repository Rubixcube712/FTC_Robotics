package org.firstinspires.ftc.robotcontroller.external.samples;

//Port one points to port 2 on VLG controller.

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class HardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  frontLeft   = null;
    public DcMotor  backLeft    = null;
    public DcMotor  backRight    = null;
    public DcMotor  frontRight  = null;

    //public Servo     armservo    = null;
    public Servo    rightClaw   = null;
    public Servo    armservo   = null;

    //public DcMotor  arm  = null;

    //public DcMotor  mainArm  = null;
    //public DcMotor  secondArm  = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontLeft   = hwMap.dcMotor.get("LeftFront");
        backLeft    = hwMap.dcMotor.get("LeftBack");
        frontRight = hwMap.dcMotor.get("RightFront");
        backRight    = hwMap.dcMotor.get("RightBack");

        //arm = hwMap.dcMotor.get("arm");
        //leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        backLeft.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);


        //mainArm    = hwMap.dcMotor.get("mainArm");
        //secondArm = hwMap.dcMotor.get("secondArm");

        // Set all motors to zero power
        frontLeft.setPower(0);
        backLeft.setPower(0);
        //backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        //arm.setPower(0);
        //mainArm.setPower(0);
        //secondArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backRight.setMaxSpeed(6056);
        backLeft.setMaxSpeed(6056);
        frontRight.setMaxSpeed(6056);
        frontLeft.setMaxSpeed(6056);

        //arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //mainArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //secondArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        armservo = hwMap.servo.get("ArmServo");
        //rightClaw = hwMap.servo.get("LeftFront");
        //leftClaw.setPosition(MID_SERVO);
        //rightClaw.setPosition(MID_SERVO);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
