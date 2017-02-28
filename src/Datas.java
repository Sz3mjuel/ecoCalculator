public class Datas {

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

    public Datas(double an, double bm, double AFal, double AAjtó, double AAblak, double AUveg, double APadlas, double ATalaj, double IPadlo, double IPadlomagassg) {
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
    }

    public void setAHomlokzat(double AHomlokzat) {
        this.AHomlokzat = AHomlokzat;
    }


    public void setUFal(double UFal) {
        this.UFal = UFal;
    }

    public void setUAblak(double UAblak) {
        this.UAblak = UAblak;
    }

    public void setUAjto(double UAjto) {
        this.UAjto = UAjto;
    }

    public void setUTeto(double UTeto) {
        this.UTeto = UTeto;
    }

    /*
        Burkolófelület összesen
    */
    public double sumCoverage(){
        double d = AHomlokzat + APadlas + ATalaj;
        return d;
    }
    /*
        Fűtött térfogat
     */
    public double heatedVolume(){
        double d = An * bm;
        return d;
    }
    /*
        Burkólófelület és a fűtött térfogat aránya
     */
    public double sumCdivHeatedV(){
        double d = sumCoverage()/heatedVolume();
        return d;
    }

    public double megengedettFajlagosHovesztesegTenyezo(){
        double d = sumCdivHeatedV();
        if(d <= 0.3){
            return 0.2;
        }else if(0.31 <= d && d <= 1.3){
            return 0.38*sumCdivHeatedV() + 0.086;
        }else{
            return 0.58;
        }
    }

    public double tervezettHoatbocsatasiTenyezo(){
        double d = megengedettFajlagosHovesztesegTenyezo()* heatedVolume();
        return d;
    }

    public double ablakEsAjtoAteresztesiKepessege(){
        double d = (AAblak * UAblak) + (AAjtó * UAjto);
        return d;
    }

    public double falEsPadlasfodem(){
        double d = (tervezettHoatbocsatasiTenyezo()-ablakEsAjtoAteresztesiKepessege()-IPadlo)/(AFal+(0.9*An));
        return d;
    }

    public double evesNettoHoEnergiaIgeny(){
        double eleje = 72*heatedVolume()*(megengedettFajlagosHovesztesegTenyezo() + (0.35 * IPadlomagassg));
        return  eleje * 0.9 - (4.4 * An * 5.0);
    }

    public double evesNettoNegyzetMeteren(){
        return evesNettoHoEnergiaIgeny()/An;
    }
}
