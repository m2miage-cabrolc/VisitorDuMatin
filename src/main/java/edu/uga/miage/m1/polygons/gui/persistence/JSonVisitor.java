package edu.uga.miage.m1.polygons.gui.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.uga.miage.m1.polygons.gui.shapes.CompositeShape;
import edu.uga.miage.m1.polygons.gui.shapes.SimpleShape;

/**
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 */
public class JSonVisitor implements Visitor, Serializable {


    private static  final Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 


    ArrayList<SimpleShape> listOfShapes;

    private StringBuilder representation = null;

    public JSonVisitor() {
    	
    	this.representation = new StringBuilder();
        this.listOfShapes = new ArrayList<>();

    }
    
    public void visit(SimpleShape simpleShape) {
        listOfShapes.add(simpleShape);
    }

    public void visit(CompositeShape compositeShape){
        // redefine 
    }

    public void save(String fileName) {
        File file = new File(fileName);
        for(int i=0; i < listOfShapes.size()-1; i++){
           representation.append(representation + "{\n\"type\": \"" + listOfShapes.get(i).getType() + "\",\n\"x\": " + listOfShapes.get(i).getX() + ",\n\"y\": " + listOfShapes.get(i).getY() + "\n},");
        }

       representation.insert(0, "{\"shapes\" : [\n" );
       representation.append("{\n\"type\": \"" + listOfShapes.get(listOfShapes.size()-1).getType() + "\",\n\"x\": " + listOfShapes.get(listOfShapes.size()-1).getX() + ",\n\"y\": " + listOfShapes.get(listOfShapes.size()-1).getY() + "\n}" +  "] }");
        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(representation.toString());
            fileWriter.flush();
        } catch (IOException e) {
           LOGGER.log(Level.SEVERE, "Erreur d ecriture dans le fichier");
        }
        
    }

    /**
     * @return the representation in JSon example for a Circle
     *
     *         <pre>
     * {@code
     *  {
     *     "shape": {
     *     	  "type": "circle",
     *        "x": -25,
     *        "y": -25
     *     }
     *  }
     * }
     *         </pre>
     */
    public String getRepresentation() {
        return representation.toString();
    }

  
}
