package br.com.rest.teste;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


@Path("/json")
public class JSONPessoa {

	@GET
	@Path("/get")
	@Produces("application/json")
	public Pessoa getProductInJSON() {
		Pessoa p  = new Pessoa();
		p.setCodigo(1);
		p.setNome("José Silva");
		
		return p;
	}
	
	@GET
	@Path("/lerExcel")
	//@Produces("application/json")
	public String getSchoolExcel() throws BiffException, IOException {
		FileInputStream fisPlan = null;
		String t = "teste";
		try {
		File file = new File("C:\\Users\\jcarvalho7\\Downloads\\plan_escola.xlsx");
		fisPlan = new FileInputStream(file);
		
		
		 /* pega o arkivo do Excel */
	     Workbook workbook = Workbook.getWorkbook(fisPlan);  
	     /* pega a primeira planilha dentro do arquivo XLS */
	     Sheet sheet = workbook.getSheet(0); 
	     int linhas = sheet.getRows();
	     System.out.println("Iniciando a leitura da planilha XLS:");

	       

	     for(int i = 0; i < linhas; i++){
	     /* pega os valores das célular como se numa matriz */
	    	 Cell a1 = sheet.getCell(0, i);

	    	 Cell a2 = sheet.getCell(1, i);

	    	 Cell a3 = sheet.getCell(2, i);
	     /* pega os conteúdos das células */
	    	 String as1 = a1.getContents();

	    	 String as2 = a2.getContents();

	    	 String as3 = a3.getContents();
	    	 
	    	 System.out.println("Coluna 1: " + as1);

	    	 System.out.println("Coluna 2: " + as2);

	    	 System.out.println("Coluna 3: " + as3);
	     }
	     
		//Escola e  = new Escola();
		}catch(FileNotFoundException e) {
			
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	
		
		return t;
	}
	
	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createPessoaInJson(Pessoa pessoa) {
		String result = "Product created : "+ pessoa;
		return Response.status(201).entity(result).build();
	}
	
}
