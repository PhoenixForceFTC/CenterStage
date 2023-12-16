package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;
//import org.firstinspires.ftc.teamcode.Subsystems.PlaneServo;
//import org.firstinspires.ftc.teamcode.util.MotorEncoder;

/**


 FTC WIRES TeleOp Example**/

@TeleOp(name = "Test Teleop Vision", group = "00-Teleop")
public class TestVisionAuto extends LinearOpMode {


    // @Override

    /*


    Constructor for passing all the subsystems in order to make the subsystem be able to use
        and work/be active*/
    private Vision vision;


    public void runOpMode() throws InterruptedException {

        vision = new Vision(this, Vision.START_POSITION.BLUE_LEFT);
        vision.initTfod();



        while (!isStopRequested() && !opModeIsActive()) {

            //Run Vuforia Tensor Flow and keep watching for the identifier in the Signal Cone.
            vision.runTfodTensorFlow();
            telemetry.addData("Vision identified Parking Location", vision.getPixelLocation());
            telemetry.update();
        }
        vision.Stop();
        while (!isStopRequested()) {

            while (opModeIsActive()) {
                //     sensor.ConePresent();
                telemetry.addData("Position of pixel",vision.getPixelLocation());
            }


        }
    }
}
