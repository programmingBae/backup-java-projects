package sample.entity;

public class User {
    private int id;
    private String nama;
    private Team team;


    public User() {
    }

    public User(int id, String nama, Team team) {
        this.id = id;
        this.nama = nama;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
