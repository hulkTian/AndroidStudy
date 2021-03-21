package com.hulk.common.unit;

import java.util.ServiceLoader;

public class ServiceLoaderUnit {

    private ServiceLoaderUnit(){}

    public static <S> S load(Class<S> service) {
        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
