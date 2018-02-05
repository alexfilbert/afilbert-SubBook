package cmput301.afilbert_subbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView subListView;
    private Double totalCharge;
    private static final String FILENAME = "subscriptions.sav";
    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TestTag", "TEST!!!");


        subListView = (ListView) findViewById(R.id.subListView);

        registerForContextMenu(subListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FABCLICK", "CLICKED!");
                Intent createNewSub = new Intent(MainActivity.this,NewSubscriptionActivity.class);

                startActivityForResult(createNewSub, 1);
                arrayAdapter.notifyDataSetChanged();
                saveInFile();
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.i("LifeCycle --->", "onStart is called");

        loadFromFile();
        //subList = new ArrayList<Subscription>();

        //Subscription S1 = new Subscription("Test1", "01-01-2018", "69.99", "This is a test");
        //Subscription S2 = new Subscription("Test2", "01-01-2018", "69.99", "This is also a test");

        //ListView subListView = (ListView) findViewById(R.id.subListView);
        arrayAdapter = new ArrayAdapter<Subscription>(
                this,
                android.R.layout.simple_list_item_1,
                subList);

        subListView.setAdapter(arrayAdapter);

        totalCharge = showTotalCharge();
        TextView monthlyChargeDisplay = (TextView) findViewById(R.id.chargeTotal);
        String chargeString = "$" +  new BigDecimal(totalCharge).setScale(2,
                RoundingMode.HALF_UP).toString();
        monthlyChargeDisplay.setText(chargeString);

        TextView listDescDisplay = (TextView) findViewById(R.id.listDescription);
        String listDescr = "Name\t\tDate\t\tCharge";
        listDescDisplay.setText(listDescr);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Based on https://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                addSub(data.getStringExtra("subName"), data.getStringExtra("subDate"),
                        data.getStringExtra("subCharge"),
                        data.getStringExtra("subComment"));
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                editSub(data.getIntExtra("position", 0), data.getStringExtra("subName"),
                        data.getStringExtra("subDate"),
                        data.getStringExtra("subCharge"),
                        data.getStringExtra("subComment"));
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Based on https://www.javatpoint.com/android-context-menu-example
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Action Menu");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getTitle()=="Edit"){
            Intent editSub = new Intent(MainActivity.this,EditSubscriptionActivity.class);
            editSub.putExtra("position", info.position);
            editSub.putExtra("curName", subList.get(info.position).getName());
            editSub.putExtra("curDate", subList.get(info.position).getDate());
            editSub.putExtra("curCharge", subList.get(info.position).getCharge());
            editSub.putExtra("curComment", subList.get(info.position).getComment());
            startActivityForResult(editSub, 2);
        }
        else if(item.getTitle()=="Delete"){
            deleteSub(info.position);
        }else{
            return false;
        }
        return true;
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-23
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            subList = new ArrayList<Subscription>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    /**
     * Save tweets to file
     *
     * @throws RuntimeException there was a problem writing to the file
     */

    private void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public Double showTotalCharge() {
        // Determines the total monthly charge of all current subscriptions
        Double monthlyTotal = new Double(0.00);
        for (int i = 0; i < subList.size(); i++) {
            Log.d("CHARGECOUNT", "Total is currently" + monthlyTotal);
            monthlyTotal += Double.parseDouble(subList.get(i).getCharge());
        }

        return monthlyTotal;

    }

    public void addSub(String name, String date, String charge, String comment) {
        Log.d("SUBINFO", name + date + charge + comment);
        Subscription newSub = new Subscription(name, date, charge, comment);

        subList.add(newSub);
        saveInFile();
        arrayAdapter.notifyDataSetChanged();
        //finish();
        //startActivity(getIntent());
        Log.d("SUBLISTSIZE", "List size = " + subList.size());
    }

    public void editSub(int pos, String name, String date, String charge, String comment) {
        if (!name.isEmpty()) {
            subList.get(pos).setName(name);
        }
        if (!date.isEmpty()) {
            subList.get(pos).setDate(date);
        }
        if (!charge.isEmpty()) {
            subList.get(pos).setCharge(charge);
        }
        if (!comment.isEmpty()) {
            subList.get(pos).setComment(comment);
        }
        saveInFile();
        arrayAdapter.notifyDataSetChanged();
    }

    public void deleteSub(int pos) {
        subList.remove(pos);
        saveInFile();
        arrayAdapter.notifyDataSetChanged();

        totalCharge = showTotalCharge();
        TextView monthlyChargeDisplay = (TextView) findViewById(R.id.chargeTotal);
        String chargeString = "$" +  new BigDecimal(totalCharge).setScale(2,
                RoundingMode.HALF_UP).toString();
        monthlyChargeDisplay.setText(chargeString);
    }
}
