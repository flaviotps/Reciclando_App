package com.flaviotps.reciclando.Fragments;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.flaviotps.reciclando.Adapter.ProductAdapter;
import com.flaviotps.reciclando.Data.DataRequestManager;
import com.flaviotps.reciclando.Data.ProductModel;
import com.flaviotps.reciclando.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentProduct extends Fragment implements View.OnClickListener{

    private View fragmentView;
    private ProductAdapter productAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView  = inflater.inflate(R.layout.fragment_product,container,false);

        productAdapter = new ProductAdapter(DataRequestManager.data.productList, getActivity());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView = fragmentView.findViewById(R.id.productRecycler);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(productAdapter);

        return fragmentView;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) search.getActionView();

        EditText et = searchView.findViewById(searchView.getContext().getResources()
                .getIdentifier("android:id/search_src_text", null, null));
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                productAdapter = new ProductAdapter(ProductModel.filterByName(query, DataRequestManager.data.productList), getActivity());
                mRecyclerView.setAdapter(productAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                productAdapter = new ProductAdapter(ProductModel.filterByName(query, DataRequestManager.data.productList), getActivity());
                mRecyclerView.setAdapter(productAdapter);
                return false;
            }
        });
    }
}
