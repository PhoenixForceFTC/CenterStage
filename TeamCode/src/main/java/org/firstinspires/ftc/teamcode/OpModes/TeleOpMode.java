package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Belt;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.ClawSensor;
import org.firstinspires.ftc.teamcode.Subsystems.Flip;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumWheels;
//import org.firstinspires.ftc.teamcode.Subsystems.NewLift2;
import org.firstinspires.ftc.teamcode.Subsystems.PlaneServo;
import org.firstinspires.ftc.teamcode.util.MotorEncoder;

/**
 * FTC WIRES TeleOp Example
 *
 */
@TeleOp(name = "mecanum drive test", group = "00-Teleop")
public class TeleOpMode extends LinearOpMode {
    private OpMode opMode;

    private MecanumWheels mecanumWheels;

    private MotorEncoder motorEncoder;
    private PlaneServo planeServo;
    private boolean isYPressed;
    //private NewLift2 newLift2;
    private Belt belt;

    public ClawSensor sensor;
    public Claw claw;
    public Flip flip;






   // @Override

    /*
     * Constructor for passing all the subsystems in order to make the subsystem be able to use
     * and work/be active
    */


    public void runOpMode() throws InterruptedException {

    /* Create Subsystem Objects*/
    // driveTrain = new DriveTrain(hardwareMap);
        //newLift2 = new NewLift2(this);
//        belt = new Belt(this);
//        mecanumWheels = new MecanumWheels(this );
//        motorEncoder = new MotorEncoder(this);
//        planeServo = new PlaneServo(this);
//        claw = new Claw(this);
//        flip = new Flip(this);
        sensor = new ClawSensor(this.hardwareMap);
        waitForStart();
        //elbowArm.resetPosition();
//        flip.setClawClosed(false);


        while (!isStopRequested()) {
            while (opModeIsActive()) {
                  sensor.ConePresent();


//                mecanumWheels.move();
//                claw.controlClaw();
//                flip.controlClaw();
//                //newLift2.controlLift(true);
//                belt.controlLift(true);
//                //if(gamepad2.a){planeServo.GoToPosition((float) 0.4);}
//                if(gamepad1.left_trigger > 0.9 && gamepad1.right_trigger > 0.9 && gamepad1.left_bumper && gamepad1.right_bumper)
//                {
//                    planeServo.GoToPosition(1);
//                }
//                else{
//                    planeServo.GoToPosition((float) 0.45);
//                }
//
//                if (gamepad1.y) {
//
//                    if (!isYPressed) {
//                        telemetry.addData("Y is pressed", gamepad1.y);
//
//                        motorEncoder.goToPickupPosition();
//                    }
//
//                    isYPressed = true;
//                } else {
//
//                    if(isYPressed){
//                        telemetry.addData("y was let go", gamepad1.y);
//
//                        motorEncoder.goToHighJunction();
//                    }
//
//                    isYPressed = false;
//
//                }
                telemetry.addData(" sensor distance:",(sensor.getDistance()));
                telemetry.update();

            }
        }

//        mecanumWheels.stop();
//        //newLift2.stop();
//        belt.stop();
    }
}
