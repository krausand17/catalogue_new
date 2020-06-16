package at.fhjoanneum.catalogue_new.helper;

public class Breed {

    private String name;
    private String idString;
    private int id;

    public Breed(String name, String idString, int id) {
        this.name = name;
        this.idString = idString;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIdString() {
        return idString;
    }

    public int getId() {
        return id;
    }
}
