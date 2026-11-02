package org.firstinspires.ftc.teamcode.Autos;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.PedroPathing.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@Autonomous(name = "BlueAuto")
public class BlueAuto extends OpMode {

    private Follower follower;
    Intake intake;
    Timer timer;
    Shooter shooter;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(83.5357, 133.7878, -90);
    private final Pose point2 = poseFactory.of(114.3551, 137.2225, 0);
    private final Pose point2Control1 = poseFactory.of(88.7224, 124.4133, 0);
    private final Pose point3 = poseFactory.of(135.0071, 139.1408, 0);
    private final Pose point4 = poseFactory.of(90.5194, 13.9459, -90);
    private final Pose point5 = poseFactory.of(93.4449, 7.4102, -90);
    private final Pose point6 = poseFactory.of(135.0602, 33.7367, 0);


    public Path shoot1() {
        return curve(start, point2Control1, point2).linear(start, point2);
    }


    public Path intake1() {
        return line(point2, point3).linear(point2, point3);
    }


    public Path travel2() {
        return line(point3, point4).linear(point3, point4);
    }


    public Path intake2() {
        return line(point4, point5).linear(point4, point5);
    }


    public Path finish() {
        return line(point5, point6).linear(point5, point6);
    }

    private Command autoRoutine() {
        return sequential(
                follow(follower, shoot1()),
                Rev(),
                Shoot(),
                waitMs(1000),
                Stop(),
                Intake(),
                follow(follower, intake1()),
                Stop(),
                follow(follower, travel2()),
                Rev(),
                Shoot(),
                waitMs(1000),
                Stop(),
                Intake(),
                follow(follower, intake2()),
                Stop(),
                follow(follower, finish())
        );
    }

    protected Command Rev() {
        return instant(() -> shooter.shoot());
    }

    protected Command Intake() {
        return instant(() -> intake.intake());
    }

    protected Command Shoot() {
        return sequential(
                waitUntil(() -> shooter.getTicksPerSecond() > 600),
                instant(() -> intake.intake())
        );
    }

    protected Command Stop() {
        return instant(() -> {
            shooter.stop();
            intake.stop();
        });
    }

    @Override
    public void init() {
        intake = new Intake();
        timer = new Timer();
        shooter = new Shooter();

        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();
    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.addData("Follower Mode", follower.mode());
        telemetry.update();
    }
}