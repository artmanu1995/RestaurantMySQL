package test.jakkit.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import android.app.ProgressDialog;
import android.view.WindowManager;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListOrderActivity extends ActionBarActivity {
    private TextView txtShowOfficer;
    private TextView txtShowTable;
    private TextView txtShowIdFood;
    private TextView txtShowFood;
    private TextView txtShowPrice;
    private TextView txtShowVolume;
    private String strOfficer, strTable, strNumFood, strFood, strPrice, strVolume;

    ConnectionClass connectionClass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        connectionClass = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        bindWidget();

        showDate();

        showOfficer();

        showTable();

        showIdFood();

        showFood();

        showPrice();

        showVolume();


    }

    private void showVolume() {
        strVolume = getIntent().getExtras().getString("Volume");
        txtShowVolume.setText(strVolume);
    }

    private void showPrice() {
        strPrice = getIntent().getExtras().getString("Price");
        txtShowPrice.setText(strPrice);
    }

    private void showFood() {
        strFood = getIntent().getExtras().getString("Food");
        txtShowFood.setText(strFood);
    }

    private void showIdFood() {
        strNumFood = getIntent().getExtras().getString("IdFood");
        txtShowIdFood.setText(strNumFood);
    }

    private void showOfficer() {
        strOfficer = getIntent().getExtras().getString("Officer");
        txtShowOfficer.setText(strOfficer);
    }

    private void showTable() {
        strTable = getIntent().getExtras().getString("Table");
        txtShowTable.setText(strTable);
    }

    private void showDate() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView time = (TextView) findViewById(R.id.txtShowDateTime);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");
                                String dateString = sdf.format(date);
                                time.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    public void Click(View view) {
        Button btt_ok = (Button) findViewById(R.id.btt_list);

        onPreExecute();
        InserMySQL();

        Intent intent = new Intent(ListOrderActivity.this, TableActivity.class);
        intent.putExtra("Officer", strOfficer);

        startActivity(intent);
    }

    protected void onPreExecute() {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    protected String InserMySQL() {
        String z="";
        boolean isSuccess=false;

        try {
            Connection con = connectionClass.CONN();
            if (con == null) {
                z = "Please check your internet connection";
            } else {

                String query = "insert into test_order values(NULL,'" + strOfficer + "','" + strNumFood + "','" + strFood + "','" + strPrice + "','" + strVolume + "','" + strTable + "')";

                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);

                z = "Register successfull";
                isSuccess = true;


            }
        } catch (Exception ex) {
            isSuccess = false;
            z = "Exceptions" + ex;
        }
        return z;
    }



    private void bindWidget() {
        txtShowOfficer = (TextView) findViewById(R.id.txtShowOfficer);
        txtShowTable = (TextView) findViewById(R.id.txtShowTable);
        txtShowIdFood = (TextView) findViewById(R.id.txtShowNumFood);
        txtShowFood = (TextView) findViewById(R.id.txtShowFood);
        txtShowPrice = (TextView) findViewById(R.id.txtShowPrice);
        txtShowVolume = (TextView) findViewById(R.id.txtShowVolume);
    }
}
