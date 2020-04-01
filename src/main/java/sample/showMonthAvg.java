package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class showMonthAvg extends JFrame implements ActionListener {
    String[] customerID;
    JComboBox<String> cmbMessageList;
    JComboBox<Object> comboBox;
    JLabel lblText = new JLabel();
    public showMonthAvg(String[] customerIDd) {
        this.customerID = customerIDd;
        cmbMessageList = new JComboBox<>(customerID);

        setLayout(new FlowLayout());
        setSize(900,300);
        setTitle("Hello to Personal customers");
        StringBuilder sb = new StringBuilder();
        sb.append("<html> Hello to Personal customers portal.<br>Choose a customer to get their monthly stats </html>");
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
            ComputeAverage computeAverage = new ComputeAverage();

            switch (msg) {
                case "3" :
                case "2" :
                case "1" : {

                    HashMap<String, int[]> map = computeAverage.monthlyAvg(msg, msg, false);
                    StringBuilder data = new StringBuilder("<br>");
                    for(String key : map.keySet()) {
                        StringBuilder listdata = new StringBuilder("More Info:");
                        data.append("<br> <br>For month ").append(key).append("<br>Total milk collected is : ").append(map.get(key)[0])
                                .append(" <br>").append(" There were ").append(map.get(key)[1]).append(" entries ").append(" <br>  with a daily average of : ")
                                .append(map.get(key)[0]/map.get(key)[1]).append( " gallons").append( "<br> The maximum milk for a day in the month was: ")
                                .append(map.get(key)[2]).append( "<br> The minimum milk for a day in the month was: ").append(map.get(key)[3]);

                    }

                    StringBuilder sb = new StringBuilder();
                    sb.append("<html>The Stats for the Client ").append(msg).
                            append(" <br>").
                            append("Total Months worked here: ").append(map.size()).append(data).append("</html>");

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
                default: {lblText.setText("Error");}
            }
        }
    }

}
