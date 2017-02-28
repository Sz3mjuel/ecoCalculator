package main;

class Main {

    public static void main (String[] args){

        double An = 190.0; //nettó fűtött szintterület
        double bm = 2.7; //belmagasság
        double AHomlokzat = 230.0; //homlokzat területe
        double AAjtó = 2.4; //Homlokzatból ajtó
        double AAblak = 30.33; //Homlokzatból ablak
        double AFal = AHomlokzat - AAjtó - AAblak; //Homlokzatból fal
        double AUveg = 27.0; //Az üvegezés felülete
        double APadlas = 1.0; //Padlásfödém
        double ATalaj = 190.0; //Talajon fekvő padló
        double IPadlo = 64.0; //Padló kerülete
        double IPadlomagassg = 0.5; //Padlószint magassága
        double UFal = 0.35; //Fal áteresztési képessége
        double UAblak = 0.8; //Ablak áteresztési képessége
        double UAjto = 1.1; //Ablak áteresztési képessége
        double UTeto = 0.17; //Tető áteresztési képessége

        Datas mDatad = new Datas(UAblak, UAjto, UTeto, AHomlokzat, UFal, An, bm, AFal, AAjtó, AAblak, AUveg, APadlas, ATalaj, IPadlo, IPadlomagassg);

        System.out.println(String.format("%1$.2f kWh/a", mDatad.getEvesNettoFutesiEnergia()));
        System.out.println(String.format("%1$.2f kWh/m2a", mDatad.getEgysegnyiEvesNettoFutesiEnergia()));
    }
}
