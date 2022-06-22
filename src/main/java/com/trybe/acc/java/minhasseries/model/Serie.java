package com.trybe.acc.java.minhasseries.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Serie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String nome;

  @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
  @JsonBackReference
  private List<Episodio> episodios = new ArrayList<>();

  /**
   * Sem esse construtor vazio não funciona teste.
   */
  public Serie() {}

  /**
   * Constructor.
   */
  public Serie(String nome) {
    this.nome = nome;
    this.episodios = new ArrayList<>();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Episodio> getEpisodios() {
    return episodios;
  }

  public void adicionarEpisodio(Episodio episodio) {
    this.episodios.add(episodio);
  }

}
