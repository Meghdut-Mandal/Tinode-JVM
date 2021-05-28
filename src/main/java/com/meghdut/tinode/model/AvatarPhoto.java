package com.meghdut.tinode.model;



import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * Utility class: constructs a square 128x128 BufferedImage from bytes/serializes to jpeg bytes
 */
public class AvatarPhoto implements Serializable
{
    public byte[] data;
    public String type;
    public String uri;

    protected transient BufferedImage mImage = null;

    public AvatarPhoto()
    {
    }

    public AvatarPhoto(byte[] bits)
    {
        data = bits;
        try{
            constructBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AvatarPhoto(BufferedImage bmp)
    {
        this.mImage = bmp;
        try{
            serializeBufferedImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void constructBufferedImage() throws IOException
    {
        if (data != null) {
            mImage = ImageIO.read(new ByteArrayInputStream(data));
        }
    }

    public BufferedImage getBufferedImage()
    {
        if (mImage == null) {
            try{
                constructBufferedImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mImage;
    }

    private void serializeBufferedImage() throws IOException
    {
        if (mImage != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(mImage, "jpeg", baos);
            this.data = baos.toByteArray();
            this.type = "jpeg";
        }
    }
}

