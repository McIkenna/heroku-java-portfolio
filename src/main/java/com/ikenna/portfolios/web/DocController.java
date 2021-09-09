package com.ikenna.portfolios.web;

import com.ikenna.portfolios.infos.Doc;
import com.ikenna.portfolios.infos.Response;
import com.ikenna.portfolios.services.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@SpringBootApplication
@RequestMapping("")
@CrossOrigin
public class DocController {
    @Autowired
    private DocStorageService docStorageService;

    @GetMapping("/api/project")
    public String get(Model model) {
        Iterable<Doc> docs = docStorageService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/admin/project/uploadFile")
        public Response docFileUpload(@RequestParam("file") MultipartFile file,
                                      Doc doc){

            Doc docFile = docStorageService.saveFile(file, doc);
            Response response = new Response();
            if(docFile != null){
                String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(docFile.getDocName())
                        .toUriString();
                response.setFileDownloadUri(downloadUri);
                response.setFileName(docFile.getDocName());
                response.setFileType(docFile.getDocType());
                response.setSize(file.getSize());
                response.setMessage("File Uploaded Successfully!");
                return response;
            }
           response.setMessage("Sorry there was an error somewhere");
            return response;
    }

    @GetMapping("/api/project/all")
    public Iterable<Doc> findAllTasks(){
        return docStorageService.getFiles();
    }

    @GetMapping("/api/project/{id}")
    public ResponseEntity<?> findTAskById(@PathVariable long id){
       Doc doc = docStorageService.getFile(id);
        return new ResponseEntity<Doc>(doc, HttpStatus.OK);
    }


    @DeleteMapping("/admin/project/{id}")
    public ResponseEntity<?> deleteWork(@PathVariable long id){
        docStorageService.DeleteDocById(id);
        return new ResponseEntity<String>("Work with company name '" + id + "' was deleted", HttpStatus.OK);
    }
}
