package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mecanum Drive")
public class mecanumDrive extends OpMode {
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor armMotorLeft = null;
    private DcMotor armMotorRight = null;
    private Servo armServo = null;

    private Boolean gamepad2Active = true;
    private GamePad armGamepad;

    @Override
    public void init() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right");
        armMotorLeft = hardwareMap.get(DcMotor.class, "arm_left");
        armMotorRight = hardwareMap.get(DcMotor.class, "arm_right");
        armServo = hardwareMap.get(Servo.class, "arm_servo");
    }

    @Override
    public void loop() {
        /*
        Driving Code in gamepad one
         */
        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX + gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;

        // Set the ranges that the wheels can go
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

        frontLeftDrive.setPower(FrontLeft);
        frontRightDrive.setPower(FrontRight);
        backLeftDrive.setPower(BackLeft);
        backRightDrive.setPower(BackRight);

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", gamepad1LeftX) + " " +  String.format("%.2f", gamepad1LeftY) + " " +  String.format("%.2f", gamepad1RightX));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));

        /*
        Arm code
        */
        if (gamepad2Active == true) {
            armGamepad = gamepad2;
        } else {
            armGamepad = gamepad1;
        }

        // Conditional statements to check if input towards arm is active (y raises arm, a lowers arm, no inputs brakes the arm)
        float armPower = 0.3;
        if (armGamepad.y) {
            armMotorLeft.setPower(1 * armPower);
            armMotorRight.setPower(1 * -armPower);
        } else if (armGamepad.a) {
            armMotorLeft.setPower(1 * -armPower);
            armMotorRight.setPower(1 * armPower);
        } else {
            armMotorLeft.setPower(0);
            armMotorRight.setPower(0);
            armMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        // Set servo position to 180 degrees
        if (armGamepad.right_bumper) {
            armServo.setPosition(1);
        }
        // Set servo position to 0 degrees
        if (armGamepad.left_bumper) {
            armServo.setPosition(0);
        }

        telemetry.addData("arm motor", "arm motor power" + String.format("%.2f", armMotorLeft.getPower()));
        telemetry.addData("servo psn", "right servo position" + String.format("%.2f", armServo.getPosition()));
    }
}
