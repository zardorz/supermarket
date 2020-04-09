package br.com.bmgsistemas.supermarket.core;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import br.com.bmgsistemas.supermarket.R;

public class Loader {

    static ProgressDialog progressBar = null;

    public static void show(Activity activity){

        if(progressBar != null){
            progressBar.dismiss();
            progressBar = null;
        }

//        https://github.com/afollestad/material-dialogs
//                MaterialDialog.Builder materialBuilder = new MaterialDialog.Builder(activity);
//                materialBuilder.progress(false, 150, true);
////                materialBuilder.title("title");
//
//                materialBuilder.widgetColorRes(R.color.progress_dialog_color);
//                materialBuilder.progress(true, 0);
//                materialBuilder.progressIndeterminateStyle(true);
//                materialBuilder.show();

        progressBar = new ProgressDialog(activity, R.style.LoaderTheme);
//        progressBar = new ProgressDialog(activity);
//                progressBar.setProgressStyle(R.style.AppCompatAlertDialogStyle);
//                progressBar.setTitle("Title");
//                progressBar.setMessage("Message");//getString(R.string.progress_message));
        progressBar.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressBar.setIndeterminate(true);
        progressBar.setCancelable(false);

//                Drawable drawable1 = getApplicationContext().getResources().getDrawable(R.drawable.custom_progress_dialog);
//                progressBar.setIndeterminateDrawable(drawable1);
//
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                    Drawable drawable = new ProgressBar(activity).getIndeterminateDrawable().mutate();
//                    drawable.setColorFilter(ContextCompat.getColor(activity, R.color.progress_dialog_color),PorterDuff.Mode.SRC_IN);
//                    progressBar.setIndeterminateDrawable(drawable);
//                }

        progressBar.show();
        progressBar.setContentView(R.layout.loader);

    }

    public static void dismiss() {
        if(progressBar != null){
            progressBar.dismiss();
            progressBar = null;
        }
    }



}
