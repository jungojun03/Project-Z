package org.firstinspires.ftc.teamcode.Autos;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.interpolator.Interpolator;
import com.pedropathing.utils.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.PedroPathing.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

import java.time.Instant;

@Autonomous(name = "RedAuto")
public class RedAuto extends OpMode {

    private Follower follower;
    Intake intake;
    Timer timer;
    Shooter shooter;

    private final PoseFactory poseFactory = PoseFactory.degrees();

        private final Pose StartingPos = poseFactory.of(38, 10, 90);
        private final Pose Preload = poseFactory.of(38, 10, 90);
        private final Pose shoot1 = poseFactory.of(38, 13, 75);
        private final Pose intake1 = poseFactory.of(3, 6, 176.71);
        private final Pose intake1Control1 = poseFactory.of(37, 4, 0);
        private final Pose intake1Segment1Start = poseFactory.of(3, 6, 70);
        private final Pose intake1Segment1End = poseFactory.of(3, 6, 180);
        private final Pose shoot2Start = poseFactory.of(3, 6, 180);
        private final Pose shoot2 = poseFactory.of(54, 124, 110);
        private final Pose shoot2Control1 = poseFactory.of(30, 40, 0);
        private final Pose shoot2Control2 = poseFactory.of(28, 100, 0);
        private final Pose intake2 = poseFactory.of(48, 138, 110);
        private final Pose shoot3 = poseFactory.of(54, 124, 113.1986);
        private final Pose parkStart = poseFactory.of(54, 124, 110);
        private final Pose park = poseFactory.of(5, 113, 90);


        public Path Preload(){return line(StartingPos, Preload).linear(StartingPos, Preload);}
        public Path shoot1() {
            return line(Preload, shoot1).linear(Preload, shoot1);
        }

        public Path intake1() {
            return curve(shoot1, intake1Control1, intake1).heading(Interpolator.piecewise().until(0.5335, Interpolator.linear(intake1Segment1Start, intake1Segment1End)).until(1, Interpolator.tangent));
        }

        public Path shoot2() {
            return curve(shoot2Start, shoot2Control1, shoot2Control2, shoot2).linear(shoot2Start, shoot2);
        }

        public Path intake2() {
            return line(shoot2, intake2).constant(intake2);
        }

        public Path shoot3() {
            return line(intake2, shoot3).reverseTangent();
        }

        public Path park() {
            return line(parkStart, park).linear(parkStart, park);
    }

        private Command autoRoutine() {
            return sequential(
                    follow(follower,shoot1()),
                    Rev(),
                    Shoot(),
                    waitMs(1000),
                    Stop(),
                    Intake(),
                    follow(follower,intake1()),
                    Stop(),
                    follow(follower,shoot2())
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
                    waitUntil(() -> shooter.getTicksPerSecond() > 600), //CHANGE TO CURRENT RPM AND CHANGE NUMBER
                    instant(() -> intake.intake())
            );
        }

        protected Command Stop() {
            return instant(() -> { shooter.stop(); intake.stop();
            });
        }

            @Override
        public void init() {
            intake = new Intake();
            timer = new Timer();
            shooter = new Shooter();

            Scheduler.reset();
            follower = Constants.create(hardwareMap);
            follower.setPose(Preload);
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
