package com.net.savephoto.services.impl;

import com.net.savephoto.services.ImageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public abstract class ImageUtilImpl implements ImageService {
    public static byte[] compress(byte[] bytes) {
        Deflater deflater = new Deflater();
        deflater.setInput(bytes);
        deflater.setLevel(Deflater.BEST_COMPRESSION);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[bytes.length];
        while (!deflater.finished()){
            deflater.finish();
            int size = deflater.deflate(temp);
            outputStream.write(temp,0,size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {

        }
        return outputStream.toByteArray();
    }
    public static byte[] decompress(byte[] bytes) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(bytes);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] temp = new byte[bytes.length];
        while (!inflater.finished()){
            int size = inflater.inflate(temp);
            outputStream.write(temp,0,size);
            inflater.finished();
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {

        }
        return outputStream.toByteArray();
    }
}
