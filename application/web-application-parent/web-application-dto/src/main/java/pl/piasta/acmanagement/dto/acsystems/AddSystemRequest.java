package pl.piasta.acmanagement.dto.acsystems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddSystemRequest implements Serializable {

    @NotNull
    @Min(1)
    private Long unitId;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime nextMaintainance;

    private boolean notified = true;

    @NotNull
    @Min(1)
    private Long customerId;
}
