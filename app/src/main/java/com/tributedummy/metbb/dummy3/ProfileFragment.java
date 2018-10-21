package com.tributedummy.metbb.dummy3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    LinearLayout linearLayout;
    View v;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        linearLayout = v.findViewById(R.id.profileLinearLayout);

        setupButtonLogout(inflater);
        setupButtonYourReviews(inflater);

        // Inflate the layout for this fragment
        return v;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    void addButton(LayoutInflater inflater, String menuText, int resourceInt, View.OnClickListener action)
    {

        View menuItem;
        menuItem = inflater.inflate(R.layout.layout_profilemenu, linearLayout,false);
        TextView text = menuItem.findViewById(R.id.profilemenuTextview);
        ImageView image = menuItem.findViewById(R.id.profilemenuImageview);
        ConstraintLayout constraintLayout = menuItem.findViewById(R.id.profilemenuConstraint);
        text.setText(menuText);
        image.setImageResource(resourceInt);
        constraintLayout.setOnClickListener(action);
        linearLayout.addView(menuItem);
    }


    // Default buttons
    void setupButtonYourReviews(LayoutInflater inflater)
    {

        View menuItem;
        menuItem = inflater.inflate(R.layout.layout_profilemenu, linearLayout,false);
        TextView text = menuItem.findViewById(R.id.profilemenuTextview);
        ImageView image = menuItem.findViewById(R.id.profilemenuImageview);
        Button button = menuItem.findViewById(R.id.profilemenuButton);
        text.setText("Your Reviews");
        image.setImageResource(R.drawable.ic_home_black_24dp);

        View.OnClickListener action = new View.OnClickListener(){@Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Go to your reviews", Toast.LENGTH_SHORT).show();}
        };

        button.setOnClickListener(action);
        linearLayout.addView(menuItem);
    }

    void setupButtonLogout(LayoutInflater inflater)
    {

        View menuItem;
        menuItem = inflater.inflate(R.layout.layout_profilemenu, linearLayout,false);
        TextView text = menuItem.findViewById(R.id.profilemenuTextview);
        ImageView image = menuItem.findViewById(R.id.profilemenuImageview);
        ConstraintLayout constraintLayout = menuItem.findViewById(R.id.profilemenuConstraint);
        text.setText("Logout");
        image.setImageResource(R.drawable.ic_home_black_24dp);
        View.OnClickListener action = new View.OnClickListener(){
            @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Go to your reviews", Toast.LENGTH_SHORT).show();
            }
        };
        constraintLayout.setOnClickListener(action);
        linearLayout.addView(menuItem);
    }
}
