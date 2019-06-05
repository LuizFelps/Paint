package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Line;

public class Linha implements Formas{
	
	Line linha = new Line(); 
	
	public void ponto1(GraphicsContext gc, ColorPicker colorPicker, Double pointx, Double pointy) {
        gc.setStroke(colorPicker.getValue());
        linha.setStartX(pointx);
        linha.setStartY(pointy);
		
	}
	
	public void ponto2(Double pointx, Double pointy, GraphicsContext gc) {
        linha.setEndX(pointx);
        linha.setEndY(pointy);
        gc.strokeLine(linha.getStartX(), linha.getStartY(), linha.getEndX(), linha.getEndY());
        
		
	}
}
