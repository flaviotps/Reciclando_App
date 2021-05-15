package com.flaviotps.reciclando.Activities;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;

import com.flaviotps.reciclando.Adapter.MaterialAdapter;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Data.MaterialModel;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityMaterial extends AppCompatActivity {


    private String type;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MaterialAdapter materialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        type = getIntent().getStringExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.menu_material);


        setupRecyclerView();





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) search.getActionView();

        EditText et = searchView.findViewById(searchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null));
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                materialAdapter = new MaterialAdapter(MaterialModel.filterByName(query, DataRequestManager.data.materialList), ActivityMaterial.this);
                mRecyclerView.setAdapter(materialAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                materialAdapter = new MaterialAdapter(MaterialModel.filterByName(query, DataRequestManager.data.materialList), ActivityMaterial.this);
                mRecyclerView.setAdapter(materialAdapter);
                return false;
            }
        });

        return true;
    }


    public void setupRecyclerView() {

        List<MaterialModel> materialModelList = MaterialModel.filterByString(type, DataRequestManager.data.materialList);
        materialAdapter = new MaterialAdapter(materialModelList, this);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.RecyclerViewMaterial);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(materialAdapter);


    }




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

