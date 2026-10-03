package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotorEx top, bottom;
    Gamepad gamepad;
    public void init(HardwareMap hardWareMap) {
        top = hardWareMap.get(DcMotorEx.class, "top");
        bottom = hardWareMap.get(DcMotorEx.class, "bottom");

        top.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        top.setDirection(DcMotorSimple.Direction.FORWARD); //change these if backwards
        bottom.setDirection(DcMotorSimple.Direction.FORWARD);

        gamepad = new Gamepad();
    }

    public void topIntake() {
        top.setPower(1);
    }

    public void topReverse() {
        top.setPower(-1);
    }

    public void topStop() {
        top.setPower(0);
    }

    public void bottomIntake() {
        top.setPower(1);
    }

    public void bottomReverse() {
        top.setPower(-1);
    }

    public void bottomStop() {
        top.setPower(0);
    }

    public void intake() {
        if (gamepad.)
    }
}
