package de.dominikemmel.reflowlab.controller.ragonetool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ui.Align;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterPlotRagone extends JPanel {

	public JFreeChart ScatterPlotRagone(String title, double xValue, double yValue) {

	    // Create dataset
	    XYDataset dataset = createDataset(xValue, yValue);

	    // Create chart
	    JFreeChart chart = ChartFactory.createScatterPlot(
	    		title,
	        "xAxisLabel", "yAxisLabel", dataset, PlotOrientation.HORIZONTAL, true, true, true);
	    chart.removeLegend();
	    chart.setBackgroundPaint(Color.white);

	    //Changes background
	    XYPlot plot = (XYPlot)chart.getPlot();

	    Image image = new ImageIcon(this.getClass().getResource("/de/dominikemmel/reflowlab/controller/ragonetool/img/ragone_plot.png")).getImage();
//	    plot.setBackgroundImageAlpha(1f);
	    plot.setBackgroundImageAlignment(Align.FIT);
		plot.setBackgroundImage(image);

		java.awt.Color transparent = new java.awt.Color(1.0f, 1.0f, 1.0f, 0f);
		chart.getPlot().setBackgroundPaint(transparent);
//		chart.getPlot().setOutlineVisible(false);
		chart.getXYPlot().setRangeGridlinesVisible(false);
		chart.getXYPlot().setDomainGridlinesVisible(false);

	    //Logarithm Axis:
	    LogarithmicAxis yAxis = new LogarithmicAxis("specific Power / W kg\u207B\u00B9");
//	    LogAxis yAxis = new LogAxis("");
//	    yAxis.setBase(10);
//	    LogFormat format = new LogFormat(yAxis.getBase(), "", "", true);
//	    yAxis.setNumberFormatOverride(format);
	    yAxis.setRange(1,100000);

	    //x Axis:
	    NumberAxis xAxis = new NumberAxis("specific Energy / Wh kg\u207B\u00B9");
	    xAxis.setRange(0,200);

		plot.setRangeAxis(yAxis);
		plot.setDomainAxis(xAxis);

		Ellipse2D circle = new Ellipse2D.Double(-3.0, -3.0, 10.0, 10.0);
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setSeriesShape(0, circle);


	    // Create Panel
	    ChartPanel panel = new ChartPanel(chart);
	    add(panel, BorderLayout.CENTER);

		return chart;
	}


	//create data:
	  private XYDataset createDataset(double xValue, double yValue) {
		    XYSeriesCollection dataset = new XYSeriesCollection();

		    XYSeries series1 = new XYSeries("value1");
		    series1.add(xValue, yValue);

		    dataset.addSeries(series1);

		    return dataset;
		  }

}
