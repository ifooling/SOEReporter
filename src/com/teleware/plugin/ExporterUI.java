package com.teleware.plugin;

import com.esri.arcgis.tools.SOEPackager;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ExporterUI extends JDialog {

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

    public ExporterUI(String jarPath, String jdkPath) {
        this.jarPath = jarPath;
        File file = new File(jarPath);
        this.textSoePath.setText(file.getParent());
        this.jdkPath = jdkPath;
        textJdkPath.setText(jdkPath);
        textJarPath.setText(jarPath);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        btnJar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onBtnJarClick();
            }
        });

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
            args[3] = "Name=SimpleRESTSOE,Description=,Version=1.0,Author=Mo,Company=Company,Date=2017/03/01 周三,Targets=10.2,libjars=";
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
}
