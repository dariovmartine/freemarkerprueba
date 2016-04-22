package ar.org.pruebas.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test {
	
	public static void main(String[] args) throws IOException {

		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File("./bin"));
		try {
			 Writer file = new FileWriter (new File("output.txt"));
			 Map<String, Object> data = new HashMap<String, Object>();
			 Template template = cfg.getTemplate("helloworld.ftl");
			 long date = new Date().getTime();
			 for (int i=0; i <= 10000; i++) {
				data.put("unformated", Math.random() * 100 + Math.random() * 10 + Math.random() / 10  + Math.random() / 100);
				template.process(data, file);
			 }
			 System.out.println("Tiempo usando funcion en ftl:" + (new Date().getTime() - date) + " ms" );
			 file.flush();
	         file.close();
			 file = new FileWriter (new File("output_java.txt"));
			 
	         
		     date = new Date().getTime();
			 template = cfg.getTemplate("java.ftl");
			 DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			 simbolos.setDecimalSeparator(',');
			 simbolos.setGroupingSeparator('.');
			 DecimalFormat formateador = new DecimalFormat("#,###,###.00",simbolos);
			 
			 for (int i=0; i <= 10000; i++) {
				data.put("formated", formateador.format(Math.random() * 100 + Math.random() * 10 + Math.random() / 10  + Math.random() / 100));
				template.process(data, file);
			 }
			 System.out.println("Tiempo formateando java:" + (new Date().getTime() - date)  + " ms" );
			file.flush();
            file.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
