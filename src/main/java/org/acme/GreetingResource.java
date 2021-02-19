package org.acme;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/imageio")
public class GreetingResource {

    /**
     * I never got this far. But believe you can solve this if you generate the config referenced in this comment, but
     * I don't know how to do that Quarkus? If we can sort this out then perhaps the issue is actually fixed!?!?!
     */
    @Path("/fail/jpg")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("quarkus-logo.png"));
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.MAGENTA);
        graphics.drawString("test", 100, 100);
        graphics.dispose();
        File newFile = new File("quarkus-logo.jpg");
        ImageIO.write(bufferedImage, "jpg", newFile);
        BufferedImage bufferedImage2 = ImageIO.read(newFile);
        return "Quarkus png logo width: " + bufferedImage.getWidth() + ". Rewritten jpeg width: " + bufferedImage2.getWidth();
    }


    /**
     * This is the reproducer I was originally working toward.
     */
    @Path("/fail/tiff")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("test.tiff"));
        return "Tiff image width: " + bufferedImage.getWidth();
    }


    /**
     * This works in native which was a surprise.
     */
    @Path("/success/png")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello3() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("quarkus-logo.png"));
        return "Quarkus png logo width: " + bufferedImage.getWidth();
    }


}