package converter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class CurrencyConverterDialog extends JDialog {

    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField tfAmount, tfResult;
    private JLabel lblFromSign, lblToSign;

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public CurrencyConverterDialog(JFrame parent) {
        super(parent, "CURRENCY CONVERTER", true);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/cur.jpg"));
        setIconImage(icon);
        setResizable(false);
        setBounds(620, 230, 700, 600);
        JPanel panel = new JPanel(null);

        JLabel heading = new JLabel("CURRENCY CONVERTER", JLabel.CENTER);
        heading.setFont(new Font("Cambria Math", Font.BOLD, 36));
        heading.setBounds(20, 30, 660, 70);
        panel.add(heading);

        String[] currencies = {
        	    "AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN",
        	    "BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL",
        	    "BSD","BTN","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY",
        	    "COP","CRC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP",
        	    "ERN","ETB","EUR","FJD","FKP","FOK","GBP","GEL","GGP","GHS",
        	    "GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF",
        	    "IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD",
        	    "JPY","KES","KGS","KHR","KID","KMF","KRW","KWD","KYD","KZT",
        	    "LAK","LBP","LKR","LRD","LSL","LYD","MAD","MDL","MGA","MKD",
        	    "MMK","MNT","MOP","MRU","MUR","MVR","MWK","MXN","MYR","MZN",
        	    "NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK",
        	    "PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR",
        	    "SBD","SCR","SDG","SEK","SGD","SHP","SLE","SLL","SOS","SRD",
        	    "STN","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD",
        	    "TVD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VES","VND",
        	    "VUV","WST","XAF","XCD","XDR","XOF","XPF","YER","ZAR","ZMW","ZWL"
        	};


        JLabel lblFrom = new JLabel("From", JLabel.CENTER); lblFrom.setFont(new Font("Arial",1,16)); lblFrom.setBounds(150,150,80,30);
        fromCurrency = new JComboBox<>(currencies); fromCurrency.setBounds(150,185,180,30);

        JLabel lblTo = new JLabel("To", JLabel.CENTER); lblTo.setFont(new Font("Arial",1,16)); lblTo.setBounds(400,150,80,30);
        toCurrency = new JComboBox<>(currencies); toCurrency.setBounds(400,185,180,30); toCurrency.setSelectedIndex(1);

        tfAmount = new JTextField(); tfAmount.setBounds(150,260,180,30);
        lblFromSign = new JLabel("", JLabel.CENTER); lblFromSign.setBounds(340,260,50,30);

        tfResult = new JTextField(); tfResult.setBounds(400,260,180,30); tfResult.setEditable(false);
        lblToSign = new JLabel("", JLabel.CENTER); lblToSign.setBounds(590,260,50,30);

        JButton convertBtn = new JButton("Convert"); convertBtn.setBounds(270,350,150,40);

        panel.add(lblFrom); panel.add(fromCurrency); panel.add(lblTo); panel.add(toCurrency);
        panel.add(tfAmount); panel.add(lblFromSign); panel.add(tfResult); panel.add(lblToSign);
        panel.add(convertBtn);

        add(panel);

        convertBtn.addActionListener(e -> convertCurrency());
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(tfAmount.getText());
            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();

            double rate = fetchRate(from, to);
            tfResult.setText(String.format("%.2f", amount * rate));

            lblFromSign.setText(from);
            lblToSign.setText(to);

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Enter valid number or check API connection!");
        }
    }

    private double fetchRate(String from, String to) throws Exception {
        URL url = new URL(API_URL + from);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) sb.append(line);
        br.close();

        JSONObject json = new JSONObject(sb.toString());
        JSONObject rates = json.getJSONObject("rates");
        return rates.getDouble(to);
    }
    
    
}


