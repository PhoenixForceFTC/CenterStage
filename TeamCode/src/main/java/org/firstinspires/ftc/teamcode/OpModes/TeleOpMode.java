package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumWheels;

/**
 * FTC WIRES TeleOp Example
 *
 */
@TeleOp(name = "mecanum drive test", group = "00-Teleop")
public class TeleOpMode extends LinearOpMode {


    private MecanumWheels mecanumWheels;



   // @Override

    /*
     * Constructor for passing all the subsystems in order to make the subsystem be able to use
     * and work/be active
    */

    public void runOpMode() throws InterruptedException {

    /* Create Subsystem Objects*/
    // driveTrain = new DriveTrain(hardwareMap);

        mecanumWheels = new MecanumWheels(this );
        waitForStart();
        //elbowArm.resetPosition();


        while (!isStopRequested()) {
            while (opModeIsActive()) {
                mecanumWheels.move();

                //telemetry.addData("Button", gamepad2.y);
                //telemetry.addData("isYPressed", isYPressed);
                //telemetry.addData("position == LOW", isYPressed);

            }
        }

        mecanumWheels.stop();
    }
}
