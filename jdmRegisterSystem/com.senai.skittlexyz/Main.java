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
import java.util.Collections;

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

    public Main() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI("http://github.com/skittlexyz/"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (URISyntaxException ex) {
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
        frame.setLocationRelativeTo(null);
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
}
