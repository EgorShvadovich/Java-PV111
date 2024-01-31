package step.learning.oop;

public class Map extends Literature{
    private final int scale;
    public int getScale() {
        return scale;
    }
    public Map(String title, String scale) {
        super(title);
        this.scale = Integer.parseInt(scale);
    }
    @Override
    public String getCard() {
        return String.format("Map. Title: '%s'. Scale: '%s'", super.getTitle(), getScale());
    }
}
