package com.flaviotps.reciclando.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flaviotps.reciclando.Activities.ActivityMaterial;
import com.flaviotps.reciclando.R;
import com.flaviotps.reciclando.Utils.Constants;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class FragmentMaterialMenu extends Fragment implements View.OnClickListener{

    private View fragmentView;

    private int[] res = {R.id.CardBtnMaterialPaper, R.id.CardBtnMaterialPlastic,
            R.id.CardBtnMaterialGlass, R.id.CardBtnMaterialMetal,
            R.id.CardBtnMaterialOrganic, R.id.CardBtnMaterialBattery,
            R.id.CardBtnMaterialMixed, R.id.CardBtnMaterialEletronics};
    private CardView[] mCardView = new CardView[Constants.BUTTONS_MATERIAL_MENU_COUNT];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView  = inflater.inflate(R.layout.fragment_material_menu,container,false);

       Init(res);

        return fragmentView;
    }

    

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), ActivityMaterial.class);

        switch (view.getId()){

            case R.id.CardBtnMaterialPaper:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_PAPER);
                startActivity(intent);
                break;

            case R.id.CardBtnMaterialPlastic:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_PLASTIC);
                startActivity(intent);
                break;

            case R.id.CardBtnMaterialGlass:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_GLASS);
                startActivity(intent);
                break;

            case R.id.CardBtnMaterialMetal:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_METAL);
                startActivity(intent);
                break;

            case R.id.CardBtnMaterialOrganic:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_ORGANIC);
                startActivity(intent);
                break;

            case R.id.CardBtnMaterialBattery:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_BATTERY);
                startActivity(intent);
                break;


            case R.id.CardBtnMaterialEletronics:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_ELETRONIC);
                startActivity(intent);
                break;

            case R.id.CardBtnMaterialMixed:
                intent.putExtra(Constants.BUNDLE_ARG_MATERIAL_TYPE, Constants.MATERIAL_TYPE_MIXED);
                startActivity(intent);
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

