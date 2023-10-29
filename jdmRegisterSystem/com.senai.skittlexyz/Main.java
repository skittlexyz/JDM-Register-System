import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf; // flatlaf-3.2.5.jar
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class Main {
    private JPanel frame;
    private JTabbedPane tabbedPane1;
    private JButton registerButton;
    private JButton deleteButton;
    private JButton dSearchButton;
    private JButton searchButton;
    private JTextField brandField;
    private JTextField modelField;
    private JTextField queryField;
    private JTextArea descriptionTextArea;
    private JTextArea searchTextArea;
    private JTextArea deleteTextArea;
    private JSpinner yearSpinner;
    private JSpinner idSpinner;
    private JSpinner stageSpinner;
    private JCheckBox bodyKitCheckBox;
    private JCheckBox nitroCheckBox;
    private JCheckBox turboCheckBox;
    private JCheckBox camberCheckBox;
    private JCheckBox airSuspensionCheckBox;
    private JCheckBox pBuildCheckBox;
    private JLabel github;

    private final String title = "JDM Register System";

    private final ArrayList<Map<String, String>> cars = new ArrayList<>();

    public Main() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(brandField.getText(), "") || Objects.equals(brandField.getText(), null)) {
                    JOptionPane.showMessageDialog(null, "Brand field can't be empty!", title, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (Objects.equals(modelField.getText(), "") || Objects.equals(modelField.getText(), null)) {
                    JOptionPane.showMessageDialog(null, "Model field can't be empty!", title, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                registerAnswer();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        dSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dSearch();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://github.com/skittlexyz/"));
                    } catch (IOException | URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        FlatMacDarkLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#4260f5"));
        FlatMacDarkLaf.setup();
        JFrame frame = new JFrame("JDM Register System");
        frame.setContentPane(new Main().frame);
        frame.setResizable(false);
        //frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        SpinnerNumberModel yearModel = new SpinnerNumberModel(2000.0, 1940.0, 2023.0, 1.0);
        yearSpinner = new JSpinner(yearModel);
        JSpinner.NumberEditor yearEditor = new JSpinner.NumberEditor(yearSpinner, "#");
        yearSpinner.setEditor(yearEditor);

        SpinnerNumberModel stageModel = new SpinnerNumberModel(1.0, 1.0, 3.0, 1.0);
        stageSpinner = new JSpinner(stageModel);
        JSpinner.NumberEditor stageEditor = new JSpinner.NumberEditor(stageSpinner, "#");
        stageSpinner.setEditor(stageEditor);
    }

    private void registerAnswer() {
        Map<String, String> carData = new HashMap<>();

        String brandString = brandField.getText();
        String modelString = modelField.getText();
        double year = (Double) yearSpinner.getValue();
        boolean bodyKitCB = bodyKitCheckBox.isSelected();
        boolean nitroCB = nitroCheckBox.isSelected();
        boolean turboCB = turboCheckBox.isSelected();
        boolean camberCB = camberCheckBox.isSelected();
        boolean airCB = airSuspensionCheckBox.isSelected();
        boolean pBuildCb = pBuildCheckBox.isSelected();
        double stage = (Double) stageSpinner.getValue();
        String descString = descriptionTextArea.getText();
        int index = (cars.size()) + 1;

        carData.put("brand", brandString);
        carData.put("model", modelString);
        carData.put("year", Double.toString(year));
        carData.put("bodyKit", Boolean.toString(bodyKitCB));
        carData.put("nitro", Boolean.toString(nitroCB));
        carData.put("turbo", Boolean.toString(turboCB));
        carData.put("camber", Boolean.toString(camberCB));
        carData.put("air", Boolean.toString(airCB));
        carData.put("pbuild", Boolean.toString(pBuildCb));
        carData.put("stage", Double.toString(stage));
        carData.put("desc", descString);
        carData.put("id", Integer.toString(index));

        cars.add(carData);
        JOptionPane.showMessageDialog(null, "Registered.", title, JOptionPane.INFORMATION_MESSAGE);

        brandField.setText("");
        modelField.setText("");
        bodyKitCheckBox.setSelected(false);
        nitroCheckBox.setSelected(false);
        turboCheckBox.setSelected(false);
        camberCheckBox.setSelected(false);
        airSuspensionCheckBox.setSelected(false);
        pBuildCheckBox.setSelected(false);
        descriptionTextArea.setText("");
    }
    private void search() {
        searchTextArea.setText("");
        String queryText = queryField.getText();
        if (Objects.equals(queryText, "") || queryText == null) {
            JOptionPane.showMessageDialog(null, "Search empty.", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Map<String, String> result = null;
        for (Map<String, String> map : cars) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(queryText)) {
                    result = map;
                }
            }
        }
        if (result == null) {
            JOptionPane.showMessageDialog(null, "Nothing found on '" + queryText + "'.", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ArrayList<Boolean> optionalsB = new ArrayList<>();
        StringBuilder optionalsS = new StringBuilder();

        optionalsB.add(result.get("bodyKit").equals("true"));
        optionalsB.add(result.get("nitro").equals("true"));
        optionalsB.add(result.get("turbo").equals("true"));
        optionalsB.add(result.get("camber").equals("true"));
        optionalsB.add(result.get("air").equals("true"));
        optionalsB.add(result.get("pbuild").equals("true"));

        for (int i = 0; i < optionalsB.size(); i++) {
            boolean value = optionalsB.get(i);
            if (value) {
                switch (i) {
                    case 0:
                        optionalsS.append("Body Kit, ");
                        break;
                    case 1:
                        optionalsS.append("Nitro, ");
                        break;
                    case 2:
                        optionalsS.append("Turbo, ");
                        break;
                    case 3:
                        optionalsS.append("Camber, ");
                        break;
                    case 4:
                        optionalsS.append("Air Suspension, ");
                        break;
                    case 5:
                        optionalsS.append("Performance Build, ");
                        break;

                }
            }
        }
        if (optionalsS.length() > 2) {
            optionalsS = new StringBuilder(optionalsS.substring(0, optionalsS.length() - 2));
            optionalsS.append(".");
        }
        if (Objects.equals(result.get("desc"), "") || result.get("desc") == null) {
            if (optionalsS.length() < 2) {
                searchTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nStage: " + result.get("stage") +
                                "\nID: " + result.get("id")
                );
            } else {
                searchTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nOptionals: " + optionalsS +
                                "\nStage: " + result.get("stage") +
                                "\nID: " + result.get("id")
                );
            }
        } else {
            if (optionalsS.length() < 2) {
                searchTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nStage: " + result.get("stage") +
                                "\nDescription: " + result.get("desc") +
                                "\nID: " + result.get("id")
                );
            } else {
                searchTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nOptionals: " + optionalsS +
                                "\nStage: " + result.get("stage") +
                                "\nDescription: " + result.get("desc") +
                                "\nID: " + result.get("id")
                );
            }
        }

    }
    private void dSearch() {
        deleteTextArea.setText("");
        int idSearch = (Integer) idSpinner.getValue();
        if (idSearch == 0) {
            JOptionPane.showMessageDialog(null, "No available cars on ID 0.", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Map<String, String> result = null;
        for (Map<String, String> map : cars) {
            if(Double.parseDouble(map.get("id")) == idSearch) {
                result = map;
            }
        }
        if (result == null) {
            JOptionPane.showMessageDialog(null, "Nothing found on ID " + idSearch + ".", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ArrayList<Boolean> optionalsB = new ArrayList<>();
        StringBuilder optionalsS = new StringBuilder();

        optionalsB.add(result.get("bodyKit").equals("true"));
        optionalsB.add(result.get("nitro").equals("true"));
        optionalsB.add(result.get("turbo").equals("true"));
        optionalsB.add(result.get("camber").equals("true"));
        optionalsB.add(result.get("air").equals("true"));
        optionalsB.add(result.get("pbuild").equals("true"));

        for (int i = 0; i < optionalsB.size(); i++) {
            boolean value = optionalsB.get(i);
            if (value) {
                switch (i) {
                    case 0:
                        optionalsS.append("Body Kit, ");
                        break;
                    case 1:
                        optionalsS.append("Nitro, ");
                        break;
                    case 2:
                        optionalsS.append("Turbo, ");
                        break;
                    case 3:
                        optionalsS.append("Camber, ");
                        break;
                    case 4:
                        optionalsS.append("Air Suspension, ");
                        break;
                    case 5:
                        optionalsS.append("Performance Build, ");
                        break;

                }
            }
        }
        if (optionalsS.length() > 2) {
            optionalsS = new StringBuilder(optionalsS.substring(0, optionalsS.length() - 2));
            optionalsS.append(".");
        }
        if (Objects.equals(result.get("desc"), "") || result.get("desc") == null) {
            if (optionalsS.length() < 2) {
                deleteTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nStage: " + result.get("stage") +
                                "\nID: " + result.get("id")
                );
            } else {
                deleteTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nOptionals: " + optionalsS +
                                "\nStage: " + result.get("stage") +
                                "\nID: " + result.get("id")
                );
            }
        } else {
            if (optionalsS.length() < 2) {
                deleteTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nStage: " + result.get("stage") +
                                "\nDescription: " + result.get("desc") +
                                "\nID: " + result.get("id")
                );
            } else {
                deleteTextArea.setText(
                        "Brand: " + result.get("brand") +
                                "\nModel: " + result.get("model") +
                                "\nYear: " + result.get("year") +
                                "\nOptionals: " + optionalsS +
                                "\nStage: " + result.get("stage") +
                                "\nDescription: " + result.get("desc") +
                                "\nID: " + result.get("id")
                );
            }
        }

    }
    private void delete() {
        deleteTextArea.setText("");
        int idSearch = (Integer) idSpinner.getValue();
        if (idSearch == 0) {
            JOptionPane.showMessageDialog(null, "No available cars on ID 0.", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Map<String, String> result = null;
        for (Map<String, String> map : cars) {
            if(Double.parseDouble(map.get("id")) == idSearch) {
                result = map;
            }
        }
        if (result == null) {
            JOptionPane.showMessageDialog(null, "Nothing found on ID " + idSearch + ".", title, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Iterator<Map<String, String>> iterator = cars.iterator();
        while (iterator.hasNext()) {
            HashMap<String, String> map = (HashMap<String, String>) iterator.next();
            if (map.get("id").equals(Integer.toString(idSearch))) {
                iterator.remove();
            }
        }
        JOptionPane.showMessageDialog(null, "Deleted ID " + idSearch + ".", title, JOptionPane.INFORMATION_MESSAGE);
    }
}
