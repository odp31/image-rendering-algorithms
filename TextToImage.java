import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpRespnose;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TextToImage
  {
    public static void generateImage(String prompt) throws IOException {
      // replace with Hugging Face API token
      String token = "your_hugging_face_token";

      CloseableHTTPClient httpClient = HttpClients.createDefault();
      HttpPost httpPost = new HttpPost("https://api-inference.huggingface.co/models/runwayml/stable-diffusion-v1-5");
      httpPost.setHeader("Authorization", "Bearer " + token);

      StringEntity entity = new StringEntity("{\"inputs\": \ "" + prompt + "\"}");
      httpPost.setEntity(entity);

      CloseableHttpResponse response = httpClient.execute(httpPost);

      byte[] imageBytes = IOUtils.toByteArray(response.getEntity().getContent());
      BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

      // save image
      File output file = new File("generated_image.png");
      ImageIO.write(image, "png", outputfile);
    }
  }
