package cmput301.afilbert_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class NewSubscriptionActivity extends AppCompatActivity {
    // Activity which handles user input for the creation of a new subscription. Parameters are
    // passed back to MainActivity on click of save button.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subscription);

        FloatingActionButton newsubdone = (FloatingActionButton) findViewById(R.id.newsubdone);
        final EditText subNameField = (EditText) findViewById(R.id.SubNameField);
        final EditText subDateField = (EditText) findViewById(R.id.SubDateField);
        final EditText subChargeField = (EditText) findViewById(R.id.SubChargeField);
        final EditText subCommentField = (EditText) findViewById(R.id.SubCommentField);

        newsubdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save new subscription

                Intent saveSub = new Intent();
                saveSub.putExtra("subName", subNameField.getText().toString());
                saveSub.putExtra("subDate", subDateField.getText().toString());
                saveSub.putExtra("subCharge", subChargeField.getText().toString());
                saveSub.putExtra("subComment", subCommentField.getText().toString());
                setResult(RESULT_OK, saveSub);
                finish();
            }
        });
    }



}
