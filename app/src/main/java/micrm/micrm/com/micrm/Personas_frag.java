package micrm.micrm.com.micrm;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Personas_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Personas_frag extends Fragment implements View.OnClickListener{
   private Button agregarPersona;
   private Button editarPersona;
   private EditText Nombre_persona,Telefono_persona,email_persona;
   private SQLite_Personas sqlite;


    private OnFragmentInteractionListener mListener;

    public Personas_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_personas_frag, container, false);
        Nombre_persona = (EditText) v.findViewById(R.id.personas_nombre);
        Telefono_persona = (EditText) v.findViewById(R.id.personas_telefono);
        email_persona = (EditText) v.findViewById(R.id.personas_email);
        sqlite = new SQLite_Personas(getContext());

        agregarPersona = (Button) v.findViewById(R.id.agregar_persona);
        editarPersona = (Button) v.findViewById(R.id.editar_persona);
        editarPersona.setOnClickListener(this);
        agregarPersona.setOnClickListener(this);
        sqlite.abrir();
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.agregar_persona:
                AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(getContext());
                cuadroDialogo.setTitle("Agregar Persona");
                cuadroDialogo.setMessage("Desea agregar esta Persona?");
                cuadroDialogo.setCancelable(false);
                cuadroDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if(sqlite.agregarRegistro(Nombre_persona.getText().toString(),Telefono_persona.getText().toString(),
                                email_persona.getText().toString()))
                       {
                           Toast.makeText(getContext(), "Persona agregada!", Toast.LENGTH_SHORT).show();
                       }
                        else
                       {
                           Toast.makeText(getContext(), "compruebe sus datos!", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
                cuadroDialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                });
                cuadroDialogo.show();
            break;
            case R.id.editar_persona:
                Intent i = new Intent(getContext(),PersonasList.class);
                startActivity(i);

            break;

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
