package main;

class Datas {

    public static final double FODEM_KORREKCIO = 0.9;
    private double An; //nettó fűtött szintterület
    private double bm; //belmagasság
    private double AHomlokzat; //honlokzat területe
    private double AFal; //Homlokzatból fal
    private double AAjtó; //Homlokzatból ajtó
    private double AAblak; //Homlokzatból ablak
    private double AUveg; //Az üvegezés felülete
    private double APadlas; //Padlásfödém
    private double ATalaj; //Talajon fekvő padló
    private double IPadlo; //Padló kerülete
    private double IPadlomagassg; //Padlószint magassága
    private double UFal; //Fal áteresztési képessége
    private double UAblak; //Ablak áteresztési képessége
    private double UAjto; //Ablak áteresztési képessége
    private double UTeto; //Tető áteresztési képessége

    private double EvesNettoFutesiEnergia;
    private double EgysegnyiEvesNettoFutesiEnergia;

    Datas(double UAblak, double UAjto, double UTeto, double AHomlokzat, double UFal, double an, double bm, double AFal, double AAjtó, double AAblak, double AUveg, double APadlas, double ATalaj, double IPadlo, double IPadlomagassg) {
        An = an;
        this.bm = bm;
        this.AFal = AFal;
        this.AAjtó = AAjtó;
        this.AAblak = AAblak;
        this.AUveg = AUveg;
        this.APadlas = APadlas;
        this.ATalaj = ATalaj;
        this.IPadlo = IPadlo;
        this.IPadlomagassg = IPadlomagassg;
        this.UFal = UFal;
        this.UAblak = UAblak;
        this.UAjto = UAjto;
        this.UTeto = UTeto;
        this.AHomlokzat = AHomlokzat;

        EvesNettoFutesiEnergia = getEvesNettoHoEnergiaIgeny();
        EgysegnyiEvesNettoFutesiEnergia = getEvesNettoNegyzetMeteren();
    }

    void setAHomlokzat(double AHomlokzat) {
        this.AHomlokzat = AHomlokzat;
    }

    public double getEgysegnyiEvesNettoFutesiEnergia() {
        return EgysegnyiEvesNettoFutesiEnergia;
    }

    void setUFal(double UFal) {
        this.UFal = UFal;
    }

    void setUAblak(double UAblak) {
        this.UAblak = UAblak;
    }

    void setUAjto(double UAjto) {
        this.UAjto = UAjto;
    }

    void setUTeto(double UTeto) {
        this.UTeto = UTeto;
    }

    public double getEvesNettoFutesiEnergia() {
        return EvesNettoFutesiEnergia;
    }

    private double getOsszesBurkolat(){
        return AHomlokzat + APadlas + ATalaj;
    }
    private double getFutottTerfogat(){
        return An * bm;
    }
    private double getBurkolatEsTerfogatArany(){
        return getOsszesBurkolat()/ getFutottTerfogat();
    }

    private double megengedettFajlagosHovesztesegTenyezo(){
        double d = getBurkolatEsTerfogatArany();
        if(d <= 0.3){
            return 0.2;
        }else if(0.31 <= d && d <= 1.3){
            return 0.38* getBurkolatEsTerfogatArany() + 0.086;
        }else{
            return 0.58;
        }
    }

    private double tervezettHoatbocsatasiTenyezo(){
        return megengedettFajlagosHovesztesegTenyezo()* getFutottTerfogat();
    }

    private double ablakEsAjtoAteresztesiKepessege(){
        return (AAblak * UAblak) + (AAjtó * UAjto);
    }

    private double falEsPadlasfodem(){
        return (tervezettHoatbocsatasiTenyezo()-ablakEsAjtoAteresztesiKepessege()-IPadlo)/(AFal+(FODEM_KORREKCIO *An));
    }

    private double getEvesNettoHoEnergiaIgeny(){
        return  (72 * getFutottTerfogat()*(megengedettFajlagosHovesztesegTenyezo() +
                (UFal * IPadlomagassg))) * FODEM_KORREKCIO - (4.4 * An * 5.0);
    }

    private double getEvesNettoNegyzetMeteren(){
        return getEvesNettoHoEnergiaIgeny()/An;
    }
}
