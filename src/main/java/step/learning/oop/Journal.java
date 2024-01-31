package step.learning.oop;

public class Journal extends Literature {
    private final int number;
    public int getNumber() {
        return number;
    }
    public Journal(String title,String number) {
        super(title);
        if (Integer.parseInt(number) > 0) {
            this.number = Integer.parseInt(number);
        }
        else
            throw new NumberFormatException("number < 0");
    }
    @Override
    public String getCard() {
        return String.format
                ("Journal. Title: '%s'. Number: '%s'",super.getTitle(),this.getNumber());
    }
}
