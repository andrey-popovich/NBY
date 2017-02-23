package com.andrey.nby.ui.currencyListAdapter;

import android.view.View;

import com.andrey.nby.data.repositories.Currency;

public interface CurrencyListView {

    void onItemClick(Currency currency, View view);
}
