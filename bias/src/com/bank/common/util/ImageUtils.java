package com.bank.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

    public static String JPEG = "JPEG";

    /**
     * @param srcImageFile
     * @param result
     * @param height
     * @param width
     * @param flag
     */
    public final static void scale(String srcImageFile, String cutImageFile, int width, int height) {
        Graphics2D g = null;
        try {
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = image.createGraphics();
            g.fillRect(0, 0, width, height);
            g.drawImage(itemp, 0, 0, width, height, Color.white, null);
            ImageIO.write(image, JPEG, new File(cutImageFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }

    /**
     * @param imageSource
     * @param imageDest
     * @return
     */
    public static void cropzoom(String imageSource, String imageDest, String imageRotate, String viewPortW,
            String viewPortH, String imageX, String imageY, String imageW, String imageH, String selectorX,
            String selectorY, String selectorW, String selectorH) {
        Graphics2D g = null;
        try {
            BufferedImage bi = ImageIO.read(new File(imageSource));
            BufferedImage tmp1 = ImageUtils.scale(bi, StringUtil.getInt(imageW), StringUtil.getInt(imageH));
            BufferedImage image = new BufferedImage(StringUtil.getInt(viewPortW) + StringUtil.getInt(imageW) * 2,
                    StringUtil.getInt(viewPortH) + StringUtil.getInt(imageH) * 2, BufferedImage.TYPE_INT_RGB);
            g = image.createGraphics();
            g.fillRect(0, 0, StringUtil.getInt(viewPortW) + StringUtil.getInt(imageW) * 2, StringUtil.getInt(viewPortH)
                    + StringUtil.getInt(imageH) * 2);
            g.drawImage(tmp1, StringUtil.getInt(imageW) + StringUtil.getInt(imageX), StringUtil.getInt(imageH)
                    + StringUtil.getInt(imageY), StringUtil.getInt(imageW), StringUtil.getInt(imageH), Color.white,
                    null);

            // rotate graphics image
            BufferedImage tmp2 = ImageUtils.rotateImage(image, StringUtil.getInt(imageRotate),
                    StringUtil.getInt(imageW) + StringUtil.getInt(imageX) + StringUtil.getInt(imageW) / 2,
                    StringUtil.getInt(imageH) + StringUtil.getInt(imageY) + StringUtil.getInt(imageH) / 2);
            // cut image by original image
            BufferedImage tmp3 = ImageUtils.cut(tmp2, StringUtil.getInt(imageW), StringUtil.getInt(imageH),
                    StringUtil.getInt(viewPortW), StringUtil.getInt(viewPortH));
            // cut image by selector area
            BufferedImage tmp4 = ImageUtils.cut(tmp3, StringUtil.getInt(selectorX), StringUtil.getInt(selectorY),
                    StringUtil.getInt(selectorW), StringUtil.getInt(selectorH));
            ImageIO.write(tmp4, JPEG, new File(imageDest));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }

    /**
     * @param srcImageFile
     * @param result
     * @param height
     * @param width
     * @param flag
     */
    public final static BufferedImage scale(BufferedImage bi, int width, int height) {
        Graphics2D g = null;
        try {
            Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = image.createGraphics();
            g.fillRect(0, 0, width, height);
            g.drawImage(itemp, 0, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
            itemp = image;
            return image;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }

    /**
     * @param bi
     * @param result
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public final static BufferedImage cut(BufferedImage bi, int x, int y, int width, int height) {
        Graphics g = null;
        try {
            ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
            Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bi.getSource(), cropFilter));
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            g = tag.getGraphics();
            g.drawImage(img, 0, 0, width, height, null);
            g.dispose();
            return tag;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
    }

    /**
     * @param srcImageFile
     * @param destImageFile
     * @param degree
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage bi, final int degree, final int x, final int y) {
        Graphics2D graphics2d = null;
        try {
            int type = bi.getColorModel().getTransparency();
            BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), type);
            graphics2d = img.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.rotate(Math.toRadians(degree), x, y);
            graphics2d.drawImage(bi, 0, 0, null);
            return img;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (graphics2d != null) {
                graphics2d.dispose();
            }
        }
        return null;
    }
}