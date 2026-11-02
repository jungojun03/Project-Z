packageTeleOp.FlyWheelClass;


import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;


public class FlyWheel {
    private DcMotorEx Launcher;
    private double encoderCPM = 28;
    private double kS= 0.1, kV= 0.000192, kP= 0.007;
    //kS overcome friction
    //kV Over shoot/undershoot target rpm = close as possible
    //kP reduce time it takes to get to goal rpm: Undershooting = kP is too low, Overshooting = kP is too high  EX: Oscillation means kP is too high
    public void init(HardwareMap hwMap){
        Launcher = hwMap.get(DcMotorEx.class, "Launcher");
        //  Launch2 = hwMap.get(DcMotorEx.class, "Launch2");
        Launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //  Launch2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);