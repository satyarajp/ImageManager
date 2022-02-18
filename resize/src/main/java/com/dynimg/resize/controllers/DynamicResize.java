package com.dynimg.resize.controllers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicResize {
	
	String errMessage = "Invalid image rescale";

	@GetMapping (path = "/cropImage", produces = "image/jpeg")
	public byte[] dynamicImgCrop( 
			@RequestParam int width, @RequestParam int height, 
			@RequestParam URL imageUrl) {
		
		BufferedImage img=null;
		try {
			img = ImageIO.read(imageUrl);
			int origHeight = img.getHeight();
			int origWidth = img.getWidth();			
			if (width < 0 ||  width > origWidth) 
				width = origWidth;
			
			if (height <0 || height > origHeight )
				height = origHeight;
	
			BufferedImage scaled = img.getSubimage((origWidth/2)-(width/2),(origHeight/2)- (height/2), width, height);
			File out = new File("src/main/resources/subimg.jpg");
			ImageIO.write(scaled, "jpg", out);
			return Files.readAllBytes(Paths.get(out.getAbsolutePath()));
		} catch (IOException e) {
			return (errMessage+":"+e.getMessage()).getBytes();
		}
	}

	
	@GetMapping (path = "/rescaleImage", produces = "image/jpeg")
	public byte[] dynamicImgScale( 
			@RequestParam int width, @RequestParam int height, 
			@RequestParam URL imageUrl) throws IOException {
		try {
		BufferedImage img = ImageIO.read(imageUrl);
		Image scaled = img.getScaledInstance(width,height,Image.SCALE_REPLICATE);
		BufferedImage bScaled = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_RGB);
		boolean buffScale = bScaled.getGraphics().drawImage(scaled, 0, 0, null);
		File out2 = new File("src/main/resources/scale.jpg");
		ImageIO.write(bScaled, "jpg", out2);
		return Files.readAllBytes(Paths.get(out2.getAbsolutePath()));
		}catch(IOException ioe) {
			return (errMessage+":"+ioe.getMessage()).getBytes();
		}
	}
	
	
}
