package es.upm.etsisi.poo;

public class Notifier {


    //Clase catálogo
    public static Category UnexpectecValue(String category){
        System.out.println("Unexpected value: " + category);
        return null;
    }

    public static Category_service UnexpectecValueService(String category){
        System.out.println("Unexpected value: " + category);
        return null;
    }

    public static void showErrorAddProduct() {
        System.out.println("The product can't be added.");
    }

    public static void showErrorAddProductWithSameId() {
        System.out.println("Can't be added a product with the same id");
    }


    public static void showSuccessAddProduct() {
        System.out.println("prod add: ok");
    }

    public static void showErrorAddEvent(TypeEvent typeEvent) {
        if (typeEvent.equals(TypeEvent.MEETING)){
            System.out.println("Error processing ->prod addMeeting ->Error adding product");
        } if(typeEvent.equals(TypeEvent.FOOD)) {
            System.out.println("Error processing ->prod addFood ->Error adding product");
        } else{
            System.out.println("Error processing ->Error adding product");
        }
    }

    public static void showSuccessAddEvent(TypeEvent typeEvent) {
        if (typeEvent.equals(TypeEvent.MEETING)){
            System.out.println("prod addMeeting: ok");
        } else {
            System.out.println("prod addFood: ok");
        }
    }

    public static void showSuccessAddService(Category_service category_service) {
        if (category_service.equals(Category_service.TRANSPORT)){
            System.out.println("prod addTransport: ok");
        } else if (category_service.equals(Category_service.SHOW)){
            System.out.println("prod addShow: ok");

        } else if (category_service.equals(Category_service.INSURANCE)){
            System.out.println("prod addInsurance: ok");
        }
    }

    public static void fullCatalog(){
        System.out.println("The catalog is full, you can't add more products.");
    }


}
