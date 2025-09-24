package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MasterConverter extends JFrame {

    public MasterConverter() {
        setTitle("MASTER CONVERTER");
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/icon.png"));
        setIconImage(icon);
        setSize(700, 600);
        setLocation(620, 230);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel(null);

        JLabel heading = new JLabel("MASTER CONVERTER", JLabel.CENTER);
        heading.setFont(new Font("Cambria Math", Font.BOLD, 42));
        heading.setBounds(120, 20, 460, 70);
        panel.add(heading);

        JButton currencyBtn = new JButton(new ImageIcon(getClass().getResource("/resources/cur.jpg")));
        JButton tempBtn = new JButton(new ImageIcon(getClass().getResource("/resources/temp.png")));
        JButton numberBtn = new JButton(new ImageIcon(getClass().getResource("/resources/num.jpg")));
        JButton lengthBtn = new JButton(new ImageIcon(getClass().getResource("/resources/len.png")));

        currencyBtn.setBounds(125,140,200,150);
        tempBtn.setBounds(375,140,200,150);
        numberBtn.setBounds(125,340,200,150);
        lengthBtn.setBounds(375,340,200,150);

        panel.add(currencyBtn); panel.add(tempBtn);
        panel.add(numberBtn); panel.add(lengthBtn);
        add(panel);

        currencyBtn.addActionListener(e -> new CurrencyConverterDialog(this).setVisible(true));
        tempBtn.addActionListener(e -> new TemperatureDialog(this).setVisible(true));
        numberBtn.addActionListener(e -> new NumberSystemDialog(this).setVisible(true));
        lengthBtn.addActionListener(e -> new LengthDialog(this).setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MasterConverter().setVisible(true));
        
    }
}
