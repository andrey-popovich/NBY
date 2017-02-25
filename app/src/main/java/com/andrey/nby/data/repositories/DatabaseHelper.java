package com.andrey.nby.data.repositories;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;

public interface DatabaseHelper {
    void deleteCurrency(Currency currency, Context context);
    void updateCurrency(Context context,  FloatingActionButton fab);
}
