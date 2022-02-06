package pl.piasta.acmanagement.domain.acsystems.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class AcDetail {


    private Long id;

    private Long systemId;

    private boolean power;

    private boolean fanV;

    private boolean fanH;

    private int fanSpeed;

    private int temp;

    private int workMode;

    private int presetMode;

    public AcDetail(Long systemId){
        this.systemId = systemId;
        this.fanH = false;
        this.fanV = false;
        this.temp = 25;
        this.workMode = 8;
        this.power = false;
    }

}
