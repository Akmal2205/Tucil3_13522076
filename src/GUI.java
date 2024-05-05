import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.util.*;

public class GUI extends JFrame {
    final private Font mainFont = new Font("Georgia", Font.BOLD, 18);
    private String selected;
    JTextField tfStartWord, tfEndWord;
    private String[] images1 = { "Cukurukuk.jpg", "lesgo.jpeg", "Bener.jpeg" };
    private String[] images2 = { "Lalu.jpeg", "Depresi.jpeg", "Hoaks.jpeg" };
    private Random random = new Random();
    private int random_idx = random.nextInt(images1.length);

    public void intialize() {
        JLabel lbStartWord = new JLabel("Start word");
        lbStartWord.setFont(mainFont);

        tfStartWord = new JTextField();
        tfStartWord.setFont(mainFont);

        JLabel lbEndWord = new JLabel("End word");
        lbEndWord.setFont(mainFont);

        tfEndWord = new JTextField();
        tfEndWord.setFont(mainFont);

        JLabel tfPath = new JLabel();
        tfPath.setFont(mainFont);

        ImageIcon img1 = new ImageIcon(
                "C:\\Users\\ASUS\\Documents\\Sem 4\\Stima\\Tucil3_13522076\\src\\Utils\\"
                        + images1[random_idx]);
        JLabel image1 = new JLabel(img1);
        JPanel image1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        image1Panel.add(image1);
        image1Panel.setOpaque(false);
        image1Panel.setVisible(false);
        image1Panel.setVisible(false);

        ImageIcon img2 = new ImageIcon(
                "C:\\Users\\ASUS\\Documents\\Sem 4\\Stima\\Tucil3_13522076\\src\\Utils\\"
                        + images2[random_idx]);
        JLabel image2 = new JLabel(img2);
        JPanel image2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        image2Panel.add(image2);
        image2Panel.setOpaque(false);
        image2Panel.setVisible(false);

        JRadioButton radio1 = new JRadioButton("UCS");
        JRadioButton radio2 = new JRadioButton("GreedyBFS");
        JRadioButton radio3 = new JRadioButton("A*");

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        radioGroup.add(radio3);

        ActionListener radioListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedRadio = (JRadioButton) e.getSource();
                selected = selectedRadio.getText();
                System.out.println("Selected: " + selectedRadio.getText());
            }
        };

        radio1.addActionListener(radioListener);
        radio2.addActionListener(radioListener);
        radio3.addActionListener(radioListener);

        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridLayout(1, 3, 5, 5));
        radioButtonsPanel.setOpaque(false);
        radioButtonsPanel.add(radio1);
        radioButtonsPanel.add(radio2);
        radioButtonsPanel.add(radio3);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 5, 5));
        formPanel.setOpaque(false);
        formPanel.add(lbStartWord);
        formPanel.add(tfStartWord);
        formPanel.add(lbEndWord);
        formPanel.add(tfEndWord);
        formPanel.add(radioButtonsPanel);

        JPanel pathPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pathPanel.setOpaque(false);
        pathPanel.add(tfPath);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setOpaque(false);
        resultPanel.setFont(mainFont);
        resultPanel.add(pathPanel);
        resultPanel.add(image1Panel);
        resultPanel.add(image2Panel);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(mainFont);
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String startWord = tfStartWord.getText();
                String endWord = tfEndWord.getText();
                Main main = new Main();
                String res = main.MainGUI(selected, startWord, endWord);
                tfPath.setText(res);
                random_idx = random.nextInt(images1.length);
                System.out.println(random_idx);
                ImageIcon img1 = new ImageIcon(
                        "C:\\Users\\ASUS\\Documents\\Sem 4\\Stima\\Tucil3_13522076\\src\\Utils\\"
                                + images1[random_idx]);
                image1.setIcon(img1);

                ImageIcon img2 = new ImageIcon(
                        "C:\\Users\\ASUS\\Documents\\Sem 4\\Stima\\Tucil3_13522076\\src\\Utils\\"
                                + images2[random_idx]);
                image2.setIcon(img2);

                image2Panel.setVisible(false);
                image1Panel.setVisible(false);
                if (res.equals("Invalid Input!")) {
                    image2Panel.setVisible(true);
                } else {
                    image1Panel.setVisible(true);
                }
            }

        });

        JButton btnClear = new JButton("Clear");
        btnClear.setFont(mainFont);
        btnClear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tfStartWord.setText("");
                tfEndWord.setText("");
                tfPath.setText("");
                image1Panel.setVisible(false);
                image2Panel.setVisible(false);
            }

        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2, 5, 5));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(btnSearch);
        buttonsPanel.add(btnClear);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // mainPanel.add(radioButtonPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        setTitle("Cukurukuk Word Ladder Solver");
        setSize(1000, 1000);
        setMinimumSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        GUI myFrame = new GUI();
        myFrame.intialize();
    }
}
