package com.wkd.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	//���������
	private Random rand = new Random();
	private String str = "012345678��9ab��cdef�Ƽ�ghi��ѧjklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��������Ӧ��������MIME����Ϊ ͼƬ image/jpeg
		response.setContentType("image/jpeg");
		
		//���������������ͼ��
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control","no-cache");
		response.setDateHeader("expires", 0);
		
		//�ڷ��������ڴ�������һ������ͼ�� BufferedImage
		int width=200,height=100;
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		//�ӻ�����ͼ���ȡGraphics���� [ͼ��������]
		Graphics g = img.getGraphics();
		g.setColor(getColor(250,255));  //R,G,B 0~255
		//���ƾ�����䱳��
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(0,0,0)); //����Ϊ��ɫ
		g.drawRect(0, 0, width-1, height-1);
		
		//��Ӹ�������
		for(int i=0;i<500;i++){
			g.setColor(getColor(120,200)); //������ɫ
			int x1 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int x2 = x1+rand.nextInt(30);
			int y2 = y1+rand.nextInt(30);
			g.drawLine(x1, y1, x2, y2);
		}
		
		//ʹ��Graphics���������֤��
		for(int i=0 ; i<4 ; i++){
			//��ȡ����ַ�
			char ch = str.charAt(rand.nextInt(str.length()));
			String s = String.valueOf(ch); //char --> String
			g.setColor(getColor(0,120));
			g.setFont(new Font("����",Font.BOLD|Font.ITALIC,15+rand.nextInt(40)));
			g.drawString(s, 20+40*i, 40+rand.nextInt(20));
		}
		
		
		//ʹ��ImageIO��ķ������������е�ͼ��������ͻ��������
		ImageIO.write(img, "jpeg", response.getOutputStream());
		
	}

	/*
	 * ���������ɫֵ
	 */
	private Color getColor(int start, int end) {
		if(start<0){
			start = 0;
		}
		if(start>255){
			start=255;
		}
		if(end<0){
			end = 0;
		}
		if(end>255){
			end=255;
		}
		
		int r = start + rand.nextInt(end-start);
		int g = start + rand.nextInt(end-start);
		int b = start + rand.nextInt(end-start);
		return new Color(r,g,b);
	}

}
