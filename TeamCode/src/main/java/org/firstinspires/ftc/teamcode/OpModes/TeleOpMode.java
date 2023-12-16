package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumWheels;
import org.firstinspires.ftc.teamcode.Subsystems.Drop;
import org.firstinspires.ftc.teamcode.Subsystems.PlaneServo;
import org.firstinspires.ftc.teamcode.Subsystems.Snagger;
import org.firstinspires.ftc.teamcode.Subsystems.TopGate;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

/**
 * FTC WIRES TeleOp Example
 *
 */
@TeleOp(name = "mecanum drive test", group = "00-Teleop")



public class TeleOpMode extends LinearOpMode {
    private OpMode opMode;
    private PlaneServo planeServo;
    private MecanumWheels mecanumWheels;
    private TopGate topGate;
    private Intake intake;

    //    private MotorEncoder motorEncoder;
    //private IntakeServo axonServo;
    private boolean isYPressed;
    private Drop drop;
    private Snagger snagger;
    // @Override

    /*
     * Constructor for passing all the subsystems in order to make the subsystem be able to use
     * and work/be active
     */


    public void runOpMode() throws InterruptedException {

        /* Create Subsystem Objects*/
        // driveTrain = new DriveTrain(hardwareMap);
        //newLift2 = new NewLift2(this);
        drop = new Drop(this);
        snagger= new Snagger(this);
        topGate= new TopGate(this,drop);
        mecanumWheels = new MecanumWheels(this);
        intake = new Intake(this,drop,topGate);
        planeServo = new PlaneServo(this);

        waitForStart();


        while (!isStopRequested()) {
            while (opModeIsActive()) {
                //     sensor.ConePresent();


                mecanumWheels.move();
                topGate.controlGate();
                snagger.move(gamepad2.left_stick_y);



                if(gamepad1.left_trigger > 0.5 && gamepad1.right_trigger > 0.5 && gamepad1.left_bumper && gamepad1.right_bumper)
                {
                    planeServo.GoToPosition((float)0.2);
                }
                else{
                    planeServo.GoToPosition(0);
                }

                drop.controlLift2();

                //drop.move(gamepad2.right_stick_y);

                if(gamepad2.dpad_down){
                    intake.transferPixel();
                } else if(gamepad2.dpad_up){
                    intake.returnPixel();
                } else if(gamepad2.left_trigger>0.2){
                    intake.eatPixel();
                }else{
                    intake.stop();
                }

                if(gamepad2.right_stick_button){
                    drop.resetPosition();
                }

                telemetry.update();
            }

            mecanumWheels.stop();
          drop.stop();
          snagger.stop();
          intake.stop();
        }
    }
}