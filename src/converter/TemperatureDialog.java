package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureDialog extends JDialog {

    private JComboBox<String> from, to;
    private JTextField tfInput, tfResult;

    public TemperatureDialog(JFrame parent) {
        super(parent,"TEMPERATURE CONVERTER", true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/temp.png"));
        setIconImage(icon);
        setResizable(false);
        setBounds(620,230,700,600);
        JPanel panel = new JPanel(null);

        JLabel heading = new JLabel("TEMPERATURE CONVERTER", JLabel.CENTER);
        heading.setFont(new Font("Cambria Math", Font.BOLD, 36));
        heading.setBounds(20,30,660,70);
        panel.add(heading);

        JLabel lblFrom = new JLabel("From", JLabel.CENTER); lblFrom.setFont(new Font("Arial",1,16)); lblFrom.setBounds(150,150,80,30);
        JLabel lblTo = new JLabel("To", JLabel.CENTER); lblTo.setFont(new Font("Arial",1,16)); lblTo.setBounds(400,150,80,30);

        String[] units = {"Celsius","Fahrenheit","Kelvin"};
        from = new JComboBox<>(units); from.setBounds(150,185,180,30);
        to = new JComboBox<>(units); to.setBounds(400,185,180,30);

        tfInput = new JTextField(); tfInput.setBounds(150,260,180,30);
        tfResult = new JTextField(); tfResult.setBounds(400,260,180,30); tfResult.setEditable(false);

        JButton convertBtn = new JButton("Convert"); convertBtn.setBounds(270,350,150,40);

        panel.add(lblFrom); panel.add(from); panel.add(lblTo); panel.add(to);
        panel.add(tfInput); panel.add(tfResult); panel.add(convertBtn);
        add(panel);

        convertBtn.addActionListener(e -> convertTemp());
    }

    private void convertTemp() {
        try {
            double val = Double.parseDouble(tfInput.getText());
            String f = from.getSelectedItem().toString();
            String t = to.getSelectedItem().toString();
            double res = 0;
            String key = f + "->" + t;

            switch(key) {
                case "Celsius->Fahrenheit":
                    res = val * 9 / 5 + 32;
                    break;
                case "Celsius->Kelvin":
                    res = val + 273.15;
                    break;
                case "Fahrenheit->Celsius":
                    res = (val - 32) * 5 / 9;
                    break;
                case "Fahrenheit->Kelvin":
                    res = (val - 32) * 5 / 9 + 273.15;
                    break;
                case "Kelvin->Celsius":
                    res = val - 273.15;
                    break;
                case "Kelvin->Fahrenheit":
                    res = (val - 273.15) * 9 / 5 + 32;
                    break;
                default:
                    res = val;
                    break;
            }

            tfResult.setText(String.format("%.2f", res));
        } catch(Exception e) {
            tfResult.setText("");
        }
    }
}
