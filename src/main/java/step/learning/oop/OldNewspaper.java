package step.learning.oop;

import java.text.ParseException;

@Used
public class OldNewspaper extends Newspaper{

    public OldNewspaper(String title, String dateString) throws ParseException {
        super(title, dateString);
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
