public class Reserva {
    private String nomeHospede;
    private String tipoQuarto;
    private int numeroDias;
    private double valorDiaria;
    private static int qtdQuartosDisponiveis = 10;
    private double valorTotalEstadia;

    public Reserva(String nomeHospede, String tipoQuarto, int numeroDias, double valorDiaria){
        this.nomeHospede = nomeHospede;
        this.tipoQuarto = tipoQuarto;
        this.numeroDias = numeroDias;
        this.valorDiaria = valorDiaria;
        this.valorTotalEstadia = calcularValorTotal();
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public static int getQtdQuartosDisponiveis() {
        return qtdQuartosDisponiveis;
    }

    public static void setValorTotalDaReserva() {
        qtdQuartosDisponiveis--;
    }

    public static double calcularValorTotal(int numeroDias, double valorDiaria){
        double valorTotalHospedagem;
        valorTotalHospedagem = numeroDias * valorDiaria;
        return valorTotalHospedagem;
    }

    private double calcularValorTotal(){
        return valorTotalEstadia = calcularValorTotal(numeroDias, valorDiaria);
    }


    @Override
    public String toString() {
        return "Nome do Hospede: " + nomeHospede + " | Tipo de quarto: " + tipoQuarto + " | Dias de estadia: " + numeroDias + " | Valor total da estadia: " + valorTotalEstadia;
    }
}
