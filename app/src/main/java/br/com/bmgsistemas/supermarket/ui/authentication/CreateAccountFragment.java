package br.com.bmgsistemas.supermarket.ui.authentication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.LoginFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateAccountFragment extends Fragment {


    private View view;

    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_create_account, container, false);


        ((TextView) view.findViewById(R.id.hypForgotPassword)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(getActivity(), LostPasswordFragment.class);
//                //activity.startActivity(intent);
            }
        });


        ((TextView) view.findViewById(R.id.hypLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(getActivity(), LoginFilter.class);
//                //activity.startActivity(intent);
            }
        });

        ((TextView) view.findViewById(R.id.lblPageTitle)).setText(getResources().getString(R.string.create_account_title));
        ((TextView) view.findViewById(R.id.lblPageTitleDescription)).setText(getResources().getString(R.string.create_account_title_description));


        return view;
    }

}
