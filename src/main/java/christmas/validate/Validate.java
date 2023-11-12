package christmas.validate;

import christmas.constants.Constants;

public class Validate {
    public static final String errorMessage ="[ERROR]";

    public static void visitDateValidate(int visitDate) {
        try{
            checkVisitDate(visitDate);
        } catch (IllegalArgumentException e){
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }

    }

    private static void checkVisitDate(int visitDate) {
        if (visitDate > Constants.MAXDATE || visitDate < Constants.MINDATE) {
            throw new IllegalArgumentException();
        }
    }

}
