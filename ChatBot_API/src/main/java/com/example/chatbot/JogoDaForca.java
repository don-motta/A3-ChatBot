package com.example.chatbot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JogoDaForca {
    private static String palavra;
    private static JLabel label;
    private static int tentativas;

    public static void main(String[] args) {
        String[] palavras = {"máquina de lavar", "guarda chuva", "computador", "programação", "java", "futebol"};
        Random random = new Random();
        palavra = palavras[random.nextInt(palavras.length)];

        tentativas = 5;

        JFrame frame = new JFrame("Jogo da Forca");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Change to DISPOSE_ON_CLOSE
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        label = new JLabel();
        label.setBounds(10, 20, 380, 25);
        panel.add(label);
        atualizarLabel();

        JTextField userText = new JTextField(20);
        userText.setBounds(10, 50, 160, 25);
        panel.add(userText);

        JButton loginButton = new JButton("Adivinhar");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char letra = userText.getText().charAt(0);
                userText.setText("");

                if (palavra.contains(String.valueOf(letra))) {
                    for (int i = 0; i < palavra.length(); i++) {
                        if (palavra.charAt(i) == letra) {
                            label.setText(label.getText().substring(0, i) + letra + label.getText().substring(i + 1));
                        }
                    }
                } else {
                    tentativas--;
                }

                if (!label.getText().contains("_")) {
                    JOptionPane.showMessageDialog(null, "Parabéns! Você adivinhou a palavra: " + palavra);
                    closeGame();
                }

                if (tentativas == 0) {
                    JOptionPane.showMessageDialog(null, "Você perdeu! A palavra era: " + palavra);
                    closeGame();
                }
            }
        });
        panel.add(loginButton);
    }

    private static void atualizarLabel() {
        StringBuilder sb = new StringBuilder();
        for (char c : palavra.toCharArray()) {
            if (c == ' ') {
                sb.append(' ');
            } else {
                sb.append('_');
            }
        }
        label.setText(sb.toString());
    }

    private static void closeGame() {
        // Close the game window without terminating the main application
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(label);
        frame.dispose();
    }
}