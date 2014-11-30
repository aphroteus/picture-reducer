package process;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import ui.PictureReducerView;
import ui.Utils;

public class Reducer {
    private LinkedList<File> todoList = new LinkedList<File>();
    private LinkedList<File> doneList = new LinkedList<File>();
    
    public Reducer() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    if (todoList.size() != 0) {
                        
                        reduce(PictureReducerView.slider.getValue());
                        for (int i = 0; i < doneList.size(); i++)
                            PictureReducerView.textArea.append(doneList.removeFirst().getName() + "\n");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public void reduce(int scaleValue) {
        
        File file = null;
        
        while (todoList.size() != 0 && (file = todoList.getFirst()) != null) {
            
            File folder = new File(file.getParent() + File.separator +  "reduce");
            if ( !folder.exists())
                folder.mkdir();
            
            String newFilePath = folder.getAbsolutePath()
                    + File.separator
                    + file.getName().substring(0, Utils.getDotIndex(file)) + "_by Photo_Reduce." + Utils.JPG;
            
            BufferedImage original;
            try {
                original = ImageIO.read(file);
                
                double scale = 1;
                if (scaleValue == 0)
                    scale = (double) 640 / original.getWidth();
                else
                    scale = (double) scaleValue / 100;
                
                
                final int width = new Double(original.getWidth() * scale).intValue();
                final int height = new Double(original.getHeight() * scale).intValue();
                final Image rescaled = original.getScaledInstance(width, height, Image.SCALE_FAST);
                final BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                final Graphics2D g2d = result.createGraphics();
                g2d.drawImage(rescaled, 0, 0, null);
                g2d.dispose();
                
                ImageIO.write(result, Utils.JPG, new File(newFilePath));
                doneList.add(todoList.removeFirst());
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean add(File file) {
        return todoList.add(file);
    }
    
    public boolean add(File[] files) {
        for (int i = 0; i < files.length; i++) {
            if ( !todoList.add(files[i]))
                return false;
        }
        return true;
    }
    
    public boolean contains(File file) {
        return todoList.contains(file);
    }
    
    
}
