import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeURI exchangeURI = new ExchangeURI();
        GenerateFile generateFile = new GenerateFile();

        while (true) {
            System.out.println("\n****************************************************************");
            System.out.println("Seja bem-vindo(a) ao Conversor de Moeda ><");
            System.out.println("1. USD to ARS");
            System.out.println("2. ARS to USD");
            System.out.println("3. USD to BRL");
            System.out.println("4. BRL to USD");
            System.out.println("5. USD to COP");
            System.out.println("6. COP to USD");
            System.out.println("7. SAIR");
            System.out.println("*****************************************************************");
            System.out.print("Escolha uma opção válida: ");

            int option = scanner.nextInt();

            if (option == 7) {
                System.out.println("Saindo...");
                break;
            }

            String baseCurrency = "";
            String targetCurrency = "";

            switch (option) {
                case 1:
                    baseCurrency = "USD";
                    targetCurrency = "ARS";
                    break;
                case 2:
                    baseCurrency = "ARS";
                    targetCurrency = "USD";
                    break;
                case 3:
                    baseCurrency = "USD";
                    targetCurrency = "BRL";
                    break;
                case 4:
                    baseCurrency = "BRL";
                    targetCurrency = "USD";
                    break;
                case 5:
                    baseCurrency = "USD";
                    targetCurrency = "COP";
                    break;
                case 6:
                    baseCurrency = "COP";
                    targetCurrency = "USD";
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    continue;
            }

            try {
                ExchangeRate exchangeRate = exchangeURI.exchangeRate(baseCurrency);

                System.out.print("Digite o valor que deseja converter: ");
                double amount = scanner.nextDouble();

                if (exchangeRate.conversion_rates().has(targetCurrency)) {
                    double conversionRate = exchangeRate.conversion_rates().get(targetCurrency).getAsDouble();
                    double convertedAmount = amount * conversionRate;

                    System.out.printf("O valor %.2f [%s] corresponde ao valor final de %.2f [%s]\n", amount, baseCurrency, convertedAmount, targetCurrency);
                    generateFile.SaveJson(exchangeRate);

                } else {
                    System.out.println("Não foi possível obter a taxa de câmbio para " + targetCurrency);
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("Erro ao obter as taxas de câmbio: " + e.getMessage());
            }
        }

        scanner.close();
    }
}