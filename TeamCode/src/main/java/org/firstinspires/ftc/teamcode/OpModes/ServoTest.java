package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;


@TeleOp(name = "servo test", group = "Test")
public class ServoTest extends LinearOpMode {
    public CRServo servo1;


    @Override
    public void runOpMode() {
        servo1 = hardwareMap.get(CRServo.class, "S1");
        servo1.resetDeviceConfigurationForOpMode();

        waitForStart();

        telemetry.addLine();
        telemetry.update();

        while (!isStopRequested()) {
            while (opModeIsActive()) {
                if(gamepad1.y) {
                    servo1.setPower(-1);
                    telemetry.addData("Y Presssed", "");
                    telemetry.update();
                    sleep(5000);
                }
                        if(gamepad1.a) {
                            servo1.setPower(0);
                            telemetry.addData("A Presssed", "");
                            telemetry.update();
                            sleep(5000);
                        }
                        if(gamepad1.b) {

                            servo1.setPower(1);
                            telemetry.addData("B Presssed", "");
                            telemetry.update();
                            sleep(5000);
                        }
                telemetry.addData(">", "Press Start to scan Servo.");
                telemetry.update();
            }
        }
    }
}