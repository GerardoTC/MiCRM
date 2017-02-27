package micrm.micrm.com.micrm;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PersonasList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> ListaDatos;
    SQLite_Personas  sqLite;
    ArrayList<String> ListaIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personas_list);
        Toast.makeText(this, "Selecciona una persona para eliminar o modificar", Toast.LENGTH_SHORT).show();
        sqLite = new SQLite_Personas(this);
        sqLite.abrir();
        listView = (ListView) findViewById(R.id.personasList);

        Cursor cursor = sqLite.ConsultarRegistros();

        ArrayList<String> ListaDatos =sqLite.getFormatList(cursor);
        ListaIDs = sqLite.getIds(cursor);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ListaDatos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        //Toast.makeText(getBaseContext(), cursor., Toast.LENGTH_SHORT).show();

        if(ListaDatos.size()==0){
            Toast.makeText(getBaseContext(), "No Existen Registros!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final CharSequence[] option = {"Editar Persona", "Eliminar Persona", "Cancelar"};
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Elige una opción:");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Editar Persona"){
                    Intent i = new Intent(PersonasList.this,UpdatePersonas.class);
                    i.putExtra("id",ListaIDs.get(position));
                    startActivity(i);

                }else if(option[which] == "Eliminar Persona"){
                    sqLite.Borrar(ListaIDs.get(position));
                    Intent i = new Intent(PersonasList.this,PersonasList.class);
                    startActivity(i);
                    Toast.makeText(PersonasList.this, "Persona Eliminada!", Toast.LENGTH_SHORT).show();
                } else{
                    dialog.dismiss();
                }
            }
        });

        builder.show();



    }
}
