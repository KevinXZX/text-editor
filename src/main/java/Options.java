import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Options extends JMenuBar {
    JMenu fileOptions = new JMenu("File"); //TODO: Extract file options into a different class
    protected File selectedFile = null;
    TextArea textArea;
    //TODO: Have a second constructor that has a File as an input in case the app is opened with a file
    public Options(TextArea textArea){
        this.setVisible(true);
        this.textArea = textArea;
        JMenuItem open = new JMenuItem("Open");
        open.setSize(20,10);
        open.addActionListener(e -> {
            selectedFile = openFile();
            try {
                String text = readFile(selectedFile);
                textArea.setText(text);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        fileOptions.add(open);
        this.add(fileOptions);
        JMenuItem save = new JMenuItem("Save");
        save.setSize(20,10);
        save.addActionListener(e ->{
            if(selectedFile != null){
                try {
                    FileWriter writer = new FileWriter(selectedFile, false);
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }else{
                selectedFile = saveFile();
            }
        });
        fileOptions.add(save);
    }
    public static String readFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        StringBuilder text = new StringBuilder();
        while(sc.hasNextLine()){
            text.append(sc.nextLine()).append("\n");
        }
        return text.toString();
    }
    public static File openFile(){
        JFileChooser open = new JFileChooser();
        int returnValue = open.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = open.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            return selectedFile;
        }
        return null;
    }
    public File saveFile(){
        JFileChooser open = new JFileChooser();
        int returnValue = open.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = open.getSelectedFile();
            try(FileWriter fw = new FileWriter(open.getSelectedFile())) {
                fw.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return selectedFile;
        }
        return null;
    }
}
