package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public DcMotorEx intakeRight, intakeLeft;
    Gamepad gamepad;
    public void init(HardwareMap hardWareMap) {
        intakeRight = hardWareMap.get(DcMotorEx.class, "intakeRight");
        intakeLeft = hardWareMap.get(DcMotorEx.class, "intakeLeft");

        intakeLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        gamepad = new Gamepad();
    }

    public void intake() {
        intakeRight.setPower(1);
        intakeLeft.setPower(1);
    }
    public void reverse() {
        intakeRight.setPower(-1);
        intakeLeft.setPower(-1);
    }

    public void stop() {
        intakeRight.setPower(0);
        intakeLeft.setPower(0);
    }
}
