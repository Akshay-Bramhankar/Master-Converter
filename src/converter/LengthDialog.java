package converter;

import javax.swing.*;
import java.awt.*;

public class LengthDialog extends JDialog {

    private JComboBox<String> from, to;
    private JTextField tfInput, tfResult;

    public LengthDialog(JFrame parent) {
        super(parent, "LENGTH CONVERTER", true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/len.png"));
        setIconImage(icon);
        setResizable(false);
        setBounds(620, 230, 700, 600);
        JPanel panel = new JPanel(null);

        JLabel heading = new JLabel("LENGTH CONVERTER", JLabel.CENTER);
        heading.setFont(new Font("Cambria Math", Font.BOLD, 36));
        heading.setBounds(20,30,660,70);
        panel.add(heading);

        JLabel lblFrom = new JLabel("From", JLabel.CENTER); lblFrom.setFont(new Font("Arial",1,16)); lblFrom.setBounds(150,150,80,30);
        JLabel lblTo = new JLabel("To", JLabel.CENTER); lblTo.setFont(new Font("Arial",1,16)); lblTo.setBounds(400,150,80,30);

        String[] units = {"Meter","Kilometer","Centimeter","Millimeter","Inch","Foot","Yard","Mile"};
        from = new JComboBox<>(units); from.setBounds(150,185,180,30);
        to = new JComboBox<>(units); to.setBounds(400,185,180,30);

        tfInput = new JTextField(); tfInput.setBounds(150,260,180,30);
        tfResult = new JTextField(); tfResult.setBounds(400,260,180,30); tfResult.setEditable(false);

        JButton convertBtn = new JButton("Convert"); convertBtn.setBounds(270,350,150,40);

        panel.add(lblFrom); panel.add(from); panel.add(lblTo); panel.add(to);
        panel.add(tfInput); panel.add(tfResult); panel.add(convertBtn);
        add(panel);

        convertBtn.addActionListener(e -> convertLength());
    }

    private void convertLength() {
        try {
            double val = Double.parseDouble(tfInput.getText());
            String f = from.getSelectedItem().toString();
            String t = to.getSelectedItem().toString();

            double valInMeters = 0;

            switch(f) {
                case "Meter":
                    valInMeters = val;
                    break;
                case "Kilometer":
                    valInMeters = val * 1000;
                    break;
                case "Centimeter":
                    valInMeters = val / 100;
                    break;
                case "Millimeter":
                    valInMeters = val / 1000;
                    break;
                case "Inch":
                    valInMeters = val * 0.0254;
                    break;
                case "Foot":
                    valInMeters = val * 0.3048;
                    break;
                case "Yard":
                    valInMeters = val * 0.9144;
                    break;
                case "Mile":
                    valInMeters = val * 1609.34;
                    break;
                default:
                    valInMeters = val;
                    break;
            }

            double result = 0;

            switch(t) {
                case "Meter":
                    result = valInMeters;
                    break;
                case "Kilometer":
                    result = valInMeters / 1000;
                    break;
                case "Centimeter":
                    result = valInMeters * 100;
                    break;
                case "Millimeter":
                    result = valInMeters * 1000;
                    break;
                case "Inch":
                    result = valInMeters / 0.0254;
                    break;
                case "Foot":
                    result = valInMeters / 0.3048;
                    break;
                case "Yard":
                    result = valInMeters / 0.9144;
                    break;
                case "Mile":
                    result = valInMeters / 1609.34;
                    break;
                default:
                    result = valInMeters;
                    break;
            }


            tfResult.setText(String.format("%.4f", result));

        } catch(Exception e) {
            tfResult.setText("");
        }
    }
}
