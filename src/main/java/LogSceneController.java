import java.util.ResourceBundle;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LogSceneController implements Initializable {


    @FXML
    private TableView<DataEntry> logTable;    

    @FXML
    private TableColumn<DataEntry, String> exerciseNameTableCol;

    @FXML
    private TableColumn<DataEntry, String> exerciseDateTableCol;

    @FXML
    private TableColumn<DataEntry, String> notesTableCol;

    @FXML
    private TableColumn<DataEntry, Integer> repsTableCol;

    @FXML
    private TableColumn<DataEntry, Integer> setsTableCol;

    @FXML
    private TableColumn<DataEntry, Double> weightTableCol;    

    @FXML
    private MenuButton logMenuButton;

    @FXML
    private MenuItem menuHelp;

    @FXML
    private MenuItem menuRecord;

    @FXML
    private Label titleMessage;

    @FXML
    private Button editActivityButton;

    @FXML
    private Button deleteActivityButton;

    Integer index;

    DataEntry editEntry;

    //helper method for the methods to find a particular DataEntry object in the TreeSet
    // and also in the CSV file
    @FXML
    void getRow(MouseEvent event){
        index = logTable.getSelectionModel().getSelectedIndex();


            //Create new DataEntry object to store values of existing DataEntry to be edited
            editEntry = new DataEntry(exerciseDateTableCol.getCellData(index).toString(),
                                                exerciseNameTableCol.getCellData(index).toString(),
                                                weightTableCol.getCellData(index),
                                                repsTableCol.getCellData(index),
                                                setsTableCol.getCellData(index),
                                                notesTableCol.getCellData(index).toString());
            System.out.println("Selected table row:");
            System.out.println("Name: "+ editEntry.getExerciseName() +" Date: "+ editEntry.getExerciseDate() + "\nWeight: " + editEntry.weight + " Sets: " + editEntry.sets + " Reps: " + editEntry.reps + " Notes: " + editEntry.notes);




    }

    @FXML
    void switchToRecord(ActionEvent event){
        Stage primaryStage = (Stage) logMenuButton.getScene().getWindow();
        Parent newRoot2;
        try {
            newRoot2 = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage.getScene().setRoot(newRoot2);
        } catch (IOException e) {
            System.out.println("noo");
        }        
    }

    @FXML
    void switchToEdit(ActionEvent event){
        DataSingleton.getInstance().setDataEntry(editEntry);
        System.out.println("Name: "+ editEntry.getExerciseName() +" Date: "+ editEntry.getExerciseDate() + "\nWeight: " + editEntry.weight + " Sets: " + editEntry.sets + " Reps: " + editEntry.reps + " Notes: " + editEntry.notes);
        Stage primaryStage = (Stage) editActivityButton.getScene().getWindow();
        Parent newRoot3;
        try {
            newRoot3 = FXMLLoader.load(getClass().getResource("EditScene.fxml"));
            primaryStage.getScene().setRoot(newRoot3);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    @FXML
    void switchToHelp(ActionEvent event){
        Stage primaryStage = (Stage) logMenuButton.getScene().getWindow();
        Parent newRoot4;
        try {
            newRoot4 = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
            primaryStage.getScene().setRoot(newRoot4);
        } catch (IOException e) {
            System.out.println("noo");
        }        
    }

    DataSingleton s = DataSingleton.getInstance();  
    
    //modified btnSaveEdit method
    @FXML
    void deleteActivity(ActionEvent event){
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
    }
    
    //add user dataEntry to treeset method

    public void addToTreeSet(Set<DataEntry> t, DataEntry entry){
        t.add(entry);
    }

    //helper method
    public void addTreeSetToList(Set<DataEntry> t, ObservableList<DataEntry> l){
        for(DataEntry element: t){
            l.add(element);
        }
    }

    /*
     * Going to have to parse each token and input them as arguments to
     * create DataEntryObjects
     */
    public void addCSVDataToTreeSet(Set<DataEntry> t){
        
        String fileName = "src\\test\\java\\ExerciseLog.csv";
        BufferedReader reader = null;
        String line = "";
        //store data values to put into DataEntry
        // String[] dataToken = new String[6];
        try {
            reader = new BufferedReader(new FileReader(fileName));
            //skip the first line, header values
            line = reader.readLine();
            //begin to read data
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                
                for(String index: row){
                    System.out.printf("%-15s", index);
                }
                System.out.println();
                
                DataEntry recordedEntry = new DataEntry(row[0], row[1], Double.parseDouble(row[2]), 
                Integer.parseInt(row[3]), Integer.parseInt(row[4]), row[5]);
                t.add(recordedEntry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    ObservableList<DataEntry> list = FXCollections.observableArrayList(
        //this is the most recent entry made by the user
        //DataSingleton.getInstance().getDataEntry() 
        //this code was taken out because the first entry is recorded twice,
        // so we are starting with an empty list at first to print to the TableView


        //the rest of the list is the previous entries including the ones already 
        //recorded in the ExerciseLog.csv prior to current app session.
  
    );

    


    @Override
    public void initialize(URL url, ResourceBundle rb){
        //update Singleton TreeSet with new values 
        //and recorded values from ExerciseLog.csv
        addCSVDataToTreeSet(s.getTree());

        //the line below was taken out because the line above already adds
        //the new entry to the tree
        /* The order of how new DataEntry objects goes as follows:
         * 1. The user enters in data on the Record Screen
         * 2. That data is written onto the ExerciseLog.csv
         * 3. addCSVDataToTreeSet() reads all the data on ExerciseLog.csv including the
         *    newly written DataEntry value.
         */
        //addToTreeSet(s.getTree(),s.getDataEntry());
        //update list
        addTreeSetToList(s.getTree(),list);
        //checks if DataEntry objects make it into list
        for(DataEntry element: list){
            System.out.println(element);
        }

        //formats TableView
        exerciseNameTableCol.setCellValueFactory(new PropertyValueFactory<DataEntry, String>("exerciseName"));
        exerciseDateTableCol.setCellValueFactory(new PropertyValueFactory<DataEntry, String>("exerciseDate"));
        weightTableCol.setCellValueFactory(new PropertyValueFactory<DataEntry, Double>("weight"));        
        repsTableCol.setCellValueFactory(new PropertyValueFactory<DataEntry, Integer>("reps"));
        setsTableCol.setCellValueFactory(new PropertyValueFactory<DataEntry, Integer>("sets"));
        notesTableCol.setCellValueFactory(new PropertyValueFactory<DataEntry, String>("notes"));

        logTable.setItems(list);
        //Edit button is disabled until user selects a row from logTable
        editActivityButton.disableProperty().bind(logTable.getSelectionModel().selectedItemProperty().isNull());        
        //print treeSize
        System.out.println("Tree size: " + s.getTree().size());

    }
    
}

