package org.jeonfeel.perpack4;

public class MyListNote {
    int _id;
    String purpose2;
    String contents2;

    public MyListNote(int _id, String purpose2, String contents2) {
        this._id = _id;
        this.purpose2 = purpose2;
        this.contents2 = contents2;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPurpose2() {
        return purpose2;
    }

    public void setPurpose2(String purpose2) {
        this.purpose2 = purpose2;
    }

    public String getContents2() {
        return contents2;
    }

    public void setContents2(String contents2) {
        this.contents2 = contents2;
    }
}
