package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Concept: helloWOrld", group = "Concept")
public class helloWorld2 extends OpMode {


  @Override
  public void init() {

    telemetry.addData("hello", "world");
    telemetry.update();
  }

  @Override
  public void init_loop() {

  }

  @Override
  public void start() {

  }

  @Override
  public void loop() {

  }
}
