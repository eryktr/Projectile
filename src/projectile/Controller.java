package projectile;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller
{
    private Model model;
    @FXML
    private Pane drawingPane;
    @FXML
    private TextField angle, initialSpeed, initialHeight, distanceCovered, currentHeight;
    private Circle object;
    private boolean drawPath;
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
                    if(model.getCurrentHeight() > 400)
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

        double initSpeed = Double.parseDouble(initialSpeed.getText());
        double initHeight = Double.parseDouble(initialHeight.getText());
        double initAngle = Double.parseDouble(angle.getText());
        model = new Model(400 - initHeight, initAngle, initSpeed);
        Circle object = new Circle(0, model.getInitialHeight(), 5);
        drawingPane.getChildren().add(object);
        this.object = object;
        Timer timer = new Timer();
        timer.start();



    }

    public void clear()
    {
        drawingPane.getChildren().clear();
    }

    public void updateAnimation()
    {
        object.setCenterX(model.getCurrentDisplacement());
        object.setCenterY(model.getCurrentHeight());
        int distInfo = (int)model.getCurrentDisplacement();
        String text1 = Integer.toString(distInfo);
        distanceCovered.setText(text1);
        currentHeight.setText(Integer.toString((int)(401 - model.getCurrentHeight())));
        if(400 - model.getCurrentHeight() < 0)
        {
            currentHeight.setText("0");
        }

        if(drawPath)
        {
            Circle point = new Circle(model.getCurrentDisplacement(), model.getCurrentHeight(), 1, Color.RED);
            drawingPane.getChildren().add(point);
        }
    }
}
