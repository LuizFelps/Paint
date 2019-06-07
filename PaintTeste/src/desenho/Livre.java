package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public class Livre {
	
	public void livre(ColorPicker colorPicker, GraphicsContext gc, double thickness, double x, double y){
		
        gc.setFill(colorPicker.getValue());
        gc.fillRect(x, y, thickness, thickness);
		
	}

}
