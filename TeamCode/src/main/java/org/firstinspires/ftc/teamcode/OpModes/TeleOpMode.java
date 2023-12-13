package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumWheels;
import org.firstinspires.ftc.teamcode.Subsystems.Drop;
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
    private MecanumWheels mecanumWheels;
    private TopGate topGate;
    private Intake intake;

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
        intake = new Intake(this,drop,topGate);
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
                snagger.move(gamepad2.left_stick_y);
                drop.controlLift2();
                drop.move(gamepad2.right_stick_y);
                if(gamepad2.left_stick_button){
                    intake.transferPixel();
                } else if(gamepad2.left_trigger>0.2){
                    intake.eatPixel();
                }else{
                    intake.stop();
                }

                telemetry.update();
            }

            mecanumWheels.stop();
          drop.stop();
          snagger.stop();
        }
    }
}