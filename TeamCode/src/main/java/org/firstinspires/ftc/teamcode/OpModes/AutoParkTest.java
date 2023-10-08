package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Config
@Autonomous()
public class AutoParkTest extends AutoOpMode {
    public static Position START = new Position(-37, -57, 90);
    public static Position MOVE_ONE = new Position(-1, -1, 0);
    public static Position PARK_TWO = new Position(-57, -10, 0);
    @Override
    public void runOpMode() {
        setup();

        setStartPosition(START);

     //   goTo(MOVE_ONE);
        goTo(PARK_TWO);
        //
        // splineTo(MOVE_ONE);
        // turn(degrees);
    }
}
