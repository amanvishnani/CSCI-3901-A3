package ca.dal;

public class Main {

    public static void main(String[] args) {
        HalifaxMap map = new HalifaxMap();
        System.out.println("newIntersection(1,2)" + map.newIntersection(1,1));
        System.out.println("newIntersection(1,2)" + map.newIntersection(2,2));
        System.out.println("newIntersection(2,2)" + map.newIntersection(2,1));
        System.out.println("newIntersection(2,2)" + map.newIntersection(1,2));
        System.out.println("defineRoad(1,2,2,2)" + map.defineRoad(1,1, 2, 1));
        System.out.println("defineRoad(1,2,2,2)" + map.defineRoad(2,1, 2, 1));
        System.out.println("defineRoad(2,2,1,2)" + map.defineRoad(1,1, 1, 2));
        System.out.println("defineRoad(2,2,1,2)" + map.defineRoad(1,2, 2, 2));
        map.navigate(1,1, 2, 2);
//        map.navigate(2, 2,1,1);
        System.out.println("newIntersection(5,5)" + map.newIntersection(-5,-5));
        System.out.println("newIntersection(6,6)" + map.newIntersection(6,6));
        System.out.println("defineRoad(6,6,5,5)" + map.defineRoad(6,6, -5, -5));
        System.out.println("defineRoad(5,5,6,6)" + map.defineRoad(1,2, 2, 2));
        System.out.println("defineRoad(5,5,6,6)" + map.defineRoad(1,1, -5, -5));
        System.out.println("defineRoad(5,5,6,6)" + map.defineRoad(2,2, -5, -5));
//        map.navigate(2, 2,-5,-5);
//        map.navigate(-5, -5,-5,-5);
//        map.navigate(6, 6,-5,-5);
        map.navigate(1, 2,6,6);
    }
}
