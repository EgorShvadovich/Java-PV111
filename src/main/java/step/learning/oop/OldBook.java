package step.learning.oop;

@Used
public class OldBook extends Book {

    public OldBook(String author, String title) {
        super(author, title);
    }

    @Override
    public String getCard() {
        return "Old " + super.getCard();
    }
    @CardMethod
    public String getOwnCard() {
        return "--Old " + super.getCard();
    }
}
