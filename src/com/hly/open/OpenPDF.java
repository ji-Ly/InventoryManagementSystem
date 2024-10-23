package com.hly.open;

import java.io.File;

import javax.swing.JOptionPane;

import com.hly.dao.InventoryUtils;

public class OpenPDF {
	
	public static void openByID(String id) {
		try {
			if((new File(InventoryUtils.billPath+id+".pdf")).exists()  ) {
				Process process = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+InventoryUtils.billPath+""+id+".pdf");
		
			}
			else {
				JOptionPane.showMessageDialog(null, "File is not exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
