

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.text.DecimalFormat;

public class FinalUI extends JFrame{

  //============================================================================
  //Panel 1 - This includes two JLabels to display the title and creator name.
  //============================================================================
  private JLabel systemName;  //"CS223 Final"
  private JLabel authorName;  //"by Mark Wiedeman"

  //Panel 2 has no additional variables because it is the graphics panel

  //============================================================================
  //Panel 3 - This panel contains all interactable items.
  //============================================================================

  //JLabels
  private JLabel earthSpeed;
  private JLabel earthLabelXY;

  //JButtons
  private JButton pauseButton;
  private JButton startButton;
  private JButton exitButton;

  //JTextFields
  private JTextField enterEarthSpeed;
  private JTextField enterEarthX;
  private JTextField enterEarthY;

  private double earthX;
  private double earthY;


  //============================================================================
  //Panels:
  // Panel 1: Display Panel
  // Panel 2: Graphic Panel
  // Panel 3: Control Panel
  //============================================================================
  private JPanel            panel1;
  private FinalGraphics     panel2;
  private JPanel            panel3;


  //============================================================================
  //UI limits - these are constants to adjust the UI size of the JFrame
  //============================================================================
  private int UI_WIDTH  = 1850;    //User Interface Width
  private int UI_HEIGHT = 925;     //User Interface Height

  //============================================================================
  //Clocks - This section is for the refresh and any motion clocks
  //============================================================================
  private clockHandler clockHandler;

  private double milliPerSecond    = 1000.0;  //milliseconds per second

  //refresh clock
  private Timer  refreshClock;                //This clock refreshes the entire panel
  private double refreshClockRate  = 100;      //default refresh rate in Hz
  private int    refreshClockDelay;           //used to instantiate refreshClock

  //earth clock
  private Timer  motionClock;            //This clock refreshes the earth position
  private double motionClockRate  = 80;     //default motion clock rate in Hz for earth
  private int    motionClockDelay;           //used to instantiate mMotionClock

  //============================================================================
  //Direction and Position - All of these are variables used to dictate position
  //============================================================================
  private double earthPixPerTic;              //speed used in FinalGraphics for earth
  private double earthPixPerSecond;           //used to calculate pixPerTic for earth

  private double earthOrbit;
  private double marsOrbit;
  private double moonOrbit;
  private double earthTheta;
  private double marsTheta;
  private double moonTheta;


  private String errorString = "ERROR: An Entered Value was INVALID"; //default error

  public FinalUI(){

    super("Final Program");

    //This sets the UI size and disables resizing the UI
    setLayout(null);
    setSize(UI_WIDTH, UI_HEIGHT);
    setResizable(false);            //locks the window size

    //==========================================================================
    //creates first Panel
    panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());
    panel1.setBackground(new Color(235, 183, 209));

    //initializes first panel object
    systemName = new JLabel("CS223 Final");
    authorName = new JLabel("by Mark Wiedeman");
    systemName.setToolTipText("CS223 Final");
    authorName.setToolTipText("by Mark Wiedeman");

    //adds JLabel to the center of the panel
    systemName.setHorizontalAlignment(JLabel.CENTER);
    authorName.setHorizontalAlignment(JLabel.CENTER);
    panel1.add(systemName, BorderLayout.PAGE_START);
    panel1.add(authorName, BorderLayout.CENTER);
    //==========================================================================

    //==========================================================================
    //creates second Panel
      panel2 = new FinalGraphics();

    //==========================================================================

    //==========================================================================
    //creates third panel
    panel3 = new JPanel();
    panel3.setLayout(null);
    panel3.setBackground(new Color(235, 183, 209));

    //JLabel
    earthSpeed = new JLabel("Input Earth Linear speed pix/sec");
    earthLabelXY = new JLabel("X & Y coordinates of Earth in Java Coordinate System");

    //JButtons
    startButton = new JButton("Start");
    pauseButton = new JButton("Pause");
    exitButton = new JButton("Exit");

    //JTextFields
    enterEarthSpeed = new JTextField(10);
    enterEarthX = new JTextField(5);
    enterEarthY = new JTextField(5);

    enterEarthX.setEditable(false);
    enterEarthY.setEditable(false);

    buttonHandler myHandler = new buttonHandler();
    startButton.addActionListener(myHandler);
    pauseButton.addActionListener(myHandler);
    exitButton.addActionListener(myHandler);

