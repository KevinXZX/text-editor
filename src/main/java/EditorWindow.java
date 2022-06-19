import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {
    private TextArea fileText;

    public EditorWindow(){
        this.setLayout(new BorderLayout());
        this.setSize(600,500);
        this.setVisible(true);
        fileText = new TextArea();
        fileText.setSize(this.getWidth(),this.getHeight()-40);
        fileText.setLocation(0,40);
        fileText.setVisible(true);
        this.add(fileText,BorderLayout.CENTER);
        Options options = new Options(fileText);
        options.setSize(this.getWidth(),40);
        this.add(options,BorderLayout.PAGE_START);
    }

}
