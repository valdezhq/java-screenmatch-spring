package br.com.alura.screenmatch.service;

public interface IDataConverter {
    <T> T obtainData(String json, Class<T> tclass);
}