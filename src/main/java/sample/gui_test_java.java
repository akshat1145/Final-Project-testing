package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;

public class gui_test_java extends JFrame implements ActionListener {
    String[] messageStings = {"Show Individual Stats", "Show stats by month", "Display Graphs - Buggy"};
    JComboBox<String> cmbMessageList = new JComboBox<>(messageStings);

    JComboBox<Object> comboBox;
    JLabel lblText = new JLabel();
    public gui_test_java() {


        setLayout(new FlowLayout());
        setSize(400,300);
        setTitle("Hello to Milk Weights Program");
       // JLabel lblText = new JLabel();
        lblText.setText("Hello to Milk Weights Program");
        lblText.setHorizontalTextPosition(JLabel.LEFT);
        lblText.setVerticalTextPosition(JLabel.BOTTOM);


        cmbMessageList.addActionListener(this);
        add(cmbMessageList);
        add(lblText);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
        if(actionEvent.getSource() == cmbMessageList) {
            JComboBox cb = (JComboBox)actionEvent.getSource();
            String msg = (String)cb.getSelectedItem();
            FileInputStream fstream = new FileInputStream("src/main/java/sample/data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String line;

            switch (msg) {
                case "Show Individual Stats" : {
                        HashSet<String> customers = new HashSet<>();

                        while ((line = br.readLine()) != null) {
                            String[] arr = line.split(",");
                            if (!customers.contains(arr[3])) {
                                customers.add(arr[3]);

                            }
                        }
                        String[] customerID = new String[customers.size()+1];
                        int i = 0;
                        for (String cust : customers) {
                            customerID[i] = cust;
                            i++;
                        }
                        fstream.close();

                        showMonthAvg showMonthavg = new showMonthAvg(customerID);
                        showMonthavg.setVisible(true);

                } break;
                case "Show stats by month" : {
                    HashSet<String> months = new HashSet<>();

                    while ((line = br.readLine()) != null) {
                        String[] arr = line.split(",");
                        if (!months.contains(arr[1])) {
                            months.add(arr[1]);

                        }
                    }
                    String[] month = new String[months.size()+1];
                    int i = 0;
                    for (String cust : months) {
                        month[i] = cust;
                        i++;
                    }
                    fstream.close();

                    TotalStats totalStats = new TotalStats(month, br);
                    totalStats.setVisible(true);

                    this.dispose();


                }
                break;
                case "Display Graphs - Buggy" : {
                    lblText.setText("We can generate the graphs here instead of showing data in a table form. This is buggy and may or maynot work.");
//                    display graph
                    DisplayGraph displayGraph = new DisplayGraph();
                    displayGraph.startMain();
                    //display image
                    JLabel lblText = new JLabel();
                    Object[] items =
                            {
                                    new ImageIcon("src/main/java/sample/sample2.png")

                            };

                    comboBox = new JComboBox<>(items);
                    getContentPane().add(comboBox, BorderLayout.NORTH);
                    JFrame frame = new gui_test_java();
                    frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
                    frame.pack();
                    frame.setLocationRelativeTo( null );
                    frame.setVisible( true );
                }
                break;
                default: {lblText.setText("Error");}
            }

        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
