package ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class JpgFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        String ext = Utils.getExtension(file);
        if (file.isDirectory()
                || ext.equals(Utils.JPEG)
                || ext.equals(Utils.JPG))
            return true;
        
        return false;
    }

    @Override
    public String getDescription() {
        return Utils.UI_ONLY_JPG_IMAGE;
    }

}
