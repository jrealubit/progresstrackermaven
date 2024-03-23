//not used
import java.util.Set;
import java.util.TreeSet;

public class DataTreeSet {
    private Set<DataEntry> treeSet = new TreeSet<>();

    //no argument constructor
    public DataTreeSet(){

    }

    //getter and setter
    public Set<DataEntry> getTreeSet() {
        return treeSet;
    }

    public void setTreeSet(Set<DataEntry> treeSet) {
        this.treeSet = treeSet;
    }

    
}
