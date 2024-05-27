package com.example.prakt7.UI.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prakt7.Data.Models.Task;
import com.example.prakt7.R;
import com.example.prakt7.UI.VM.TaskListViewModel;

public class AddTaskFragment extends Fragment {
    public AddTaskFragment() {
        super(R.layout.fragment_add_task);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TaskListViewModel list = new ViewModelProvider(getActivity()).get(TaskListViewModel.class);
        Button bAccept = view.findViewById(R.id.b_accept);
        EditText eTaskText = view.findViewById(R.id.e_tasktext);
        EditText eTaskAnswer = view.findViewById(R.id.e_taskanswer);
        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.addUserTask(new Task(
                        eTaskText.getText().toString(),
                        R.drawable.ic_launcher_foreground,
                        eTaskAnswer.getText().toString()
                        ));
            }
        });
    }
}