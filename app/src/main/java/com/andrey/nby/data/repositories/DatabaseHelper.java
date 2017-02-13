package com.andrey.nby.data.repositories;

import android.content.Context;

public interface DatabaseHelper {
    void deleteCurrency(Currency currency, Context context);
    void updateCurrency(Context context);
}
