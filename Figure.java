package ru.vsu.cs.course1;

import java.util.ArrayList;
import java.util.List;

public class Figure {

    private final List<Point> coordinates;

    public Figure (List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Point> getCoordinates () {
        return coordinates;
    }

    public Figure rotateMatrix(double[][]matrix){
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value: coordinates) {
                newList.add(new Point((value.getX()*matrix[0][0]+value.getY()*matrix[0][1]), (value.getX()*matrix[1][0]+ value.getY()*matrix[1][1])));
            }
            return new Figure(newList);
        }
    }

    public Figure translateMatrix(double[][] matrix){
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value: coordinates) {
                newList.add(new Point((value.getX()+matrix[0][2]), (value.getY()-matrix[1][2])));
            }
            return new Figure(newList);
        }
    }

    public Figure scaleMatrix(double[][] matrix){
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value: coordinates) {
                newList.add(new Point((value.getX()*matrix[0][0]+value.getY()*matrix[0][1]), (value.getX()*matrix[1][0]+ value.getY()*matrix[1][1])));
            }
            return new Figure(newList);
        }
    }

    public Figure moveFigure (double dx, double dy) {
        if (coordinates.size() == 0) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value : coordinates) {
                value.setX((int) (dx + value.getX()));
                value.setY((int) (dy + value.getY()));
                newList.add(value);
            }
            return new Figure(newList);
        }
    }


    public Figure scaleUpX (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(-300, -300);
            Point b = new Point(-301, -301);
            for (Point value : coordinates) {
                if (value.getY() == a.getY()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getY() > a.getY()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(-301, -301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(new Point((int) (value.getX()*scale), value.getY()));
                } else {
                    newList.add(value);
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleUpY (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(-300, -300);
            Point b = new Point(-301, -301);
            for (Point value : coordinates) {
                if (value.getY() == a.getY()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getY() > a.getY()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(-301, -301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(new Point(value.getX(), (int) (value.getY()*scale)));
                } else {
                    newList.add(value);
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleBottomX (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(300, 300);
            Point b = new Point(301, 301);
            for (Point value : coordinates) {
                if (value.getY() == a.getY()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getY() < a.getY()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(301, 301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(new Point((int) (value.getX()*scale), value.getY()));
                } else {
                    newList.add(value);
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleBottomY (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(300, 300);
            Point b = new Point(301, 301);
            for (Point value : coordinates) {
                if (value.getY() == a.getY()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getY() < a.getY()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(301, 301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(new Point(value.getX(), (int) (value.getY()*scale)));
                } else {
                    newList.add(value);
                }
            }
            return new Figure(newList);
        }
    }


    public Figure rotate (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value: coordinates) {
                    newList.add(new Point((int) (-value.getY()*Math.sin(scale)+value.getX()*Math.cos(scale)), (int) (value.getY()*Math.cos(scale)+value.getX()*Math.sin(scale))));
            }
            return new Figure(newList);
        }
    }

    public Figure scaleLeftX (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(300, 300);
            Point b = new Point(301, 301);
            for (Point value : coordinates) {
                if (value.getX() == a.getX()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getX() < a.getX()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(301, 301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(value);
                } else {
                    newList.add(new Point((int)(value.getX()*scale),value.getY()));
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleRightX (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(-300, -300);
            Point b = new Point(-301, -301);
            for (Point value : coordinates) {
                if (value.getX() == a.getX()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getX() > a.getX()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(-301, -301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(value);
                } else {
                    newList.add(new Point((int) (value.getX()*scale), value.getY()));
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleLeftY (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(300, 300);
            Point b = new Point(301, 301);
            for (Point value : coordinates) {
                if (value.getX() == a.getX()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getX() < a.getX()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(301, 301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(value);
                } else {
                    newList.add(new Point(value.getX(), (int) (value.getY()*scale)));
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleRightY (double scale) {
        if (coordinates.size() == 0 || coordinates.size() == 1) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            Point a = new Point(-300, -300);
            Point b = new Point(-301, -301);
            for (Point value : coordinates) {
                if (value.getX() == a.getX()) {
                    b = new Point(value.getX(), value.getY());
                } else if (value.getX() > a.getX()) {
                    a = new Point(value.getX(), value.getY());
                    b = new Point(-301, -301);
                }
            }
            for (Point value: coordinates) {
                if ((value.getX() == a.getX() && value.getY() == a.getY()) ||
                        (value.getX() == b.getX() && value.getY() == b.getY())) {
                    newList.add(value);
                } else {
                    newList.add(new Point(value.getX(), (int) (value.getY()*scale)));
                }
            }
            return new Figure(newList);
        }
    }

    public Figure scaleCenterX (double scale) {
        if (coordinates.size() == 0) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value : coordinates) {
                newList.add(new Point((int) (value.getX()*scale), value.getY()));
            }
            return new Figure(newList);
        }
    }

    public Figure scaleCenter (double scale) {
        if (coordinates.size() == 0) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value : coordinates) {
                newList.add(new Point((int) (value.getX()*scale), (int) (value.getY()*scale)));
            }
            return new Figure(newList);
        }
    }

    public Figure scaleCenterY (double scale) {
        if (coordinates.size() == 0) {
            return null;
        } else {
            List<Point> newList = new ArrayList<>();
            for (Point value : coordinates) {
                newList.add(new Point(value.getX(), (int) (value.getY()*scale)));
            }
            return new Figure(newList);
        }
    }
}
