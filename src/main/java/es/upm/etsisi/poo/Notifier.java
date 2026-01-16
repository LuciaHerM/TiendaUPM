package es.upm.etsisi.poo;

public class Notifier {


    //Clase catálogo
    public static Category UnexpectecValue(String category){
        System.out.println("Unexpected value: " + category);
        return null;
    }


    public static void showSuccessAddProduct() {
        System.out.println("prod add: ok");
    }


    public static void showSuccessAddEvent(TypeEvent typeEvent) {
        if (typeEvent.equals(TypeEvent.MEETING)){
            System.out.println("prod addMeeting: ok");
        } else {
            System.out.println("prod addFood: ok");
        }
    }


    //Clase Events
    public static void dateIncorrectFormat() {
        System.out.println("The date is not correct written must be in this format yyyy-mm-dd");
    }

    public static void dateBeforeToday() {
        System.out.println("The date can't be before today");
    }

    //Clase Personalized
    public static void ErrorNumberPersonalization(){
        System.out.println("It's not possible to insert that number of personalization.");
    }

    public static void CantPersonalize(){
        System.out.println("Can't personalize a product not customizable");
    }


    public static void showErrorAddServicesInComunTicket() {
        System.out.println("Can't add services in a comun ticket");
    }
}
