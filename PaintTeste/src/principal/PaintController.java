package principal;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import desenho.Circulo;
import desenho.Formas;
import desenho.Linha;
import desenho.Quadrado;
import desenho.Texto;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

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
    private void unDo() {

    }

    @FXML
    private void reDo() {

    }




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

    @FXML
    private void saveImage() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Salvar Imagem");
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

    public void initialize() {

        Formas quadrado = new Quadrado();
        Formas circulo = new Circulo();
        Linha linha = new Linha();
        Texto texto = new Texto();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double thickness = lineThickness.getValue();
            double x = e.getX() - thickness / 2;
            double y = e.getY() - thickness / 2;
            if (this.activeButton == pencil) {
                gc.setFill(colorPicker.getValue());
                gc.fillRect(x, y, thickness, thickness);
            } else if (this.activeButton == eraser) {
                gc.clearRect(x, y, thickness, thickness);
            }
        });

        canvas.setOnMousePressed(e -> {
            if (this.activeButton == rectangle) {
                quadrado.ponto1(gc, colorPicker, e.getX(), e.getY());

            } else if (this.activeButton == circle) {
                circulo.ponto1(gc, colorPicker, e.getX(), e.getY());

            } else if (this.activeButton == text) {
                texto.escrever(gc, lineThickness, textField, colorPicker, e.getX(), e.getY());

            } else if (this.activeButton == line) {
                linha.ponto1(gc, colorPicker, e.getX(), e.getY());
                
            }
        });

        canvas.setOnMouseReleased(e -> {
            if (this.activeButton == rectangle) {
                quadrado.ponto2(e.getX(), e.getY(), gc);

            } else if (this.activeButton == circle) {
                circulo.ponto2(e.getX(), e.getY(), gc);
            } else if (this.activeButton == line) {
                linha.ponto2(e.getX(), e.getY(), gc);
                
            }
        });
    }
}