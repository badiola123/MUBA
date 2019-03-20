package bujny.atlas.robak.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROBAK")
public class Robak {

    @Id
    @Column(name = "ROBAKID", insertable = false, updatable = false)
    private int robakId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "OWNER")
    private int owner;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATE")
    private int rate;

    @Lob
    @Column(name = "PIC", columnDefinition = "BLOB")
    private byte[] pic;

    public Robak() {
    }

    public Robak(String name, int owner, String description, int rate, byte[] pic) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.rate = rate;
        this.pic = pic;
    }

    public int getRobakId() {
        return robakId;
    }

    public String getName() {
        return name;
    }

    public int getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public int getRate() {
        return rate;
    }

    public byte[] getPic() {
        return pic;
    }
}