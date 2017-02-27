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
 * {@link Organizacion_frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Organizacion_frag extends Fragment implements View.OnClickListener{
    Button agregarOrganizacion,editarOrganizacion;
    private EditText Nombre_Org,Direccion_Org,Telefono_Org;
    private SQLite_Organizacion sqlite;

    private OnFragmentInteractionListener mListener;

    public Organizacion_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_organizacion_frag, container, false);
        agregarOrganizacion = (Button) v.findViewById(R.id.agregar_organizacion);
        editarOrganizacion = (Button) v.findViewById(R.id.editar_organizacion);
        agregarOrganizacion.setOnClickListener(this);
        editarOrganizacion.setOnClickListener(this);
        sqlite = new SQLite_Organizacion(getContext());
        Nombre_Org = (EditText) v.findViewById(R.id.organizacion_nombre);
        Direccion_Org = (EditText) v.findViewById(R.id.organizacion_direccion);
        Telefono_Org = (EditText) v.findViewById(R.id.organizacion_telefono);

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
        int opc = v.getId();
        switch (opc) {

            case R.id.agregar_organizacion:
                AlertDialog.Builder cuadroDialogo = new AlertDialog.Builder(getContext());
                cuadroDialogo.setTitle("Agregar Negocio");
                cuadroDialogo.setMessage("Desea agregar este negocio?");
                cuadroDialogo.setCancelable(false);
                cuadroDialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(sqlite.add(Nombre_Org.getText().toString(),Direccion_Org.getText().toString(),
                                Telefono_Org.getText().toString())) {
                            Toast.makeText(getContext(), "Organizacion Agregada", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(), "Error! Verifique sus datos", Toast.LENGTH_SHORT).show();
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
            case R.id.editar_organizacion:
                Intent i = new Intent(getContext(),OrganizacionesList.class);
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
