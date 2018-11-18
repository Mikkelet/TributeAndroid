package com.tributedummy.metbb.dummy3;

import android.content.Context;
import android.databinding.DataBindingUtil;
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

import com.tributedummy.metbb.dummy3.databinding.FragmentProfileBinding;
import com.tributedummy.metbb.dummy3.databinding.LayoutProfilemenuBinding;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding profileBinding;

    public ProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileBinding = DataBindingUtil.bind(inflater.inflate(R.layout.fragment_profile, container, false));

        setupButtonLogout(inflater);
        setupButtonYourReviews(inflater);

        // Inflate the layout for this fragment
        return profileBinding.getRoot();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void addButton(LayoutInflater inflater, String menuText, int resourceInt, View.OnClickListener action) {
        // gets the view to inflate
        LayoutProfilemenuBinding profilemenuBinding = LayoutProfilemenuBinding.inflate(inflater);

        // adds the features
        profilemenuBinding.setMenuOption(menuText);
        profilemenuBinding.setImage(resourceInt);
        profilemenuBinding.setOnclick(action);

        // addsview to layout
        profileBinding.profileLinearLayout.addView(profilemenuBinding.getRoot());
    }


    // Default buttons
    void setupButtonYourReviews(LayoutInflater inflater) {
        // gets the view to inflate
        LayoutProfilemenuBinding profilemenuBinding = LayoutProfilemenuBinding.inflate(inflater);

        // defines the onclick actions
        View.OnClickListener action = new View.OnClickListener(){@Override
        public void onClick(View v) { Toast.makeText(getContext(), "Go to your reviews", Toast.LENGTH_SHORT).show();}
        };

        // adds the features
        profilemenuBinding.setMenuOption("Your Reviews");
        profilemenuBinding.setImage(R.drawable.ic_home_black_24dp);
        profilemenuBinding.setOnclick(action);

        // addsview to layout
        profileBinding.profileLinearLayout.addView(profilemenuBinding.getRoot());
    }
    void setupButtonLogout(LayoutInflater inflater) {
        // gets the view to inflate
        LayoutProfilemenuBinding profilemenuBinding = LayoutProfilemenuBinding.inflate(inflater);

        // defines the onclick actions
        View.OnClickListener action = new View.OnClickListener(){@Override
        public void onClick(View v) { Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();}
        };

        // adds the features
        profilemenuBinding.setMenuOption("Logout");
        profilemenuBinding.setImage(R.drawable.ic_home_black_24dp);
        profilemenuBinding.setOnclick(action);

        // addsview to layout
        profileBinding.profileLinearLayout.addView(profilemenuBinding.getRoot());
    }
}
