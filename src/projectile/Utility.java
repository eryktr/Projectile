package projectile;

import javafx.scene.control.TextField;

public class Utility
{
    public static double getValue(TextField textField)
    {
        return Double.parseDouble(textField.getText());
    }

}
