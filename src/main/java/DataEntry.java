// import java.util.Date;

public class DataEntry implements Comparable<DataEntry> {

    String exerciseDate; 
    String exerciseName;
    double weight;
    int reps, sets;
    String notes;

    // constructor
    public DataEntry(String exerciseDate, String exerciseName, double weight, int reps, int sets, String notes) {
        this.exerciseDate = exerciseDate;
        this.exerciseName = exerciseName;
        this.weight = weight;
        this.reps = reps;
        this.sets = sets;
        this.notes = notes;
    }




    // implementing an edit exercise functionality
    // Approach:
    // Edit page will display text prompts with text fields
    // those prompts will have the current values in the box
    // users can change whichever text field. The object's data does not change
    // unless
    // the user hits save, then the program will call the getter and setter methods
    // based on
    // which value was changed.

    // delete activity
    // this will not be deleted from memory unless all references from object are
    // gone
    // the deletion method might just end up being a part of the linked list class
    public void deleteEntry(DataEntry x) {
        x = null;
    }    
    
    // getter and setter methods for each data member
    
    public String getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(String exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }




    @Override
    public int compareTo(DataEntry otherDataEntry) {
        // return Integer.compare(reps, otherDataEntry.reps);
        if(exerciseName.compareTo(otherDataEntry.exerciseName) != 0){
            return exerciseName.compareTo(otherDataEntry.exerciseName);
        }
        else if(exerciseDate.compareTo(otherDataEntry.exerciseDate) != 0){
            return exerciseDate.compareTo(otherDataEntry.exerciseDate);
        }
        else if(notes.compareTo(otherDataEntry.notes) != 0){
            return notes.compareTo(otherDataEntry.notes);
        }
        else{
            return 0;
        }
    }

}