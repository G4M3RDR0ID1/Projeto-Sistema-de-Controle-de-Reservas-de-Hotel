import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        int opcaoSelecionada;
        Reserva[] quartos = new Reserva[10];
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Bem vindo ao Sistema de Contole de Reserva de Hotel\nEscolha uma opcão a baixo: ");
            opcoesMenuPrincipal();
            System.out.print("Opçao selecionada: ");
            opcaoSelecionada = sc.nextInt();

            switch (opcaoSelecionada) {
                case 1:
                    cadastrarNovaReserva(quartos, sc);
                    break;
                case 2:
                    listarReserva(quartos);
                    break;
                case 3:
                    buscarPorNomeDoHospede(quartos, sc);
                    break;
                case 4:
                    ordenarPorDias(quartos, sc);
                    break;
                case 5:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Numero digitado nao correponde a nenhuma opçao do menu. Digite novamente: ");
            }
        } while (opcaoSelecionada != 5);
        sc.close();
    }

    public static void opcoesMenuPrincipal() {
        System.out.println("1: Cadastrar nova reserva");
        System.out.println("2: Listar reserva");
        System.out.println("3: Buscar por nome do hospede");
        System.out.println("4: Ordenar por dias (ordem decrescente)");
        System.out.println("5: Sair");
    }

    public static void cadastrarNovaReserva(Reserva[] quartosDisponiveis, Scanner sc) {
        sc.nextLine();
        if (Reserva.getQtdQuartosDisponiveis() > 0) {
            //coletar dados
            System.out.println("Digite o nome do hospede: ");
            String nomeHospede = sc.nextLine();
            System.out.println("Digite o numero do quarto que deseja:\n1: Standard\n2: Luxo\n3: Presidencial");
            System.out.print("Opção desejada: ");
            int opcaoQuarto = sc.nextInt();
            //loop caso digite uma opçao de quarto que nao exista
            while (opcaoQuarto < 1 || opcaoQuarto > 3) {
                System.out.println("Numero digitado nao corresponde as opçoes do menu, digite novamente.");
                System.out.print("Opção desejada: ");
                opcaoQuarto = sc.nextInt();
            }
            //Associa o numero digitado a um dos tipos de quarto
            String tipoQuarto = "";
            switch (opcaoQuarto) {
                case 1:
                    tipoQuarto = "Standard";
                    break;
                case 2:
                    tipoQuarto = "Luxo";
                    break;
                case 3:
                    tipoQuarto = "Presidencial";
            }
            //numero de dias que o hospede vai ficar.
            System.out.println("Quantos dias de estadia:");
            int diasDeEstadia = sc.nextInt();
            //validando se o numero digitado e maior que 1 dias
            if (diasDeEstadia < 1) {
                do {
                    System.out.println("Numero de dias nao pode ser menor que 1. Digite novamente os dias de estadia: ");
                    diasDeEstadia = sc.nextInt();
                } while (diasDeEstadia < 1);

            }
            //Valor da diaria
            System.out.println("Valor da diaria: ");
            double valorDiaria = sc.nextDouble();
            //Validar que o valor da diaria e maior que 1;
            if (valorDiaria < 1) {
                do {
                    System.out.println("Valor da diaria nao pode ser menor que R$1,00");
                    valorDiaria = sc.nextDouble();
                } while (valorDiaria < 1);
            }
            //Mostrar o valor que vai dar a estadia

            System.out.println("Valor total da estadia: ");
            System.out.println(Reserva.calcularValorTotal(diasDeEstadia, valorDiaria));


            //verificar se deseja confirmar a reserva ou nao
            System.out.println("Deseja confirmar a reserva?\nDigite:\n1: Sim\n2: Não");
            System.out.print("Opção Desejada: ");
            int opcaoDesejada = sc.nextInt();

            //loop para garantir que seja digitado apenas as opçoes do menu.
            boolean opcaoValida = false;
            while (!opcaoValida) {
                if (opcaoDesejada == 1) {
                    //Cria a reserva, adiciona no array e diminui a quantidad de quartos disponiveis da classe
                    Reserva reserva = new Reserva(nomeHospede, tipoQuarto, diasDeEstadia, valorDiaria);
                    System.out.println("Reserva realizada com Sucesso!");
                    System.out.println("-----------------------------------------------");
                    quartosDisponiveis[10 - Reserva.getQtdQuartosDisponiveis()] = reserva;
                    Reserva.setValorTotalDaReserva();
                    opcaoValida = true;
                } else if (opcaoDesejada == 2) {
                    System.out.println("Cancelando Reserva!");
                    ;
                    opcaoValida = true;
                } else {
                    System.out.println("Numero digitado não corresponde as opçoes do menu. Digite novamente!");
                    System.out.println("Deseja confirmar a reserva?\nDigite:\n1: Sim\n2: Não");
                    System.out.print("Opção Desejada: ");
                    opcaoDesejada = sc.nextInt();
                }
            }
        }
        //Se todos os quartos estiverem ocupados retorna:
        else {
            System.out.println("Não a quartos disponiveis no momento!!!");
        }
    }

    public static void listarReserva(Reserva[] quartosDisponiveis) {
        //Mostra somente as reservas realizadas
        System.out.println("Reservas Realizadas: ");
        for (int i = 0; i < quartosDisponiveis.length; i++) {
            if (quartosDisponiveis[i] != null) {
                System.out.println(quartosDisponiveis[i]);
                System.out.println("---------------------------------------------------------------------------------------------------");
            }
        }
    }

    public static void buscarPorNomeDoHospede(Reserva[] quartos, Scanner sc) {
        sc.nextLine();
        System.out.println("Digite o nome do hospede que deseja procurar: ");
        String nomePesquisado = sc.nextLine();
        System.out.println("Reservas realizadas para esse Nome: ");
        //Logica para verificar se existe um hospede cadastrado com esse nome
        boolean hospedeCadastrado = false;
        for (int i = 0; i < quartos.length; i++) {
            if (quartos[i] != null) {
                if (quartos[i].getNomeHospede().equalsIgnoreCase(nomePesquisado)) {
                    ;
                    System.out.println(quartos[i]);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    hospedeCadastrado = true;
                }
            }
        }


        if (!hospedeCadastrado) {
            System.out.println("Não foi encontrado reserva para esse nome!");
            System.out.println("-------------------------------------------");
        }
    }

    public static void ordenarPorDias(Reserva[] quartos, Scanner sc) {
        //Ordernar por dias (ordem decrescente)
        for (int i = 0; i < quartos.length; i++) {
            if (quartos[i] != null) {
                for (int j = 0; j < quartos.length; j++) {
                    if (quartos[j] != null) {
                        if (quartos[j].getNumeroDias() < quartos[i].getNumeroDias()) {
                            int aux = quartos[i].getNumeroDias();
                            quartos[i].setNumeroDias(quartos[j].getNumeroDias());
                            quartos[j].setNumeroDias(aux);
                        }
                    }
                }
            }
        }

        //Retornar as reservas ordem decrescente
        System.out.println("Reservas em ordem decrescente de dias: ");
        for (int i = 0; i < quartos.length; i++) {
            if (quartos[i] != null) {
                System.out.println(quartos[i]);
                System.out.println("---------------------------------------------------------------------------------------------------");
            }
        }
    }
}
