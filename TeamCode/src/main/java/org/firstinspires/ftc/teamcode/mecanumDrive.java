package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class mecanumDrive extends OpMode {
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        float gamepad1LeftY = -gamepad1.left_stick_y;
        float gamepad1LeftX = gamepad1.left_stick_x;
        float gamepad1RightX = gamepad1.right_stick_x;

        float FrontLeft = -gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
        float FrontRight = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;
        float BackLeft = -gamepad1LeftY + gamepad1LeftX + gamepad1RightX;
        float BackRight = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;

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


        


    }
}
