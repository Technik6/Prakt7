package com.example.prakt7.UI.VM;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prakt7.Data.DataSources.TaskList;
import com.example.prakt7.Data.Models.Task;
import com.example.prakt7.Data.Repositories.TaskListRepository;
import com.example.prakt7.R;

import java.util.Random;

public class TaskListViewModel extends ViewModel {
    private MutableLiveData<TaskListRepository> uiState = new MutableLiveData<>(new TaskList());
    private MutableLiveData<TaskListRepository> uiStateUser =new MutableLiveData<>(new TaskList());
    public LiveData<TaskListRepository> getUiState() {
        return uiState;
    }
    public LiveData<TaskListRepository> getUiStateUser() {
        return uiStateUser;
    }
    public Task getTask(int index) {
        return uiState.getValue().get(index);
    }
    public void addToList(Task task) {
        uiState.getValue().add(task);
    }
    public void clearTaskList() {
        uiState.getValue().clearAll();
    }
    public Task createRandomMathematicalTask() {
        Random random = new Random();
        Task task;
        int r = random.nextInt(2);
        if (r == 0) {
            task = setRandomArithmeticTask();
        }
        else {
            task = setRandomUnTask();
        }
        return task;
    }
    public void addUserTask(Task task) {
        uiStateUser.getValue().add(task);
    }
    public Task setRandomUserTask() {
        if (uiStateUser.getValue().isEmpty()) {
            return createRandomMathematicalTask();
        }
        Random random = new Random();
        Task task = uiStateUser.getValue().get(
                random.nextInt(uiStateUser.getValue().getSize())
        );
        return task;
    }
    private Task setRandomArithmeticTask() {
        Random random = new Random();
        int image = R.drawable.grus;
        int first = random.nextInt(100);
        int second = random.nextInt(100);
        int coperator = random.nextInt(3);
        Integer answer = 0;
        String operator = null;
        switch (coperator) {
            case 0:
                operator=" + ";
                answer = first + second;
                break;
            case 1:
                operator=" - ";
                answer = first - second;
                break;
            case 2:
                operator=" * ";
                answer = first * second;
                break;
        }
        String text = "Вычислите: " + first + operator + second;
        return new Task(text, image, answer.toString());
    }
    private Task setRandomUnTask() {
        Random random = new Random();
        int image = R.drawable.checkbox_unchecked;
        String text = "Сложность Экзамена ";
        int angles = random.nextInt(5);
        String answer = "нет";
        switch (angles) {
            case 0:
                text+="иностранному \n языку?";
                answer = "Сложно";
                break;
            case 1:
                text+="програмированию \n баз данных?";
                answer = "Сложно";
                break;
            case 2:
                text+="теории \n вероятности?";
                answer = "Легко";
                break;
        }
        return new Task(text, image, answer.toString());
    }
}
