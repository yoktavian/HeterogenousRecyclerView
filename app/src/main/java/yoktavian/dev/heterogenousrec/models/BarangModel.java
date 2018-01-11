package yoktavian.dev.heterogenousrec.models;

/**
 * Created by yoktavian on 1/10/18.
 */

public class BarangModel {
    private String _id, name, desc;

    public BarangModel set_id(String _id) {
        this._id = _id;
        return this;
    }

    public BarangModel setName(String name) {
        this.name = name;
        return this;
    }

    public BarangModel setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public String getName() {
        return name;
    }

    public String get_id() {
        return _id;
    }

    public String getDesc() {
        return desc;
    }


    @Override
    public String toString() {
        return _id+"\n"
                +name
                +"\n"
                +desc;
    }
}
