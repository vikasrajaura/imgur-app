package ls.imgur.app.restapi;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import ls.imgur.app.exception.ResourceNotFoundException;
import ls.imgur.app.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class ImageApiController {

    private static final String ACCESS_TOKEN = "aa8ad792c762a5681190cf64329eeea6e0266206";
    private static HttpHeaders headers = new HttpHeaders();
    static {
        headers.add("Authorization", "Bearer "+ACCESS_TOKEN);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    private static String IMGUR_URL = "https://api.imgur.com/3/";
    private static String ACCOUNT_IMAGES = IMGUR_URL+"account/me/images";
    private static String GET_ACCOUNT_IMAGE = IMGUR_URL+"account/%s/image/%s";
    private static String DELETE_ACCOUNT_IMAGE = IMGUR_URL+"account/%s/image/%s";
    private static String POST_ACCOUNT_IMAGE = IMGUR_URL+"account/%s/image}";


    @Autowired
    RestTemplate restTemplate;


    /**
     * This will return all images details list stored for an imgur account
     */
    @GetMapping(value="/account/images", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Image>> getAccountImages() throws JsonProcessingException {
        HttpEntity <String> entity = new HttpEntity<>(headers);

        Map<String, Object> jsonObj = restTemplate.exchange(ACCOUNT_IMAGES, HttpMethod.GET, entity, Map.class).getBody();
        List<Object> arr = (ArrayList)jsonObj.get("data");

        List<Image> images = new ArrayList<>();
        for(Object ob : arr) {
            HashMap<String, ?> map = (HashMap) ob;
            Image img = Image.mapper(map);
            images.add(img);
        }
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

    /**
     * This will return a single image detail stored for an imgur account
     */
    @GetMapping(value="/account/{username}/image/{imageId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> getAccountImageById(@PathVariable("username") String username, @PathVariable("imageId") String imageId) throws JsonProcessingException, ResourceNotFoundException {
        HttpEntity <String> entity = new HttpEntity<>(headers);
        String url = String.format(GET_ACCOUNT_IMAGE, username, imageId);

        Map<String, Object> jsonObj = restTemplate.exchange(String.format(GET_ACCOUNT_IMAGE, username, imageId), HttpMethod.GET, entity, Map.class).getBody();
        HashMap<String, ?> imgObj = (HashMap)jsonObj.get("data");
        Image image = null;
        if(imgObj.get("error")==null) {
            image = Image.mapper(imgObj);
        } else {
            String errorMsg = (String)imgObj.get("error");
            log.error(errorMsg);
            throw new ResourceNotFoundException(errorMsg);
        }
        return new ResponseEntity<>(image, HttpStatus.OK);
    }


    /**
     * This will delete an image stored on an imgur account
     */
    @DeleteMapping(value="/account/{username}/image/{deleteHash}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAccountImageById(@PathVariable("username") String username, @PathVariable("deleteHash") String deleteHash) throws JsonProcessingException {

        HttpEntity <String> entity = new HttpEntity<>(headers);
        String url = String.format(DELETE_ACCOUNT_IMAGE, username, deleteHash);

        Map<String, Object> jsonObj = restTemplate.exchange(url, HttpMethod.DELETE, entity, Map.class).getBody();

        return new ResponseEntity("Request Successful", HttpStatus.NO_CONTENT);
    }


    /**
     * Need to Implement
     * This will upload a file for an imgur account
     */
    @PostMapping(value="/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity uploadImage() throws JsonProcessingException {

        HttpEntity <String> entity = new HttpEntity<>(headers);
        String url = String.format("", "", "");

        Map<String, Object> jsonObj = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class).getBody();

        return new ResponseEntity("Request Successful", HttpStatus.CREATED);
    }

    /**
     * Need to Implement
     * This will download a file for an imgur account
     */
    @GetMapping(value="/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity downloadImage() throws JsonProcessingException {

        HttpEntity <String> entity = new HttpEntity<>(headers);
        String url = String.format(GET_ACCOUNT_IMAGE, "", "");

        Map<String, Object> jsonObj = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class).getBody();

        return new ResponseEntity("Request Successful", HttpStatus.OK);
    }

}
