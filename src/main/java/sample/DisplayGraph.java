package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer;
import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class DisplayGraph extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
        dataset.add(1, 0.1, "series", "FARM 1");
        dataset.add(2, 0.4, "series", "FARM 2");
        dataset.add(2, 0.2, "series", "FARM 3");

        // System.out.println(avg());

        JFrame f = new JFrame("Graph Plot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CategoryAxis domain = new CategoryAxis();
        ValueAxis range = new NumberAxis();
        StatisticalLineAndShapeRenderer renderer = new StatisticalLineAndShapeRenderer(true, true);
        CategoryPlot plot = new CategoryPlot(dataset, domain, range, renderer);

        JFreeChart chart = new JFreeChart(
                "Avg Milk Production in X100 Gallons. ", JFreeChart.DEFAULT_TITLE_FONT, plot, false);

        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",
                NumberFormat.getNumberInstance()));

        renderer.setDefaultItemLabelsVisible(true);

        renderer.setSeriesShape(0, new Rectangle2D.Double(0, 0, 0, 0));
        new StandardChartTheme("JFree").apply(chart);
        f.add(new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 300);
            }
        });

        //f.setContentPane();

//        saving the image
        Container content = f.getContentPane();
        BufferedImage image = new BufferedImage(content.getWidth()+1, content.getHeight()+1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        content.printAll(g2d);
        g2d.dispose();
        String  fileName = "src/main/java/sample/sample2.png";
        ImageIO.write ( image, "png", new File(fileName) );

        f.pack();
//        f.setVisible(false);
    }

    public void startMain() {
        launch();
    }

}
