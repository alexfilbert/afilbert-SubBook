package cmput301.afilbert_subbook;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Beta on 2018-02-03.
 */

public class Subscription {

    private String name;
    private String comment;
    private String dateStarted;
    private String charge;

    Subscription(String name, String dateStarted, String charge){
        this.name = name;
        this.dateStarted = dateStarted;
        this.charge = charge;
    }

    Subscription(String name, String dateStarted, String charge, String comment){
        this.name = name;
        this.dateStarted = dateStarted;
        this.charge = charge;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return this.getName().toString();
    }

    public String getName(){
        return name;
    }

    public void setName(String newName) {
        if (newName.length() > 20){
            newName = newName.substring(0, 20);
        }
        this.name = newName;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String newComment) {
        if (newComment.length() > 20){
            newComment = newComment.substring(0, 20);
        }
        this.comment = newComment;
    }

    public String getDate(){
        return dateStarted;
    }

    public void setDate(String newDate) {
        this.dateStarted = newDate;
    }

    public String getCharge(){
        return charge;
    }

    public void setCharge(String newCharge) {
        this.charge = newCharge;
    }

}
