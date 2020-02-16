package ba.unsa.etf.rpr.projekat;

import java.util.ArrayList;

public class Sector {
    private String tag;
    private ArrayList<Container> containers = new ArrayList<>();
    private int id;
    private int capacity;

    public Sector(int id, String tag, int capacity) {
        this.tag = tag;
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ArrayList<Container> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<Container> containers) {
        this.containers = containers;
    }
}
