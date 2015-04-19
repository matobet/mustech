package cz.muni.fi.pv243.mustech.rest;

import java.util.HashMap;

public class Result<T> extends HashMap<String, Object> {

    public Result(T data) {
    }

    public static <T> Result<T> forData(T data) {
        return new Result<>(data);
    }
}
