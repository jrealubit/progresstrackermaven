import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSceneController implements Initializable{

    @FXML
    private Button saveEditButton;

    @FXML
    private DatePicker dateSelect;

    @FXML
    private ComboBox<String> exerciseNameComboBox;

    @FXML
    private TextField exerciseNameTextField;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private TextField repsTextField;

    @FXML
    private TextField setsTextField;

    @FXML
    private Label titleMessage;

    @FXML
    private TextField weightTextField;

    @FXML
    private Button cancelButton;

    DataSingleton s = DataSingleton.getInstance();
    //this is where the activity will be updated in the TreeSet
    // figure out how to search for an entry
    // we might end up storing data from LogScene into singleton as a new data entry, just to 
    //take values and pass them into this scene.
    @FXML
    void btnSaveEdit(ActionEvent event) {
        /*
         * Create temp csv file
         * line by line write all lines that do not match edit criteria
         * edit criteria is the current DataEntry that is being edited (Stored in DataSingleton)
         * write new values in csv file with call to writeToCSV()
         * delete old file, rename temp file to old file
         */
        String currentFileName = "src\\test\\java\\ExerciseLog.csv";
        String tempFileName = "temp.csv";
        String removeLine = s.getActivity().exerciseDate + "," +
                            s.getActivity().exerciseName + "," +
                            Double.toString(s.getActivity().weight) + "," +
                            Integer.toString(s.getActivity().reps) + "," +
                            Integer.toString(s.getActivity().sets) + "," +
                            s.getActivity().notes;
        String headerLine = "ExerciseDate,ExerciseName,Weight,Reps,Sets,Notes";
        try {
            File currentFile = new File(currentFileName);
            File tempFile = new File(tempFileName);

            try (BufferedReader br = new BufferedReader(new FileReader(currentFile));
                   BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile, true))) {
                
                String line = null;
                while((line = br.readLine()) != null){
                    if(line.equals(headerLine)){
                        bw.write(line);
                    }
                    if(!line.equalsIgnoreCase(removeLine) && !line.equalsIgnoreCase(headerLine)){
                        bw.newLine();
                        bw.write(line);
                    }
                }
                //remove edited entry from TreeSet
                s.getTree().remove(s.getDataEntry());
                //set edited data from user input into DataSinglenton's DataEntry object
                s.setDataEntry(new DataEntry(dateSelect.getValue().toString(),
                exerciseNameTextField.getText(), Double.parseDouble(weightTextField.getText()),
                Integer.parseInt(repsTextField.getText()), Integer.parseInt(setsTextField.getText()), notesTextArea.getText()));

                //write edited DataEntry to csv
                bw.write("\n" + s.getDataEntry().exerciseDate + "," + s.getDataEntry().exerciseName +
                "," + s.getDataEntry().weight + "," + s.getDataEntry().reps + "," + s.getDataEntry().sets + 
                "," + s.getDataEntry().notes);                
                s.getTree().add(s.getDataEntry());                
                br.close();
                bw.close();
            } 

            if(currentFile.delete()){
                if(!tempFile.renameTo(currentFile)){
                    throw new IOException("Could not rename new file");
                }
                // else{
                //     throw new IOException("Could not delete old file");
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage primaryStage = (Stage) saveEditButton.getScene().getWindow();
        Parent newRoot;
        try {
            newRoot = FXMLLoader.load(getClass().getResource("LogScene.fxml"));
            primaryStage.getScene().setRoot(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
    
    //copied directly from MainSceneController 
   
    @FXML
    void comboBoxExerciseSelect(ActionEvent event) {
        exerciseNameTextField.setText(exerciseNameComboBox.getValue());
    }

    @FXML
    void switchToLog(ActionEvent event){
        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        Parent newRoot;
        try {
            newRoot = FXMLLoader.load(getClass().getResource("LogScene.fxml"));
            primaryStage.getScene().setRoot(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }

    public ArrayList<String> readCSVFileForComboBox() {
        ArrayList<String> exerciseNamesList = new ArrayList<String>();
        String fileName = "src\\test\\java\\ExerciseLog.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            // skip the first line, header values
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");

                exerciseNamesList.add(row[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return exerciseNamesList;
    }

    //helper method to take string date value and convert it into LocalDate value
    public LocalDate dateFormat(String s){

        LocalDate activityD;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.US ); 
        activityD = LocalDate.parse(s, formatter);
        return activityD;
    }

   @Override
    public void initialize(URL url, ResourceBundle rb){
        //creating new ArrayList<String> from all exercise names, removing duplicates with use of a HashSet
        ArrayList<String> arrayListNoDuplicates = new ArrayList<>(new HashSet<>(readCSVFileForComboBox()));
        exerciseNameComboBox.setItems(FXCollections.observableArrayList(arrayListNoDuplicates));
        //Filling in textfields with appropriate activity information
        dateSelect.setValue(dateFormat(DataSingleton.getInstance().getActivity().exerciseDate));
        exerciseNameTextField.setText(DataSingleton.getInstance().getActivity().exerciseName);
        weightTextField.setText(Double.toString(DataSingleton.getInstance().getActivity().weight));
        repsTextField.setText(Integer.toString(DataSingleton.getInstance().getActivity().reps));
        setsTextField.setText(Integer.toString(DataSingleton.getInstance().getActivity().sets));
        notesTextArea.setText(DataSingleton.getInstance().getActivity().notes);        
    }     

}
