/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ultilities;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.ByteArrayOutputStream;
import java.io.Writer;
import java.util.Hashtable;

/**
 *
 * @author Admin
 */
public class ZXingHelper {
    public static byte[]getQRCodeImage(String text,int width,int height){
        try {
            QRCodeWriter qrCodeWrite = new QRCodeWriter();
            BitMatrix bitMaTrix = qrCodeWrite.encode(text, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMaTrix, "png", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static byte[] getBarCodeImage(String text,int width,int height) throws WriterException{
        
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        Code128Writer write = new Code128Writer();
        BitMatrix bitMaTrix = write.encode(text, BarcodeFormat.CODE_128, width, height);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMaTrix, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
}
