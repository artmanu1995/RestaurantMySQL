package test.jakkit.restaurant;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderActivity extends ActionBarActivity {

    private FoodTABLE objFoodTABLE;
    private TextView txtShowOfficer;
    private TextView txtShowTable;
    private ListView myListView;
    private String strOfficer, strTable, strNumFood, strFood, strPrice, strTime, strVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        bindWidget();

        objFoodTABLE = new FoodTABLE(this);

        //Show officer
        showOfficer();

        // showTable
        showTable();

        //Create ListView
        createListView();



    }   // onCreate

    private void createListView() {
        final String[] strListNumFood = objFoodTABLE.readAllNumFood();
        final String[] strListFood = objFoodTABLE.readAllFood();
        final String[] strListPrice = objFoodTABLE.readAllPrice();

        MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strListNumFood, strListFood, strListPrice);
        myListView.setAdapter(objMyAdapter);

        //Click Active
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                strFood = strListFood[position];
                strPrice = strListPrice[position];
                strNumFood = strListNumFood[position];

                chooseItem();

            }   // event
        });

    }   // createListView
    private void chooseItem() {
        CharSequence[] charItem = {"1 จาน", "2 จาน", "3 จาน", "4 จาน", "5 จาน"};

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.restaurant2);
        objBuilder.setTitle("กรุณาเลือกจำนวน !");
        objBuilder.setCancelable(false);
        objBuilder.setSingleChoiceItems(charItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case 0:
                        strVolume = "1";
                        break;
                    case 1:
                        strVolume = "2";
                        break;
                    case 2:
                        strVolume = "3";
                        break;
                    case 3:
                        strVolume = "4";
                        break;
                    case 4:
                        strVolume = "5";
                        break;
                }   // switch

                dialog.dismiss();

            }
        });
        AlertDialog obAlertDialog = objBuilder.create();
        obAlertDialog.show();

    }   // showChooseItem

   private void showOfficer() {
        strOfficer = getIntent().getExtras().getString("Officer");
        txtShowOfficer.setText(strOfficer);
    }
    
    private void showTable(){
        strTable = getIntent().getExtras().getString("Table");
        txtShowTable.setText(strTable);
    }

    private void bindWidget() {
        txtShowOfficer = (TextView) findViewById(R.id.txtShowOfficer);
        txtShowTable = (TextView) findViewById(R.id.txtShowTable);
        myListView = (ListView) findViewById(R.id.listView);
    }
    public void Click(View view){
        Button btn_ok = (Button)findViewById(R.id.button1);
        Intent intent = new Intent(OrderActivity.this, ListOrderActivity.class);
        intent.putExtra("Officer", strOfficer);
        intent.putExtra("Table", strTable);
        intent.putExtra("IdFood",strNumFood);
        intent.putExtra("Food",strFood);
        intent.putExtra("Price",strPrice);
        intent.putExtra("Volume",strVolume);

        startActivity(intent);
    }

    private void checkLog() {
        Log.d("order", "Officer ==> " + strOfficer);
        Log.d("order", "Table ==> " + strTable);
        Log.d("order", "IdFood ==> " + strNumFood);
        Log.d("order", "Food ==> " + strFood);
        Log.d("order", "Price ==> " + strPrice);
        Log.d("order", "Volume ==> " + strVolume);

    } // checkLog

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
