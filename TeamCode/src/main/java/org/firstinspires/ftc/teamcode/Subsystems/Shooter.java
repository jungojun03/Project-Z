package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    DcMotorEx shooter;

    private double encoderCPM = 28;

    private
    private double kV, kS, kP;

    private void init(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setMotorPower(double power) {
        shooter.setPower(power);
    }

    public double getTicksPerSecond() {
        return shooter.getVelocity();
    }

    public void shoot() {

    }

    public void reverse() {

    }
}
