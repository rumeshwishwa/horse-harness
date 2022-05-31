package com.rumesh.horseharnes.converter;

@FunctionalInterface
public interface RecordConvertable<T> {

    T convert(String[] tokenArray, int lineNo , String fileName);

}
