package org.jeonfeel.perpack4;

public class Note {
    int _id;
    String purpose;
    String contents;
    String createDatestr;

    public Note(int _id, String purpose, String contents, String createDatestr) {
        this._id = _id;
        this.purpose = purpose;
        this.contents = contents;
        this.createDatestr = createDatestr;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCreateDatestr() {
        return createDatestr;
    }

    public void setCreateDatestr(String createDatestr) {
        this.createDatestr = createDatestr;
    }

}
