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

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class DisplayGraph extends Application {
    public int avg() {
        int avg = 0;
        try {
            FileInputStream fstream = new FileInputStream("/Users/akshat./Desktop/UW/CS739/Final-Project-testing/src/main/java/sample/data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String strLine;
            String line = "";
            StringTokenizer st = null;
            int lineNumber = 0;
            int tokenNumber = 0;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                System.out.println(arr[0]+" " + arr[1] + "---");
                lineNumber++;

            }
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
            }
            fstream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return avg;
    }

    @Override
    public void start(Stage primaryStage) {

        DefaultStatisticalCategoryDataset dataset = new DefaultStatisticalCategoryDataset();
        dataset.add(1, 0.1, "series", "A");
        dataset.add(2, 0.4, "series", "B");
        dataset.add(2, 0.2, "series", "C");

        // System.out.println(avg());

        JFrame f = new JFrame("Graph Plot");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CategoryAxis domain = new CategoryAxis();
        ValueAxis range = new NumberAxis();
        StatisticalLineAndShapeRenderer renderer = new StatisticalLineAndShapeRenderer(true, true);
        CategoryPlot plot = new CategoryPlot(dataset, domain, range, renderer);

        JFreeChart chart = new JFreeChart(
                "ErrorBars", JFreeChart.DEFAULT_TITLE_FONT, plot, false);

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

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

//        saving the image
//        Container content = f.getContentPane();
//        BufferedImage image = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = image.createGraphics();
//        content.printAll(g2d);
//        g2d.dispose();
//        String  fileName = "/Users/akshat./Desktop/image5.png";
//        ImageIO.write ( image, "png", new File(fileName) );


    }

    public void startMain() {
        launch();
    }

}