    //locations
    startButton.setBounds(200, 30, 150, 50);
    pauseButton.setBounds(500, 30, 150, 50);
    exitButton.setBounds(700, 30, 150, 50);

    earthSpeed.setBounds(200, 100, 300, 50);
    earthLabelXY.setBounds(500, 100, 400, 50);
    enterEarthSpeed.setBounds(200, 140, 150, 30);
    enterEarthX.setBounds(500, 140, 150, 30);
    enterEarthY.setBounds(700, 140, 150, 30);

    panel3.add(startButton);
    panel3.add(pauseButton);
    panel3.add(exitButton);
    panel3.add(earthSpeed);
    panel3.add(earthLabelXY);
    panel3.add(enterEarthSpeed);
    panel3.add(enterEarthX);
    panel3.add(enterEarthY);
    //==========================================================================

    //==========================================================================
    //combines panels on the frame
    add(panel1);
    add(panel2);
    add(panel3);

    panel1.setBounds(0,   0, UI_WIDTH, 50);
    panel2.setBounds(0,  50, UI_WIDTH, 600);
    panel3.setBounds(0, 650, UI_WIDTH, (UI_HEIGHT - 650));
    //==========================================================================

    //creates clockhandler
    clockHandler = new clockHandler();

    //instantiates refreshClock
    refreshClockDelay  = (int)Math.round(milliPerSecond / refreshClockRate);
    refreshClock       = new Timer(refreshClockDelay, clockHandler);

    //instantiates MotionClock
    motionClockDelay  = (int)Math.round(milliPerSecond / motionClockRate);
    motionClock   = new Timer(motionClockDelay, clockHandler);

  }

  private class buttonHandler implements ActionListener{
    public void actionPerformed(ActionEvent event){

      if(event.getSource() == startButton)
      {
        try{

        earthOrbit = panel2.getEarthOrbit();
        marsOrbit  = panel2.getMarsOrbit();
        moonOrbit  = panel2.getMoonOrbit();

        earthPixPerSecond = Double.parseDouble(enterEarthSpeed.getText());
        earthPixPerTic = earthPixPerSecond / motionClockRate;

        earthTheta = (earthPixPerTic)/earthOrbit;
        marsTheta  = (earthPixPerTic*0.59)/marsOrbit;
        moonTheta  = (earthPixPerTic*2)/moonOrbit;
        panel2.setEarthTheta(earthTheta);
        panel2.setMarsTheta(marsTheta);
        panel2.setMoonTheta(moonTheta);

        refreshClock.start();
        motionClock.start();
        pauseButton.setText("Pause");
        }
        catch(Exception InvalidInput)
        {
          //invalid input error
          errorString = "ERROR: An invalid input has been entered AND/OR a field has been left empty,"
                      + "\n please avoid symbols (!@#$%^&) and scientific"
                      + "\n notation.  Values entered as '123.12' are valid"
                      + "\n while values such as '1/2', '5*10^3', or alternate"
                      + "\n number formats will not work.";

          JOptionPane.showMessageDialog(null, errorString);
        }

      }
      else if(event.getSource() == pauseButton)
      {
        if(pauseButton.getText() == "Pause")
        {
          refreshClock.stop();
          motionClock.stop();

          pauseButton.setText("Resume");
        }
        else if(pauseButton.getText() == "Resume")
        {
          refreshClock.start();
          motionClock.start();

          pauseButton.setText("Pause");
        }
      }
      else if(event.getSource() == exitButton)
      {
        System.exit(0); //simply closes the program
      }

    }

  }

  private class clockHandler implements ActionListener{
    //==========================================================================
    //Function: "actionPerformed"
    //==========================================================================
    //
    //==========================================================================
    public void actionPerformed(ActionEvent event){

      //checks which clock is being used
      if(event.getSource() == refreshClock)
      {
        panel2.repaint(); //repaints the graphics panel

      }
      else if(event.getSource() == motionClock)
      {

        DecimalFormat numberFormat = new DecimalFormat("0.00");
        enterEarthX.setText(numberFormat.format(panel2.getEarthX()));
        enterEarthY.setText(numberFormat.format(panel2.getEarthY()));

        panel2.moveEarth();
        panel2.moveMoon();
        panel2.moveMars();

      }

    }
  }

}
