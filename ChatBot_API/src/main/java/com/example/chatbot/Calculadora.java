package com.example.chatbot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculadora {

    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton, equalButton, delButton, resetButton;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora window = new Calculadora();
            window.initialize();
        });
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 250, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setBounds(20, 20, 200, 30);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        // Adicionando botões para números
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + e.getActionCommand());
                }
            });
            numberButtons[i].setBounds(20 + (i % 3) * 60, 60 + (i / 3) * 60, 50, 50);
            frame.getContentPane().add(numberButtons[i]);
        }

        // Adicionando botões para operações
        addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
            }
        });
        addButton.setBounds(200, 60, 50, 50);
        frame.getContentPane().add(addButton);

        subButton = new JButton("-");
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
            }
        });
        subButton.setBounds(200, 120, 50, 50);
        frame.getContentPane().add(subButton);

        mulButton = new JButton("*");
        mulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
            }
        });
        mulButton.setBounds(200, 180, 50, 50);
        frame.getContentPane().add(mulButton);

        divButton = new JButton("/");
        divButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
            }
        });
        divButton.setBounds(200, 240, 50, 50);
        frame.getContentPane().add(divButton);

        equalButton = new JButton("=");
        equalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }

                textField.setText(String.valueOf(result));
                num1 = result;
            }
        });
        equalButton.setBounds(200, 300, 50, 50);
        frame.getContentPane().add(equalButton);

        // Adicionando botão de apagar
        delButton = new JButton("Del");
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = textField.getText();
                if (str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                    textField.setText(str);
                }
            }
        });
        delButton.setBounds(20, 300, 70, 50);
        frame.getContentPane().add(delButton);

        // Adicionando botão de reset
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                num1 = 0;
                num2 = 0;
            }
        });
        resetButton.setBounds(100, 300, 70, 50);
        frame.getContentPane().add(resetButton);

        frame.setVisible(true);
    }
}
