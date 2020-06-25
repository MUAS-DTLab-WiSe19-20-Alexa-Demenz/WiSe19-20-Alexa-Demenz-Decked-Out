package deckedout.model;

public class ObjectDO {
    String location = "";
    final String name;

    public ObjectDO(final String name, final String location) {
        this.name = name;
        this.location = location;
    }

    public ObjectDO(final String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public boolean locationIsValid() {
        return (this.location != null && !this.location.isEmpty());

    }

    public boolean nameIsValid() {
        return (this.name != null && !this.name.isEmpty());
    }
        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectDO objectDO = (ObjectDO) o;

        if (location != null ? !location.equals(objectDO.location) : objectDO.location != null) return false;
        return name != null ? name.equals(objectDO.name) : objectDO.name == null;
    }

    public String toStringDO() {
        return name + ":" + location;
    }

    public static ObjectDO fromString (String imput) {
        String[] seg = imput.split(":");
        ObjectDO obj = new ObjectDO(seg[0], seg[1]);
        return ((obj.nameIsValid() && obj.locationIsValid()) ? obj: null);
    }


    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}