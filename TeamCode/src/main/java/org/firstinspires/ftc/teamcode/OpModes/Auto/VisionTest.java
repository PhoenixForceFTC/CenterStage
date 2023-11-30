package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AbstractAutonomous.Side;
import org.firstinspires.ftc.teamcode.vision.PropDetector;
@Autonomous(name="camera test", group="test")
public class VisionTest extends AbstractAutonomous{
    @Override
    public void runOpMode() throws InterruptedException {
        side = Side.RED;
        detector = new PropDetector(this, true, side);
        telemetry.addData("leftAvg",detector.getProc().getLeftAvg());
        telemetry.addData("rightAvg",detector.getProc().getRightAvg());
        telemetry.addData("case: ",detector.getCase());
        telemetry.update();
        waitForStart();
    }
}
