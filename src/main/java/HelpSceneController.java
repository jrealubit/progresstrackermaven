import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//This controller is used by HelpScene, and QuestionScene .FXML
public class HelpSceneController implements Initializable{

    @FXML
    private MenuButton helpMenuButton;

    @FXML
    private MenuItem menuLog;

    @FXML
    private MenuItem menuRecord;

    //This is only visible on Question Scenes
    @FXML 
    private MenuItem menuHelp;

    @FXML
    private Label titleMessage;

    @FXML
    private VBox helpVBox;

    @FXML
    private Hyperlink question1;

    @FXML
    private Label q1Answer;

    @FXML
    private Hyperlink question2;

    @FXML
    private Label q2Answer;

    @FXML
    private Hyperlink question3;

    @FXML
    private Label q3Answer;

    @FXML
    private Hyperlink question4;

    @FXML
    private Label q4Answer;

    @FXML
    private Hyperlink question5;

    @FXML
    private Label q5Answer;
    /* Idea:
     * have a list of FAQ and clicking the a question will display the answer on how to use the app.
     * --
     * How do I record an exercise?
     * How do I enter an entry for a reoccuring exercise?
     * How do I edit an exercise?
     * Can I enter the same exact entry twice?
     * What should I enter in weight for body weight exercises?
     */


    @FXML
    void switchToRecord(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRoot2;
        try {
            newRoot2 = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            primaryStage.getScene().setRoot(newRoot2);
        } catch (IOException e) {
            System.out.println("noo");
        }        
    }

    @FXML
    void switchToLog(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
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
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRoot4;
        try {
            newRoot4 = FXMLLoader.load(getClass().getResource("HelpScene.fxml"));
            primaryStage.getScene().setRoot(newRoot4);
        } catch (IOException e) {
            System.out.println("noo");
        }        
    }

    @FXML
    void question1Switch(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRootQ1;
        try {
            newRootQ1 = FXMLLoader.load(getClass().getResource("Question1Scene.fxml"));
            primaryStage.getScene().setRoot(newRootQ1);
        } catch (IOException e) {
            e.printStackTrace();    
        }   
    }

    @FXML
    void question2Switch(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRootQ2;
        try {
            newRootQ2 = FXMLLoader.load(getClass().getResource("Question2Scene.fxml"));
            primaryStage.getScene().setRoot(newRootQ2);
        } catch (IOException e) {
            e.printStackTrace();    
        }   
    }

    @FXML
    void question3Switch(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRootQ3;
        try {
            newRootQ3 = FXMLLoader.load(getClass().getResource("Question3Scene.fxml"));
            primaryStage.getScene().setRoot(newRootQ3);
        } catch (IOException e) {
            e.printStackTrace();    
        }   
    }

    @FXML
    void question4Switch(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRootQ4;
        try {
            newRootQ4 = FXMLLoader.load(getClass().getResource("Question4Scene.fxml"));
            primaryStage.getScene().setRoot(newRootQ4);
        } catch (IOException e) {
            e.printStackTrace();    
        }   
    }

    @FXML
    void question5Switch(ActionEvent event){
        Stage primaryStage = (Stage) helpMenuButton.getScene().getWindow();
        Parent newRootQ5;
        try {
            newRootQ5 = FXMLLoader.load(getClass().getResource("Question5Scene.fxml"));
            primaryStage.getScene().setRoot(newRootQ5);
        } catch (IOException e) {
            e.printStackTrace();    
        }   
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }

}
