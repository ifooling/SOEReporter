package com.teleware.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by miaoj on 2017/2/28.
 */
public class ExporterAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        ExporterUI exportSoeMain = new ExporterUI();
        exportSoeMain.setVisible(true);
    }

    @Override
    public void update(AnActionEvent e) {
        String extension = getFileExtension(e.getDataContext());
        this.getTemplatePresentation().setEnabled(extension != null && "jar".equals(extension));
    }


    public static String getFileExtension(DataContext dataContext) {
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        return file == null ? null : file.getExtension();
    }
}
