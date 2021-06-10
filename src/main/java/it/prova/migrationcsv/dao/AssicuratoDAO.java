package it.prova.migrationcsv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.migrationcsv.model.Assicurato;
import it.prova.migrationcsv.model.NotProcessedAssicurato;

public class AssicuratoDAO extends AbstractMySQLDAO{
	
	public void setConnection(Connection connection) {
		this.connection= connection;
	}
	
	public int insert(Assicurato input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		int result=0;
		try (PreparedStatement st = connection.prepareStatement(
				"INSERT INTO assicurato( nome, cognome, data_nascita,nuovi_sinistri,codice_fiscale) VALUES (?, ?, ?, ?, ?);")) {
			st.setString(1, input.getNome());
			st.setString(2, input.getCognome());
			st.setDate(3, new java.sql.Date(input.getDataNascita().getTime()));
			st.setInt(4, input.getNuoviSinistri());
			st.setString(5, input.getCodiceFiscale());
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
		
	}
	
	public int insertNotProcessed(Assicurato input) throws Exception{
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		int result=0;
		try (PreparedStatement st = connection.prepareStatement(
				"INSERT INTO not_processed( nome, cognome, data_nascita,nuovi_sinistri,codice_fiscale) VALUES (?, ?, ?, ?, ?);")) {				
			st.setString(1, input.getNome());
			st.setString(2, input.getCognome());
			st.setDate(3, new java.sql.Date(input.getDataNascita().getTime()));
			st.setInt(4, input.getNuoviSinistri());
			st.setString(5, input.getCodiceFiscale());
			result = st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}
	
	public List<Assicurato> listAll()throws Exception{
		List<Assicurato> result = new ArrayList<Assicurato>();
		Assicurato holderTemp = null;
		try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("select * from assicurato; ");){
			
			while (rs.next()) {
				holderTemp = new Assicurato();
				holderTemp.setId(rs.getLong("id"));
				holderTemp.setNome(rs.getString("nome"));
				holderTemp.setCognome(rs.getString("cognome"));
				holderTemp.setDataNascita(rs.getDate("data_nascita"));
				holderTemp.setCodiceFiscale(rs.getString("codice_fiscale"));
				holderTemp.setNuoviSinistri(rs.getInt("nuovi_sinistri"));
				result.add(holderTemp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<NotProcessedAssicurato> listAllNotProcessed()throws Exception{
		List<NotProcessedAssicurato> result = new ArrayList<NotProcessedAssicurato>();
		NotProcessedAssicurato holderTemp = null;
		try(Statement st = connection.createStatement(); ResultSet rs = st.executeQuery("select * from not_processed; ");){
			
			while (rs.next()) {
				holderTemp = new NotProcessedAssicurato();
				holderTemp.setId(rs.getLong("id"));
				holderTemp.setNome(rs.getString("nome"));
				holderTemp.setCognome(rs.getString("cognome"));
				holderTemp.setDataNascita(rs.getDate("data_nascita"));
				holderTemp.setCodiceFiscale(rs.getString("codice_fiscale"));
				holderTemp.setNuoviSinistri(rs.getInt("nuovi_sinistri"));
				result.add(holderTemp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
