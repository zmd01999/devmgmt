package pl.piasta.acmanagement.api.acsystems;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.piasta.acmanagement.api.common.VResponse;
import pl.piasta.acmanagement.api.mapper.AcSystemMapper;
import pl.piasta.acmanagement.domain.acsystems.model.AcDetail;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;
import pl.piasta.acmanagement.dto.acsystems.AcSystemFullResponse;
import pl.piasta.acmanagement.dto.acsystems.AcSystemResponse;
import pl.piasta.acmanagement.dto.acsystems.AddSystemRequest;
import pl.piasta.acmanagement.api.service.AcSystemsService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/systems")
@Validated
@RequiredArgsConstructor
public class AcSystemsServiceController {

    private final AcSystemsService service;
    private final AcSystemMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<Long> addSystem(@RequestBody @Valid AddSystemRequest request) {
        AcSystem system = mapper.mapToAcSystem(request);
        Long id = service.addSystem(system);
        return VResponse.success(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<AcSystemFullResponse> getSystem(@PathVariable @Min(1) Long id) {
        AcSystemFull system = service.getSystemById(id);
        return VResponse.success(mapper.mapToFullResponse(system));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<List<AcSystemResponse>> getAllSystems() {
        List<AcSystem> systemList = service.getAllSystems();
        return VResponse.success(mapper.mapToResponseList(systemList));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeSystem(@PathVariable @Min(1) Long id) {
        service.removeSystemById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}/maintainance", produces = MediaType.APPLICATION_JSON_VALUE)
    public void setNextMaintainance(
            @PathVariable @Min(1) Long id,
            @NotNull @Past @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nextDate) {
        service.setNextMaintainance(id, nextDate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}/notifications", produces = MediaType.APPLICATION_JSON_VALUE)
    public void setNotifications(@PathVariable @Min(1) Long id, @RequestParam boolean enabled) {
        service.setNotifications(id, enabled);
    }

    @PostMapping(value = "/detail/update")
    public VResponse<AcDetail> updateDetail(@RequestBody AcDetail acDetail) {
        return VResponse.success(service.updateDetail(acDetail));
    }

    @GetMapping(value = "/detail/{id}/get")
    public VResponse<AcDetail> getDetail(@PathVariable @Min(1) Long id) {
        return VResponse.success(service.getDetail(id));
    }
}
