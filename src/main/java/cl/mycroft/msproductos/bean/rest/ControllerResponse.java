package cl.mycroft.msproductos.bean.rest;

public record ControllerResponse<T>(int code, String message, T data) {
}
