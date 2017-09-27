package com.example.hsg.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    static final int Req_code = 3;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button btn;

    String[] myDataset = {"서울특별시", "인천광역시","광주광역시", "대구광역시","울산광역시","대전광역시",
            "부산광역시","경기도","강원도","충청남도","충청북도","경상북도","경상남도","전라북도","전라남도","제주도"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        btn = (Button) findViewById(R.id.btn_now);

        //현재 위치 날씨 보기
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent_2 = new Intent(ListActivity.this, MainActivity.class);

                startActivityForResult(intent_2,Req_code);


            }
        });




    }
}
