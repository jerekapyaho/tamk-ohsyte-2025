public class Category {
    private String primary;
    private String secondary;

    public Category(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public String getPrimary() {
        return this.primary;
    }

    public String getSecondary() {
        return this.secondary;
    }

    @Override
    public String toString() {
        return String.format("%s/%s", this.primary, this.secondary);
    }
}
