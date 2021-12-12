package com.example.sharedpreferences;

import java.util.Date;

final class UserNote {

    final String note;
    final Date date;
    final String type;

    public UserNote(String note, Date date, String type) {
        this.note = note;
        this.date = date;
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
