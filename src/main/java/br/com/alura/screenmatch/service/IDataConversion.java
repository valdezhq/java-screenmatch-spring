package br.com.alura.screenmatch.service;

public interface IDataConversion {
    <T> T obtainData(String json, Class<T> tclass);
}