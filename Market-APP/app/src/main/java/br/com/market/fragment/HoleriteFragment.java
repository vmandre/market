package br.com.market.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.Calendar;

import br.com.market.R;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_holerite)
public class HoleriteFragment extends Fragment {

    private static final String TAG = "HoleriteFragment";

    @RestService
    MarketRestService marketService;
    @ViewById(R.id.dataHolerite)
    public DatePicker datePicker;

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_holerite, container, false);
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

    private void initScreen(final View view) {
        Log.i(TAG, "METHOD: initScreen");

        Calendar mCalendar = Calendar.getInstance();
        if (datePicker == null) {
            datePicker = (DatePicker) view.findViewById(R.id.dataHolerite);
        }
        datePicker.init(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH),null);
        datePicker.setMaxDate(System.currentTimeMillis());

        LinearLayout ll = (LinearLayout)datePicker.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(0);
        ll2.getChildAt(0).setVisibility(View.INVISIBLE);
    }

    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText(getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
