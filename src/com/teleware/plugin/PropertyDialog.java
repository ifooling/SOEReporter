package com.teleware.plugin;

import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PropertyDialog extends JDialog {
    private Description description;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textName;
    private JTextField textDescription;
    private JTextField textVersion;
    private JTextField textAuthor;
    private JTextField textCompany;
    private JTextField textDate;
    private JTextField textTargets;
    private JTextField textLibjars;
    private JButton buttonThirdJar;

    public PropertyDialog(Description description) {
        this.description = description;
        textName.setText(description.getName());
        textDescription.setText(description.getDescription());
        textVersion.setText(description.getVersion());
        textAuthor.setText(description.getAuthor());
        textCompany.setText(description.getCompany());
        textDate.setText(description.getDate());
        textTargets.setText(description.getTarget());
        textLibjars.setText(description.getLibJarsPath());

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        buttonThirdJar.addActionListener(e -> onButtonThirdJarClick());

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

    private void onButtonThirdJarClick() {
        FileDialog fileDialog = new FileDialog(this, "请选择三方jar文件", FileDialog.LOAD);
        fileDialog.setFilenameFilter((dir, name) -> name.endsWith(".jar"));
        fileDialog.setFile("*.jar");
        fileDialog.setDirectory(".");
        fileDialog.setMultipleMode(true);
        fileDialog.setVisible(true);
        if (!fileDialog.getFiles().equals(null)) {
            String jarValue = StringUtils.join(fileDialog.getFiles(), " ");
            textLibjars.setText(jarValue);
        }
    }

    private void onOK() {
        // add your code here
        this.description.setAuthor(textAuthor.getText());
        this.description.setCompany(textCompany.getText());
        this.description.setDate(textDate.getText());
        this.description.setDescription(textDescription.getText());
        this.description.setName(textName.getText());
        this.description.setTarget(textTargets.getText());
        this.description.setVersion(textVersion.getText());
        this.description.setLibJarsPath(textLibjars.getText());

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Description getDescription() {
        return description;
    }
}
