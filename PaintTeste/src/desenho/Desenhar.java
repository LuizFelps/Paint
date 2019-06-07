package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public abstract class Desenhar {
	/*---Quadrado, Circulo e Texto estender�o essa classe ---*/
	/*---pois todos dever�o ser solidos(preenchidos)---*/
	
	public void preencher(GraphicsContext gc, ColorPicker colorPicker) {
        gc.setStroke(colorPicker.getValue());
        gc.setFill(colorPicker.getValue());
	}
	
}
