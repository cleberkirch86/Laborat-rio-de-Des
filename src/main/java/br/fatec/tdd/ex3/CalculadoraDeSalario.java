package br.fatec.tdd.ex3;

public class CalculadoraDeSalario {

    public double calcular(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo.");
        }

        if (funcionario.getCargo() == null) {
            throw new IllegalArgumentException("Cargo do funcionário não pode ser nulo.");
        }

        double salarioBase = funcionario.getSalarioBase();
        if (salarioBase < 0) {
            throw new IllegalArgumentException("Salário base não pode ser negativo.");
        }

        double percentualDesconto = obterPercentualDesconto(funcionario.getCargo(), salarioBase);
        return salarioBase * (1.0 - percentualDesconto);
    }

    private double obterPercentualDesconto(Funcionario.Cargo cargo, double salarioBase) {
        return switch (cargo) {
            case DESENVOLVEDOR -> (salarioBase >= 3000.0) ? 0.20 : 0.10;
            case DBA, TESTADOR -> (salarioBase >= 2000.0) ? 0.25 : 0.15;
            case GERENTE -> (salarioBase >= 5000.0) ? 0.30 : 0.20;
        };
    }
}
