package br.com.uri.soma;

import javax.swing.JOptionPane;

public class Somar {

	
	public static void main(String[] args) {
		
		int a = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o primeiro numero: "));
		int b = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o segundo numero: "));
		
		int soma = a + b;
		
		System.out.println("A soma dos valores é: " + soma);
	}
}
