package hva.work;

import java.io.Serializable;

public class Employee implements Serializable {
    private String _id;
    private String _name;

    public Employee(String id, String name){
        _id = id;
        _name = name;
    }

    //getters e setters para _id e _name
    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }


}
