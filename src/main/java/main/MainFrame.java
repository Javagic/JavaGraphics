package main;

import main.rendering.Luminosity;
import main.rendering.ZBuffer;
import main.utils.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame{

    private JMenuItem mExitItem, mOpenFileItem;
    private JMenuBar mMenuBar;
    private JMenu mMenu;
    private Checkbox mIntensity, mTexture;
    private String PATH_OBJ;
    private JLabel mImageLabel;
    private JPanel mPanel;
    private JButton mStartBtn;

    static int width = 1024;
    static int height = 1024;
    static BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    static BufferedImage diffuseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


    public MainFrame(String title){
        initFrame();
        createMenu();

        initSettingsOfFrame(title);
    }
    // Инициализация компанентов на главном экране
    private void initFrame(){
        mPanel = new JPanel();

        mStartBtn = new JButton("Обработать");
        mStartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPathObj(PATH_OBJ))
                    start();
            }
        });
        if(PATH_OBJ != null)
            mStartBtn.setEnabled(true);
        else
            mStartBtn.setEnabled(false);

        mPanel.add(mStartBtn);

        mIntensity = new Checkbox("Освященность");
        mPanel.add(mIntensity);

        mTexture = new Checkbox("Текстуры");
        mPanel.add(mTexture);


        mImageLabel = new JLabel();
        mPanel.add(mImageLabel);

        getContentPane().add(mPanel);
    }
    // Создание верхнего меню
    private void createMenu(){
        mOpenFileItem = new JMenuItem("Open");
        mOpenFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    // TODO: 07.04.2018 здесь нужен тест
                    if(checkPathObj(file.getPath())) {
                        PATH_OBJ = file.getPath();
                        mStartBtn.setEnabled(true);
                        mIntensity.setEnabled(true);
                        mTexture.setEnabled(true);
                    } else {
                        PATH_OBJ = null;
                        mStartBtn.setEnabled(false);
                        mIntensity.setEnabled(false);
                        mTexture.setEnabled(false);
                    }
                }
            }
        });
        mExitItem = new JMenuItem("Exit");
        mExitItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mMenu = new JMenu("File");
        mMenu.add(mOpenFileItem);
        mMenu.add(mExitItem);

        mMenuBar = new JMenuBar();
        mMenuBar.add(mMenu);
    }
    // Стандартная настройка JFrame
    private void initSettingsOfFrame(String title){
        setTitle(title);
        setJMenuBar(mMenuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // TODO: 07.04.2018 Проверка на корректно введеный путь <Имя_файла>.obj
    private boolean checkPathObj(String PATH){
        return true;
    }
    // Отрисовка итогового изображения
    private void showResultImage(BufferedImage image){
        mImageLabel.setIcon(new ImageIcon(image));
        mImageLabel.setBounds(10,10,27,30);
        mImageLabel.repaint();
        //repaint();
    }
    private void start(){
        mImageLabel.setIcon(null);

        new OrtoManager(image, 1).init();
        Parser.readFile(PATH_OBJ);

        List<Face> faces = Parser.getFaces();
        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++)
                image.setRGB(j, i, Color.BLACK.getRGB());
        }
        try {
            diffuseImage = ((BufferedImage) TGAReader.getImage("src/main/resources/african_head_diffuse .tga"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Luminosity.Companion.render(faces, new ZBuffer(image, diffuseImage));

        showResultImage(image);
        endProcessing();
        ImageUtils.saveImage(image);

    }
    private void endProcessing(){
        mIntensity.setEnabled(false);
        mTexture.setEnabled(false);
        mStartBtn.setEnabled(false);
    }
}
