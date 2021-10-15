package ru.vsu.cs.course1;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ListIterator;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JTable coordinates;
    private JButton paint;
    private JLabel mistakeLabel;
    private JLabel squareLabel;
    private JLabel pLabel;
    private JTextField xField;
    private JTextField yField;
    private JButton moveBut;
    private JLabel rectMistakeLabel;
    private JLabel moveMistLabel;
    private JTextField scaleField;
    private JButton ScaleTopX;
    private JButton ScaleTopY;
    private JButton ScaleBotX;
    private JButton ScaleBotY;
    private JButton ScaleLeftX;
    private JButton ScaleLeftY;
    private JButton ScaleRightY;
    private JButton ScaleRightX;
    private JButton Scale;
    private JButton ScaleCenterX;
    private JButton ScaleCenterY;
    private JButton clearBut;
    private JLabel scaleMistLabel;
    private JLabel saveMistLabel;
    private JPanel figures;
    private JButton rotateButton;
    private JTextField CornerField;
    private JButton MatrixRotatebutton;
    private JButton MatrixScalebutton;
    private JTable parameters;
    private JLabel ErrormatrixLabel;
    private JButton TranslateMatrixButton;
    private JButton ClearMatrixButton;


    private int imageWidth = 600;
    private int imageHeight = 300;
    private int stepH = 150;
    private int stepW = 300;
    private int scale = 10;

    Figure fig = new Figure(new ArrayList<>());
    Figure addit = new Figure(new ArrayList<>());


    private void drawFigure (Graphics g, Color color, Figure f) {
        g.setColor(color);
        ListIterator<Point> lt = f.getCoordinates().listIterator();
        Point firstPoint = lt.next();
        Point prev = firstPoint;
        while (lt.hasNext()) {
            Point curr = lt.next();
            g.drawLine((int)prev.getX()*scale + stepW, (int)prev.getY()*scale + stepH, (int)curr.getX()*scale + stepW, (int)curr.getY()*scale + stepH);
            prev = curr;
        }
        g.drawLine((int)firstPoint.getX()*scale + stepW, (int)firstPoint.getY()*scale + stepH, (int)prev.getX()*scale + stepW, (int)prev.getY()*scale + stepH);
    }

    private void clearFigures (Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imageWidth*10, imageHeight*10);
        g.setColor(Color.BLACK);
        g.drawLine(imageWidth / 2, 0, imageWidth / 2, imageHeight);
        g.drawLine(0, imageHeight / 2, imageWidth, imageHeight / 2);
    }

    public FrameMain() {
        this.setTitle("График");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        int[][] arr = new int[2][3];
        JTableUtils.initJTableForArray(coordinates, 30, true, true, false, true);
        coordinates.setRowHeight(30);
        JTableUtils.writeArrayToJTable(coordinates, arr);

        double [][] atributs = new double[3][3];
        JTableUtils.initJTableForArray(parameters, 100, false, false, false, false);
        parameters.setRowHeight(50);
        atributs[2][2]=1;
        JTableUtils.writeArrayToJTable(parameters, atributs);
        this.pack();

        JPanel myPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawLine(imageWidth / 2, 0, imageWidth / 2, imageHeight);
                g.drawLine(0, imageHeight / 2, imageWidth, imageHeight / 2);
            }
        };
        figures.setLayout(new BorderLayout());
        myPanel.setPreferredSize(new Dimension(imageWidth,imageHeight));
        figures.add(myPanel, BorderLayout.CENTER);

        paint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFigures(figures.getGraphics());
                try {
                    int [][] arr = JTableUtils.readIntMatrixFromJTable(coordinates);
                    if (arr != null) {
                        fig = Logic.saveFromArrayToFigure(arr);
                        Graphics g = figures.getGraphics();
                        drawFigure(g, Color.MAGENTA, fig);
                    } else {
                        fig = null;
                        mistakeLabel.setText("Не удалось отобразить рисунок");
                    }
                } catch (ParseException parseException) {
                    saveMistLabel.setText("Ошибка, вводите координаты в целых числах!");
                }
            }
        });


        moveBut.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                drawFigure(figures.getGraphics(), Color.MAGENTA, fig);
                String xs = xField.getText();
                double x;
                if (xs.isEmpty()) {
                    x = 0;
                } else {
                    x = Double.parseDouble(xs);
                }
                String ys = yField.getText();
                double y;
                if (ys.isEmpty()) {
                    y = 0;
                } else {
                    y = Double.parseDouble(ys);
                }
                addit = fig.moveFigure(x, y*(-1));
                if (addit == null) {
                    moveMistLabel.setText("Нельзя переместить фигуру");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                moveMistLabel.setText("Не удалость переместить фигуру");
            }
        });

        ScaleTopX.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleBottomX(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleTopY.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleBottomY(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleBotX.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleUpX(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleBotY.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleUpY(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleLeftX.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleRightX(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        rotateButton.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 0;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.rotate(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });


        ScaleLeftY.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleRightY(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleRightX.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleLeftX(scale);

                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });
        Scale.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleCenter(scale);

                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleRightY.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleLeftY(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleCenterX.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleCenterX(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        ScaleCenterY.addActionListener(e -> {
            try {
                clearFigures(figures.getGraphics());
                String scales = scaleField.getText();
                double scale;
                if (scales.isEmpty()) {
                    scale = 1;
                } else {
                    scale = Double.parseDouble(scales);
                }
                addit = fig.scaleCenterY(scale);
                if (addit == null) {
                    scaleMistLabel.setText("Масштабировать нельзя");
                } else {
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (Exception err) {
                scaleMistLabel.setText("Не удалость масштабировать фигуру");
            }
        });

        clearBut.addActionListener(e -> {

            JTableUtils.writeArrayToJTable(coordinates,arr);
            mistakeLabel.setText("");
            squareLabel.setText("");
            pLabel.setText("");
            xField.setText("");
            yField.setText("");
            rectMistakeLabel.setText("");
            moveMistLabel.setText("");
            scaleField.setText("");
            scaleMistLabel.setText("");
            saveMistLabel.setText("");
            clearFigures(figures.getGraphics());
            fig = new Figure(new ArrayList<>());
            addit = new Figure(new ArrayList<>());
        });
        MatrixRotatebutton.addActionListener(e -> {
            clearFigures(figures.getGraphics());
            double[][]matrix;
            if (!CornerField.getText().trim().isEmpty()) {
                matrix = atributs;
                addit = fig.rotateMatrix(matrix);
                drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
            }else {
                try {
                    matrix = JTableUtils.readDoubleMatrixFromJTable(parameters);
                if (matrix == null) {
                    ErrormatrixLabel.setText("На входе пустая матрица");
                } else {
                    addit = fig.rotateMatrix(matrix);
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            }
        });

             CornerField.addActionListener(e -> {
                try {
                    atributs[0][0]=Math.cos((Integer.parseInt(CornerField.getText())*Math.PI)/180);
                    atributs[0][1]=-Math.sin((Integer.parseInt(CornerField.getText())*Math.PI)/180);
                    atributs[1][0]=Math.sin((Integer.parseInt(CornerField.getText())*Math.PI)/180);
                    atributs[1][1]=Math.cos((Integer.parseInt(CornerField.getText())*Math.PI)/180);
                            JTableUtils.writeArrayToJTable(parameters,atributs);

                } catch (Exception exc) {
                    ru.vsu.cs.util.SwingUtils.showErrorMessageBox(exc);
                }
        });
        MatrixScalebutton.addActionListener(e -> {
            clearFigures(figures.getGraphics());
            try {
                double[][]matrix = JTableUtils.readDoubleMatrixFromJTable(parameters);
                if (matrix == null) {
                    ErrormatrixLabel.setText("На входе пустая матрица");
                } else {
                    addit = fig.scaleMatrix(matrix);
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (ParseException exception) {
                exception.printStackTrace();
            }
        });
        TranslateMatrixButton.addActionListener(e -> {
            clearFigures(figures.getGraphics());
            try {
                double[][]matrix = JTableUtils.readDoubleMatrixFromJTable(parameters);
                if (matrix == null) {
                    ErrormatrixLabel.setText("На входе пустая матрица");
                } else {
                    addit = fig.translateMatrix(matrix);
                    drawFigure(figures.getGraphics(), Color.MAGENTA, addit);
                }
            } catch (ParseException exception) {
                exception.printStackTrace();
            }

        });
        ClearMatrixButton.addActionListener(e -> {
            double [][] atributs1 = new double[3][3];
            JTableUtils.initJTableForArray(parameters, 100, false, false, false, false);
            parameters.setRowHeight(50);
            atributs1[2][2]=1;
            JTableUtils.writeArrayToJTable(parameters, atributs1);
            CornerField.setText(null);
        });
    }
}
