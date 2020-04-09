package br.com.bmgsistemas.supermarket.ui.about;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.core.GlobalState;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    private GlobalState gs;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gs = (GlobalState) this.getActivity().getApplication();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);


        ((TextView) view.findViewById(R.id.tvVersion)).setText("Versão: " + gs.getVersion());

        return view;
    }

}
