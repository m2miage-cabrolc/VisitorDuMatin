package edu.uga.miage.m1.polygons.gui.shapes;

import java.io.Serializable;

public class ShapeFactory implements Serializable {
    
    public SimpleShape getShape(String type, int x, int y){
        switch(type){
            case "circle":
                return new Circle(x, y);
            case "triangle":
                return new Triangle(x, y);
            case "square":
                return new Square(x, y);
            default:
                return null;
        }
    }

   
}
