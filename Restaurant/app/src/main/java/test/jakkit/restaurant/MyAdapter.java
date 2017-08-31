package test.jakkit.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by KHAMMA on 4/2/2017.
 */

public class MyAdapter extends BaseAdapter{

    private Context objContext;
    private String[] strNumFood, strNameFood, strPriceFood;

    public MyAdapter(Context objContext, String[] strNumFood, String[] strNameFood, String[] strPriceFood) {
        this.objContext = objContext;
        this.strNumFood = strNumFood;
        this.strNameFood = strNameFood;
        this.strPriceFood = strPriceFood;
    }   // Constructor


    @Override
    public int getCount() {
        return strNameFood.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.list_view_row, parent, false);

        //NumFood
        TextView listNumFood = (TextView) view.findViewById(R.id.txtShowNumFood);
        listNumFood.setText(strNumFood[position]);

        //Food
        TextView listFood = (TextView) view.findViewById(R.id.txtShowFood);
        listFood.setText(strNameFood[position]);

        //Price
        TextView listPrice = (TextView) view.findViewById(R.id.txtShowPrice);
        listPrice.setText(strPriceFood[position]);

        return view;
    }
}   // Main Class
