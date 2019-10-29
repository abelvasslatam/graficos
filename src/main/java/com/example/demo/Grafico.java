package com.example.demo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

import com.orsonpdf.PDFDocument;
import com.orsonpdf.PDFGraphics2D;
import com.orsonpdf.Page;

public class Grafico {
	
	public static void main(String[] args) {
		JFreeChart chart = createChart(createDataset());

		PDFDocument pdfDoc = new PDFDocument();
		pdfDoc.setTitle("Tutor de Programacion");
		pdfDoc.setAuthor("carmelo");

		Page page = pdfDoc.createPage(new Rectangle(612, 468));
		PDFGraphics2D g2 = page.getGraphics2D();

		chart.draw(g2, new Rectangle(0, 0, 612, 468));

		pdfDoc.writeToFile(new File("E:\\JFreeChart-PDF.pdf"));
	}

	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("OpenCV 3.x", new Double(40.0));
		dataset.setValue("Java", new Double(20.0));
		dataset.setValue("Python 3.x", new Double(20.0));
		dataset.setValue("OpengGL", new Double(10.5));
		dataset.setValue("Win32", new Double(5.25));
		dataset.setValue(".NET", new Double(5.25));
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart("TUTOR DE PROGRAMACION", dataset, false, false, false);

		chart.setBackgroundPaint(
				new GradientPaint(new Point(0, 0), new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

		TextTitle t = chart.getTitle();
		t.setHorizontalAlignment(HorizontalAlignment.CENTER);
		t.setPaint(new Color(240, 240, 240));
		t.setFont(new Font("Arial", Font.BOLD, 26));

		PiePlot plot = (PiePlot) chart.getPlot();

		plot.setBackgroundPaint(null);
		plot.setInteriorGap(0.04);
		plot.setOutlineVisible(false);
		plot.setShadowPaint(null);
		plot.setLabelShadowPaint(null);
		plot.setBaseSectionOutlinePaint(Color.WHITE);
		plot.setSectionOutlinesVisible(true);
		plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));
		plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
		plot.setLabelLinkPaint(Color.WHITE);
		plot.setLabelLinkStroke(new BasicStroke(2.0f));
		plot.setLabelOutlineStroke(null);
		plot.setLabelPaint(Color.WHITE);
		plot.setLabelBackgroundPaint(null);

		TextTitle url = new TextTitle("Blog Oficial: http://acodigo.blospot.com",
				new Font("Courier New", Font.PLAIN, 12));
		url.setPaint(Color.WHITE);
		url.setPosition(RectangleEdge.BOTTOM);
		url.setHorizontalAlignment(HorizontalAlignment.RIGHT);

		TextTitle description = new TextTitle("Los tutoriales mas frecuentes en nuestro blog",
				new Font("Arial", Font.PLAIN, 18));
		description.setPaint(Color.LIGHT_GRAY);
		description.setPosition(RectangleEdge.TOP);
		description.setHorizontalAlignment(HorizontalAlignment.LEFT);

		chart.addSubtitle(description);
		chart.addSubtitle(url);

		return chart;
	}
}