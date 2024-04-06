import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainSceneController implements Initializable{

    @FXML
    private Label titleMessage;

    @FXML
    private DatePicker dateSelect;

    @FXML
    private ComboBox<String> exerciseNameComboBox;

    @FXML
    private TextField exerciseNameTextField, weightTextField, repsTextField, setsTextField;

    @FXML
    private TextArea notesTextArea;

    @FXML
    private Button addExerciseButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private MenuItem menuHelp;

    @FXML
    private MenuItem menuLog;

    //local variables used to validate user input
    LocalDate dateUserInput;
    double weightUserInput;
    int repsUserInput, setsUserInput;
    


    @FXML
    void comboBoxExerciseSelect(ActionEvent event){

        exerciseNameTextField.setText(exerciseNameComboBox.getValue());
    }



    public ArrayList<String> readCSVFileForComboBox(){
        ArrayList<String> exerciseNamesList = new ArrayList<String>();
        String fileName = "src\\test\\java\\ExerciseLog.csv";
        BufferedReader reader = null;
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(fileName));
            //skip the first line, header values
            line = reader.readLine();
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                
                exerciseNamesList.add(row[1]);
            }            
        } catch (Exception e) {
            // TODO: handle exception
        }
        finally{
            try {
                if(reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return exerciseNamesList;
    }

    public void writeToCSVFile(DataSingleton s){
        try {
            //appends to existing file provided with program
            BufferedWriter writer = new BufferedWriter(new FileWriter("src\\test\\java\\ExerciseLog.csv", true));
            //write singleton containing DataEntry values into .csv file 
            writer.write("\n" + s.getDataEntry().exerciseDate + "," + s.getDataEntry().exerciseName +
            "," + s.getDataEntry().weight + "," + s.getDataEntry().reps + "," + s.getDataEntry().sets + 
            "," + s.getDataEntry().notes);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    DataSingleton singleton = DataSingleton.getInstance();
    @FXML
    void btnAddExcercise(ActionEvent event) {
        
        try {
            dateUserInput = dateSelect.getValue();
            weightUserInput = Double.parseDouble(weightTextField.getText());
            repsUserInput = Integer.parseInt(repsTextField.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        //creating new dataEntry from user input
        singleton.setDataEntry(new DataEntry(dateSelect.getValue().toString(),
        exerciseNameTextField.getText(), Double.parseDouble(weightTextField.getText()),
        Integer.parseInt(repsTextField.getText()), Integer.parseInt(setsTextField.getText()), notesTextArea.getText()));

        writeToCSVFile(singleton);

        //clear date picker and all text fields
        dateSelect.setValue(null);
        exerciseNameTextField.clear();
        weightTextField.clear();
        repsTextField.clear();
        setsTextField.clear();
        notesTextArea.clear();



        System.out.println("Date: " + singleton.getDataEntry().exerciseDate + "\n" + "Weight: " 
        + singleton.getDataEntry().getWeight() + " Sets: " + singleton.getDataEntry().getSets() +
        " Reps: " + singleton.getDataEntry().getReps() + "\nNotes: " + singleton.getDataEntry().getNotes() + "\n");

    }

    @FXML
    void switchToLog(ActionEvent event){
        Stage primaryStage = (Stage) menuButton.getScene().getWindow();
        Parent newRoot;
        try {
            newRoot = FXMLLoader.load(getClass().getResource("LogScene.fxml"));
            primaryStage.getScene().setRoot(newRoot);
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    

    }

    @FXML
    void switchToHelp(ActionEvent event){
        Stage primaryStage = (Stage) menuButton.getScene().getWindow();
        Parent newRoot4;
        try {
            newRoot4 = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
            primaryStage.getScene().setRoot(newRoot4);
        } catch (IOException e) {
            System.out.println("noo");
        }        
    }    

   @Override
    public void initialize(URL url, ResourceBundle rb){
        //creating new ArrayList<String> from all exercise names, removing duplicates with use of a HashSet
        ArrayList<String> arrayListNoDuplicates = new ArrayList<>(new HashSet<>(readCSVFileForComboBox()));
        exerciseNameComboBox.setItems(FXCollections.observableArrayList(arrayListNoDuplicates));
    }        

}
