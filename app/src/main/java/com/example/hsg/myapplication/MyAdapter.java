package com.example.hsg.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsg on 2017. 8. 27..
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {


    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        static final int Request_Num = 3;

        private final Context context;
        private Intent intent;
        // each data item is just a string in this case
        public TextView mTextView;

        public ViewHolder(View itemview) {

            super(itemview);


            mTextView = (TextView) itemview.findViewById(R.id.mTextView);

            context = itemview.getContext();

            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    switch(getAdapterPosition()){

                        case 0 : //서울특별시
                            intent = new Intent(context, MainActivity.class);
                            intent.putExtra("lat_ob",37.540705);
                            intent.putExtra("lon_ob",126.956764);

                        case 1: //인천광역시
                            intent = new Intent(context, MainActivity.class);
                            intent.putExtra("lat_ob",37.469221);
                            intent.putExtra("lon_ob",126.573234);

                        case 2: //광주광역시
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",35.126033);
                            intent.putExtra("lon_ob",126.831302);

                        case 3: //대구광역시
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",35.798838);
                            intent.putExtra("lon_ob",128.583052);

                        case 4: //울산광역시
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",35.519301);
                            intent.putExtra("lon_ob",129.239078);

                        case 5: //대전광역시
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",37.469221);
                            intent.putExtra("lon_ob",127.378953);

                        case 6: //부산광역시
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",35.198362);
                            intent.putExtra("lon_ob",129.053922);

                        case 7: //경기도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",37.567167);
                            intent.putExtra("lon_ob",127.190292);

                        case 8: //강원도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",37.555837);
                            intent.putExtra("lon_ob",128.209315);

                        case 9: //충청남도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",36.557229);
                            intent.putExtra("lon_ob",126.779757);

                        case 10: //충청북도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",36.628503);
                            intent.putExtra("lon_ob",127.929344);

                        case 11: //경상북도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",36.248647);
                            intent.putExtra("lon_ob",128.664734);

                        case 12: //경상남도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",35.259787);
                            intent.putExtra("lon_ob",128.664734);

                        case 13: //전라북도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",35.716705);
                            intent.putExtra("lon_ob",127.144185);

                        case 14: //전라남도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",34.819400);
                            intent.putExtra("lon_ob",126.893113);

                        case 15: //제주도
                            intent = new Intent(context,MainActivity.class);
                            intent.putExtra("lat_ob",33.364805);
                            intent.putExtra("lon_ob",126.542671);

                            break;

                        default:
                            intent = new Intent(context, MainActivity.class);
                            intent.putExtra("lat_ob",37.540705);
                            intent.putExtra("lon_ob",126.956764);

                    }

                    context.startActivity(intent);

                }
            });
            

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }






}