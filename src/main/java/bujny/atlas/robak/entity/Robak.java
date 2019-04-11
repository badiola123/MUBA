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

    @Lob
    @Column(name = "PIC")
    private byte[] pic;

    public byte[] getPic() {
        return pic;
    }

    public Robak() {
    }

    public Robak(String name, int owner, String description, byte[] pic) {
        this.name = name;
        this.owner = owner;
        this.description = description;
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

}