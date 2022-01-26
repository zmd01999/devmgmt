package pl.piasta.acmanagement.api.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.validation.Valid;

@Component
@Validated
@RequiredArgsConstructor
public class JsonPatchHandler {

    private final ObjectMapper jsonPatchObjectMapper;

    @Valid
    public <T> T patch(JsonPatch patch, T targetBean, Class<T> beanClass) {
        JsonStructure target = jsonPatchObjectMapper.convertValue(targetBean, JsonStructure.class);
        JsonValue patched = patch.apply(target);
        return jsonPatchObjectMapper.convertValue(patched, beanClass);
    }
}
