package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberSystemDialog extends JDialog {

    private JComboBox<String> from, to;
    private JTextField tfInput, tfResult;

    public NumberSystemDialog(JFrame parent) {
        super(parent,"NUMBER SYSTEM CONVERTER",true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/num.jpg"));
        setIconImage(icon);
        setResizable(false);
        setBounds(620,230,700,600);

        JPanel panel = new JPanel(null);

        JLabel heading = new JLabel("NUMBER SYSTEM CONVERTER", JLabel.CENTER);
        heading.setFont(new Font("Cambria Math", Font.BOLD, 36));
        heading.setBounds(20,30,660,70);
        panel.add(heading);

        JLabel lblFrom = new JLabel("From", JLabel.CENTER);
        lblFrom.setFont(new Font("Arial",1,16));
        lblFrom.setBounds(150,150,80,30);

        JLabel lblTo = new JLabel("To", JLabel.CENTER);
        lblTo.setFont(new Font("Arial",1,16));
        lblTo.setBounds(400,150,80,30);

        String[] systems = {"Decimal","Binary","Octal","Hexadecimal"};
        from = new JComboBox<>(systems);
        from.setBounds(150,185,180,30);

        to = new JComboBox<>(systems);
        to.setBounds(400,185,180,30);

        tfInput = new JTextField();
        tfInput.setBounds(150,260,180,30);

        tfResult = new JTextField();
        tfResult.setBounds(400,260,180,30);
        tfResult.setEditable(false);

        JButton convertBtn = new JButton("Convert");
        convertBtn.setBounds(270,350,150,40);

        panel.add(lblFrom); panel.add(from);
        panel.add(lblTo); panel.add(to);
        panel.add(tfInput); panel.add(tfResult);
        panel.add(convertBtn);

        add(panel);

        // Action listener for conversion
        convertBtn.addActionListener(e -> convertNumberSystem());
    }

    private void convertNumberSystem() {
        try {
            String input = tfInput.getText().trim();
            String f = from.getSelectedItem().toString();
            String t = to.getSelectedItem().toString();

            int decimal = 0;

         // Convert input to decimal
         switch(f) {
             case "Decimal":
                 decimal = Integer.parseInt(input);
                 break;
             case "Binary":
                 decimal = Integer.parseInt(input, 2);
                 break;
             case "Octal":
                 decimal = Integer.parseInt(input, 8);
                 break;
             case "Hexadecimal":
                 decimal = Integer.parseInt(input, 16);
                 break;
             default:
                 decimal = 0;
                 break;
         }

         // Convert decimal to target format
         String result = "";

         switch(t) {
             case "Decimal":
                 result = Integer.toString(decimal);
                 break;
             case "Binary":
                 result = Integer.toBinaryString(decimal);
                 break;
             case "Octal":
                 result = Integer.toOctalString(decimal);
                 break;
             case "Hexadecimal":
                 result = Integer.toHexString(decimal).toUpperCase();
                 break;
             default:
                 result = "";
                 break;
         }


            tfResult.setText(result);
        } catch(Exception e) {
            tfResult.setText("");
            JOptionPane.showMessageDialog(this, "Invalid input for selected number system!");
        }
    }
}
