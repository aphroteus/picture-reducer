package ui;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextArea;

public class DropTextArea extends JTextArea implements DropTargetListener {
        
    public DropTextArea(){
        new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this);
        
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        float h = (float) 180;
        float s = (float) 0.8;
        float b = (float) 50;
        this.setBackground(Color.getHSBColor(h, s, b));
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        this.setBackground(Color.WHITE);
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    	
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        this.setBackground(Color.WHITE);
        try {
            
            DataFlavor df = DataFlavor.javaFileListFlavor;
            
            if (dtde.isDataFlavorSupported(df)) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                List<File> list = (List<File>)dtde.getTransferable().getTransferData(df);
                Iterator<File> iterator =  list.iterator();
                
                while (iterator.hasNext()) {
                    File file = (File) iterator.next();
                    String ext = Utils.getExtension(file);
                    if (file.isFile() && (Utils.JPG.equals(ext)
                            || Utils.JPEG.equals(ext)) && !PictureReducerView.rf.contains(file))
                        PictureReducerView.rf.add(file);
                    else
                        System.err.println("NO-OP");
                    
                }
                
                dtde.dropComplete(true);
                this.updateUI();
                
            } else {
                dtde.rejectDrop();
                System.err.println(Utils.DROP_IS_NOT_SUPPORTED);
                PictureReducerView.textArea.append(Utils.DROP_IS_NOT_SUPPORTED + "\n");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
        }
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        
    }
    

}
