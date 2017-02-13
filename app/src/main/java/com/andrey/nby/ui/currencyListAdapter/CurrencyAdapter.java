package com.andrey.nby.ui.currencyListAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrey.nby.R;
import com.andrey.nby.data.repositories.Currency;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class CurrencyAdapter extends RealmRecyclerViewAdapter<Currency, CurrencyAdapter.CurrencyViewHolder> {
    private static final String TAG = "CurrencyAdapter";

//    final CurrencyPresenter exchangesPresenter;

    public CurrencyAdapter(RealmResults<Currency> books, Context context) {
        super(context, books, true);
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exchange_item, parent, false);
        return new CurrencyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        Currency currency = getItem(position);
        if (currency != null) {
            Log.i(TAG, "Currency: " + currency.getCc());
            holder.r030.setText(Integer.toString(currency.getR030()));
            holder.txt.setText(currency.getTxt());
            holder.cc.setText(currency.getCc());
            holder.rate.setText(Double.toString(currency.getRate()));
            holder.date.setText(currency.getExchangeDate());
        }
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {

        public TextView r030;
        public TextView txt;
        public TextView cc;
        public TextView rate;
        public TextView date;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            r030 = (TextView) itemView.findViewById(R.id.exchange_r030);
            txt = (TextView) itemView.findViewById(R.id.exchange_txt);
            cc = (TextView) itemView.findViewById(R.id.exchange_cc);
            rate = (TextView) itemView.findViewById(R.id.exchange_rate);
            date = (TextView) itemView.findViewById(R.id.exchange_date);
        }
    }
}
