package io.jagoketik.pakeedi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonObject;

import java.util.concurrent.ExecutionException;


public class search extends Fragment {
    EditText form;
    Button submit;
    String value;
    String json;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        form = (EditText) v.findViewById(R.id.search);
        submit = (Button) v.findViewById(R.id.submitform);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    value = form.getText().toString();
                    String key = value.replaceAll("\\s","");
                    json = "http://165.22.97.31/music/v2/search/" + key;
                    result resultFrag = new result();
                    Bundle bundle = new Bundle();
                    bundle.putString("json",json);
                    bundle.putString("key",key);
                    resultFrag.setArguments(bundle);
                    FragmentManager manager = getFragmentManager();
                    manager.beginTransaction()
                        .replace(R.id.containermain, resultFrag)
                        .commit();
            }
        });


        form.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    submit.performClick();
                    return true;
                }
                return false;
            }
        });


        return v;
    }

}
