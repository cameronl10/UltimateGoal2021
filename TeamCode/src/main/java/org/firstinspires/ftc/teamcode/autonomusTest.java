package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto Test")
public class autonomusTest extends LinearOpMode {

    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor armMotorLeft = null;
    private DcMotor armMotorRight = null;
    private Servo armServo = null;
    private ElapsedTime runtime =new ElapsedTime();
    private ElapsedTime totalTime = new ElapsedTime();
    //find value of velocity to calculate distance traveled.
    static final double velocity = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        backLeftDrive =hardwareMap.get(DcMotor.class,"back_left");
        backRightDrive = hardwareMap.get(DcMotor.class,"back_right");
        frontLeftDrive = hardwareMap.get(DcMotor.class,"front_left");
        frontRightDrive = hardwareMap.get(DcMotor.class,"front_right");
        armMotorLeft = hardwareMap.get(DcMotor.class,"arm_left");
        armMotorRight = hardwareMap.get(DcMotor.class,"arm_right");
        armServo = hardwareMap.get(Servo.class,"arm_servo");

        waitForStart();
        forward(2.6);
        armForward(0.1);
        armBackward(0.1);

        telemetry.addData("total time","seconds:", totalTime);
    }
    public void stop(double x) {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);

        while (opModeIsActive() && (runtime.seconds() < x)) {
            telemetry.addData("Path", "Stop", runtime.seconds());
            telemetry.update();
            idle();
        }
        runtime.reset();

    }
    public void forward(double x){
        frontLeftDrive.setPower(-velocity);
        frontRightDrive.setPower(velocity);
        backLeftDrive.setPower(-velocity);
        backRightDrive.setPower(velocity);

        while(opModeIsActive()&&(runtime.seconds() <x)){
            telemetry.addData("Path","Forward",runtime.seconds());
            telemetry.update();
            idle();
        }
        runtime.reset();

    }
    public void backward(double x){
        frontLeftDrive.setPower(velocity);
        frontRightDrive.setPower(-velocity);
        backLeftDrive.setPower(velocity);
        backRightDrive.setPower(-velocity);

        while(opModeIsActive()&&(runtime.seconds() <x)){
            telemetry.addData("Path","Forward",runtime.seconds());
            telemetry.update();
            idle();
        }
        runtime.reset();

    }
    public void armForward(double x){
        armMotorLeft.setPower(0.75);
        armMotorRight.setPower(-0.75);
        while(opModeIsActive()&&(runtime.seconds() <x)){
            telemetry.addData("Path","arm forward",runtime.seconds());
            telemetry.update();
            idle();
        }
        runtime.reset();
    }
    public void armBackward(double x){
        armMotorLeft.setPower(-0.75);
        armMotorRight.setPower(0.75);
        while(opModeIsActive()&&(runtime.seconds() <x)){
            telemetry.addData("Path","arm backward",runtime.seconds());
            telemetry.update();
            idle();
        }
        runtime.reset();
    }
}
