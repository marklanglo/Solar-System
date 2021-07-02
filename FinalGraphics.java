

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FinalGraphics extends JPanel
{
  private int    panelWidth   = 1850;
  private int    panelHeight  = 600;
  private int    panelCenterX = 925;
  private int    panelCenterY = 275;

  //============================================================================
  //Ball Coordinates - Tracks and instiates ball coordinates
  //============================================================================
  private int    sunWidth   = 70;
  private int    sunHeight  = 70;
  private int    sunRadiusX = sunWidth/2;
  private int    sunRadiusY = sunHeight/2;
  private double sunCenterX = panelCenterX;
  private double sunCenterY = panelCenterY;
  private double sunCornerX = (panelCenterX - sunWidth/2);
  private double sunCornerY = (panelCenterY - sunHeight/2);;

  private int    earthWidth   = 20;
  private int    earthHeight  = 20;
  private int    earthRadiusX = earthWidth/2;
  private int    earthRadiusY = earthHeight/2;
  private double earthCenterX = panelCenterX;
  private double earthCenterY = panelCenterY;
  private double earthCornerX = (panelCenterX - earthWidth/2);
  private double earthCornerY = (panelCenterY - earthHeight/2);
  private double earthOrbit   = 200 + sunRadiusX/2; //this would be 235
  private double marsOrbit    = earthOrbit + 40;
  private double moonOrbit    = 20;
  private double earthTheta;
  private double earthT       = 0;

  private int    marsWidth   = 17;
  private int    marsHeight  = 17;
  private int    marsRadiusX = marsWidth/2;
  private int    marsRadiusY = marsHeight/2;
  private double marsCenterX = panelCenterX;
  private double marsCenterY = panelCenterY;
  private double marsCornerX = (panelCenterX - marsWidth/2);
  private double marsCornerY = (panelCenterY - marsHeight/2);
  private double marsTheta;
  private double marsT       = 0;

  private int    moonWidth   = 6;
  private int    moonHeight  = 6;
  private int    moonRadiusX = moonWidth/2;
  private int    moonRadiusY = moonHeight/2;
  private double moonCenterX = panelCenterX;
  private double moonCenterY = panelCenterY;
  private double moonCornerX = (panelCenterX - moonWidth/2);
  private double moonCornerY = (panelCenterY - moonHeight/2);
  private double moonTheta;
  private double moonT       = 0;


  public void paintComponent(Graphics table)
  {

    super.paintComponent(table);
    setBackground(new Color(70, 57, 135)); //sets green background color to panel

    table.setColor(new Color(87, 168, 74));  //creates the earth ball
    table.fillOval((int)earthCornerX, (int)earthCornerY, earthWidth, earthHeight);

    table.setColor(new Color(232, 169, 21));  //creates the mars ball
    table.fillOval((int)marsCornerX, (int)marsCornerY, marsWidth, marsHeight);

    table.setColor(new Color(242, 240, 235));  //creates the moon ball
    table.fillOval((int)moonCornerX, (int)moonCornerY, moonWidth, moonHeight);

    table.setColor(new Color(236, 255, 66)); //creates the sun ball
    table.fillOval((int)sunCornerX, (int)sunCornerY, sunWidth, sunHeight);

  }//END - public void paintComponent(Graphics table)

  public void moveEarth()
  {
    earthT += earthTheta;

    earthCenterX = earthOrbit * Math.sin(earthT) + sunCenterX;
    earthCenterY = earthOrbit * Math.cos(earthT) + sunCenterY;
    earthCornerX = earthCenterX - earthWidth/2;
    earthCornerY = earthCenterY - earthWidth/2;
  }

  public void moveMars()
  {
    marsT += marsTheta;

    marsCenterX = marsOrbit * Math.sin(marsT) + sunCenterX;
    marsCenterY = marsOrbit * Math.cos(marsT) + sunCenterY;
    marsCornerX = marsCenterX - marsWidth/2;
    marsCornerY = marsCenterY - marsWidth/2;
  }

  public void moveMoon()
  {
    moonT += moonTheta;

    moonCenterX = moonOrbit * Math.sin(moonT) + earthCenterX;
    moonCenterY = moonOrbit * Math.cos(moonT) + earthCenterY;
    moonCornerX = moonCenterX - moonWidth/2;
    moonCornerY = moonCenterY - moonWidth/2;
  }

  public void setEarthTheta(double newTheta)
  {
    earthTheta = newTheta;
  }

  public double getEarthOrbit()
  {
    return earthOrbit;
  }

  public double getEarthX()
  {
    return earthCenterX;
  }

  public double getEarthY()
  {
    return earthCenterY;
  }

  public void setMarsTheta(double newTheta)
  {
    marsTheta = newTheta;
  }

  public double getMarsOrbit()
  {
    return marsOrbit;
  }

  public void setMoonTheta(double newTheta)
  {
    moonTheta = newTheta;
  }

  public double getMoonOrbit()
  {
    return moonOrbit;
  }
}
