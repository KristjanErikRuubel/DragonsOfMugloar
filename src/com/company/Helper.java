package com.company;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Helper {

    static Type getType(Class<?> rawClazz, Class<?> tClazz) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { tClazz };
            }

            @Override
            public Type getRawType() {
                return rawClazz;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

}
