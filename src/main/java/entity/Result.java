package entity;

import java.util.List;

public class Result {
    private int total;
    private int nextId;
    List<Task> task;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
