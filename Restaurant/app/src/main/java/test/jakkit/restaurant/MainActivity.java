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
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private UserTABLE objUserTABLE;
    private FoodTABLE objFoodTABLE;
    private EditText edtUser, edtPassword;
    private String strUserChoose, strPasswordChoose, strPasswordTrue, strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Connected Database
        objUserTABLE = new UserTABLE(this);
        objFoodTABLE = new FoodTABLE(this);

        //Test Add New Value to SQLite
//        addValue();

    }   // onCreate

    private void bindWidget() {
        edtUser = (EditText) findViewById(R.id.editText);
        edtPassword = (EditText) findViewById(R.id.editText2);
    }

    public void clickLogin(View view) {

        strUserChoose = edtUser.getText().toString().trim();
        strPasswordChoose = edtPassword.getText().toString().trim();

        if (strUserChoose.equals("") || strPasswordChoose.equals("") ) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this, "คำเเนะนำ !", "Username Password ไม่ถูกต้อง");

        } else {

            //Check User
            checkUser();

        }

    }   // clickLogin

    private void checkUser() {

        try {

            String strMyResult[] = objUserTABLE.searchUser(strUserChoose);
            strPasswordTrue = strMyResult[2];
            strName = strMyResult[3];

            //Log.d("oic", "Welcome ==> " + strName);

            //Check Password
            checkPassword();

        } catch (Exception e) {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this, "Username ไม่ถูกต้อง !", "("+ strUserChoose +") Username นี้ไม่มีในฐานข้อมูล");
        }
    }

    private void checkPassword() {
        if (strPasswordChoose.equals(strPasswordTrue)) {
            welcome();
        } else {
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainActivity.this, "Password ไม่ถูกต้อง !", "กรุณากรอก Password อีกครั้ง");
        }
    }
    private void welcome() {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.restaurant2);
        objBuilder.setTitle("ยินดีต้อนรับ");
        objBuilder.setMessage("[" + strName + "] เข้าสู่ระบบร้านอาหาร");
        objBuilder.setCancelable(false);

        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent objIntent = new Intent(MainActivity.this, TableActivity.class);
                objIntent.putExtra("Officer", strName);
                startActivity(objIntent);
                dialog.dismiss();
                finish(); //ปิดการย้อนกลับ
            }
        });
        objBuilder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edtUser.setText("");
                edtPassword.setText("");
                dialog.dismiss();
            }
        });
        objBuilder.show();

    }

    private void addValue() {
        objUserTABLE.addValueToUser("art", "1234", "จักรกฤษณ์ คำมา");
        objUserTABLE.addValueToUser("nang", "1234", "พรศิริ คำมา");
        objUserTABLE.addValueToUser("earth", "1234", "วโรตม์ คำมา");
        objUserTABLE.addValueToUser("ass", "1234", "สุรชัย คำมา");
        objFoodTABLE.addValueToFood("01","ตำถาด", "150");
        objFoodTABLE.addValueToFood("02","ตำทะเล", "60");
        objFoodTABLE.addValueToFood("03","ตำปูม้า", "50");
        objFoodTABLE.addValueToFood("04","ตำกุ้งสด", "50");
        objFoodTABLE.addValueToFood("05","ตำมั่ว", "50");
        objFoodTABLE.addValueToFood("06","ตำไทย", "40");
        objFoodTABLE.addValueToFood("07","ตำปูปลาร้า", "40");
        objFoodTABLE.addValueToFood("08","ตำถั่ว", "40");
        objFoodTABLE.addValueToFood("09","ตำเเตง", "40");
        objFoodTABLE.addValueToFood("10","ตำลาว", "40");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
