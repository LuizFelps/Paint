package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;

public class Circulo extends Desenhar implements Formas   {

	Circle circ = new Circle();
	
	public void ponto1(GraphicsContext gc, ColorPicker colorPicker, Double pointx, Double pointy) {
		this.preencher(gc, colorPicker);
        circ.setCenterX(pointx);
        circ.setCenterY(pointy);
		
	}
	
	public void ponto2(Double pointx, Double pointy, GraphicsContext gc) {
        circ.setRadius((Math.abs(pointx - circ.getCenterX()) + Math.abs(pointy - circ.getCenterY())) / 2);

        circ.setRadius((Math.abs(pointx - circ.getCenterX()) + Math.abs(pointy - circ.getCenterY())) / 2);

        if (circ.getCenterX() > pointx) {
            circ.setCenterX(pointx);
        }
        if (circ.getCenterY() > pointy) {
            circ.setCenterY(pointy);
        }

        gc.fillOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(), circ.getRadius());
        gc.strokeOval(circ.getCenterX(), circ.getCenterY(), circ.getRadius(), circ.getRadius());	
		
	}

}
