package GRAPHICS;

import javax.swing.*;

public class GUI_1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple GUI");

        JLabel label1 = new JLabel("Enter Text 1:");
        JTextField textField1 = new JTextField(20);

        JLabel label2 = new JLabel("Enter Text 2:");
        JTextField textField2 = new JTextField(20);

        JButton button = new JButton("Submit");

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(label1);
        frame.add(textField1);
        frame.add(label2);
        frame.add(textField2);
        frame.add(button);

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
