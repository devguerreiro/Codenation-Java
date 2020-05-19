package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private static final int LIMIT_SPACE_AVAILABLE = 10;
    private List<Carro> parking = new ArrayList<>();

    public void estacionar(Carro carro) {
        Motorista motorista = carro.getMotorista();
        int parkedCars = carrosEstacionados();

        hasMinimumRequirementToParking(motorista);

        if (parkedCars < LIMIT_SPACE_AVAILABLE) {
            parking.add(carro);
            return;
        }

        verifyAndReleaseSpaceIfNecessary(carro, parkedCars);
    }

    public int carrosEstacionados() {
        return parking.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return parking.contains(carro);
    }

    private void hasDriver(Motorista motorista) {
        if (motorista == null) {
            throw new EstacionamentoException("Carro sem motorista.");
        }
    }

    private void hasDriverLicenseSuspended(Motorista motorista) {
        if (motorista.getPontos() > 20) {
            throw new EstacionamentoException("Licença suspensa.");
        }
    }

    private void hasMinAge(Motorista motorista) {
        if (motorista.getIdade() < 18) {
            throw new EstacionamentoException("Motorista menor de idade.");
        }
    }

    private void hasMinimumRequirementToParking(Motorista motorista) {
        hasDriver(motorista);
        hasDriverLicenseSuspended(motorista);
        hasMinAge(motorista);
    }

    private void verifyAndReleaseSpaceIfNecessary(Carro carro, int parkedCars) {
        for (int index = 0; index < parkedCars; index++) {
            int driverAge = parking.get(index).getMotorista().getIdade();

            if (driverAge <= 55) {
                parking.set(index, carro);
                return;
            }
        }
        throw new EstacionamentoException("Todos tem mais de 55 anos.");
    }
}
