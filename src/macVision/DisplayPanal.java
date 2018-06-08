package macVision;

import java.awt.*;  
import java.awt.image.BufferedImage;  
import java.awt.image.DataBufferByte;  
import javax.swing.*;  
import org.opencv.core.Mat;  

class DisplayPanal extends JPanel{  
    private static final long serialVersionUID = 1L;  
    private BufferedImage image;  
    // Create a constructor method  
    public DisplayPanal(){  
        super();   
    }  
    public boolean MatToBufferedImage(Mat matBGR){  
        long startTime = System.nanoTime();  
        int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels() ;  
        byte[] sourcePixels = new byte[width * height * channels];  
        matBGR.get(0, 0, sourcePixels);  
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);  
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();  
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);  
        long endTime = System.nanoTime();  
        System.out.println(String.format("Elapsed: %.2f ms", (float)(endTime - startTime)/1000000));  
        return true;  
    }  
    public void paintComponent(Graphics g){  
        super.paintComponent(g);   
        if (this.image==null) return;  
        g.drawImage(this.image,10,10,2*this.image.getWidth(),2*this.image.getHeight(), null);  
    }  
}  