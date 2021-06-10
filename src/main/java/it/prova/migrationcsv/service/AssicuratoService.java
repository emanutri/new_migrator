package it.prova.migrationcsv.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.prova.migrationcsv.Constants;
import it.prova.migrationcsv.connection.MyConnection;
import it.prova.migrationcsv.dao.AssicuratoDAO;
import it.prova.migrationcsv.model.Assicurato;
import it.prova.migrationcsv.model.NotProcessedAssicurato;

public class AssicuratoService {
	
	 AssicuratoDAO assicuratodao= new AssicuratoDAO();
	 
	 public List<Assicurato> ListAll()throws Exception{
		 
		 List<Assicurato> result = new ArrayList<>();
		 try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEWSCHEMA)) {
			
			 this.assicuratodao.setConnection(connection);
			 result= assicuratodao.listAll();
			 
		 } catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		 
		return result;
	}
	
	public int inserisciNuovo(Assicurato input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");
		
		int result = 0;
		try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEWSCHEMA)) {

			this.assicuratodao.setConnection(connection);
			
			if(validaAssicurati(input)) {
				System.out.println("validazione ok : inserimento in Assicurato");
				result= assicuratodao.insert(input);
			}else {
				System.out.println("attenzione ci sono errori di validazione : inserimento in not_processed");
				result= assicuratodao.insertNotProcessed(input);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		return result;
	}
	
 public List<NotProcessedAssicurato> ListNotProcessed()throws Exception{
		 
		 List<NotProcessedAssicurato> result = new ArrayList<>();
		 try(Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL_NEWSCHEMA)) {
			
			 this.assicuratodao.setConnection(connection);
			 result= assicuratodao.listAllNotProcessed();
			 
		 } catch (Exception e) {
			e.printStackTrace();
			throw e;
		} 
		 
		return result;
	}
	
	private static boolean validaAssicurati(Assicurato assicuratoItem) {
		
		if(StringUtils.isBlank(assicuratoItem.getNome()) || 
				StringUtils.isBlank(assicuratoItem.getCognome()) ||
				StringUtils.isBlank(assicuratoItem.getCodiceFiscale()) ||
				assicuratoItem.getCodiceFiscale().length()!=16 ||
				assicuratoItem.getDataNascita()==null||
				assicuratoItem.getNuoviSinistri()==null || assicuratoItem.getNuoviSinistri()< 0 || assicuratoItem.getNuoviSinistri()>10 ) {
			
			return false;
		}
		return true;
	}
	
	

}
