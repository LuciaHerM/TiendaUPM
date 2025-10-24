package es.upm.etsisi.poo;
import java.time.LocalDate;


public class Events extends Product {
    private static String expiration_day;
    private static final int MAX_PARTICIPANTS=100;
    private static int num_person;

    public static void Events(String id, String name, Double price, String expiration_day, int num_person) {
        Events.id=id;
        Events.name=name;
        Events.price=price;
        Events.expiration_day=expiration_day;
        if(num_person<=MAX_PARTICIPANTS){
            Events.num_person=num_person;
        }else{
            System.err.println("The number of people is surpasses the maximon of people accepted ");
        }
    }

    private void check_min_time(String date){
        int day, year, week;
        String[] array;
        array = date.split("-");
        if(array.length==3){
            day= Integer.parseInt(array[2]);
            year=Integer.parseInt(array[0]);
            week=Integer.parseInt(array[1]);
            if(year==LocalDate.now().getYear() && week==LocalDate.now().getDayOfMonth() && day-LocalDate.now().getDayOfYear()<3){
                System.err.println("The minimum planing time is not of 3 days at least");
            }
        }else{
            System.err.println("The date is not correct written");
        }

    }

    public static String getExpiration_day() {
        return expiration_day;
    }

    public static void setExpiration_day(String expiration_day) {
        Events.expiration_day = expiration_day;
    }

    public static int getNum_person() {
        return num_person;
    }

    public static void setNum_person(int num_person) {
        Events.num_person = num_person;
    }
}
