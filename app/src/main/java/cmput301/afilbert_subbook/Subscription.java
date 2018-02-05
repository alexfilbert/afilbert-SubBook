package cmput301.afilbert_subbook;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Date;

/**
 * Created by Beta on 2018-02-03.
 */

public class Subscription {
    // Subscription class that represents a subscription object and provides the functionality
    // needed to get and set Subscription attributes.

    private String name;
    private String comment;
    private String dateStarted;
    private String charge;

    Subscription(String name, String dateStarted, String charge){
        // Constructor without comment.
        this.name = name;
        this.dateStarted = dateStarted;
        this.charge = charge;
    }

    Subscription(String name, String dateStarted, String charge, String comment){
        // Constructor with comment.
        this.name = name;
        this.dateStarted = dateStarted;
        this.charge = charge;
        this.comment = comment;
    }

    @Override
    public String toString() {
        // Determines output format in list view of each subscription object. This could use some
        // work as the format is currently not aligned.
        String dispName = this.getName();
        String dispDate = this.getDate();
        String dispCharge = new BigDecimal(this.getCharge()).setScale(2).toString();
        String dispComment = this.getComment();
        return dispName + "\t\t" + dispDate + "\t\t" + dispCharge + "\n" + dispComment;
    }

    public String getName(){
        return name;
    }
    // Return name of current subscription object.

    public void setName(String newName) {
        // Set name of current subscription object.
        if (newName.length() > 20){
            newName = newName.substring(0, 20);
        }
        this.name = newName;
    }

    public String getComment(){
        return comment;
    }
    // Return comment of current subscription object.

    public void setComment(String newComment) {
        // Set comment of current subscription object.
        if (newComment.length() > 20){
            newComment = newComment.substring(0, 20);
        }
        this.comment = newComment;
    }

    public String getDate(){
        return dateStarted;
    }
    // Return date of current subscription object.

    public void setDate(String newDate) {
        this.dateStarted = newDate;
    }
    // Set date of current subscription object.

    public String getCharge(){
        return charge;
    }
    // Return charge of current subscription object.

    public void setCharge(String newCharge) {
        this.charge = newCharge;
    }
    // Set charge of current subscription object.

}
