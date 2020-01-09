/*
 * Copyright (c) 2019 The Newman School Robotics
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmLiftHardware extends BotHardware {
    public DcMotor armLifter;
    public Servo leftGrabber;
    public Servo rightGrabber;
    public Servo foundationGrabberLeft;
    public Servo foundationGrabberRight;

    public void initGrabbers(HardwareMap hardwareMap){
        // The lifter of the arm
        armLifter = hardwareMap.dcMotor.get("ArmLifter");

        // The grabbers of the arm
        leftGrabber = hardwareMap.servo.get("ClipGrabberLeft");
        rightGrabber = hardwareMap.servo.get("ClipGrabberRight");

        // The foundation grabber
        foundationGrabberLeft = hardwareMap.servo.get("FoundationGrabberLeft");
        foundationGrabberRight = hardwareMap.servo.get("FoundationGrabberRight");

        // TODO: measure the position of the initialization state of the foundation grabber should be
        foundationGrabberLeft.setPosition(0.0);
        foundationGrabberRight.setPosition(0.0);

        armLifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void openBlockGrabber() {
    }

    public void closeBlockGrabber() {
    }

    public void openFoundationGrabber() {

    }

    public void closeFoundationGrabber() {

    }

    public enum GrabberSide {
        LEFT,
        RIGHT
    }

    public static double accumulate(double original, double accumulation) {
        double attempt = original + accumulation;
        return Math.min(1.0, Math.max(attempt, 0.0));
    }

    public void operateGrabber(GrabberSide side, double amount) {
        if (side == GrabberSide.LEFT) {
            leftGrabber.setPosition(accumulate(leftGrabber.getPosition(), amount));
        } else if (side == GrabberSide.RIGHT) {
            rightGrabber.setPosition(accumulate(rightGrabber.getPosition(), amount));
        }
    }
}