package GUI;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author brean
 */
public class MenuItem extends HBox {
        final private Rectangle r1 = new Rectangle(5,5), r2=new Rectangle(5,5);
    final private Text text;
        
    public MenuItem(String name){
        super(15);
        setAlignment(Pos.CENTER);
        text = new Text(name);
        
        getChildren().addAll(r1, text, r2);
        setActive(false);
    }
    
    public void setActive(boolean b) {
        r1.setVisible(b);
        r2.setVisible(b);
        text.setFill(b ? Color.WHITE : Color.BLACK);
    }
    
}
