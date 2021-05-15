package com.flaviotps.reciclando.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flaviotps.reciclando.Activities.ActivityLocation;
import com.flaviotps.reciclando.Activities.ActivityMain;
import com.flaviotps.reciclando.Activities.ActivityStatistics;
import com.flaviotps.reciclando.Activities.ActivityVideos;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment implements View.OnClickListener{

    private View fragmentView;
    private ActivityMain activityMain;
    private CardView[] mCardView = new CardView[Constants.BUTTONS_MAIN_MENU_COUNT];
    private static final int[] res = {R.id.MainCardViewButtonMaterial,R.id.MainCardViewButtonLocation,
                         R.id.MainCardViewButtonProducts,R.id.MainCardViewStatistics,
                         R.id.MainCardViewButtonLearn,R.id.MainCardViewButtonVideos};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView  = inflater.inflate(R.layout.fragment_main,container,false);
        activityMain = (ActivityMain) getActivity();
        Init(res);
        return fragmentView;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.MainCardViewStatistics:
                startActivity(new Intent(getActivity(), ActivityStatistics.class));
                break;
            case R.id.MainCardViewButtonLocation:
                startActivity(new Intent(getActivity(), ActivityLocation.class));
                break;
            case R.id.MainCardViewButtonMaterial:
                activityMain.setViewPager(Constants.MAIN_FRAGMENT_MATERIAL);
                break;

            case R.id.MainCardViewButtonProducts:
                activityMain.setViewPager(Constants.MAIN_FRAGMENT_PRODUCTS);
                break;

            case R.id.MainCardViewButtonVideos:

                startActivity(new Intent(getActivity(), ActivityVideos.class));
                break;
        }

    }


    private void Init(int[] res){
        for(int i=0;i<res.length;i++){
            mCardView[i] = fragmentView.findViewById(res[i]);
            mCardView[i].setOnClickListener(this);
        }
    }


}
