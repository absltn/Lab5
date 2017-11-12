package com.lab5;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * {@link GUI} is a class that provided graphic user interface for world clock.
 * It uses {@link JFrame}, {@link JPanel} and {@link JLabel} classes
 * For time to update i implement {@link Runnable} interface.
 * While running i implement {@link Thread} for updating time.
 * Time itself is formatted String, that comes from {@link TimeClass} which
 * provides ZonedDateTime instant, invoked with ZoneId. ZoneId is set from
 * GUI class with run() method.
 */
public class GUI implements Runnable {
    public GUI(){
        JFrame frame = new JFrame();
        frame.setBounds(100, 200, 145, 200);
        frame.setTitle("World clock");

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(3,1));

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel leftLabel1 = new JLabel("Local time (Moscow) ");
        panel1.add(leftLabel1);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel leftLabel2= new JLabel("London ");
        panel2.add(leftLabel2);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel leftLabel3= new JLabel("New York ");
        panel3.add(leftLabel3);

        final JLabel timeLabel1 = new JLabel();
        timeLabel1.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(timeLabel1);

        final JLabel timeLabel2 = new JLabel();
        timeLabel2.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(timeLabel2);

        final JLabel timeLabel3 = new JLabel();
        timeLabel3.setHorizontalAlignment(JLabel.CENTER);
        panel3.add(timeLabel3);

        contentPane.add(panel1);
        contentPane.add(panel2);
        contentPane.add(panel3);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });

        timerThread = new TimerThread(timeLabel1, timeLabel2, timeLabel3);
        frame.setVisible(true);
    }
    public TimerThread timerThread;

    @Override
    public void run() {

        timerThread.start();


    }

    public void exitProcedure() {
        timerThread.setRunning(false);
        System.exit(0);
    }

    public static void main(String[] args) {
       // SwingUtilities.invokeLater(new GUI());
        GUI gui = new GUI();
        gui.run();
    }

    public static class TimerThread extends Thread {

        protected boolean isRunning;

        protected JLabel timelabel_moscow;
        protected JLabel timelabel_london;
        protected JLabel timelabel_NY;

        public TimerThread(JLabel timeLabel1, JLabel timeLabel2, JLabel timeLabel3) {
            this.timelabel_moscow = timeLabel1;
            this.timelabel_london = timeLabel2;
            this.timelabel_NY = timeLabel3;
            this.isRunning = true;
        }

        @Override
        public void run() {
            while (isRunning) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        TimeClass moscow = new TimeClass("Europe/Moscow");
                        TimeClass london = new TimeClass("Europe/London");
                        TimeClass NY = new TimeClass("America/New_York");
                        timelabel_moscow.setText(moscow.getTime());
                        timelabel_london.setText(london.getTime());
                        timelabel_NY.setText(NY.getTime());
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }

        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }
    }
}
