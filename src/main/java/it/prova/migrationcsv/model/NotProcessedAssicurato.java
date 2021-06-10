package it.prova.migrationcsv.model;

import java.util.Date;

import com.opencsv.bean.CsvDate;

public class NotProcessedAssicurato {

	private Long id;

	private String nome;

	private String cognome;

	@CsvDate(value = "yyyy-MM-dd")
	private Date dataNascita;

	private Integer nuoviSinistri;

	private String codiceFiscale;

	public NotProcessedAssicurato() {
	}

	public NotProcessedAssicurato(String nome, String cognome, Date dataNascita, Integer nuoviSinistri,
			String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.nuoviSinistri = nuoviSinistri;
		this.codiceFiscale = codiceFiscale;
	}

	public NotProcessedAssicurato(Long id, String nome, String cognome, Date dataNascita, Integer nuoviSinistri,
			String codiceFiscale) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.nuoviSinistri = nuoviSinistri;
		this.codiceFiscale = codiceFiscale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public Integer getNuoviSinistri() {
		return nuoviSinistri;
	}

	public void setNuoviSinistri(Integer nuoviSinistri) {
		this.nuoviSinistri = nuoviSinistri;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	@Override
	public String toString() {
		return "NotProcessedAssicurato [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita="
				+ dataNascita + ", nuoviSinistri=" + nuoviSinistri + ", codiceFiscale=" + codiceFiscale + "]";
	}

}
