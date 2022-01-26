package pl.piasta.acmanagement.api.misc;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@ControllerAdvice
public class ResourceCreatedResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return ResourceCreatedResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @Nullable MethodParameter returnType,
            @Nullable MediaType selectedContentType,
            @Nullable Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @Nullable ServerHttpRequest request,
            @NonNull ServerHttpResponse response) {
        ResourceCreatedResponse resourceCreatedResponse = (ResourceCreatedResponse) body;
        setHeaders(response, resourceCreatedResponse);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void setHeaders(ServerHttpResponse response, ResourceCreatedResponse resourceCreatedResponse) {
        response.getHeaders().set(HttpHeaders.LOCATION, ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(resourceCreatedResponse.getId())
                .encode()
                .getPath());
        response.getHeaders().set(HttpHeaders.CONTENT_LENGTH, "0");
    }
}
