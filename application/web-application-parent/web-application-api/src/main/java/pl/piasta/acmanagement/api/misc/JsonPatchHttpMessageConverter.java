package pl.piasta.acmanagement.api.misc;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonPatch;
import javax.json.JsonReader;
import javax.json.JsonWriter;

@Component
public class JsonPatchHttpMessageConverter extends AbstractHttpMessageConverter<JsonPatch> {

    public JsonPatchHttpMessageConverter() {
        super(MediaType.valueOf("application/json-patch+json"));
    }

    @Override
    protected boolean supports(@NonNull Class<?> clazz) {
        return JsonPatch.class.isAssignableFrom(clazz);
    }

    @Override
    @NonNull
    protected JsonPatch readInternal(@NonNull Class<? extends JsonPatch> clazz, @NonNull HttpInputMessage inputMessage) {
        try (JsonReader reader = Json.createReader(inputMessage.getBody())) {
            return Json.createPatch(reader.readArray());
        } catch (Exception ex) {
            throw new HttpMessageNotReadableException(ex.getMessage(), inputMessage);
        }
    }

    @Override
    protected void writeInternal(@NonNull JsonPatch jsonPatch, @NonNull HttpOutputMessage outputMessage) {
        try (JsonWriter writer = Json.createWriter(outputMessage.getBody())) {
            writer.write(jsonPatch.toJsonArray());
        } catch (Exception ex) {
            throw new HttpMessageNotWritableException(ex.getMessage(), ex);
        }
    }
}
