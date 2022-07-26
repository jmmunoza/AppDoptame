package com.appdoptame.appdoptame.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.appdoptame.appdoptame.AppDoptameApp;
import com.appdoptame.appdoptame.R;

/**
 * Esta clase se encarga de convertir datos de tipo Date a texto en donde se puede apreciar
 * cuanto tiempo lleva la fecha hasta este mismo momento (Similar a Facebook).
 * Por ejemplo retorna los siguientes valores:
 *      - Hace 5 horas
 *      - Hace 2 dias
 *      - Hace pocos momentos
 *      - Hace 22 minutos
 *
 * @author: Juan Manuel Muñoz
 */
public class DateTextGetter {
    /**
     * Método que se encarga del objetivo de la clase.
     * @param date dato tipo Date al que se busca convertir a texto
     * @return conversión de date a texto
     */
    @SuppressLint("StringFormatMatches")
    public static String getDateText(Date date){
        Date currentTime = Calendar.getInstance().getTime();
        long diff = currentTime.getTime() - date.getTime();

        TimeUnit timeDays = TimeUnit.DAYS;
        long difference = timeDays.convert(diff, TimeUnit.MILLISECONDS);

        if(difference >= 7){
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
            return formatter.format(date);
        } else if(difference >= 1) {
            return String.format(
                    AppDoptameApp.getContext().getString(R.string.days_ago),
                    difference);
        } else {
            TimeUnit timeHours = TimeUnit.HOURS;
            difference = timeHours.convert(diff, TimeUnit.MILLISECONDS);

            if(difference >= 1){
                return String.format(
                        AppDoptameApp.getContext().getString(R.string.hours_ago),
                        difference);
            } else {
                TimeUnit timeMinutes = TimeUnit.MINUTES;
                difference = timeMinutes.convert(diff, TimeUnit.MILLISECONDS);

                if(difference > 1){
                    return String.format(
                            AppDoptameApp.getContext().getString(R.string.minutes_ago),
                            difference);
                } else {
                    return AppDoptameApp.getContext().getString(R.string.few_moments_ago);
                }
            }
        }
    }
}