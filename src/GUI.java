import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame {
    static JFrame mainFrame;
    static JPanel rectanglePan;

    static Color color;
    static double[] cmyk;
    static int[] rgb;
    static double[] hsl;
    static JTextField cyanField;
    static JSlider cyanSlider;
    static JTextField magentaField;
    static JSlider magentaSlider;
    static JTextField yellowField;
    static JSlider yellowSlider;
    static JTextField blackField;
    static JSlider blackSlider;
    static JTextField redField;
    static JSlider redSlider;
    static JTextField greenField;
    static JSlider greenSlider;
    static JTextField blueField;
    static JSlider blueSlider;
    static JTextField codeField;
    static JTextField hueField;
    static JSlider hueSlider;
    static JTextField saturationField;
    static JSlider saturationSlider;
    static JTextField lightnessField;
    static JSlider lightnessSlider;
    public GUI(String name) {
        mainFrame = new JFrame(name);
        JColorChooser JCCh = new JColorChooser();
        JPanel panel1 = new JPanel();
        panel1.add(JCCh);
        ArrayList<AbstractColorChooserPanel> list = new ArrayList<>();
        for (var pan : JCCh.getChooserPanels()) {
            if (!pan.getDisplayName().equals("HSV") && !pan.getDisplayName().equals("Swatches")) {
                list.add(pan);
            }
            JCCh.removeChooserPanel(pan);
        }
        JCCh.addChooserPanel(list.get(2));
        JCCh.addChooserPanel(list.get(1));
        JCCh.addChooserPanel(list.get(0));
        JCCh.setPreviewPanel(new JPanel());

        mainFrame.add(panel1);
        //mainFrame.setLayout(new BorderLayout());

        mainFrame.setSize(800,400);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GUI(int i) {
        //Set default values
        color = Color.BLACK;
        cmyk = new double[4];
        cmyk[3] = 100;
        rgb = new int[3];
        hsl = new double[3];


        mainFrame = new JFrame("Lab1: Color Models");
        rectanglePan = new JPanel();
        mainFrame.setLayout(new FlowLayout());
        Container container = new Container();
        container.setLayout(new GridLayout(2,1, 10, 10));

        JTabbedPane tabbedPane = new JTabbedPane();

        //CMYK
        JPanel cmykPan = new JPanel();
        cmykPan.setLayout(new BoxLayout(cmykPan, BoxLayout.X_AXIS));
        //Cyan
        JLabel cyanLabel = new JLabel("Cyan");

        cyanField = new JTextField(3);
        cyanField.setText("0");
        cyanSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

        JPanel cyanLabelPan = new JPanel();
        cyanLabelPan.add(cyanLabel);
        JPanel cyanFieldPan = new JPanel();
        cyanFieldPan.add(cyanField);
        JPanel cyanSliderPan = new JPanel();
        cyanSliderPan.add(cyanSlider);

        cyanField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = cyanField.getText();
                int l = value.length();
                cyanField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        cyanField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = cyanField.getText();
                int value = Integer.parseInt(str);
                if (value > 100) {
                    value = 100;
                    cyanField.setText("100");
                }
                cmyk[0] = value;
                cyanSlider.setValue(value);
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        cyanSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                cyanField.setText(Integer.toString(value));
                cmyk[0] = value;
                if (source.getValueIsAdjusting()) {
                    TransformFunctions.cmykToRgb(cmyk, rgb);
                    TransformFunctions.rgbToHsl(rgb, hsl);
                    updateColors();
                }
            }
        });
        //Magenta
        JLabel magentaLabel = new JLabel("Magenta");
        magentaField = new JTextField(3);
        magentaField.setText("0");
        magentaSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

        JPanel magentaLabelPan = new JPanel();
        magentaLabelPan.add(magentaLabel);
        JPanel magentaFieldPan = new JPanel();
        magentaFieldPan.add(magentaField);
        JPanel magentaSliderPan = new JPanel();
        magentaSliderPan.add(magentaSlider);

        magentaField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = magentaField.getText();
                int l = value.length();
                magentaField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        magentaField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = magentaField.getText();
                int value = Integer.parseInt(str);
                if (value > 100) {
                    value = 100;
                    magentaField.setText("100");
                }
                magentaSlider.setValue(value);
                cmyk[1] = value;
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        magentaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                magentaField.setText(Integer.toString(value));
                cmyk[1] = value;
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        //Yellow
        JLabel yellowLabel = new JLabel("Yellow");
        yellowField = new JTextField(3);
        yellowField.setText("0");
        yellowSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

        JPanel yellowLabelPan = new JPanel();
        yellowLabelPan.add(yellowLabel);
        JPanel yellowFieldPan = new JPanel();
        yellowFieldPan.add(yellowField);
        JPanel yellowSliderPan = new JPanel();
        yellowSliderPan.add(yellowSlider);

        yellowField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = yellowField.getText();
                int l = value.length();
                yellowField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        yellowField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = yellowField.getText();
                int value = Integer.parseInt(str);
                if (value > 100) {
                    value = 100;
                    yellowField.setText("100");
                }
                yellowSlider.setValue(value);
                cmyk[2] = value;
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        yellowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                yellowField.setText(Integer.toString(value));
                cmyk[2] = value;
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });

        //Black
        JLabel blackLabel = new JLabel("Black");
        blackField = new JTextField(3);
        blackField.setText("100");
        blackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);

        JPanel blackLabelPan = new JPanel();
        blackLabelPan.add(blackLabel);
        JPanel blackFieldPan = new JPanel();
        blackFieldPan.add(blackField);
        JPanel blackSliderPan = new JPanel();
        blackSliderPan.add(blackSlider);

        blackField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = blackField.getText();
                int l = value.length();
                blackField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        blackField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = blackField.getText();
                int value = Integer.parseInt(str);
                if (value > 100) {
                    value = 100;
                    blackField.setText("100");
                }
                blackSlider.setValue(value);
                cmyk[3] = value;
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        blackSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                blackField.setText(Integer.toString(value));
                cmyk[3] = value;
                TransformFunctions.cmykToRgb(cmyk, rgb);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });

        JPanel cmykLabelPan = new JPanel();
        cmykLabelPan.setLayout(new GridLayout(4,1, 10, 10));
        cmykLabelPan.add(cyanLabelPan);
        cmykLabelPan.add(magentaLabelPan);
        cmykLabelPan.add(yellowLabelPan);
        cmykLabelPan.add(blackLabelPan);

        JPanel cmykFieldPan = new JPanel();
        cmykFieldPan.setLayout(new GridLayout(4,1, 10, 10));
        cmykFieldPan.add(cyanFieldPan);
        cmykFieldPan.add(magentaFieldPan);
        cmykFieldPan.add(yellowFieldPan);
        cmykFieldPan.add(blackFieldPan);

        JPanel cmykSliderPan = new JPanel();
        cmykSliderPan.setLayout(new GridLayout(4,1, 10, 10));
        cmykSliderPan.add(cyanSliderPan);
        cmykSliderPan.add(magentaSliderPan);
        cmykSliderPan.add(yellowSliderPan);
        cmykSliderPan.add(blackSliderPan);

        cmykPan.add(cmykLabelPan);
        cmykPan.add(cmykFieldPan);
        cmykPan.add(cmykSliderPan);

        //RGB
        JPanel rgbPanNorth = new JPanel();
        rgbPanNorth.setLayout(new BoxLayout(rgbPanNorth, BoxLayout.X_AXIS));
        //Red
        JLabel redLabel = new JLabel("Red");
        JPanel redLabelPan = new JPanel();
        redLabelPan.add(redLabel);
        redField = new JTextField(3);
        redField.setText("0");
        JPanel redFieldPan = new JPanel();
        redFieldPan.add(redField);
        redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        JPanel redSliderPan = new JPanel();
        redSliderPan.add(redSlider);
        redField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = redField.getText();
                int l = value.length();
                redField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        redField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = redField.getText();
                int value = Integer.parseInt(str);
                if (value > 255) {
                    value = 255;
                    redField.setText("255");
                }
                redSlider.setValue(value);
                rgb[0] = value;
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        redSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                redField.setText(Integer.toString(value));
                rgb[0] = value;
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        //Green
        JLabel greenLabel = new JLabel("Green");
        JPanel greenLabelPan = new JPanel();
        greenLabelPan.add(greenLabel);
        greenField = new JTextField(3);
        greenField.setText("0");
        JPanel greenFieldPan = new JPanel();
        greenFieldPan.add(greenField);
        greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        JPanel greenSliderPan = new JPanel();
        greenSliderPan.add(greenSlider);
        greenField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = greenField.getText();
                int l = value.length();
                greenField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        greenField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = greenField.getText();
                int value = Integer.parseInt(str);
                if (value > 255) {
                    value = 255;
                    greenField.setText("255");
                }
                greenSlider.setValue(value);
                rgb[1] = value;
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        greenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                greenField.setText(Integer.toString(value));
                rgb[1] = value;
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        //Blue
        JLabel blueLabel = new JLabel("Blue");
        JPanel blueLabelPan = new JPanel();
        blueLabelPan.add(blueLabel);
        blueField = new JTextField(3);
        blueField.setText("0");
        JPanel blueFieldPan = new JPanel();
        blueFieldPan.add(blueField);
        blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
        JPanel blueSliderPan = new JPanel();
        blueSliderPan.add(blueSlider);
        blueField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = blueField.getText();
                int l = value.length();
                blueField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        blueField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = blueField.getText();
                int value = Integer.parseInt(str);
                if (value > 255) {
                    value = 255;
                    blueField.setText("255");
                }
                blueSlider.setValue(value);
                rgb[2] = value;
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        blueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                blueField.setText(Integer.toString(value));
                rgb[2] = value;
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                TransformFunctions.rgbToHsl(rgb, hsl);
                updateColors();
            }
        });
        //code
        JLabel codeLabel = new JLabel("Color Code");
        JPanel codeLabelPan = new JPanel();
        codeLabelPan.add(codeLabel);
        codeField = new JTextField(7);
        codeField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = codeField.getText();
                int l = value.length();
                if (l == 1 && (ke.getKeyCode() == KeyEvent.VK_DELETE || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
                    codeField.setEditable(false);
                }
                else if (((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') || (ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'f') || ke.getKeyChar() == '#') && l < 7 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE) {
                    codeField.setEditable(true);
                }
                else {
                    codeField.setEditable(false);
                }
            }
        });
        String hex = "#" + Integer.toHexString(color.getRGB()).substring(2);
        codeField.setText(hex);
        codeLabelPan.add(codeField);

        JPanel rgbPanSouth = new JPanel();
        rgbPanSouth.setLayout(new BoxLayout(rgbPanSouth, BoxLayout.X_AXIS));
        rgbPanSouth.add(codeLabelPan);

        JPanel rgbLabelPan = new JPanel();
        rgbLabelPan.setLayout(new GridLayout(3,1, 10, 10));
        rgbLabelPan.add(redLabelPan);
        rgbLabelPan.add(greenLabelPan);
        rgbLabelPan.add(blueLabelPan);

        JPanel rgbFieldPan = new JPanel();
        rgbFieldPan.setLayout(new GridLayout(3,1, 10, 10));
        rgbFieldPan.add(redFieldPan);
        rgbFieldPan.add(greenFieldPan);
        rgbFieldPan.add(blueFieldPan);

        JPanel rgbSliderPan = new JPanel();
        rgbSliderPan.setLayout(new GridLayout(3,1, 10, 10));
        rgbSliderPan.add(redSliderPan);
        rgbSliderPan.add(greenSliderPan);
        rgbSliderPan.add(blueSliderPan);

        rgbPanNorth.add(rgbLabelPan);
        rgbPanNorth.add(rgbFieldPan);
        rgbPanNorth.add(rgbSliderPan);

        JPanel rgbPanMain = new JPanel();
        rgbPanMain.setLayout(new BoxLayout(rgbPanMain, BoxLayout.Y_AXIS));
        rgbPanMain.add(rgbPanNorth);
        rgbPanMain.add(rgbPanSouth);

        //HLS
        JPanel hlsPan = new JPanel();
        hlsPan.setLayout(new BoxLayout(hlsPan, BoxLayout.X_AXIS));
        //Hue
        JLabel hueLabel = new JLabel("Hue");
        hueField = new JTextField(3);
        hueField.setText("0");
        hueSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);

        JPanel hueLabelPan = new JPanel();
        hueLabelPan.add(hueLabel);
        JPanel hueFieldPan = new JPanel();
        hueFieldPan.add(hueField);
        JPanel hueSliderPan = new JPanel();
        hueSliderPan.add(hueSlider);

        hueField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = hueField.getText();
                int l = value.length();
                hueField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        hueField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = hueField.getText();
                int value = Integer.parseInt(str);
                if (value > 360) {
                    value = 360;
                    hueField.setText("360");
                }
                hueSlider.setValue(value);
                hsl[0] = value;
                TransformFunctions.hslToRgb(hsl, rgb);
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                updateColors();
            }
        });
        hueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                hueField.setText(Integer.toString(value));
                hsl[0] = value;
                TransformFunctions.hslToRgb(hsl, rgb);
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                updateColors();
            }
        });
        //Saturation
        JLabel saturationLabel = new JLabel("Saturation");
        saturationField = new JTextField(3);
        saturationField.setText("0");
        saturationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

        JPanel saturationLabelPan = new JPanel();
        saturationLabelPan.add(saturationLabel);
        JPanel saturationFieldPan = new JPanel();
        saturationFieldPan.add(saturationField);
        JPanel saturationSliderPan = new JPanel();
        saturationSliderPan.add(saturationSlider);

        saturationField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = saturationField.getText();
                int l = value.length();
                saturationField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        saturationField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = saturationField.getText();
                int value = Integer.parseInt(str);
                if (value > 100) {
                    value = 100;
                    saturationField.setText("100");
                }
                saturationSlider.setValue(value);
                hsl[1] = value;
                TransformFunctions.hslToRgb(hsl, rgb);
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                updateColors();
            }
        });
        saturationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                saturationField.setText(Integer.toString(value));
                hsl[1] = value;
                TransformFunctions.hslToRgb(hsl, rgb);
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                updateColors();
            }
        });
        //Lightness
        JLabel lightnessLabel = new JLabel("Lightness");
        lightnessField = new JTextField(3);
        lightnessField.setText("0");
        lightnessSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);

        JPanel lightnessLabelPan = new JPanel();
        lightnessLabelPan.add(lightnessLabel);
        JPanel lightnessFieldPan = new JPanel();
        lightnessFieldPan.add(lightnessField);
        JPanel lightnessSliderPan = new JPanel();
        lightnessSliderPan.add(lightnessSlider);

        lightnessField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                String value = lightnessField.getText();
                int l = value.length();
                lightnessField.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' && l < 3 || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE ||
                        ke.getKeyCode() == KeyEvent.VK_DELETE);
            }
        });
        lightnessField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = lightnessField.getText();
                int value = Integer.parseInt(str);
                if (value > 100) {
                    value = 100;
                    lightnessField.setText("100");
                }
                lightnessSlider.setValue(value);
                hsl[2] = value;
                TransformFunctions.hslToRgb(hsl, rgb);
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                updateColors();
            }
        });
        lightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    return;
                }
                int value = source.getValue();
                lightnessField.setText(Integer.toString(value));
                hsl[2] = value;
                TransformFunctions.hslToRgb(hsl, rgb);
                TransformFunctions.rgbToCmyk(rgb, cmyk);
                updateColors();
            }
        });
        JPanel hlsLabelPan = new JPanel();
        hlsLabelPan.setLayout(new GridLayout(3,1, 10, 10));
        hlsLabelPan.add(hueLabelPan);
        hlsLabelPan.add(saturationLabelPan);
        hlsLabelPan.add(lightnessLabelPan);

        JPanel hlsFieldPan = new JPanel();
        hlsFieldPan.setLayout(new GridLayout(3,1, 10, 10));
        hlsFieldPan.add(hueFieldPan);
        hlsFieldPan.add(saturationFieldPan);
        hlsFieldPan.add(lightnessFieldPan);

        JPanel hlsSliderPan = new JPanel();
        hlsSliderPan.setLayout(new GridLayout(3,1, 10, 10));
        hlsSliderPan.add(hueSliderPan);
        hlsSliderPan.add(saturationSliderPan);
        hlsSliderPan.add(lightnessSliderPan);

        hlsPan.add(hlsLabelPan);
        hlsPan.add(hlsFieldPan);
        hlsPan.add(hlsSliderPan);

        //Adding Tabs
        tabbedPane.addTab("CMYK", cmykPan);
        tabbedPane.addTab("RGB", rgbPanMain);
        tabbedPane.addTab("HLS", hlsPan);


        container.add(tabbedPane);

        rectanglePan.setSize(400, 200);
        rectanglePan.setBackground(color);
        container.add(rectanglePan);

        mainFrame.add(container);

        mainFrame.setSize(800,500);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void updateColors() {
        //cmyk
        cyanField.setText(Integer.toString((int)Math.round(cmyk[0])));
        cyanSlider.setValue((int)Math.round(cmyk[0]));
        magentaField.setText(Integer.toString((int)Math.round(cmyk[1])));
        magentaSlider.setValue((int)Math.round(cmyk[1]));
        yellowField.setText(Integer.toString((int)Math.round(cmyk[2])));
        yellowSlider.setValue((int)Math.round(cmyk[2]));
        blackField.setText(Integer.toString((int)Math.round(cmyk[3])));
        blackSlider.setValue((int)Math.round(cmyk[3]));
        //rgb
        redField.setText(Integer.toString(rgb[0]));
        redSlider.setValue(rgb[0]);
        greenField.setText(Integer.toString(rgb[1]));
        greenSlider.setValue(rgb[1]);
        blueField.setText(Integer.toString(rgb[2]));
        blueSlider.setValue(rgb[2]);
        //hsl
        hueField.setText(Integer.toString((int)Math.round(hsl[0])));
        hueSlider.setValue((int)Math.round(hsl[0]));
        saturationField.setText(Integer.toString((int)Math.round(hsl[1])));
        saturationSlider.setValue((int)Math.round(hsl[1]));
        lightnessField.setText(Integer.toString((int)Math.round(hsl[2])));
        lightnessSlider.setValue((int)Math.round(hsl[2]));
        //color
        color = new Color(rgb[0], rgb[1], rgb[2]);
        rectanglePan.setBackground(color);
        String hex = "#" + Integer.toHexString(color.getRGB()).substring(2);
        codeField.setText(hex);
    }
}