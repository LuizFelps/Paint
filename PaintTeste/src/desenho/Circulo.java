package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;

public class Circulo extends Desenhar implements Formas   {
	

	Circle circulo = new Circle();
	
	public void ponto1(GraphicsContext gc, ColorPicker colorPicker, Double pointx, Double pointy) {
		this.preencher(gc, colorPicker);
        circulo.setCenterX(pointx);
        circulo.setCenterY(pointy);	
	}
	
	public void ponto2(Double pointx, Double pointy, GraphicsContext gc) {
        circulo.setRadius((Math.abs(pointx - circulo.getCenterX()) + Math.abs(pointy - circulo.getCenterY())) / 2);

        circulo.setRadius((Math.abs(pointx - circulo.getCenterX()) + Math.abs(pointy - circulo.getCenterY())) / 2);

        if (circulo.getCenterX() > pointx) {
            circulo.setCenterX(pointx);
        }
        if (circulo.getCenterY() > pointy) {
            circulo.setCenterY(pointy);
        }

        gc.fillOval(circulo.getCenterX(), circulo.getCenterY(), circulo.getRadius(), circulo.getRadius());
        gc.strokeOval(circulo.getCenterX(), circulo.getCenterY(), circulo.getRadius(), circulo.getRadius());	
	}
}
