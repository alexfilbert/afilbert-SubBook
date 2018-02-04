package cmput301.afilbert_subbook;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Beta on 2018-02-03.
 */

public class Subscription {

    private String name;
    private String comment;
    private Date dateStarted;
    private Currency charge;

    Subscription(String name, Date dateStarted, Currency charge){
        this.name = name;
        this.dateStarted = dateStarted;
        this.charge = charge;
    }

    Subscription(String name, Date dateStarted, Currency charge, String comment){
        this.name = name;
        this.dateStarted = dateStarted;
        this.charge = charge;
        this.comment = comment;
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

    public Date getDate(){
        return dateStarted;
    }

    public void setDate(Date newDate) {
        this.dateStarted = newDate;
    }

    public Currency getCharge(){
        return charge;
    }

    public void setName(Currency newCharge) {
        this.charge = newCharge;
    }

}
