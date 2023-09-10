package org.firstinspires.ftc.teamcode.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumWheels;
import org.firstinspires.ftc.teamcode.Subsystems.Servos;
import org.firstinspires.ftc.teamcode.util.MotorEncoder;

/**
 * FTC WIRES TeleOp Example
 *
 */
@TeleOp(name = "mecanum drive test", group = "00-Teleop")
public class TeleOpMode extends LinearOpMode {


    private MecanumWheels mecanumWheels;

    private MotorEncoder motorEncoder;
    private Servos servos;
    private boolean isYPressed;


    public Claw claw;





   // @Override

    /*
     * Constructor for passing all the subsystems in order to make the subsystem be able to use
     * and work/be active
    */

    public void runOpMode() throws InterruptedException {

    /* Create Subsystem Objects*/
    // driveTrain = new DriveTrain(hardwareMap);

        mecanumWheels = new MecanumWheels(this );
        motorEncoder = new MotorEncoder(this);
        servos = new Servos(this);
        claw = new Claw(this);
        waitForStart();
        //elbowArm.resetPosition();


        while (!isStopRequested()) {
            while (opModeIsActive()) {
                mecanumWheels.move();
                claw.controlClaw();

                //if(gamepad2.a){servos.GoToPosition(0.4);}
                if(gamepad2.b)
                {
                    servos.GoToPosition(1);

                }
                else{
                    servos.GoToPosition((float) 0.45);
                }

                if (gamepad2.y) {

                    if (!isYPressed) {
                        telemetry.addData("Y is pressed", gamepad2.y);
                        telemetry.update();
                        motorEncoder.moveToTop();
                    }

                    isYPressed = true;
                } else {

                    if(isYPressed){
                        telemetry.addData("y was let go", gamepad2.y);
                        telemetry.update();
                        motorEncoder.moveToBottom();
                    }
                    isYPressed = false;

                }


            }
        }

        mecanumWheels.stop();
    }
}
