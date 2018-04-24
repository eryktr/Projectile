package projectile;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Controller
{
    private Model model;
    @FXML
    private Pane drawingPane;
    @FXML
    private TextField angle, initialSpeed, initialHeight, distanceCovered, currentHeight, timeField, dragCoefficient, mass;
    @FXML
    private Button clearBtn;
    private Circle object;
    private boolean drawPath;
    private Color pathColor;
    private double time;
    private class Timer extends AnimationTimer
    {

        @Override
        public synchronized void handle(long now)
        {
                try
                {
                    double dt = model.get_dt();
                    wait((long) (1000 * dt));
                    model.update();
                    updateAnimation();
                    if(object.getCenterY() > 400)
                    {
                        this.stop();
                    }
                }
                catch (InterruptedException ex) { }
            }
    }

    public void changeDrawingMode()
    {
        drawPath = !drawPath;
    }

    public void startSimulation()
    {
        time = 0;
        pathColor = getRandomColor();
        double initSpeed = Utility.getValue(initialSpeed);
        double initHeight = Utility.getValue(initialHeight);
        double initAngle = Utility.getValue(angle);
        double dragCoeff = Utility.getValue(dragCoefficient);
        double mass = Utility.getValue(this.mass);
        model = new Model(400 - initHeight, initAngle, initSpeed, dragCoeff, mass);
        Circle object = new Circle(0, model.getInitialHeight(), 3);
        drawingPane.getChildren().add(object);
        this.object = object;
        clearBtn.setDisable(true);
        Timer timer = new Timer();
        timer.start();
    }

    public void clear()
    {
        drawingPane.getChildren().clear();
    }

    public void updateAnimation()
    {
        time += model.get_dt();
        object.setCenterX(model.getCurrentDisplacement());
        object.setCenterY(model.getCurrentHeight());
        int distInfo = (int)model.getCurrentDisplacement();
        String text1 = Integer.toString(distInfo);
        distanceCovered.setText(text1);
        currentHeight.setText(Integer.toString((int)(401 - model.getCurrentHeight())));
        timeField.setText(Double.toString((double)Math.round(100*time)/100));

        if(400 - model.getCurrentHeight() < 0)
        {
            currentHeight.setText("0");
            clearBtn.setDisable(false);
        }

        if(drawPath)
        {
            Circle point = new Circle(model.getCurrentDisplacement(), model.getCurrentHeight(), 1, pathColor);
            drawingPane.getChildren().add(point);
        }

    }

    public Color getRandomColor()
    {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);
        return Color.rgb(red, green, blue);
    }
}
