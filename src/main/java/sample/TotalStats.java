package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.util.HashMap;


public class TotalStats extends JFrame implements ActionListener {
    String[] customerID;
    BufferedReader br;
    JComboBox<String> cmbMessageList;
    JComboBox<Object> comboBox;
    JLabel lblText = new JLabel();
    public TotalStats(String[] customerIDd, BufferedReader bufferedReader) {
        this.br = bufferedReader;

        this.customerID = customerIDd;
        cmbMessageList = new JComboBox<>(customerID);

        setLayout(new FlowLayout());
        setSize(900,300);
        setTitle("Hello to Total stats");
        StringBuilder sb = new StringBuilder();
        sb.append("<html> Hello to total stats portal.<br>Choose a month to get their customer stats </html>");
        JLabel lblText = new JLabel(sb.toString());
        lblText.setHorizontalTextPosition(JLabel.LEFT);
        lblText.setVerticalTextPosition(JLabel.BOTTOM);

        cmbMessageList.setSelectedIndex(0);
        cmbMessageList.addActionListener(this);
        add(cmbMessageList);
        add(lblText);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cmbMessageList) {
            JComboBox cb = (JComboBox)actionEvent.getSource();
            String msg = (String)cb.getSelectedItem();

            switch (msg) {
                case "March" :
                case "April" : {
                    ComputeAverage computeAverage = new ComputeAverage();
                    HashMap<String, int[]> map = computeAverage.monthlyAvg(msg, msg, true);
                    StringBuilder data = new StringBuilder("<br>");
                    for(String key : map.keySet()) {
                        data.append("<br> <br>For Customer ").append(key).append("<br>Total milk collected is : ").append(map.get(key)[0])
                                .append(" <br>").append(" There were ").append(map.get(key)[1]).append(" entries ").append(" <br>  with a monthly average of : ")
                                .append(map.get(key)[0]/map.get(key)[1]).append( " gallons").append( "<br> The maximum milk for a day was: ")
                                .append(map.get(key)[2]).append( "<br> The minimum milk for a day was: ").append(map.get(key)[3]);

                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append("<html>The Stats for the month ").append(msg).
                            append(" <br>").
                            append("Total Clients: ").append(map.size()).append(data).append("</html>");

                    JLabel label = new JLabel(sb.toString());
                    JFrame frame = new JFrame("Monthly Stats");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLayout(new BorderLayout());
                    frame.add(label);
                    frame.setSize(500, 600);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                }
                break;
                case "back" : {
                    lblText.setText("Back");
                    this.dispose();
                }
                default: {lblText.setText("Error");}
            }
        }
    }

}
