package it.prova.migrationcsv.model;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class Assicurato {

	@CsvBindByName(column = "id")
	private Long id;
	@CsvBindByName(column = "nome")
	private String nome;
	@CsvBindByName(column = "cognome")
	private String cognome;

	@CsvDate(value = "yyyy-MM-dd")
	@CsvBindByName(column = "data_nascita")
	private Date dataNascitaFromCsv;

	@CsvBindByName(column = "nuovi_sinistri")
	private Integer nuoviSinistri;
	
	@CsvBindByName(column = "codice_fiscale")
	private String codiceFiscale;

	public Assicurato() {
	}

	public Assicurato(String nome, String cognome, Date dataNascita, Integer sinistri, String codiceFiscale) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascitaFromCsv = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.nuoviSinistri = sinistri;
	}

	public Assicurato(Long id, String nome, String cognome, Date dataNascita, Integer nuoviSinistri,
			String codiceFiscale) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.nuoviSinistri = nuoviSinistri;
		this.dataNascitaFromCsv = dataNascita;
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
		return dataNascitaFromCsv;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascitaFromCsv = dataNascita;
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
		StringBuilder builder = new StringBuilder();
		builder.append("{id=").append(id).append(", codice_fiscale=").append(codiceFiscale).append(", nome=")
				.append(nome).append(", cognome").append(cognome).append(", data_nascita=").append(dataNascitaFromCsv)
				.append(", sinistri").append(nuoviSinistri).append("}");

		return builder.toString();
	}

//	public Date parseDate(String data) throws Exception {
//		 return new SimpleDateFormat("yyyy-MM-dd").parse(data); 
//	}

}
