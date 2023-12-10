package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.Subsystems.IntakeServo;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumWheels;
import org.firstinspires.ftc.teamcode.Subsystems.Drop;
import org.firstinspires.ftc.teamcode.Subsystems.Snagger;
import org.firstinspires.ftc.teamcode.Subsystems.TopGate;


/**
 * FTC WIRES TeleOp Example
 *
 */
@TeleOp(name = "mecanum drive test", group = "00-Teleop")



public class TeleOpMode extends LinearOpMode {
    private OpMode opMode;
    private MecanumWheels mecanumWheels;
    private TopGate topGate;

    //    private MotorEncoder motorEncoder;
    //private IntakeServo axonServo;
    private boolean isYPressed;
    private Drop drop;
    private Snagger snagger;
    public int returnDrop(){
        return drop.getPos();
    }
    public boolean reached(){
        return drop.reachedTarget();
    }
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
//        motorEncoder = new MotorEncoder(this);
//        planeServo = new PlaneServo(this);
        //axonServo = new IntakeServo(this);

//        claw = new Claw(this);
//        flip = new Flip(this);
        //   sensor = new ClawSensor(this.hardwareMap);
        waitForStart();
        //elbowArm.resetPosition();
//        flip.setClawClosed(false);


        while (!isStopRequested()) {
            while (opModeIsActive()) {
                //     sensor.ConePresent();


                mecanumWheels.move();
                topGate.controlGate();
                drop.controlLift2();
                snagger.move(gamepad2.right_stick_y);
                if (gamepad1.y) {

                    if (!isYPressed) {
//                        telemetry.addData("Y is pressed", gamepad1.y);
                        //axonServo.setPower(1);

                    }

                    isYPressed = true;
                } else {
//
                    if (isYPressed) {
//                        telemetry.addData("y was let go", gamepad1.y);
                        //axonServo.setPower(0);

                    }
                    telemetry.update();
                    isYPressed = false;

                }
            }

            mecanumWheels.stop();
          drop.stop();
          snagger.stop();
        }
    }
}