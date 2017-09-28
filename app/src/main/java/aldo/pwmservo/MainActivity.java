package aldo.pwmservo;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.things.contrib.driver.pwmservo.Servo;

import java.io.IOException;

public class MainActivity extends Activity {
    private Servo mServo0, mServo1;
    Thread mThread;
    Runnable mRunnable = new Runnable() {
        public void run() {
            while (true) {
                try {
                    mServo0.setAngle(90);
                    mServo1.setAngle(-90);
                } catch (IOException e) {}

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}

                try {
                    mServo0.setAngle(-90);
                    mServo1.setAngle(90);
                } catch (IOException e) {}

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mServo0 = new Servo("PWM0");
            mServo0.setPulseDurationRange(0.75,2.4);
            mServo0.setAngleRange(-90, 90);
            mServo0.setEnabled(true);
        } catch (IOException e) {}

        try {
            mServo1 = new Servo("PWM1");
            mServo1.setPulseDurationRange(0.75,2.6);
            mServo1.setAngleRange(-90, 90);
            mServo1.setEnabled(true);
        } catch (IOException e) {}

        mThread = new Thread(mRunnable);
        mThread.start();
    }
}
