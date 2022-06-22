package com.trybe.acc.java.minhasseries.controller;

import com.trybe.acc.java.minhasseries.model.Episodio;
import com.trybe.acc.java.minhasseries.model.Serie;
import com.trybe.acc.java.minhasseries.service.SerieService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/series")
public class SerieController {

  @Autowired
  SerieService serieService;

  @PostMapping
  public ResponseEntity<Serie> postSerie(@RequestBody Serie serie) {
    return ResponseEntity.ok(serieService.cadastrarSerie(serie));
  }

  @GetMapping
  public ResponseEntity<List<Serie>> getSeries() {
    return ResponseEntity.ok(serieService.seriesCadastradas());
  }

  @DeleteMapping("/{id}")
  public void removeSerie(@PathVariable Integer id) {
    serieService.serieRemove(id);
  }

  @PostMapping("/{id}/episodios")
  public ResponseEntity<Serie> addEpisodio(
      @PathVariable Integer id,
      @RequestBody Episodio episodio) {

    return ResponseEntity.ok(serieService.addEpisodio(id, episodio));
  }

  @GetMapping("/{id}/episodios")
  public ResponseEntity<List<Episodio>> getEpisodios(@PathVariable Integer id) {

    return ResponseEntity.ok(serieService.searchEpisodiosBySerie(id));
  }

}
