
class Main {

    public static void main (String[] args){

        double An = 180.0; //nettó fűtött szintterület
        double bm = 2.7; //belmagasság
        double AHomlokzat = 192.0; //homlokzat területe
        double AAjtó = 2.4; //Homlokzatból ajtó
        double AAblak = 30.33; //Homlokzatból ablak
        double AFal = AHomlokzat - AAjtó - AAblak; //Homlokzatból fal
        double AUveg = 25.0; //Az üvegezés felülete
        double APadlas = 1.0; //Padlásfödém
        double ATalaj = 120.0; //Talajon fekvő padló
        double IPadlo = 48.0; //Padló kerülete
        double IPadlomagassg = 0.5; //Padlószint magassága
        double UFal = 0.35; //Fal áteresztési képessége
        double UAblak = 0.8; //Ablak áteresztési képessége
        double UAjto = 1.1; //Ablak áteresztési képessége
        double UTeto = 0.17; //Tető áteresztési képessége


        Datas mDatad = new Datas(An, bm, AFal, AAjtó, AAblak, AUveg, APadlas, ATalaj, IPadlo, IPadlomagassg);
        mDatad.setAHomlokzat(AHomlokzat);
        mDatad.setUFal(UFal);
        mDatad.setUAblak(UAblak);
        mDatad.setUAjto(UAjto);
        mDatad.setUTeto(UTeto);

        System.out.println(String.format("%1$.2f kWh/a", mDatad.evesNettoHoEnergiaIgeny()));
        System.out.println(String.format("%1$.2f kWh/m2a", mDatad.evesNettoNegyzetMeteren()));
    }
}
