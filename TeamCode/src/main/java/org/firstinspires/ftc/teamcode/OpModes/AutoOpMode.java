package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Subsystems.CameraVision;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ElbowArm;
import org.firstinspires.ftc.teamcode.Subsystems.Lights;
import org.firstinspires.ftc.teamcode.Subsystems.NewLift;
import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.Arrays;

public abstract class AutoOpMode extends LinearOpMode {
    public SampleMecanumDrive drive;
    public NewLift lift;
    public ElbowArm elbowArm;
    public Claw claw;
    public CameraVision camera;
    public Lights lights;

    private Speed speed = Speed.FAST;

    private final ProfileAccelerationConstraint accelConstraint =
            new ProfileAccelerationConstraint(DriveConstants.MAX_ACCEL);

    // In inches per second
    private static final double VERY_SLOW_MAX_VEL = 3;
    private static final double SLOW_MAX_VEL = DriveConstants.MAX_VEL / 4;
    private static final double MEDIUM_MAX_VEL = DriveConstants.MAX_VEL / 2;
    private static final double FAST_MAX_VEL = DriveConstants.MAX_VEL;

    public enum Speed {
        VERY_SLOW,
        SLOW,
        MEDIUM,
        FAST
    }

    @Config
    public static class Position {
        public double X;
        public double Y;
        public double HEADING;

        /**
         * Creates a new position
         * @param x the x coordinate
         * @param y the y coordinate
         * @param heading the heading, in degrees
         */
        public Position(double x, double y, double heading) {
            X = x;
            Y = y;
            HEADING = heading;
        }

        public Pose2d toPose2d() {
            return new Pose2d(X, Y, Math.toRadians(HEADING));
        }

        public Vector2d toVector2d() {
            return new Vector2d(X, Y);
        }
    }

    public void setup() {
        drive = new SampleMecanumDrive(hardwareMap);
        camera = new CameraVision(this);
        lift = new NewLift(this);
        elbowArm = new ElbowArm(this);
        claw = new Claw(this);
        lights = new Lights(this);

        camera.listener(); //replaces waitForStart

        while(!isStarted() && !isStopRequested()){
            camera.detection();
        }

        if (isStopRequested()) return;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Speed getSpeed() {
        return speed;
    }

    private MinVelocityConstraint getVelConstraint() {
        double maxVel = 0;
        switch (speed) {
            case VERY_SLOW:
                maxVel = VERY_SLOW_MAX_VEL; break;
            case SLOW:
                maxVel = SLOW_MAX_VEL; break;
            case MEDIUM:
                maxVel = MEDIUM_MAX_VEL; break;
            case FAST:
                maxVel = FAST_MAX_VEL; break;
        }

        return new MinVelocityConstraint(
                Arrays.asList(
                        new AngularVelocityConstraint(DriveConstants.MAX_ANG_VEL),
                        new MecanumVelocityConstraint(maxVel, DriveConstants.TRACK_WIDTH)
                ));
    }

    /**
     * Sets the starting position of the robot
     * @param position the starting position
     */
    public void setStartPosition(Position position) {
        drive.setPoseEstimate(position.toPose2d());
    }

    /**
     * Spline to the specified position
     * @param position the position
     */
    public void splineTo(Position position) {
        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .splineTo(position.toVector2d(), Math.toRadians(position.HEADING), getVelConstraint(), accelConstraint)
                .build();

        drive.followTrajectory(traj);
    }

    /**
     * Go to the specified position
     * @param position the position
     */
    public void goTo(Position position) {
        Trajectory traj = drive.trajectoryBuilder(drive.getPoseEstimate())
                .lineToLinearHeading(position.toPose2d(), getVelConstraint(), accelConstraint)
                .build();

        drive.followTrajectory(traj);
    }

    /**
     * Turns the specified amount
     * @param angle angle, in degrees
     */
    public void turn(double angle) {
        drive.turn(Math.toRadians(angle));
    }
}
