package br.com.bmgsistemas.supermarket.ui.account;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.bmgsistemas.supermarket.R;
import br.com.bmgsistemas.supermarket.models.Account;

public class AccountAdapter extends ArrayAdapter<Account> {

    static class ViewHolder {
        //        private ImageView imageView;
        private TextView instituitionName;
    }

    private List<Account> accounts;
    //    private DrawableCache    drawableCache = DrawableCache.getInstance();
    private int              layout;

    @Override
    public int getCount() {
        return accounts.size();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        // position always 0-7

        View       view = null;
        ViewHolder viewHolder = null;

        if (contentView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            if (view != null) {
                viewHolder = new ViewHolder();
//                viewHolder.imageView = (ImageView) view.findViewById(R.id.happyactivity_row_image_left);
//                viewHolder.textViewBottom = (TextView) view.findViewById(R.id.happyactivity_row_text_bottom);
//                viewHolder.textViewTop = (TextView) view.findViewById(R.id.happyactivity_row_text_top);
                viewHolder.instituitionName = (TextView) view.findViewById(R.id.tvItem);
                view.setTag(viewHolder);
            }
        } else {
            view = contentView;
            viewHolder = (ViewHolder) contentView.getTag();
        }

        if (viewHolder != null) {
            Account account = accounts.get(position);
            if (account != null) {
//                viewHolder.imageView.setUrl(happy.getImageThumbnail());
//                drawableCache.fetchDrawable(happy.getImageThumbnail(), viewHolder.imageView);
//                viewHolder.textViewBottom.setText(String.valueOf(position));
//                viewHolder.textViewTop.setText(String.valueOf(viewHolder.position));
                viewHolder.instituitionName.setText(String.valueOf(account.getName()));
            }
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View       view = null;
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);

            if (view != null) {
                viewHolder = new ViewHolder();
//                viewHolder.imageView = (ImageView) view.findViewById(R.id.happyactivity_row_image_left);
//                viewHolder.textViewBottom = (TextView) view.findViewById(R.id.happyactivity_row_text_bottom);
//                viewHolder.textViewTop = (TextView) view.findViewById(R.id.happyactivity_row_text_top);
                viewHolder.instituitionName = (TextView) view.findViewById(R.id.tvItem);
                view.setTag(viewHolder);
            }
        } else {
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (viewHolder != null) {
            Account account = accounts.get(position);
            if (account != null) {
//                viewHolder.imageView.setUrl(happy.getImageThumbnail());
//                drawableCache.fetchDrawable(happy.getImageThumbnail(), viewHolder.imageView);
//                viewHolder.textViewBottom.setText(String.valueOf(position));
//                viewHolder.textViewTop.setText(String.valueOf(viewHolder.position));
                viewHolder.instituitionName.setText(String.valueOf(account.getName()));
            }
        }

////        if(convertView == null){
////            convertView = flater.inflate(R.layout.list_itemslayout,parent, false);
////        }
//        FinanceAccount rowItem = getItem(position);
////        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
////        txtTitle.setText(rowItem.getTitle());
////        ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);
////        imageView.setImageResource(rowItem.getImageId());
//        viewHolder.instituitionName.setText(String.valueOf(financeAccount.getName()));

        return view;
    }

    public AccountAdapter(Context context, int layout, List<Account> accounts) {
        super(context, layout, accounts);

        this.accounts = accounts;
        this.layout = layout;
    }
}