//package converter;
//
//import javax.swing.*;
//import javax.swing.event.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import java.net.*;
//import org.json.JSONObject;
//
//public class CurrencyConverterDialog extends JDialog {
//
//    private JComboBox<String> fromCurrency, toCurrency;
//    private JTextField tfInput, tfResult;
//    private JLabel lblFromSign, lblToSign;
//
//    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
//
//    private final String[] currencies = {
//        "AED","AFN","ALL","AMD","ANG","AOA","ARS","AUD","AWG","AZN",
//        "BAM","BBD","BDT","BGN","BHD","BIF","BMD","BND","BOB","BRL",
//        "BSD","BTN","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY",
//        "COP","CRC","CUP","CVE","CZK","DJF","DKK","DOP","DZD","EGP",
//        "ERN","ETB","EUR","FJD","FKP","FOK","GBP","GEL","GGP","GHS",
//        "GIP","GMD","GNF","GTQ","GYD","HKD","HNL","HRK","HTG","HUF",
//        "IDR","ILS","IMP","INR","IQD","IRR","ISK","JEP","JMD","JOD",
//        "JPY","KES","KGS","KHR","KID","KMF","KRW","KWD","KYD","KZT",
//        "LAK","LBP","LKR","LRD","LSL","LYD","MAD","MDL","MGA","MKD",
//        "MMK","MNT","MOP","MRU","MUR","MVR","MWK","MXN","MYR","MZN",
//        "NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK",
//        "PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","RWF","SAR",
//        "SBD","SCR","SDG","SEK","SGD","SHP","SLE","SLL","SOS","SRD",
//        "STN","SYP","SZL","THB","TJS","TMT","TND","TOP","TRY","TTD",
//        "TVD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VES","VND",
//        "VUV","WST","XAF","XCD","XDR","XOF","XPF","YER","ZAR","ZMW","ZWL"
//    };
//
//    public CurrencyConverterDialog(JFrame parent) {
//        super(parent, "CURRENCY CONVERTER", true);
//        setResizable(false);
//        setBounds(620,230,700,600);
//
//        JPanel panel = new JPanel(null);
//
//        JLabel heading = new JLabel("CURRENCY CONVERTER", JLabel.CENTER);
//        heading.setFont(new Font("Cambria Math", Font.BOLD, 36));
//        heading.setBounds(20,30,660,70);
//        panel.add(heading);
//
//        JLabel lblFrom = new JLabel("From", JLabel.CENTER);
//        lblFrom.setFont(new Font("Arial", 1, 16));
//        lblFrom.setBounds(150,150,80,30);
//        panel.add(lblFrom);
//
//        JLabel lblTo = new JLabel("To", JLabel.CENTER);
//        lblTo.setFont(new Font("Arial", 1, 16));
//        lblTo.setBounds(400,150,80,30);
//        panel.add(lblTo);
//
//        fromCurrency = new JComboBox<>(currencies);
//        fromCurrency.setBounds(150,185,180,30);
//        panel.add(fromCurrency);
//
//        toCurrency = new JComboBox<>(currencies);
//        toCurrency.setBounds(400,185,180,30);
//        panel.add(toCurrency);
//
//        tfInput = new JTextField();
//        tfInput.setBounds(150,260,180,30);
//        panel.add(tfInput);
//
//        tfResult = new JTextField();
//        tfResult.setBounds(400,260,180,30);
//        tfResult.setEditable(false);
//        panel.add(tfResult);
//
//        lblFromSign = new JLabel("", JLabel.CENTER);
//        lblFromSign.setBounds(120,260,30,30);
//        panel.add(lblFromSign);
//
//        lblToSign = new JLabel("", JLabel.CENTER);
//        lblToSign.setBounds(580,260,30,30);
//        panel.add(lblToSign);
//
//        add(panel);
//
//        // Listeners for smooth reactive updates
//        ItemListener comboListener = e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED) convertCurrencyAsync();
//        };
//
//        fromCurrency.addItemListener(comboListener);
//        toCurrency.addItemListener(comboListener);
//
//        tfInput.getDocument().addDocumentListener(new DocumentListener() {
//            public void insertUpdate(DocumentEvent e) { convertCurrencyAsync(); }
//            public void removeUpdate(DocumentEvent e) { convertCurrencyAsync(); }
//            public void changedUpdate(DocumentEvent e) { convertCurrencyAsync(); }
//        });
//    }
//
//    // Convert currency in background thread
//    private void convertCurrencyAsync() {
//        String input = tfInput.getText().trim();
//        if (input.isEmpty()) {
//            tfResult.setText("");
//            return;
//        }
//
//        try {
//            double amount = Double.parseDouble(input);
//            String from = (String) fromCurrency.getSelectedItem();
//            String to = (String) toCurrency.getSelectedItem();
//
//            // Use SwingWorker for background API call
//            new SwingWorker<Double, Void>() {
//                protected Double doInBackground() throws Exception {
//                    return fetchRate(from, to) * amount;
//                }
//
//                protected void done() {
//                    try {
//                        double result = get();
//                        tfResult.setText(String.format("%.2f", result));
//                        lblFromSign.setText(from);
//                        lblToSign.setText(to);
//                    } catch (Exception ex) {
//                        tfResult.setText("Error");
//                    }
//                }
//            }.execute();
//
//        } catch (NumberFormatException e) {
//            tfResult.setText("");
//        }
//    }
//
//    // API fetch
//    private double fetchRate(String from, String to) throws Exception {
//        String urlStr = API_URL + from;
//        HttpURLConnection conn = (HttpURLConnection)new URL(urlStr).openConnection();
//        conn.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        StringBuilder response = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) response.append(line);
//        reader.close();
//
//        JSONObject json = new JSONObject(response.toString());
//        JSONObject rates = json.getJSONObject("rates");
//
//        return rates.getDouble(to);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            CurrencyConverterDialog dlg = new CurrencyConverterDialog(null);
//            dlg.setVisible(true);
//        });
//    }
//}

