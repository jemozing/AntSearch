package com.jemozstudios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static java.lang.Math.pow;
import static java.lang.Math.random;

public class Ant {
    private static final int ALPHA = 1;
    private static final int BETTA = 4;
    double sigma;
    int ncities;
    double[] probability_cities; //массив вероятностей до n города
    double[] probability_move; // массив верятного перехода в город
    ArrayList<Integer> cities;
    Iterator<Integer> citeIter;
    ArrayList<Integer> way;
    World world;
    int place;
    double way_lenth = 0;

    public Ant(int ncities, World world, int place){
        probability_cities = new double[ncities];
        probability_move = new double[ncities];
        this.world = world;
        this.place = place-1;
        this.ncities = ncities;
        this.way = new ArrayList<Integer>();
        cities = new ArrayList<Integer>(ncities); //список городов доступных для муравья
        for(int i = 0; i < ncities; i++){
            cities.add(i+1);
        }

    }
    public void move(){
        way.add(place+1);
        cities.remove(new Integer(place+1));  //удаление города в котором находится муравей
        for(int counter = 0; counter < ncities-1; counter++) {
            sigma = 0;
            int city;
            for (int i = 0; i < probability_move.length; i++) {
                probability_move[i] = 0;
            }
            citeIter = cities.iterator();
            while (citeIter.hasNext()) {
                city = citeIter.next();
                probability_cities[city - 1] = probability_all_cities_in_length(world.ferom_peak[place][city - 1], world.lenth_peak[place][city - 1]);
                sigma += probability_cities[city - 1];
            }
            citeIter = cities.iterator();
            while (citeIter.hasNext()) {
                city = citeIter.next();
                probability_move[city - 1] = probability_cities[city - 1]/sigma;
            }
            int moveToCity = ruletka(probability_move) + 1;
            way_lenth += world.getLenth_peak(place,moveToCity-1);
            place = moveToCity - 1;
            way.add(moveToCity);
            cities.remove(new Integer(moveToCity));
        }
    }
    public double probability_all_cities_in_length(double feromon, double length){
        return Math.pow(feromon, ALPHA) * Math.pow(200/length, BETTA);
    }

    public void setDefaultCities(){
        for(int i = 0; i < ncities; i++)
            cities.add(i+1);
    }

    public void cleanWay(){
        way.removeAll(way);
    }

    public int ruletka(double probability[]){
        double rul = Math.random();
        int i;
        for(i = 0; i < probability.length; i++){
            if(rul >= 0){
                rul = rul - probability[i];
            } else{
                break;
            }
        }
        return i-1;
    }

    public void setNcities(int ncities){
        this.ncities = ncities;
    }

}
