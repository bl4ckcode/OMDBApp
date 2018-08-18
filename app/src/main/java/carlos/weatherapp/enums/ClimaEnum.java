package carlos.weatherapp.enums;

import carlos.weatherapp.R;

/**
 * Created by Carlos on 17/08/2018.
 */

public enum ClimaEnum {
    ENSOLARADO(R.string.ensolarado_en, R.string.ensolarado, R.drawable.ic_temp_sunny, android.R.color.holo_red_light),
    NUBLADO(R.string.nublado_en, R.string.nublado, R.drawable.ic_temp_clouds, android.R.color.darker_gray),
    CHUVOSO(R.string.chuvoso_en, R.string.chuvoso, R.drawable.ic_temp_rainy, android.R.color.holo_blue_light);

    private int idAPI, idClima, idIcone, idCor;

    ClimaEnum(int idAPI, int idClima, int idIcone, int idCor) {
        this.idClima = idClima;
        this.idAPI = idAPI;
        this.idIcone = idIcone;
        this.idCor = idCor;
    }

    public int getIdClima() {
        return idClima;
    }

    public int getIdAPI() {
        return idAPI;
    }

    public int getIdIcone() {
        return idIcone;
    }

    public int getIdCor() {
        return idCor;
    }

    public ClimaEnum valueOf(int idAPI) {
        for (ClimaEnum climaEnum : ClimaEnum.values()) {
            if (climaEnum.getIdAPI() == idAPI) {
                return climaEnum;
            }
        }

        return null;
    }
}
