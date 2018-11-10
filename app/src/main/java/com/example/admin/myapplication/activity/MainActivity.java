package com.example.admin.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.view.TempRecyclerView;

public class MainActivity extends AppCompatActivity {
    private TempRecyclerView mRecycleView;
    private MyAdapter mAdapter;

    private LinearLayoutManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleView = findViewById(R.id.recycleView);
        initRecycleView();

    }


    private void initRecycleView() {
        mAdapter = new MyAdapter();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycleView.setLayoutManager(manager);
        mRecycleView.setAdapter(mAdapter);
    }


    public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(getLayoutInflater().inflate(R.layout.list_rv_item, null));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecycleView.smoothScrollBy(v);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgDesc;
        private View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.imgDesc = itemView.findViewById(R.id.imgDesc);

        }
    }
}
