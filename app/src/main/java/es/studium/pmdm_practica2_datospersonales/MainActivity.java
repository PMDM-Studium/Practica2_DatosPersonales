package es.studium.pmdm_practica2_datospersonales;
//Importamos los paquetes necesarios
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//Declaramos variables para el traductor
    private Button button;
    private TextView textview;
    private Locale locale;
    private Configuration config = new Configuration();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Preparamos el boton traducir y mostramos el dialogo al pulsarlo
        button = ((Button)findViewById(R.id.traducir));
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        showDialog();
                    }});

            // creamos el spinner Estado Civil
            Spinner spinner = (Spinner) findViewById(R.id.spinnerEstadoCivil);
            // Creamos el array para el Spinner
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.despegableEstadoCivil, android.R.layout.simple_spinner_item);
            // Especificamos el dineño del spinner
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // aplicamos el diseño al spinner
            spinner.setAdapter(adapter);
            //Declaramos las variables
            final TextView txtNombre = findViewById(R.id.txtNombre);
            final TextView txtApellidos = findViewById(R.id.txtApellidos);
            final TextView txtEdad = findViewById(R.id.txtEdad);
            RadioButton rdBHombre = findViewById(R.id.rdHombre);
            RadioButton rdBMujer = findViewById(R.id.rdMujer);
            final Spinner spinnerEstadoCivil = findViewById(R.id.spinnerEstadoCivil);
            final Switch switchHijos = findViewById(R.id.switchHijos);
            Button btnAceptar = findViewById(R.id.btnAceptar);
            Button btnLimpiar = findViewById(R.id.btnLimpiar);
            final TextView resultado = findViewById(R.id.resultado);
            final RadioButton rdHombre = findViewById(R.id.rdHombre);
            final RadioButton rdMujer = findViewById(R.id.rdMujer);
            //Indicamos que hara el boton limpiar
            btnLimpiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtNombre.setText("");
                    txtApellidos.setText("");
                    txtEdad.setText("");
                    //Elimino la seleccion de los dos radio, aunque creo que es un error. Ya que al ser RadioGroup lo suyo seria dejar uno marcado para que el programa no falle.
                    rdHombre.setChecked(false);
                    rdMujer.setChecked(false);
                    switchHijos.setChecked(false);
                    resultado.setText("");
                    spinnerEstadoCivil.setSelection(0);
                    txtNombre.setHint(getResources().getString(R.string.txtNombre));
                    txtApellidos.setHint(getResources().getString(R.string.txtApellidos));
                    txtEdad.setHint(getResources().getString(R.string.txtEdad));
                    txtNombre.setHintTextColor(getResources().getColor(R.color.material_on_primary_disabled));
                    txtApellidos.setHintTextColor(getResources().getColor(R.color.material_on_primary_disabled));
                    txtEdad.setHintTextColor(getResources().getColor(R.color.material_on_primary_disabled));
                }
            });
            //Indicamos que hara el boton Aceptar
            btnAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nombre = txtNombre.getText().toString();
                    String apellidos = txtApellidos.getText().toString();
                    String edad = txtEdad.getText().toString();
                    String mayorDeEdad = null;
                    String hijos;
                    String sexo;
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.errorToast, Toast.LENGTH_SHORT);

                    if (nombre.isEmpty()) {
                        txtNombre.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
                        txtNombre.setHint(getResources().getString(R.string.errorNombre));
                        //Mostramos un Toast indicando que faltan datos
                        toast.show();
                    }else if (apellidos.isEmpty()) {
                        //Ya que si hay error se cambia a rojo volvemos a cambiar el hint para ponerlo en el color correcto
                        txtNombre.setHintTextColor(getResources().getColor(R.color.black));
                        txtNombre.setHint(getResources().getString(R.string.txtNombre));;
                        //Sustituimos el hint por un mensaje indicando que faltan datos.
                        txtApellidos.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
                        txtApellidos.setHint(getResources().getString(R.string.errorApellidos));;
                        //Mostramos un Toast indicando que faltan datos
                        toast.show();
                    }else if (edad.isEmpty()) {
                        //Ya que si hay error se cambia a rojo volvemos a cambiar el hint para ponerlo en el color correcto
                        txtNombre.setHintTextColor(getResources().getColor(R.color.black));
                        txtNombre.setHint(getResources().getString(R.string.txtNombre));
                        txtApellidos.setHintTextColor(getResources().getColor(R.color.black));
                        txtApellidos.setHint(getResources().getString(R.string.txtApellidos));;
                        //Sustituimos el hint por un mensaje indicando que faltan datos.
                        txtEdad.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
                        txtEdad.setHint(getResources().getString(R.string.errorEdad));;
                        //Mostramos un Toast indicando que faltan datos
                        toast.show();
                    }else {
                        //Ya que si hay error se cambia a rojo volvemos a cambiar el hint para ponerlo en el color correcto
                        txtNombre.setHintTextColor(getResources().getColor(R.color.black));
                        txtNombre.setHint(getResources().getString(R.string.txtNombre));;
                        txtApellidos.setHintTextColor(getResources().getColor(R.color.black));
                        txtApellidos.setHint(getResources().getString(R.string.txtApellidos));;
                        txtEdad.setHintTextColor(getResources().getColor(R.color.black));
                        txtEdad.setHint(getResources().getString(R.string.errorEdad));;
                        String spinnerEstadoCivil = spinner.getSelectedItem().toString();
                        //Hombre o Mujer
                        if (rdHombre.isChecked()){
                            sexo = getResources().getString(R.string.rdHombre);
                            spinnerEstadoCivil=spinnerEstadoCivil.replace("o/a","o").toLowerCase();
                        } else {
                            sexo = getResources().getString(R.string.rdMujer);
                            spinnerEstadoCivil=spinnerEstadoCivil.replace("o/a","a").toLowerCase();
                        }
                        //Comprueba la edad
                        if (edad.length() != 0){
                            int edadNumero = Integer.parseInt(edad);
                            if (edadNumero < 18){
                                mayorDeEdad = getResources().getString(R.string.mayorDeEdadNo);
                            } else {
                                mayorDeEdad = getResources().getString(R.string.mayorDeEdadSi);
                            }
                        }
                        //Comprueba si tiene hijos
                        if (switchHijos.isChecked()) {
                            hijos = getResources().getString(R.string.hijosSi);
                        } else {
                            hijos = getResources().getString(R.string.hijosNo);
                        }
                        resultado.setText(apellidos + ", " + nombre + ", " + mayorDeEdad + ", " + sexo.toLowerCase() + " " + spinnerEstadoCivil.toLowerCase() + ", " + hijos.toLowerCase());
                    }

                }
            });
        }
    /**
     * Muestra una ventana de dialogo para elegir el nuevo idioma de la aplicacion
     * Cuando se hace clic en uno de los idiomas, se cambia el idioma de la aplicacion
     * y se recarga la actividad para ver los cambios
     * */
    private void showDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getResources().getString(R.string.btnTraducir));
        //obtiene los idiomas del array de string.xml
        String[] types = getResources().getStringArray(R.array.languages);
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch(which){
                    case 0:
                        locale = new Locale("en");
                        config.locale =locale;
                        break;
                    case 1:
                        locale = new Locale("es");
                        config.locale =locale;
                        break;
                }
                getResources().updateConfiguration(config, null);
                Intent refresh = new Intent(MainActivity.this, MainActivity.class);
                startActivity(refresh);
                finish();
            }
        });
        b.show();
    }
}