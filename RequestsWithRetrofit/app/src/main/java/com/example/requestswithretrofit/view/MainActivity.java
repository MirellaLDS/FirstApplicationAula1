package com.example.requestswithretrofit.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.requestswithretrofit.R;
import com.example.requestswithretrofit.RequestResult;
import com.example.requestswithretrofit.model.CEP;
import com.example.requestswithretrofit.model.Desenvolvedor;
import com.example.requestswithretrofit.model.DevMessage;
import com.example.requestswithretrofit.repository.CepRepository;
import com.example.requestswithretrofit.repository.DevRepository;
import com.example.requestswithretrofit.services.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public final CepRepository cepRepository = new CepRepository();
    public final DevRepository devRepository = new DevRepository();
    private RequestResult resultListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cep = findViewById(R.id.etMain_cep);
        final TextView resposta = findViewById(R.id.etMain_resposta);
        final Button btnBuscarCep = findViewById(R.id.btnMain_buscarCep);

        this.response(resposta);

        btnBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                cepRepository.buscarCep(cep.getText().toString(), resultListener);
                devRepository.getUserProfile(cep.getText().toString(), resultListener);
            }
        });
    }

    private void response(final TextView resposta) {
        resultListener = new RequestResult() {

            @Override
            public <T> void returnSuccess(T cep) {
                Desenvolvedor dev = (Desenvolvedor) cep;
                resposta.setText(dev.getName());
            }

            @Override
            public void returnError(String message) {
                resposta.setText(message);
            }
        };
    }
}