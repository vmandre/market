package br.com.market.fragment;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.web.client.HttpClientErrorException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.market.R;
import br.com.market.infra.ParametrosAplicacao;
import br.com.market.infra.Utils;
import br.com.market.models.Ferias;
import br.com.market.models.Funcionario;
import br.com.market.models.Holerite;
import br.com.market.services.MarketRestService;

@EFragment(R.layout.fragment_holerite)
public class HoleriteFragment extends Fragment {

    private static final String TAG = "HoleriteFragment";

    @RestService
    MarketRestService marketService;
    @ViewById(R.id.btnDownload)
    public Button btnDownload;
    @ViewById(R.id.dataHolerite)
    public DatePicker datePicker;

    private View view;
    private ProgressDialog pDialog;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "METHOD: onCreateView");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_holerite, container, false);
        //Inicia os campos necessários caso não tenha sido injetado.
        initFields();
        // Inicia Listeners
        initScreen(view);
        // Retorna view
        return view;
    }

    private void initScreen(final View view) {
        Log.i(TAG, "METHOD: initScreen");

        Calendar mCalendar = Calendar.getInstance();
        datePicker.init(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH),null);
        datePicker.setMaxDate(System.currentTimeMillis());

        LinearLayout ll = (LinearLayout)datePicker.getChildAt(0);
        LinearLayout ll2 = (LinearLayout)ll.getChildAt(0);
        ll2.getChildAt(0).setVisibility(View.INVISIBLE);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "METHOD: btnDownload.onClick");
                Funcionario funcionarioLogado = (Funcionario) Utils.jsonToObject(
                        ParametrosAplicacao.getParametro(getActivity().getApplicationContext(), ParametrosAplicacao.CHAVE_FUNCIONARIO_LOGADO), Funcionario.class);

                String dataString = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-1";
                String nomeArquivo = "holerite_" + (datePicker.getMonth() + 1) + "_" + datePicker.getYear();

                downloadHolerite(funcionarioLogado.getCod(), dataString, nomeArquivo);
            }
        });
    }

    private void initFields() {
        if (btnDownload == null) {
            btnDownload = (Button) view.findViewById(R.id.btnDownload);
        }

        if (datePicker == null) {
            datePicker = (DatePicker) view.findViewById(R.id.dataHolerite);
        }
    }

    @Background
    public void downloadHolerite(Long codFuncionario, String data, String nomeArquivo) {
        Holerite resposta = null;

        try {
            resposta = marketService.consultarHolerite(codFuncionario, data);
        }catch (HttpClientErrorException e) {
            erroServico(Utils.validaErroServicoHttpClient(e,
                    "Erro no download do holerite, favor tentar mais tarde."));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            erroServico("Erro no download do holerite, favor tentar mais tarde.");
            return;
        }

        if (resposta != null && resposta.getPath() != null) {
            int count;
            try {
                URL url = new URL(resposta.getPath());
                URLConnection conection = url.openConnection();
                conection.connect();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream(Environment
                        .getExternalStorageDirectory().toString() +"/"+ nomeArquivo);

                byte dataFile[] = new byte[1024];

                while ((count = input.read(dataFile)) != -1) {
                    // writing data to file
                    output.write(dataFile, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
        } else {
            nomeArquivo = null;
        }

        verificarResposta(resposta, nomeArquivo);
    }

    @UiThread
    void verificarResposta(Holerite holerite, String nomeArquivo) {
        Log.i(TAG, "METHOD: verificarResposta");
        if (nomeArquivo != null) {
            File file = new File(Environment.getExternalStorageDirectory().toString() +"/"+ nomeArquivo);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            startActivity(intent);
        } else {
            erroServico("Erro no download do holerite, favor tentar mais tarde.");
            return;
        }
    }


    @UiThread
    void erroServico(String mensagem) {
        exibirToast(mensagem, Toast.LENGTH_LONG);
    }

    private void exibirToast(CharSequence mensagem, int duration) {
        Toast.makeText(getActivity().getApplicationContext(), mensagem, duration).show();
    }
}
