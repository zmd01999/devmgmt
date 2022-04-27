package pl.piasta.acmanagement.api.acunits;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.piasta.acmanagement.api.common.VResponse;
import pl.piasta.acmanagement.api.mapper.AcUnitMapper;
import pl.piasta.acmanagement.api.misc.JsonPatchHandler;

import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.domain.acunits.model.Devices;
import pl.piasta.acmanagement.domain.acunits.model.EnergyConsum;
import pl.piasta.acmanagement.dto.acunits.AcUnitResponse;
import pl.piasta.acmanagement.dto.acunits.EnergyAdvResponse;
import pl.piasta.acmanagement.dto.acunits.UpdateUnitRequest;
import pl.piasta.acmanagement.api.service.AcUnitsService;

import javax.json.JsonPatch;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/units")
@Validated
@RequiredArgsConstructor
public class AcUnitsServiceController {

    private final AcUnitsService service;
    private final AcUnitMapper mapper;
    private final JsonPatchHandler jsonPatchHandler;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<Long> addUnit(@RequestBody @Valid UpdateUnitRequest request) {
        AcUnit unit = mapper.mapToAcUnit(request);
        Long id = service.updateUnit(unit);
        return VResponse.success(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public void updateUnit(@PathVariable @Min(1) Long id, @RequestBody JsonPatch patchDocument) {
        AcUnitResponse response = mapper.mapToResponse(service.getUnitById(id));
        UpdateUnitRequest request = mapper.mapToRequest(response);
        request = jsonPatchHandler.patch(patchDocument, request, UpdateUnitRequest.class);
        AcUnit unit = mapper.mapToAcUnit(response);
        mapper.update(request, unit);
        service.updateUnit(unit);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<AcUnitResponse> getUnit(@PathVariable @Min(1) Long id) {
        AcUnit unit = service.getUnitById(id);
        return VResponse.success(mapper.mapToResponse(unit));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<List<AcUnitResponse>> getAllUnits() {
        List<AcUnit> unitList = service.getAllUnits();
        return VResponse.success(mapper.mapToResponseList(unitList));
    }

    @GetMapping(value = "/consumption",produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<List<EnergyConsum>> getConsumption(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date startFmt = sdf.parse( start );
        Date ebdFmt = sdf.parse( end );
        List<EnergyConsum> consumList = service.getEnergyConsum(startFmt, ebdFmt);
        return VResponse.success(consumList);
    }

    @GetMapping(value = "/comparison",produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<List<EnergyAdvResponse>> getComparison(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date startFmt = sdf.parse( start );
        Date ebdFmt = sdf.parse( end );
        List<EnergyConsum> consumList = service.getEnergyConsum(startFmt, ebdFmt);
        return VResponse.success(mapper.mapToEnergyAdv(consumList));
    }

    @GetMapping(value = "/devices",produces = MediaType.APPLICATION_JSON_VALUE)
    public VResponse<List<Devices>> getDevices(String user, String type) {
        List<Devices> devicesList = service.getDevices(user, type);
        return VResponse.success(devicesList);
    }

    @PostMapping(value = "/addDevice")
    public VResponse<Devices> addDevice(@RequestBody  Devices devices) {
        return VResponse.success(service.updateDevice(devices));
    }
}
