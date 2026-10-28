package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public DcMotorEx intake;
    Gamepad gamepad;
    public void init(HardwareMap hardWareMap) {
        intake = hardWareMap.get(DcMotorEx.class, "intake");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        gamepad = new Gamepad();
    }

    public Command intake() {
        intake.setPower(1);
        return null;
    }
    public Command reverse() {
        intake.setPower(-1);
        return null;
    }
}
