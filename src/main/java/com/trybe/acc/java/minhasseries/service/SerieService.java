package com.trybe.acc.java.minhasseries.service;

import com.trybe.acc.java.minhasseries.exception.EpisodioExistenteException;
import com.trybe.acc.java.minhasseries.exception.SerieExistenteException;
import com.trybe.acc.java.minhasseries.exception.SerieNaoEncontradaException;
import com.trybe.acc.java.minhasseries.model.Episodio;
import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.repository.SerieRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

  @Autowired
  SerieRepository serieRepository;

  /**
   * Javadoc comment.
   */
  public Serie cadastrarSerie(Serie serie) {
    boolean isSerieExists = serieRepository
        .existsByNome(serie.getNome());

    if (isSerieExists) {
      throw new SerieExistenteException();
    }

    return serieRepository.save(serie);
  }

  public List<Serie> seriesCadastradas() {
    return serieRepository.findAll();
  }

  /**
   * Método para remover série existente.
   */
  public void serieRemove(Integer id) {
    Serie serie = serieRepository
        .findById(id)
        .orElseThrow(SerieNaoEncontradaException::new);

    serieRepository.delete(serie);
  }

  /**
   * Método para adicionar episódio à série.
   */
  @CircuitBreaker(name = "episodios")
  public Serie addEpisodio(Integer id, Episodio episodio) {
    Serie serie = serieRepository
        .findById(id)
        .orElseThrow(SerieNaoEncontradaException::new);

    for (Episodio ep : serie.getEpisodios()) {
      if (ep.equals(episodio)) {
        throw new EpisodioExistenteException();
      }
    }

    serie.adicionarEpisodio(episodio);
    episodio.setSerie(serie);

    return serieRepository.save(serie);
  }

  /**
   * Método para listar episodios de série indicada.
   */
  public List<Episodio> searchEpisodiosBySerie(Integer id) {
    Serie serie = serieRepository
        .findById(id)
        .orElseThrow(IllegalArgumentException::new);

    return serie.getEpisodios();
  }

}
