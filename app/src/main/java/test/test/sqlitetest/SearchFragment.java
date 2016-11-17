package test.test.sqlitetest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
public class SearchFragment extends Fragment {

    EditText keyword;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        keyword = (EditText) view.findViewById(R.id.keyword);
        final DatabaseHelper db = new DatabaseHelper(getContext());
        final ListView list = (ListView) view.findViewById(R.id.search_list_view);
        keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if(text.length()==0){
                    list.setAdapter(null);
                    return;
                }
                ArrayList<Student> students = db.findStudents(text);
                final StudentDetailsListAdapter adapter = new StudentDetailsListAdapter(getContext(),students);
                list.setAdapter(adapter);
                list.deferNotifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

}