package cmput301.afilbert_subbook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditSubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);

        Bundle extras = getIntent().getExtras();

        FloatingActionButton editsubdone = (FloatingActionButton) findViewById(R.id.editsubdone);
        final int position = extras.getInt("position");
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
                //startActivity(saveSub);
                setResult(RESULT_OK, saveSub);
                finish();
            }
        });
    }


}
