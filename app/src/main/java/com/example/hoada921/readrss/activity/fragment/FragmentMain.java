package com.example.hoada921.readrss.activity.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoada921.readrss.R;
import com.example.hoada921.readrss.models.Informations;

/**
 * Created by FRAMGIA\nguyen.huy.quyet on 17/03/2016.
 */
public class FragmentMain extends Fragment {
    private Informations informations = new Informations();
    TextView textView_main_title, textView_main_link,
            textView_main_description, textView_main_language,
            textView_main_copyright, textView_main_ttl,
            textView_main_lastBuildDate, textView_main_generator,
            textView_main_atom;

    public FragmentMain(Informations informations) {
        this.informations = informations;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getControl(view);
        setDataControl(informations);
        return view;
    }

    private void getControl(View view) {
        textView_main_title = (TextView) view.findViewById(R.id.textView_main_title);
        textView_main_link = (TextView) view.findViewById(R.id.textView_main_link);
        textView_main_description = (TextView) view.findViewById(R.id.textView_main_description);
        textView_main_language = (TextView) view.findViewById(R.id.textView_main_language);
        textView_main_copyright = (TextView) view.findViewById(R.id.textView_main_copyright);
        textView_main_ttl = (TextView) view.findViewById(R.id.textView_main_ttl);
        textView_main_lastBuildDate = (TextView) view.findViewById(R.id.textView_main_lastBuildDate);
        textView_main_generator = (TextView) view.findViewById(R.id.textView_main_generator);
        textView_main_atom = (TextView) view.findViewById(R.id.textView_main_atom);
    }

    private void setDataControl(Informations informations) {
        textView_main_title.setText(informations.getTitle());
        textView_main_link.setText(informations.getLink());
        textView_main_description.setText(informations.getDescription());
        textView_main_language.setText(informations.getLanguage());
        textView_main_copyright.setText(informations.getCopyright());
        textView_main_ttl.setText(informations.getTtl());
        textView_main_lastBuildDate.setText(informations.getLastBuildDate());
        textView_main_generator.setText(informations.getGenerator());
        textView_main_atom.setText(informations.getAtom());
    }

    private void update() {
        setDataControl(informations);
    }
}
