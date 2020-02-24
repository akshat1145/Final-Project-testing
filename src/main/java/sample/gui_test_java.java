package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui_test_java extends JFrame implements ActionListener {
    String[] messageStings = {"A", "B", "C"};
    JComboBox<String> cmbMessageList = new JComboBox<>(messageStings);
    JLabel lblText = new JLabel();
    JComboBox<Object> comboBox;

    public gui_test_java() {


        setLayout(new FlowLayout());
        setSize(400,300);
        setTitle("Hello to this box");


     //   cmbMessageList.setSelectedIndex(0);
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
                case "A" : {
                    //display image
                    lblText.setText("This is opt A");
                    Object[] items =
                            {
                                    new ImageIcon("/Users/akshat./Desktop/image5.png")

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
                case "B" : {
                    lblText.setText("This is B");

                    //display graph
                    DisplayGraph displayGraph = new DisplayGraph();
                    displayGraph.startMain();

                }
                break;
                case "C" : lblText.setText("This is C");
                break;
                default: lblText.setText("Error");
            }
        }
    }

}
