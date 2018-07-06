package com.xxx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.swetake.util.Qrcode;

public class CreatQRCode {

	public static void main(String[] args) {
		int version=5;
		int imgsize=67+(version-1)+12;
		Qrcode qrcode = new Qrcode();
		BufferedImage bufferedImage=new BufferedImage(imgsize, imgsize,BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufferedImage.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		String context="¿¼ÊÔÍ¨¹ý";
		qrcode.setQrcodeVersion(version);
		gs.clearRect(0, 0, imgsize, version);
		boolean[][] calQrcode=qrcode.calQrcode(context.getBytes());
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode[i].length; j++) {
				
			}
			
		}
		
		
		

	}

}
