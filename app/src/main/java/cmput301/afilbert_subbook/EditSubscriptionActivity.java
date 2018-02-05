package cmput301.afilbert_subbook;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditSubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subscription);

        FloatingActionButton editsubdone = (FloatingActionButton) findViewById(R.id.editsubdone);
        editsubdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save changes to subscription
                startActivity(new Intent(EditSubscriptionActivity.this, ScrollingActivity.class));
            }
        });
    }


}
