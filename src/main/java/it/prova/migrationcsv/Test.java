package it.prova.migrationcsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import it.prova.migrationcsv.model.Assicurato;
import it.prova.migrationcsv.model.NotProcessedAssicurato;
import it.prova.migrationcsv.service.AssicuratoService;

public class Test {

	@SuppressWarnings(value = { "rawtypes", "unchecked" })
	// annotation che mi permette di ignorare i warning durante la compilazione che non sono rilevanti 

	public static void main(String[] args) throws Exception {

		System.out.println("avvio lettura file:");
		String nomeFile="C:\\Users\\NEW ROOM\\Desktop\\CsvAssiucrato.csv";
		String scartiFile="C:\\Users\\NEW ROOM\\Desktop\\scarti.csv";
		String nuoviFile="C:\\Users\\NEW ROOM\\Desktop\\processed.csv";
		AssicuratoService assicuratoService= new AssicuratoService();
		//ORA COSTRUISCO ATTRAVERSO CsvToBeanBuilder un oggetto opportunamente annotato, utilizzando i dati presi 
		//dal file CsvAssiucrato.csv
		List<Assicurato> assicurati = new CsvToBeanBuilder(new FileReader(nomeFile))
				.withType(Assicurato.class)
				.build()
				.parse();
		System.out.println("Ora inseriamoli nella base dati:");
//		for(Assicurato app: assicurati) {			 
			 //INSERIMENTO IN TABELLA ASSICURATO:
			 
//			System.out.println("Sto processando:"+ app.toString());

		assicurati.forEach(item -> {
				try {
					
					assicuratoService.inserisciNuovo(item);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			 
//			assicuratoService.inserisciNuovo(app);
			 
//		}
//		System.out.println("ecco i nuovi assicurati");
//		assicuratoService.ListAll().forEach(x -> System.out.println(x.toString()));		
		 
		//PROVO A SCRIVERE SU UN FILE CSV :
		 
		 
		 //LA CLASSE FILEWRITER PREVEDE UN COSTRUTTORE  CON UN PARAMETRO BOOLEAN 
		 //CHE MI PEREMETTE DI SCRIVERE IN CODA AL FILE, SENZA SOVRASCRIVERE IL CONTENUTO GIA' PRESENTE
		 try ( CSVWriter writer = new CSVWriter(new FileWriter(nuoviFile))){
			 StatefulBeanToCsv<Assicurato> beanToCsv = new StatefulBeanToCsvBuilder<Assicurato>(writer)
					    .build();
			
			 beanToCsv.write(assicuratoService.ListAll());
		 }
		 
		 //SCRIVO SUL FILE DEDICATO AGLI ASSICURATI NON PROCESSATI
		 try ( CSVWriter writer = new CSVWriter(new FileWriter(scartiFile))){
			 StatefulBeanToCsv<NotProcessedAssicurato> beanToCsv = new StatefulBeanToCsvBuilder<NotProcessedAssicurato>(writer)
					    .build();
			
			 beanToCsv.write(assicuratoService.ListNotProcessed());
		 }
		
	}

}
