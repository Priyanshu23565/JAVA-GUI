package GRAPHICS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple GUI");

        JLabel label1 = new JLabel("Enter Number 1:");
        JTextField textField1 = new JTextField(20);

        JLabel label2 = new JLabel("Enter Number 2:");
        JTextField textField2 = new JTextField(20);

        JButton button = new JButton("Submit");

        JLabel sumLabel = new JLabel("Sum: ");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number1 = Integer.parseInt(textField1.getText());
                int number2 = Integer.parseInt(textField2.getText());
                int sum = number1 + number2;
                sumLabel.setText("Sum: " + sum);
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(label1);
        frame.add(textField1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(button);
        frame.add(sumLabel);

        frame.setSize(300, 150);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}



//package GRAPHICS;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class GUI_3 {
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Simple GUI");
//
//        JLabel label1 = new JLabel("Enter Number 1:");
//        JTextField textField1 = new JTextField(20);
//
//        JLabel label2 = new JLabel("Enter Number 2:");
//        JTextField textField2 = new JTextField(20);
//
//        JButton button = new JButton("Submit");
//
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int number1 = Integer.parseInt(textField1.getText());
//                int number2 = Integer.parseInt(textField2.getText());
//                int sum = number1 + number2;
//                JOptionPane.showMessageDialog(frame, "Sum: " + sum);
//            }
//        });
//
//        frame.setLayout(new java.awt.FlowLayout());
//        frame.add(label1);
//        frame.add(textField1);
//        frame.add(label2);
//        frame.add(textField2);
//        frame.add(button);
//
//        frame.setSize(300, 150);
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//}
