package principal;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import desenho.Circulo;
import desenho.Formas;
import desenho.Linha;
import desenho.Livre;
import desenho.Quadrado;
import desenho.Texto;

import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class PaintController {

    private static Stage stage;

    private Button activeButton;
    
    @FXML
    private StackPane imageScreen;

    @FXML
    private Canvas canvas;

    @FXML
    private ImageView imageView;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider lineThickness;

    @FXML
    private TextField textField;

    @FXML
    private Button pencil;

    @FXML
    private Button line;

    @FXML
    private Button circle;

    @FXML
    private Button rectangle;

    @FXML
    private Button text;

    @FXML
    private Button eraser;

    @FXML
    private Button undo;

    @FXML
    private Button redo;
    
    @FXML
    private Button fill;

    /*---Verificando qual botão será pressionado---*/
    
    @FXML
    private void setPencilButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.pencil;
        this.pencil.setStyle("-fx-background-color: green;");
    }

    @FXML
    private void setLineButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.line;
        this.line.setStyle("-fx-background-color: green;");
    }

    @FXML
    private void setCircleButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.circle;
        this.circle.setStyle("-fx-background-color: green;");
    }

    @FXML
    private void setRectangleButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.rectangle;
        this.rectangle.setStyle("-fx-background-color: green;");
    }

    @FXML
    private void setTextButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.text;
        this.text.setStyle("-fx-background-color: green;");
    }

    @FXML
    private void setEraserButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.eraser;
        this.eraser.setStyle("-fx-background-color: green;");
    }
    
    @FXML
    private void setFillButtonAsActive() {
        if (this.activeButton != null) this.activeButton.setStyle(null);
        this.activeButton = this.fill;
        this.fill.setStyle("-fx-background-color: green");
    }

    
    @FXML
    private void unDo() {

    }

    @FXML
    private void reDo() {

    }

    /*---Abrindo imagem---*/
    @FXML
    private void openImage() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpeg", "*.jpg"));
        File file = fc.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setPreserveRatio(true);
            imageView.setImage(image);
            imageView.setSmooth(true);
            imageView.setCache(true);
            stage.setTitle(file.getName() + " - SuperPaint");
        }
    }

    /*---Salvando imagem (.jpg, .png)---*/
    @FXML
    private void saveImage() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Salvar Imagem");
        fc.setInitialFileName("Imagem");
        fc.getExtensionFilters().addAll(new ExtensionFilter("*.jpg", "*.jpg"), new ExtensionFilter("*.png", "*.png"));
        File file = fc.showSaveDialog(stage);
        if (file != null) {
            WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            imageScreen.snapshot(null, wi);
            RenderedImage ri = SwingFXUtils.fromFXImage(wi, null);
            try {
                ImageIO.write(ri, "png", file);
            } catch (IOException e) {
                System.out.println("Nao foi possivel salvar a imagem!");
            }
        }
    }

    /*---Aba "sobre"---*/
    @FXML
    private void openAboutWindow() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
        stage.setTitle("Sobre - SuperPaint");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

    public static void setStage(Stage stg) {
        stage = stg;
    }
    
    private void floodFill(WritableImage writableImage, int x, int y, Color baseColor) {

        // verifica se o eixo x ultrapassa o limite da tela.
        if (x < 0 || x > writableImage.getWidth() - 1)
            return;
        // verifica se o eixo y ultrapassa o limite da tela.
        if (y < 0 || y > writableImage.getHeight() - 1)
            return;
        // verifica se a cor a ser substituida eh a mesma que vai substituir.
        if (baseColor.equals(colorPicker.getValue()))
            return;

        writableImage.getPixelWriter().setColor(x, y, colorPicker.getValue());

        Queue<Pixel> queue = new LinkedList<>();

        Pixel p = new Pixel(x, y, colorPicker.getValue());

        queue.add(p);

        while (queue.peek() != null) {

            Pixel pixel = queue.remove();

            if (!(pixel.getX() - 1 < 0 || pixel.getX() - 1 >= (int) writableImage.getWidth()) && writableImage.getPixelReader().getColor(pixel.getX() - 1, pixel.getY()).equals(baseColor)) {
                Pixel westPixel = new Pixel(pixel.getX() - 1, pixel.getY(), colorPicker.getValue());
                writableImage.getPixelWriter().setColor(westPixel.getX(), westPixel.getY(), westPixel.getColor());
                queue.add(westPixel);
            }
            if (!(pixel.getX() + 1 < 0 || pixel.getX() + 1 >= (int) writableImage.getWidth()) && writableImage.getPixelReader().getColor(pixel.getX() + 1, pixel.getY()).equals(baseColor)) {
                Pixel eastPixel = new Pixel(pixel.getX() + 1, pixel.getY(), colorPicker.getValue());
                writableImage.getPixelWriter().setColor(eastPixel.getX(), eastPixel.getY(), eastPixel.getColor());
                queue.add(eastPixel);
            }
            if (!(pixel.getY() + 1 < 0 || pixel.getY() + 1 >= (int) writableImage.getHeight()) && writableImage.getPixelReader().getColor(pixel.getX(), pixel.getY() + 1).equals(baseColor)) {
                Pixel northPixel = new Pixel(pixel.getX(), pixel.getY() + 1, colorPicker.getValue());
                writableImage.getPixelWriter().setColor(northPixel.getX(), northPixel.getY(), northPixel.getColor());
                queue.add(northPixel);
            }
            if (!(pixel.getY() - 1 < 0 || pixel.getY() - 1 >= (int) writableImage.getHeight()) && writableImage.getPixelReader().getColor(pixel.getX(), pixel.getY() - 1).equals(baseColor)) {
                Pixel southPixel = new Pixel(pixel.getX(), pixel.getY() - 1, colorPicker.getValue());
                writableImage.getPixelWriter().setColor(southPixel.getX(), southPixel.getY(), southPixel.getColor());
                queue.add(southPixel);
            }
        }

        return;
    }

    public void initialize() {
    	
    	/*---Inicializando os desenhos---*/
    	Livre livre = new Livre();
        Formas quadrado = new Quadrado();
        Formas circulo = new Circulo();
        Formas linha = new Linha();
        Texto texto = new Texto();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double thickness = lineThickness.getValue();
            double x = e.getX() - thickness / 2;
            double y = e.getY() - thickness / 2;
            /*---Desenho livre---*/
            if (this.activeButton == pencil) {
               	livre.livre(colorPicker, gc, thickness, x, y); 
               	
            }/*---Borracha---*/ 
            else if (this.activeButton == eraser) {
                gc.clearRect(x, y, thickness, thickness);
            }
        });

        
        canvas.setOnMousePressed(e -> {
        	
            if (this.activeButton == rectangle) {
            	/*---Pegando o primeiro ponto - Quadrado---*/
            	quadrado.ponto1(gc, colorPicker, e.getX(), e.getY());

            } else if (this.activeButton == circle) {
            	/*---Pegando o primeiro ponto - Circulo---*/
            	circulo.ponto1(gc, colorPicker, e.getX(), e.getY());

            } else if (this.activeButton == text) {
            	/*---Quando clicado aparecera o texto por isso só um ponto---*/
            	texto.escrever(gc, lineThickness, textField, colorPicker, e.getX(), e.getY());

            } else if (this.activeButton == line) {
            	/*---Pegando o primeiro ponto - Linha/Reta---*/
            	linha.ponto1(gc, colorPicker, e.getX(), e.getY());
            	
            }else if (this.activeButton == fill) {
                WritableImage wi = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(new SnapshotParameters(), wi);
                gc.setFill(colorPicker.getValue());
                floodFill(wi, (int) e.getX(), (int) e.getY(), wi.getPixelReader().getColor((int) e.getX(), (int) e.getY()));
                canvas.getGraphicsContext2D().drawImage(wi, 0, 0);
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (this.activeButton == rectangle) {
            	/*---Pegando o segundo ponto - Quadrado---*/
            	quadrado.ponto2(e.getX(), e.getY(), gc);

            } else if (this.activeButton == circle) {
            	/*---Pegando o segundo ponto - Circulo---*/
               	circulo.ponto2(e.getX(), e.getY(), gc);
            } else if (this.activeButton == line) {
            	/*---Pegando o segundo ponto - Linha/Reta---*/
            	linha.ponto2(e.getX(), e.getY(), gc);
            	
            }
        });
    }
}