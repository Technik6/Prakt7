package com.example.prakt7.UI.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.prakt7.R;
import com.example.prakt7.UI.VM.TaskListViewModel;

public class MFragment extends Fragment {
    public MFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button b_left = view.findViewById(R.id.b_l);
        Button b_right = view.findViewById(R.id.b_r);
        Button b_addTask = view.findViewById(R.id.b_add_task);
        TextView tTasktextmenu = view.findViewById(R.id.t_tasktextmenu);
        EditText e_qcount = view.findViewById(R.id.e_q);
        TaskListViewModel list = new ViewModelProvider(getActivity()).get(TaskListViewModel.class);
        getParentFragmentManager().setFragmentResultListener("getElement", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        list.getUiState().observe(getViewLifecycleOwner(), uiState -> {
                            tTasktextmenu.setText(uiState.getTasks().
                                    get(result.getInt("getElement")).getText());
                        });
                    }
                });
        b_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = e_qcount.getText().toString();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("CreateList", true);
                if (!str.isEmpty() && str.matches("\\d+")) {
                    bundle.putInt("qCount", Integer.parseInt(str));
                }
                Navigation.findNavController(v).navigate(R.id.a_left, bundle);
            }
        });
        b_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = e_qcount.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putBoolean("CreateList", true);
                if (!str.isEmpty() && str.matches("\\d+")) {
                    bundle.putInt("qCount", Integer.parseInt(str));
                }
                Navigation.findNavController(v).navigate(R.id.a_right, bundle);
            }
        });
        b_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.a_statistics);
            }
        });
    }
}