package main.ui;

import main.helpers.OrtoManager;
import main.helpers.Parser;
import main.helpers.TGAReader;
import main.rendering.BackFaceCulling;
import main.rendering.Luminosity;
import main.rendering.ZBuffer;
import main.shape.primtives.Face;
import main.utils.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import java.io.File;

public class MainFrame extends JFrame {

    private JMenuItem mExitItem, mOpenFileItem;
    private JMenuBar mMenuBar;
    private JMenu mMenu;
    private Checkbox mIntensity, mTexture, mBackFaceCulling;
    private String PATH_OBJ;
    private JLabel mImageLabel;
    private JPanel mPanel;
    private JButton mStartBtn;

    static int width = 800;
    static int height = 800;
    static BufferedImage image = generateImage(width, height);
    static BufferedImage diffuseImage = generateImage(width, height);
    private double[] zBuffer = new double[image.getHeight() * image.getWidth()];

    public MainFrame(String title) {
        initFrame();
        createMenu();

        initSettingsOfFrame(title);
    }

    // Инициализация компанентов на главном экране
    private void initFrame() {
        mPanel = new JPanel();
//
        mStartBtn = new JButton("Обработать");
        mStartBtn.addActionListener(e -> {
            if (new File(PATH_OBJ).exists())
                start();
        });
        if (PATH_OBJ != null)
            mStartBtn.setEnabled(true);
        else
            mStartBtn.setEnabled(false);

        mPanel.add(mStartBtn);

        mIntensity = new Checkbox("Освещенность");
        mPanel.add(mIntensity);

        mTexture = new Checkbox("Текстуры");
        mPanel.add(mTexture);

        mBackFaceCulling = new Checkbox("Отсечение не лицевых граней");
        mPanel.add(mBackFaceCulling);

        mImageLabel = new JLabel();
        mPanel.add(mImageLabel);

        getContentPane().add(mPanel);
    }

    // Создание верхнего меню
    private void createMenu() {
        mOpenFileItem = new JMenuItem("Open");
        mOpenFileItem.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            fileopen.setCurrentDirectory(new File("src/main/resources/"));
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                if (file.exists()) {
                    PATH_OBJ = file.getPath();
                    addStartListener(mIntensity);
                    addStartListener(mTexture);
                    addStartListener(mBackFaceCulling);
                } else {
                    PATH_OBJ = null;
                }
            }
        });
        mExitItem = new JMenuItem("Exit");
        mExitItem.addActionListener(e -> System.exit(0));

        mMenu = new JMenu("File");
        mMenu.add(mOpenFileItem);
        mMenu.add(mExitItem);

        mMenuBar = new JMenuBar();
        mMenuBar.add(mMenu);
    }

    // Стандартная настройка JFrame
    private void initSettingsOfFrame(String title) {
        setTitle(title);
        setJMenuBar(mMenuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Отрисовка итогового изображения
    private void showResultImage(BufferedImage image) {
        mImageLabel.setIcon(new ImageIcon(image));
        mImageLabel.setBounds(10, 10, 27, 30);
        mImageLabel.repaint();
        //repaint();
    }

    private void start() {
        clearBuffer();
        mImageLabel.setIcon(null);

        new OrtoManager(image, 1).init();
        Parser.readFile(PATH_OBJ);

        List<Face> faces = Parser.getFaces();
        for (int i = 0; i < image.getHeight() - 1; i++) {
            for (int j = 0; j < image.getWidth() - 1; j++)
                image.setRGB(j, i, Color.BLACK.getRGB());
        }

        diffuseImage = ((BufferedImage) generateDiffuseImage(PATH_OBJ));

        if (mBackFaceCulling.getState()) {
            Luminosity.Companion.render(faces, new BackFaceCulling(image), mIntensity.getState());
        } else {
            Luminosity.Companion.render(faces, new ZBuffer(image, diffuseImage, mTexture.getState(), zBuffer), mIntensity.getState());
        }

        showResultImage(image);
        ImageUtils.saveImage(image);
    }


    private void addStartListener(Checkbox cb) {
        cb.addItemListener(e -> {
            start();
        });
    }

    private Image generateDiffuseImage(String path) {
        String diffusePath = path.replace(".obj", "_diffuse.tga");
        if (!new File(diffusePath).exists()) return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return TGAReader.getImage(diffusePath, width, height);
    }

    public static BufferedImage generateImage(int width, int height) {
        if (width <= 0 || height <= 0) {
            width = 100;
            height = 100;
        }
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    private void clearBuffer() {
        Arrays.fill(zBuffer, Double.NEGATIVE_INFINITY);
    }
}
