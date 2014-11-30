package ui;

import java.io.File;

public class Utils {
    
    /* UI */
    public final static String UI_TITLE          = "Picture Reducer";
    public final static String UI_FILE           = "File";
    public final static String UI_EXIT           = "Exit";
    public final static String UI_OPEN_FILE      = "Open File...";
    public final static String UI_EDIT           = "Edit";
    public final static String UI_AUTO_MODE      = "Auto-Mode";
    public final static String UI_HELP           = "Help";
    public final static String UI_About          = "About";
    public final static String UI_WEBSITE        = "On GitHub";
    public final static String UI_EAMIL_HOMEWORK = "Email or Homework (Size: 640x?)";
    public final static String UI_CUSTOMIZED     = "Customized";
    public final static String UI_WEBSITE_URL       = "https://github.com/aphroteus/picture_reducer";
    
    /* Drop Area */
    public final static String DROP_IS_NOT_SUPPORTED = "Drop is NOT supported!";
    
    /* Filter */
    public final static String UI_ONLY_JPG_IMAGE     = "Only JPG/JPEG Image";
    
    /* File Extension */
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    
    public static int getDotIndex(File file) {
        return file.getName().lastIndexOf(".");
    }
    
    public static String getExtension(File file) {
        return file.getName().substring(Utils.getDotIndex(file) + 1).toLowerCase();
    }
}
