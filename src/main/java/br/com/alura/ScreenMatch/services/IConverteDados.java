package br.com.alura.ScreenMatch.services;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

}
