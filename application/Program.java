package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "d://test//test.txt";
		String anotherPath = "d://test2";
		List<Product> list = new ArrayList<>();
		
		boolean sucess = new File(anotherPath + "\\subdir").mkdir(); //da pra melhorar essas declarações, colocar em variaveis, deixar reutilizavel
		System.out.println(sucess);

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine();
			

			while (line != null) {
				String[] lines = line.split(",");
				
				double price = Double.parseDouble(lines[1]);
				int quantity = Integer.parseInt(lines[2]);
				
				
				list.add(new Product(lines[0], price, quantity));
				line = br.readLine();
			}
			
			for(Product product : list) {
				System.out.println(product);
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(anotherPath + "\\subdir\\out.txt"))){
			
			for(Product product : list) {
				bw.write(product.getName() + ", R$" + String.format("%.2f", product.total()));
				bw.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
