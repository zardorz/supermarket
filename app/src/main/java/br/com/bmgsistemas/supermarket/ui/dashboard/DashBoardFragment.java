package br.com.bmgsistemas.supermarket.ui.dashboard;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bmgsistemas.supermarket.MainActivity;
import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.enums.eAccountType;
import br.com.bmgsistemas.supermarket.enums.eStatementOperationType;
import br.com.bmgsistemas.supermarket.models.Account;
import br.com.bmgsistemas.supermarket.models.Statement;
import br.com.bmgsistemas.supermarket.partial.CardAccounts;
import br.com.bmgsistemas.supermarket.partial.CardAccountsResume;
import br.com.bmgsistemas.supermarket.partial.CardLastTransactions;
import br.com.bmgsistemas.supermarket.partial.CardResumeMonth;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment {

    private View view;
    private Activity activity;

    public DashBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

//        Toolbar toolbar = MainActivity.getToolbar(); // (Toolbar) view.findViewById(R.id.toolbar);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dashboard");

//        doLoadFinanceAccounts();

        MainActivity.setFragmentTitle("Dashboard");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = (Activity) getActivity();

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dash_board, container, false);

//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        ImageButton imgButton =  view.findViewById(R.id.ib_popup_menu);
//
//        imgButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(view, 0 /*position*/);
//            }
//        });

        LinearLayout ll_cards_container = view.findViewById(R.id.ll_cards_container);

        CardResumeMonth cardResumeMonth = new CardResumeMonth( activity, ll_cards_container);
        CardAccounts cardAccount = new CardAccounts(  activity, ll_cards_container);
        CardLastTransactions cardLastTransactions = new CardLastTransactions( activity, ll_cards_container);


        return  view;
    }

    private void showPopupMenu(View view, int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu( getActivity(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        //popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

}
