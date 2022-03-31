package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class SimulationView extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldN;
    private JTextField textFieldQ;
    private JTextField textFieldSimTime;
    private JTextField textFieldArrTimeMin;
    private JTextField textFieldArrTimeMax;
    private JTextField textFieldServTimeMin;
    private JTextField textFieldServTimeMax;
    private JButton btnStart;
    private JTextArea textArea;
    public SimulationView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(new Color(230, 230, 250));
        panelLeft.setBounds(0, 0, 290, 400);
        contentPane.add(panelLeft);
        panelLeft.setLayout(null);

        btnStart = new JButton("Start");
        btnStart.setBackground(new Color(253, 245, 230));
        btnStart.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnStart.setBounds(100, 320, 85, 40);
        panelLeft.add(btnStart);

        textFieldN = new JTextField();
        textFieldN.setBounds(180, 10, 50, 30);
        panelLeft.add(textFieldN);
        textFieldN.setColumns(10);

        JLabel lblN = new JLabel("N (no. clients)");
        lblN.setHorizontalAlignment(SwingConstants.CENTER);
        lblN.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblN.setBounds(70, 9, 85, 30);
        panelLeft.add(lblN);

        textFieldQ = new JTextField();
        textFieldQ.setColumns(10);
        textFieldQ.setBounds(180, 51, 50, 30);
        panelLeft.add(textFieldQ);

        JLabel lblQ = new JLabel("Q (no. queues)");
        lblQ.setHorizontalAlignment(SwingConstants.CENTER);
        lblQ.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblQ.setBounds(70, 50, 85, 30);
        panelLeft.add(lblQ);

        textFieldSimTime = new JTextField();
        textFieldSimTime.setColumns(10);
        textFieldSimTime.setBounds(180, 92, 50, 30);
        panelLeft.add(textFieldSimTime);

        JLabel lblSimTime = new JLabel("Simulation Time");
        lblSimTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblSimTime.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblSimTime.setBounds(55, 91, 115, 30);
        panelLeft.add(lblSimTime);

        JLabel lblArrivalTime = new JLabel("Arrival Time");
        lblArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblArrivalTime.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblArrivalTime.setBounds(86, 132, 115, 30);
        panelLeft.add(lblArrivalTime);

        textFieldArrTimeMin = new JTextField();
        textFieldArrTimeMin.setColumns(10);
        textFieldArrTimeMin.setBounds(75, 160, 50, 30);
        panelLeft.add(textFieldArrTimeMin);

        textFieldArrTimeMax = new JTextField();
        textFieldArrTimeMax.setColumns(10);
        textFieldArrTimeMax.setBounds(150, 160, 50, 30);
        panelLeft.add(textFieldArrTimeMax);

        JLabel lbl5 = new JLabel(";");
        lbl5.setHorizontalAlignment(SwingConstants.CENTER);
        lbl5.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl5.setBounds(135, 157, 10, 34);
        panelLeft.add(lbl5);

        JLabel lbl4 = new JLabel("[");
        lbl4.setHorizontalAlignment(SwingConstants.CENTER);
        lbl4.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl4.setBounds(55, 153, 10, 38);
        panelLeft.add(lbl4);

        JLabel lbl6 = new JLabel("]");
        lbl6.setHorizontalAlignment(SwingConstants.CENTER);
        lbl6.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl6.setBounds(211, 152, 10, 38);
        panelLeft.add(lbl6);

        JLabel lblServiceTime = new JLabel("Service Time");
        lblServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        lblServiceTime.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lblServiceTime.setBounds(86, 218, 115, 30);
        panelLeft.add(lblServiceTime);

        JLabel lbl2 = new JLabel("[");
        lbl2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl2.setBounds(55, 239, 10, 38);
        panelLeft.add(lbl2);

        textFieldServTimeMin = new JTextField();
        textFieldServTimeMin.setColumns(10);
        textFieldServTimeMin.setBounds(75, 246, 50, 30);
        panelLeft.add(textFieldServTimeMin);

        JLabel lbl1 = new JLabel(";");
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lbl1.setBounds(135, 243, 10, 34);
        panelLeft.add(lbl1);

        textFieldServTimeMax = new JTextField();
        textFieldServTimeMax.setColumns(10);
        textFieldServTimeMax.setBounds(150, 246, 50, 30);
        panelLeft.add(textFieldServTimeMax);

        JLabel lbl3 = new JLabel("]");
        lbl3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl3.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lbl3.setBounds(211, 238, 10, 38);
        panelLeft.add(lbl3);

        JPanel panelRight = new JPanel();
        panelRight.setBackground(new Color(253, 245, 230));
        panelRight.setBounds(300, 0, 400, 363);
        contentPane.add(panelRight);
        panelRight.setLayout(null);

        JPanel panelSeparator2 = new JPanel();
        panelSeparator2.setBackground(new Color(218, 112, 214));
        panelSeparator2.setBounds(0, 30, 400, 10);
        panelRight.add(panelSeparator2);

        JPanel panelLabelRezultat = new JPanel();
        panelLabelRezultat.setBackground(new Color(240, 255, 240));
        panelLabelRezultat.setBounds(0, 0, 400, 30);
        panelRight.add(panelLabelRezultat);

        JLabel lblImagineaCozilor = new JLabel("Queues status");
        panelLabelRezultat.add(lblImagineaCozilor);
        lblImagineaCozilor.setBackground(new Color(255, 228, 196));
        lblImagineaCozilor.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagineaCozilor.setFont(new Font("Times New Roman", Font.BOLD, 14));

        JPanel panelAfisare = new JPanel();
        panelAfisare.setBackground(new Color(216, 191, 216));
        panelAfisare.setBounds(0, 40, 400, 323);
        panelRight.add(panelAfisare);
        panelAfisare.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        textArea = new JTextArea(20,41);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textArea.setBackground(new Color(255, 248, 220));
        textArea.setLineWrap(true);
        panelAfisare.add(textArea);
        textArea.setEditable(true);

        textArea.setCaretPosition(textArea.getDocument().getLength());
        JScrollPane scrollPane= new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panelAfisare.add(scrollPane);

        JPanel panelSeparator1 = new JPanel();
        panelSeparator1.setBackground(new Color(218, 112, 214));
        panelSeparator1.setBounds(290, 0, 10, 363);
        contentPane.add(panelSeparator1);

        setVisible(true);
    }

    public int getTextFieldN() {
        return Integer.parseInt(textFieldN.getText());
    }

    public int getTextFieldQ() {
        return Integer.parseInt(textFieldQ.getText());
    }

    public int getTextFieldSimTime() {
        return Integer.parseInt(textFieldSimTime.getText());
    }

    public int getTextFieldArrTimeMin() {
        return Integer.parseInt(textFieldArrTimeMin.getText());
    }

    public int getTextFieldArrTimeMax() {
        return Integer.parseInt(textFieldArrTimeMax.getText());
    }

    public int getTextFieldServTimeMin() {
        return Integer.parseInt(textFieldServTimeMin.getText());
    }

    public int getTextFieldServTimeMax() {
        return Integer.parseInt(textFieldServTimeMax.getText());
    }

    public void setTextArea(String message) {
        this.textArea.setText(textArea.getText()+"\n"+message);
    }
    public void addStartListener(ActionListener actionListener)
    {
        btnStart.addActionListener(actionListener);
    }
}
