/*
 * This is the object for this program which stores the various information about an activity a user
 * wants to record.
 */
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
    //DataEntry objects are compared to eachother by their difference in exercise name, 
    //exercise date, and exercise notes
    public int compareTo(DataEntry otherDataEntry) {
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