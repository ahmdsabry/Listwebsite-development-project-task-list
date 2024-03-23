import java.util.Date;

public class Note {
    private int noteid;
    private String note;
    private String status;
    private String priority;
    private Date date; // New attribute for storing the date

    public Note(int noteid, String note, String status, String priority, Date date) {
        this.noteid = noteid;
        this.note = note;
        this.status = status;
        this.priority = priority;
        this.date = date;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
