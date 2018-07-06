package com.xxx;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
	static void createqrcode(int version, String content, String imgpath, String startRgb, String endRgb)
			throws IOException {
		
		Qrcode qrcode = new Qrcode();

		qrcode.setQrcodeVersion(version);
		// System.out.println("版本号："+qrcode.getQrcodeVersion());
		// 设置二维码的排错率，可以是L（7%），M（15%），Q（%25），H（30）
		// 二维码的排错率越高可存储的信息就越小，二维码的清晰度要求就越小
		qrcode.setQrcodeErrorCorrect('M');
		System.out.println("排错率：" + qrcode.getQrcodeErrorCorrect());
		qrcode.setQrcodeEncodeMode('B');
		System.out.println("可存储类型：" + qrcode.getQrcodeErrorCorrect());
		int imgSize = 67 + (version - 1)*12;
		BufferedImage bufferedImage = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufferedImage.createGraphics();
		qrcode.setQrcodeVersion(version);
		gs.setBackground(Color.WHITE);
		gs.setColor(Color.BLACK);
		gs.clearRect(0, 0, imgSize, imgSize);

	

		// 通过二维码要填充的内容获取一个布尔类型的二维数组
		boolean[][] calQrocde = qrcode.calQrcode(content.getBytes());
		
		// 声明偏移量
		int pixoff = 2;
		int startR = 0, startG = 0, startB = 0;
		if (null != startRgb) {
			String[] rgb = startRgb.split(",");
			startR = Integer.valueOf(rgb[0]);
			startG = Integer.valueOf(rgb[1]);
			startB = Integer.valueOf(rgb[2]);
		}
		int endR = 0, endG = 0, endB = 0;
		if (null != endRgb) {
			String[] rgb = endRgb.split(",");
			endR = Integer.valueOf(rgb[0]);
			endG = Integer.valueOf(rgb[1]);
			endB = Integer.valueOf(rgb[2]);
		}
		for (int i = 0; i < calQrocde.length; i++) {// 遍历行

			for (int j = 0; j < calQrocde[i].length; j++) {// 遍历列

				if (calQrocde[i][j]) {// true填充二维码颜色，false则不填充

					int r = startR + (endR - startR) * (i + j) / 2 / calQrocde.length;
					int g = startG + (endG - startG) * (i + j) / 2 / calQrocde.length;
					int b = startB + (endB - startB) * (i + j) / 2 / calQrocde.length;

					Color color = new Color(r, g, b);
					gs.setColor(color);
					gs.fillRect(i * 3 + pixoff, j * 3 + pixoff, 3, 3);
				}
			}

		}
		// 添加头像
		BufferedImage logo = ImageIO.read(new File("D:/logo.jpg"));
		// 头像大小
		int logoSize = imgSize / 3;
		// 头像的起始位置
		int o = (imgSize - logoSize) / 2;
		// 往二维码上画头像
		gs.drawImage(logo, o, o, logoSize, logoSize, null);
		// 关闭绘画对象
		gs.dispose();
		// 把缓冲区图片输出到内存
		bufferedImage.flush();
		try {
			ImageIO.write(bufferedImage, "png", new File(imgpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("成功");
	}

	public static void main(String[] args) throws IOException {
		int version = 15;
		String content = "BEGIN:VCARD\r\n" + 
						   "FN:姓名:hjj\r\n"+
						   "ORG:学校:河北科技师范学院 	职位:学生\r\n"+
						   "TITLE:  \r\n" + 
						   "TEL;WORK;VOICE:12345677456\r\n"+
						   "TEL;HOME;VOICE:66973635435\r\n"+
						   "TEL;CELL;VOICE:45654544424\r\n"+
						   "ADR;WORK:河北秦皇岛\r\n"+
						   "ADR;HOME:河北石家庄\r\n"+
						   "URL:http://www.hjj.com\r\n"+
						   "EMAIL;HOME:4546454645@qq.com\r\n" + 
						   "PHOTO;VALUE=uri:http://www.hjj.com/images/logo.jpg\r\n" + 
						   "END:VCARD";

		String imgpath = "D:/q.png";
		String startRgb = "255,20,255";
		String endRgb = "100,125,255";

		createqrcode(version, content, imgpath, startRgb, endRgb);

	}

}
