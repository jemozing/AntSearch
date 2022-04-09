package com.jemozstudios;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        double best_way = 1000000000;
        String best_way_city_num = "";
        int countIter, antCount;
        int Q = 4;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество муравьев:  ");
        antCount= scanner.nextInt();
        System.out.print("Введите количество итераций:  ");
        countIter = scanner.nextInt();
        System.out.print("Введите количество городов:  ");
        int cities = scanner.nextInt();
        int antWayLength = 0;
	    World world = new World(cities);
        for(int i = 0; i < cities; i++){
            for(int j = 0; j < cities; j++)
            world.setLenth_peak(i,j,scanner.nextInt());
        }
        ArrayList<Ant> antColony = new ArrayList<Ant>();
        Iterator<Ant> antColonyIter;
        for (int i = 0; i < antCount; i++){
            antColony.add(new Ant(cities, world, 1 + random.nextInt(cities-1)));
            //antColony.add(new Ant(cities, world, 1));
        }

       for (int iteration = 0; iteration < countIter; iteration++){
           antColonyIter = antColony.iterator();
           while (antColonyIter.hasNext()){
               Ant nAnt = antColonyIter.next();
               nAnt.move();
               if(nAnt.way_lenth <= best_way){
                   best_way = nAnt.way_lenth;
                   best_way_city_num = nAnt.way.toString();
               }
           }
           world.evaporation_ferom();
           antColonyIter = antColony.iterator();
           while (antColonyIter.hasNext()){
               Ant nAnt = antColonyIter.next();
               antWayLength = nAnt.way.size();
               for(int countAntWay = 1; countAntWay < antWayLength; countAntWay++){
                       int city1 = nAnt.way.get(countAntWay-1);
                       int city2 = nAnt.way.get(countAntWay);
                       world.addFeromon(city1-1,city2-1,Q / world.getLenth_peak(city1-1,city2-1));
               }
               nAnt.cleanWay();
               nAnt.setDefaultCities();
           }
       }


        System.out.println(best_way);
        System.out.println(best_way_city_num);
        for (int i = 0; i < cities; i++){
            for (int j = 0; j < cities; j++)
                System.out.print(world.ferom_peak[i][j] + "  ");
            System.out.println("");
        }
        /*antColony.add(new Ant(cities,world,1));
        antColony.get(0).move();
        System.out.println(antColony.get(0).way_lenth);
        System.out.println(antColony.get(0).way.toString());
        System.out.println(antColony.get(0).way.contains(new Integer(2)));*/
    }
}
