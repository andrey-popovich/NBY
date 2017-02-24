package com.andrey.nby.ui.currencyListAdapter;

import android.view.View;

import com.andrey.nby.data.repositories.Currency;

public interface CurrencyListView {

    void onFavoriteClick(Currency currency, View view, CurrencyAdapter.CurrencyViewHolder holder);
}
