package de.dominikemmel.reflowlab.controller.costanalysistool;

import java.awt.geom.Rectangle2D;

import org.jfree.chart.JFreeChart;
import org.jfree.fx.FXGraphics2D;

import javafx.scene.canvas.Canvas;

public class ChartCanvasJFreeChart extends Canvas {

    JFreeChart chart;
    private FXGraphics2D graphics2D;

    public ChartCanvasJFreeChart(JFreeChart chart) {
        this.chart = chart;
        this.graphics2D = new FXGraphics2D(getGraphicsContext2D());
        // Redraw canvas when size changes.
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(evt -> draw());

    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();
        getGraphicsContext2D().clearRect(0, 0, width, height);
        this.chart.draw(this.graphics2D, new Rectangle2D.Double(0, 0, width, height));
        //(this.graphics2D,, new Rectangle2D.Double(0, 0, width, height));
    }
}