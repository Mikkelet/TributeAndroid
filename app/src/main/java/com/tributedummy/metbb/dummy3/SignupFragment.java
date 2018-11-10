package com.tributedummy.metbb.dummy3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tributedummy.metbb.dummy3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private Button buttonEmail;
    private Button buttonFacebook;
    private MainActivity mainActivity;

    private View v;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_signup, container, false);
        // Inflate the layout for this fragment
        mainActivity = ((MainActivity)getActivity());
        setupButtonEmail(v);
        setupButtonFacebook(v);
        return v;
    }

    private void setupButtonEmail(View v)
    {
        buttonEmail = v.findViewById(R.id.signupButtonEmail);

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Email", Toast.LENGTH_SHORT).show();
                mainActivity.switchFragment(mainActivity.getLoginFragment(),false);
                mainActivity.toggleMenu(true);
            }
        });
    }

    private void setupButtonFacebook(View v)
    {
        buttonFacebook = v.findViewById(R.id.signupButtonFacebook);

        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Facebook", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
