package cmput301.afilbert_subbook;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        String dispName = this.getName() +;
        String dispDate = this.getDate();
        String dispCharge = new BigDecimal(this.getCharge()).setScale(2).toString();
        String dispComment = this.getComment();
        //String output = this.getName() + " " + this.getDate() + " " +
        //        new BigDecimal(this.getCharge()).setScale(2) + " " + this.getComment();
        //return String.format("%-30s %-20s %-20s\n%-30s", this.getName(),  this.getDate(),
        //        new BigDecimal(this.getCharge()).setScale(2, RoundingMode.HALF_UP),
        //        this.getComment());

        //return String.format("%s\t\t\t\t%s\t\t\t\t%1$.2f\t\t\t\t%s", this.getName(), this.getDate(),
        //        Double.parseDouble(this.getCharge()), this.getComment());
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
