package entity;
/**
 * @author AbednegoSteven-1972009
 */
import java.util.Date;

public class Maintenance {
    private int id;
    private User user;
    private Laboratorium laboratorium;
    private String task;
    private Date date;

    public Maintenance() {
    }

    public Maintenance(int id, User user, Laboratorium laboratorium, String task, Date date) {
        this.id = id;
        this.user = user;
        this.laboratorium = laboratorium;
        this.task = task;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Laboratorium getLaboratorium() {
        return laboratorium;
    }

    public void setLaboratorium(Laboratorium laboratorium) {
        this.laboratorium = laboratorium;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
