package by.intexsoft.rest;

import java.io.File;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The controller processes requests to upload images to the server.
 */
@RestController
public class LoadImageRestController {
	private static final String IMAGE_PATH = "/image/";
	private ServletContext servletContext;

	@Autowired
	public LoadImageRestController(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * Method of the controller file name for further upload to the server. pecifies
	 * the location of the servlet and loads the file into a folder.
	 * 
	 * @see {@link ServletContext}
	 */
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public ResponseEntity<String> getImage(@RequestParam MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				File destinationFile = new File(servletContext.getRealPath(IMAGE_PATH) + file.getOriginalFilename());
				file.transferTo(destinationFile);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception exeption) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
