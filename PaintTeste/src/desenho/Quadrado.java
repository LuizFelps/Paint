package desenho;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Rectangle;

public class Quadrado extends Desenhar implements Formas {
	
	Rectangle rect = new Rectangle();
	
	public void ponto1(GraphicsContext gc, ColorPicker colorPicker, Double pointx, Double pointy) {
		this.preencher(gc, colorPicker);
        rect.setX(pointx);
        rect.setY(pointy);
		
	}
	
	public void ponto2(Double pointx, Double pointy, GraphicsContext gc) {
        rect.setWidth(Math.abs((pointx - rect.getX())));
        rect.setHeight(Math.abs((pointy - rect.getY())));

        if (rect.getX() > pointx) {
            rect.setX(pointx);
        }
        if (rect.getY() > pointy) {
            rect.setY(pointy);
        }

        gc.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
        gc.strokeRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		
	}
	
	

}
