package ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import process.Reducer;

public class PictureReducerView extends JFrame {

	private JPanel contentPane;
    private JComboBox comboBox;
    private FileDialog fd = new FileDialog(this, "Open File", FileDialog.LOAD);
    
    public static JTextArea textArea = new DropTextArea();
    public static JSlider slider;
    public static Reducer rf = new Reducer();
    
    public PictureReducerView() {
        
        File file = new File("." + File.separator + "drog_jpg_images_here.png");
        ImageIcon tipImage = new ImageIcon(file.toString());
        final JLabel imageLabel = new JLabel(tipImage);
        
        setResizable(false);
        //setAlwaysOnTop(true);
        setTitle(Utils.UI_TITLE);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(0, 0, 640, 430);
        setBounds(0, 0, 576, 387);
        
        java.awt.Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getWidth(), dim.height / 2 - getHeight() * 5 / 6);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnFile = new JMenu(Utils.UI_FILE);
        menuBar.add(mnFile);
        
        JMenuItem mntmExit = new JMenuItem(Utils.UI_EXIT);
        mntmExit.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(NORMAL);
            }
        });
        
        JMenuItem mntmOpenFile = new JMenuItem(Utils.UI_OPEN_FILE);
        mntmOpenFile.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	fd.setAlwaysOnTop(true);
                fd.setMultipleMode(true);
                fd.setFile("*.jpg;*.jpeg");
                
                fd.setVisible(true);;
                
                if (fd.getFiles().length > 0) {
                    contentPane.add(textArea, BorderLayout.CENTER);
                    imageLabel.setVisible(false);
                    rf.add(fd.getFiles());
                }
            }
        });
        mnFile.add(mntmOpenFile);
        mnFile.add(mntmExit);
        
        JMenu mnHelp = new JMenu(Utils.UI_HELP);
        menuBar.add(mnHelp);
        
        JMenuItem mntmWebsite = new JMenuItem(Utils.UI_WEBSITE);
        mnHelp.add(mntmWebsite);
        
        mntmWebsite.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String url = Utils.UI_WEBSITE_URL;
                try {
                    if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                        Desktop.getDesktop().browse(java.net.URI.create(url));
                    else System.err.println("err");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        });
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        textArea.setEditable(false);
        
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                contentPane.add(textArea, BorderLayout.CENTER);
                imageLabel.setVisible(false);
            }
        });
        contentPane.add(imageLabel, BorderLayout.CENTER);
        
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);
        
        final JLabel lblRate = new JLabel("50%");
        lblRate.setVisible(false);
        
        slider = new JSlider();
        slider.setValue(0);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                lblRate.setText(String.valueOf(slider.getValue()) + "%");
            }
        });
        slider.setVisible(false);
        
        
        
        comboBox = new JComboBox();
        comboBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (0 == comboBox.getSelectedIndex()){
                    slider.setVisible(false);
                    lblRate.setVisible(false);
                    slider.setValue(0);
                } else if (1 == comboBox.getSelectedIndex()){
                    slider.setValue(50);
                    slider.setVisible(true);
                    lblRate.setVisible(true);
                }
            }
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[] {Utils.UI_EAMIL_HOMEWORK, Utils.UI_CUSTOMIZED}));
        
        panel.add(comboBox);
        panel.add(slider);
        panel.add(lblRate);
        
        
    }
    
    public static void main(String[] args) {
        
        try {
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (InstantiationException e1) {
          e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PictureReducerView frame = new PictureReducerView();
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
    }
}
