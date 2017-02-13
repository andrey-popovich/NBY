package com.andrey.nby.data.repositories;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.di.component.ApplicationComponent;
import com.andrey.nby.service.NBUClient;
import com.andrey.nby.service.NBUService;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatabaseHelperImpl implements DatabaseHelper {

    private static final String TAG = "DatabaseHelperImpl";

    public DatabaseHelperImpl(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void deleteCurrency(Currency currency, Context context) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.deleteAll();
                }
            });
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        toastMessage(R.string.on_delete, context);
    }

    @Override
    public void updateCurrency(final Context context) {
        NBUService service = NBUClient.getNBUService();
        Call<List<Currency>> call = service.getCurrencyList("");
        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                Log.i(TAG, "onResponse");
                if (response.isSuccessful()) {
                    final List<Currency> currencyList = response.body();
                    Realm realm = null;
                    try {
                        realm = Realm.getDefaultInstance();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                for (int i = 0; i < currencyList.size(); i++) {
                                    realm.insertOrUpdate(currencyList.get(i));
                                }
                            }
                        });
                    } finally {
                        if (realm != null) {
                            realm.close();
                        }
                    }
                }
                toastMessage(R.string.on_response, context);
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getMessage());
                toastMessage(R.string.on_failure, context);
            }
        });
    }

    public void toastMessage(int text, Context context){
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
