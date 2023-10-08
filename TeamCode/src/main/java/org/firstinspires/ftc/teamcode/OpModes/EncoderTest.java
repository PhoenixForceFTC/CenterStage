package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.StandardTrackingWheelLocalizer;

@TeleOp
public class EncoderTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        StandardTrackingWheelLocalizer localizer = new StandardTrackingWheelLocalizer(hardwareMap);

        int initLeft = localizer.leftEncoder.getCurrentPosition();
        int initRight = localizer.rightEncoder.getCurrentPosition();
        int initFront = localizer.frontEncoder.getCurrentPosition();

        waitForStart();
        while (!isStopRequested()) {
            telemetry.addData("Left encoder ticks", localizer.leftEncoder.getCurrentPosition() - initLeft);
            telemetry.addData("Right encoder ticks", localizer.rightEncoder.getCurrentPosition() - initRight);
            telemetry.addData("Front encoder ticks:", localizer.frontEncoder.getCurrentPosition() - initFront);
            telemetry.update();
        }
    }
}
