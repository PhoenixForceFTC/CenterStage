package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.OpModes.Auto.AbstractAutonomous.Side;
import org.firstinspires.ftc.teamcode.vision.PropDetector;
@Autonomous(name="camera test", group="test")
public class VisionTest extends AbstractAutonomous{

    @Override
    public void init() {
        side = Side.RED;
        detector = new PropDetector(this, true, side);

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        waitOpMode();
        telemetry.addData("leftAvg",detector.getProc().getLeftAvg());
        telemetry.addData("rightAvg",detector.getProc().getRightAvg());
        telemetry.addData("case: ",detector.getCase());
        telemetry.update();
    }
    @Override
    public void loop() {

    }
    @Override
    public void stop() {
        startOpMode();
    }
}
