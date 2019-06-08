package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

public interface Formas {
	/*---Quadrado, Circulo e Linha implementarão essa interface---*/
	/*---pois todos eles tem os dois pontos---*/
	
	public void ponto1(GraphicsContext gc, ColorPicker colorPicker, Double pointx, Double pointy);
	
	public void ponto2(Double pointx, Double pointy, GraphicsContext gc);
	
}
