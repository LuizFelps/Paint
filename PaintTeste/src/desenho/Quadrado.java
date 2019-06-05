package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class Quadrado extends Desenhar implements Formas {
	
	Rectangle quadrado = new Rectangle();
	
	public void ponto1(GraphicsContext gc, ColorPicker colorPicker, Double pointx, Double pointy) {
		this.preencher(gc, colorPicker);
        quadrado.setX(pointx);
        quadrado.setY(pointy);
		
	}
	
	public void ponto2(Double pointx, Double pointy, GraphicsContext gc) {
        quadrado.setWidth(Math.abs((pointx - quadrado.getX())));
        quadrado.setHeight(Math.abs((pointy - quadrado.getY())));

        if (quadrado.getX() > pointx) {
            quadrado.setX(pointx);
        }
        if (quadrado.getY() > pointy) {
            quadrado.setY(pointy);
        }

        gc.fillRect(quadrado.getX(), quadrado.getY(), quadrado.getWidth(), quadrado.getHeight());
        gc.strokeRect(quadrado.getX(), quadrado.getY(), quadrado.getWidth(), quadrado.getHeight());
		
	}
}
