package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {
		Model model = new Model();
		
		Corso c = model.cercaCorso("Strategia, tecnologia e marketing");
		System.out.println(c);
		
		System.out.println(model.getIscritti(c));
		
	}

}
