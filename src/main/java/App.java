import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) {
    
    Parent root;
    try {
       root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
       
        Scene scene = new Scene(root);
  
       primaryStage.setTitle("Fitness Progress Tracker");
             primaryStage.setScene(scene);
             primaryStage.show();
    } catch (IOException e) {
        System.err.println("failed");
        e.printStackTrace();
    }    
    
}

public static void main (String[] args){
    launch(args);
}

}

/*
 * App should store data that can be exported into a file?
 * 
 * should be able to store data in general
 * 
 * 2nd page
 * have a log of exercise, this screen will have a graph and the top of the page is a drop down
 * menu that allows you to select exercises
 * the default view of the screen is the first stored exercise log
 * also has export button at the bottom of the screen
 * 
 * 3rd page
 * allows you to rename exercise, and or delete entire exercise or individual exercises
 * deletion exercise prompts warning screen
 * 
 * 
 * data entries might have to be singletons, each entry should be unique
 * with date,time, other variables
 * 
 * 3 scenes, two buttons to change scene from current scene at the top
 * 
 * Exercise name should allow user to either pick from previously 
 * added exercise names, or make a new one
 * 
 * In addition to the singleton entry, when a new exercise name is mention, a new exercise 
 * name object must be created. This is so all the excerises are seperated and have their own data
 * points. (i.e. all exercises that are named 'Push Ups' will be grouped)
 * How I think this will come into play is when the user submits an excercise name
 * that string will go to the DataEntry object as well as the Exercise? object
 * This is probably where linked lists come into play, a linked list of DataEntry objects?
 * 
 * When exporting to excel file, only one linked list of a specific exercise can be imported.
 * (i.e. seperate .cvs files for push up and pull up exercises)
 * //this cannot be done with a single linked list because there are different type of exercises.
 * 
 * TreeHashSet - underlying data structure is tree, not as fast as HashSet, but store in "natural order of elements"
 * HashSet - underlying date structure is hashmap for storage, fastest, used when order is not cared about
 * LinkedHashSet - almost as fast as hashset, but memory cost greater and only should be used if insertion order is required
 * 
 * 
 * I might go with TreeHastSet or Hashset
 * The data structure will store all the newly created DataEntry instances as well as the entries read from the .csv file
 * , when the user, saves or closes the app all the entries currently in the data structure will overwrite to the .csv file 
 */

