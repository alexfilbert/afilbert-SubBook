package cmput301.afilbert_subbook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditSubscriptionActivity extends AppCompatActivity {
    // Activity which handles user input for editing an active subscription. Parameters are
    // passed back to MainActivity on click of save button. Boxes are pre-filled with
    // existing object attributes passed from MainActivity in extras Bundle.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);

        Bundle extras = getIntent().getExtras();

        FloatingActionButton editsubdone = (FloatingActionButton) findViewById(R.id.editsubdone);
        String curName = extras.getString("curName");
        String curDate = extras.getString("curDate");
        String curCharge = extras.getString("curCharge");
        String curComment = extras.getString("curComment");
        final int position = extras.getInt("position");

        TextView subNameDisplay = (TextView) findViewById(R.id.SubNameField);
        subNameDisplay.setText(curName);
        TextView subDateDisplay = (TextView) findViewById(R.id.SubDateField);
        subDateDisplay.setText(curDate);
        TextView subChargeDisplay = (TextView) findViewById(R.id.SubChargeField);
        subChargeDisplay.setText(curCharge);
        TextView subCommentDisplay = (TextView) findViewById(R.id.SubCommentField);
        subCommentDisplay.setText(curComment);

        final EditText subNameField = (EditText) findViewById(R.id.SubNameField);
        final EditText subDateField = (EditText) findViewById(R.id.SubDateField);
        final EditText subChargeField = (EditText) findViewById(R.id.SubChargeField);
        final EditText subCommentField = (EditText) findViewById(R.id.SubCommentField);

        editsubdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save changes to subscription
                Intent saveSub = new Intent();
                saveSub.putExtra("position", position);
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
