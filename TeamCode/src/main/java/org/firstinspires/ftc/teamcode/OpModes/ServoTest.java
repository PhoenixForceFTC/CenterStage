package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp
public class ServoTest extends LinearOpMode {
    public Servo servo1;
    public Servo servo2;
    public Servo Pitch;


    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(Servo.class, "s1");
        servo2 = hardwareMap.get(Servo.class, "s2");
        Pitch = hardwareMap.get(Servo.class, "s3");

        telemetry.addData("Servo1 : ", servo1.getPosition());
        telemetry.addData("Servo2 : ", servo2.getPosition());
        telemetry.addData("Servo3 : ", Pitch.getPosition());

        servo2.setDirection(Servo.Direction.REVERSE);
        Pitch.setDirection(Servo.Direction.REVERSE);
        //servo3.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        telemetry.addLine();
        telemetry.update();

        while (!isStopRequested()) {
            while (opModeIsActive()) {


                //roll - startup position

//            servo2.setPosition(.089);
//            servo1.setPosition(0);
//            sleep(2000);
//
//         Grab it

                        if(gamepad1.a) {
                            servo2.setPosition(0);
                            sleep(5000);
                        }
                        if(gamepad1.b)
                        {

                            servo2.setPosition(0.075);
                            sleep(5000);
                        }





//
//            //roll - drop
//           servo1.setPosition(.65);
//            sleep(2000);
//           servo2.setPosition(.5);
//          sleep(1000);
//            servo2.setPosition(.3);
//            //sleep(1000);
//
//            //release
//           servo1.setPosition(0.15);
//            sleep(1000);
//          //  servo1.setposition(0)
//            //servo2.setPosition(0.1);
//            sleep(2000);
//            //pitch

                // servo3.setPosition(0);
                //sleep(1000);
                //servo3.setPosition(0);
                //sleep(1000);

                //grip

                boolean Grab = gamepad1.b;

                telemetry.addData(">", "Press Start to scan Servo.");
                telemetry.update();
                waitForStart();
            }
        }
    }
}