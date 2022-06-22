package com.trybe.acc.java.minhasseries.controller;

import com.trybe.acc.java.minhasseries.exception.EpisodioExistenteException;
import com.trybe.acc.java.minhasseries.exception.ErrorHandler;
import com.trybe.acc.java.minhasseries.exception.SerieExistenteException;
import com.trybe.acc.java.minhasseries.exception.SerieNaoEncontradaException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GerenciadorAdvice {

  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(SerieExistenteException.class)
  public ErrorHandler handleSerieExistenteException(SerieExistenteException e) {
    return new ErrorHandler(e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(EpisodioExistenteException.class)
  public ErrorHandler handleEpisodioExistenteException(EpisodioExistenteException e) {
    return new ErrorHandler(e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(SerieNaoEncontradaException.class)
  public ErrorHandler handleSerieNaoEncontradaException(SerieNaoEncontradaException e) {
    return new ErrorHandler(e.getMessage());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  @ExceptionHandler(CallNotPermittedException.class)
  public ErrorHandler handleCallNotPermittedException() {
    return new ErrorHandler("Serviço temporariamente indisponível");
  }

}
