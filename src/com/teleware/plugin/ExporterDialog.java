package com.teleware.plugin;

import com.esri.arcgis.tools.SOEPackager;
import com.intellij.openapi.ui.Messages;
import org.bouncycastle.jcajce.provider.symmetric.DES;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ExporterDialog extends JDialog {

    private Description description;
    private String jarPath;
    private String jdkPath;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textJarPath;
    private JButton btnJar;
    private JButton btnJdk;
    private JTextField textJdkPath;
    private JTextField textSoePath;
    private JButton btnSoe;
    private JButton buttonProperty;

    public ExporterDialog(String jarPath, String jdkPath) {
        this.jarPath = jarPath;
        description = new Description(this.jarPath);
        File file = new File(jarPath);
        this.textSoePath.setText(file.getParent());
        this.jdkPath = jdkPath;
        textJdkPath.setText(jdkPath);
        textJarPath.setText(jarPath);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        btnJar.addActionListener(e -> onBtnJarClick());

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        buttonProperty.addActionListener(e -> onButtonPropertyClick());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            String[] args = new String[4];
            args[0] = textJarPath.getText();
            args[1] = textSoePath.getText();
            args[2] = textJdkPath.getText();
            args[3] = this.description.toString();
            SOEPackager.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onBtnJarClick() {
        FileDialog fileDialog = new FileDialog(this, "请选择jar文件", FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) -> name.endsWith(".jar"));
        fileDialog.setVisible(true);
        if (!fileDialog.getFile().equals(null)) {
            String path = fileDialog.getDirectory() + "\\" + fileDialog.getFile();
            Messages.showInfoMessage(path, "");
        }
    }

    private void onButtonPropertyClick() {
        PropertyDialog dialog = new PropertyDialog(this.description);
        dialog.setSize(new Dimension(600, 400));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        this.description = dialog.getDescription();
    }
}
