package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    public static final double FULL_POWER = 1.0; //in case INTAKEPOWER don work
    public static final double NO_POWER = 0.0;
    public static final double INTAKEPOWER = 1;
    public static final double NINTAKEPOWER =-1;
    public static final double HALFPOWER = 0.75;

    public final DcMotor leftMotor, rightMotor;
    private final Gamepad Driver1;
    // private final Gamepad Driver2;
    private final Telemetry telemetry;

    public Intake(OpMode opMode) {
        Driver1 = opMode.gamepad1;
        telemetry = opMode.telemetry;
        HardwareMap hardwareMap = opMode.hardwareMap;

        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void teleOp() {
        // --- Intake Control ---
        if (Driver1.x) {
            leftMotor.setPower(INTAKEPOWER);
            rightMotor.setPower(INTAKEPOWER);
        } else if (Driver1.y) {
            leftMotor.setPower(NINTAKEPOWER);
            rightMotor.setPower(NINTAKEPOWER);
        } else {
            leftMotor.setPower(NO_POWER);
            rightMotor.setPower(NO_POWER);
        }

        if (Driver1.x) {
            leftMotor.setPower(INTAKEPOWER);
            rightMotor.setPower(INTAKEPOWER);
        } else if (Driver1.y) {
            leftMotor.setPower(NINTAKEPOWER);
            rightMotor.setPower(NINTAKEPOWER);
        } else {
            leftMotor.setPower(NO_POWER);
            rightMotor.setPower(NO_POWER);
        }

        // --- Hood Control ---


        // Optional telemetry feedback
        telemetry.addData("Intake Power", leftMotor.getPower());
        telemetry.addData("Intake Power", rightMotor.getPower());
//        telemetry.addData("Servo Left Pos", Hood.getPower());
//        telemetry.addData("Servo Right Pos", Hood.getPower());
        telemetry.update();
    }
    //if anything messes up during testing check this:
    public void IntakeOn() {
        leftMotor.setPower(-INTAKEPOWER);
        rightMotor.setPower(-INTAKEPOWER);
    }
    public void IntakeReverse() {
        leftMotor.setPower(INTAKEPOWER);
        rightMotor.setPower(INTAKEPOWER);

    }
    public void setLeftMotor() {leftMotor.setPower(-HALFPOWER);}
    public void setRightMotor() {rightMotor.setPower(-HALFPOWER);}


    public void IntakeOff() {
        leftMotor.setPower(NO_POWER);
        rightMotor.setPower(NO_POWER);

    }

}
