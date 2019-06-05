package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class Desenhar {
	
	public void preencher(GraphicsContext gc, ColorPicker colorPicker) {
        gc.setStroke(colorPicker.getValue());
        gc.setFill(colorPicker.getValue());
	}
	
}
