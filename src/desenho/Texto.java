package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class Texto extends Desenhar{
	
	public void escrever(GraphicsContext gc, Slider lineThickness, TextField textField, ColorPicker colorPicker, Double pointx, Double pointy){
        gc.setFont(Font.font(lineThickness.getValue()));
        this.preencher(gc, colorPicker);
        gc.fillText(textField.getText(), pointx, pointy);
        gc.strokeText(textField.getText(), pointx, pointy);
		
		
	}

}
