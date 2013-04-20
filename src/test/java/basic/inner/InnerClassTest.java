package basic.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * @Author: caoxiao
 * @Date: 13-3-17 下午6:03
 */
public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock(1000,true);
        talkingClock.start();
        JOptionPane.showMessageDialog(null,"Quit program?");
        talkingClock.setBeep(false);
        //System.exit(0);
    }
}

class TalkingClock{

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start(){
        ActionListener listener = new TimerPrinter();
        Timer t = new Timer(interval,listener);
        t.start();
    }

    private class TimerPrinter implements  ActionListener{

        /**
         * Invoked when an action occurs.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(beep){
                Date date = new Date();
                System.out.println("At the tone , the time is "+date);
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    private int interval;
    private boolean beep;

    int getInterval() {
        return interval;
    }

    void setInterval(int interval) {
        this.interval = interval;
    }

    boolean isBeep() {
        return beep;
    }

    void setBeep(boolean beep) {
        this.beep = beep;
    }
}
