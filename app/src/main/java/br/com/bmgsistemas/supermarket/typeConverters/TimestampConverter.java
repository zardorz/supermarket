package br.com.bmgsistemas.supermarket.typeConverters;

import android.arch.persistence.room.TypeConverter;
import android.provider.SyncStateContract;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
//
//public class TimestampConverter {
//    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
//
//    @TypeConverter
//    public static Date fromTimestamp(String value) {
//        if (value != null) {
//            try {
//                return df.parse(value);
//            } catch (Exception e) {
//                System.out.println("Excep"+e);
//            }
//            return null;
//        } else {
//            return null;
//        }
//}
