//this is singleton object is what brings DataEntry objects into a stored place

import java.util.Set;
import java.util.TreeSet;

public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();

    //initialize so user can switch to Log page without entering anything
    private DataEntry activity = new DataEntry("", "", 0, 0, 0, "");

    //most important structure to hold data during current session
    // it is initialized so the compiler does not throw an error when trying to access it the first time
    private Set<DataEntry> tree = new TreeSet<>();

    //private constructor
    private DataSingleton(){}

    public static DataSingleton getInstance(){
        return instance;
    }

    public DataEntry getDataEntry(){
        return activity;
    }

    public void setDataEntry(DataEntry activity){
        this.activity = activity;
    }

    public DataEntry getActivity() {
        return activity;
    }

    public void setActivity(DataEntry activity) {
        this.activity = activity;
    }

    public Set<DataEntry> getTree() {
        return tree;
    }

    public void setTree(Set<DataEntry> tree) {
        this.tree = tree;
    }    
    
}
