package com.example.prakt7.UI.View;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prakt7.R;
import com.example.prakt7.UI.VM.TaskListViewModel;
import com.example.prakt7.UI.View.RecyclerViewAdapter;

public class RFragment extends Fragment {
    private int count;
    private RecyclerView recyclerView;
    public RFragment() {
        super(R.layout.fragment_right);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        TaskListViewModel list = new ViewModelProvider(getActivity()).get(TaskListViewModel.class);
        Bundle bundle = getArguments();
        if (bundle.getBoolean("CreateList")) {
            list.getUiState().observe(getViewLifecycleOwner(), uiState -> {
                list.clearTaskList();
            });
            if (bundle.containsKey("qCount")) {
                count = bundle.getInt("qCount");
            } else {
                count = 200;
            }
            for (int i = 0; i < count; i++) {
                list.getUiState().observe(getViewLifecycleOwner(), uiState -> {
                    list.addToList(
                            list.setRandomUserTask());
                });
            }
        }
        bundle.putBoolean("CreateList", false);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(
                getContext(),
                list.getUiState().getValue().getTasks()
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()
                .getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}