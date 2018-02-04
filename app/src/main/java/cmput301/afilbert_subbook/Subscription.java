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

}
