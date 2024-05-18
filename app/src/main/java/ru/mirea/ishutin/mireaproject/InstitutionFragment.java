package ru.mirea.ishutin.mireaproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class InstitutionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public InstitutionFragment() {
        // Required empty public constructor
    }

    public static InstitutionFragment newInstance(String param1, String param2) {
        InstitutionFragment fragment = new InstitutionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_institution, container, false);
    }

    private RecyclerView institutionList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        institutionList = view.findViewById(R.id.institution_list);
        institutionList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Institution> arrayList = new ArrayList<>();

        arrayList.add(new Institution(new GeoPoint(55.709292, 37.764734),"Дом", "Зеленодольская улица, 36к1", "Место, где я проживаю"));
        arrayList.add(new Institution(new GeoPoint(55.710854, 37.757278),"Школа", "Окская улица, 16к3", "Школа, в которой я учился"));
        arrayList.add(new Institution(new GeoPoint(55.669986, 37.480409),"Вуз", "проспект Вернадского, 78с4", "ВУЗ, где я сейчас учусь"));

        InstitutionAdapter adapter = new InstitutionAdapter(getContext());
        adapter.setInstitutions(arrayList);
        institutionList.setAdapter(adapter);
    }
}

class Institution {
    public GeoPoint point;

    public String name, address, description;

    public Institution(GeoPoint point, String name, String address, String description) {
        this.point = point;
        this.name = name;
        this.address = address;
        this.description = description;
    }
}