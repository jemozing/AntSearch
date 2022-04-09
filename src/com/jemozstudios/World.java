package com.jemozstudios;

public class World {

    int cities;
    double lenth_peak[][];
    double ferom_peak[][];
    private static final double evaporation_const = 0.7;
    public World(int cities){
        this.cities = cities;
        this.lenth_peak = new double[cities][cities];
        this.ferom_peak = new double[cities][cities];
        set_default_ferom();
    }
    void set_default_ferom(){
        for(int i = 0; i < cities; i++){
            for(int j = 0; j < cities; j++){
                ferom_peak[i][j] = 0.200;
            }
        }
    }
    void evaporation_ferom(){
        for(int i = 0; i < cities; i++){
            for(int j = 0; j < cities; j++){
                ferom_peak[i][j] = ferom_peak[i][j]*evaporation_const;
            }
        }
    }

    public void addFeromon(int city1, int city2, double add_fer){
        ferom_peak[city1][city2] += add_fer;
    }
    public void setLenth_peak(double[][] lenth_peak) {
        this.lenth_peak = lenth_peak;
    }
    public void setLenth_peak(int cite1, int cite2, int lenth) {
        lenth_peak[cite1][cite2] = lenth;
    }
    public double getLenth_peak(int cite1, int cite2){
        return lenth_peak[cite1][cite2];
    }
}
