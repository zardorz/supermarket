package br.com.bmgsistemas.supermarket.ui.statement;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatementFragment extends Fragment {

    private View view;
    private Activity activity;

    public StatementFragment() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();

        MainActivity.setFragmentTitle("Extrato");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = (Activity) getActivity();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_statement, container, false);

        return  view;
    }

}
