package com.example.usuariocliente.Cliente.Options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Driver.DriverMenu;
import com.example.usuariocliente.Login;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.usuariocliente.Models.Globals.id_usuario;

public class AgregarCard extends AppCompatActivity {

    Button btntarjeta;
    EditText nametarjeta, numtarjeta, fechatarjeta, cvvtarjeta;
    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago_form);
        cargarSP();
        Cliente cliente = Globals.getCliente(id_usuario);
        id_user = String.valueOf(cliente.getId_Usuario());
        nametarjeta = findViewById(R.id.nametarjeta);
        numtarjeta = findViewById(R.id.numtarjeta);
        fechatarjeta = findViewById(R.id.fechatarjeta);
        cvvtarjeta = findViewById(R.id.cvvtarjeta);
        btntarjeta = findViewById(R.id.btntarjeta);
        //metodosExistentes(id_user);
        btntarjeta.setOnClickListener(v -> {

            if(  !(nametarjeta.getText().toString().isEmpty())  && !(numtarjeta.getText().toString().isEmpty()) && !(fechatarjeta.getText().toString().isEmpty()) && !(cvvtarjeta.getText().toString().isEmpty())) {
            registroMetodoPago();
            }
            else{
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
            }
        });


    }




    private void registroMetodoPago() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "metodoPago.php", response -> {
            if (response.equals("MENSAJE")){
                Toast.makeText(this, "Método de pago agregado con éxito", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, ListaCards.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }

        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                parametros.put("id_usuario", String.valueOf(id_usuario));
                parametros.put("numeracion_tarjeta", numtarjeta.getText().toString());
                parametros.put("fecha_vencimiento", fechatarjeta.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void cargarSP(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        id_usuario = preferences.getInt("id_unico", 0);
    }

    public void back(View view) {
        Intent i = new Intent(this, ListaCards.class);
        startActivity(i);
        finish();
    }
}