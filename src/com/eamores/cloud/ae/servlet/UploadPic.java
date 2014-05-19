package com.eamores.cloud.ae.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eamores.cloud.ae.dao.Blob;
import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;


public class UploadPic extends HttpServlet {
	private static final long serialVersionUID = -65765132281305858L;
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        BlobKey blobKey = blobs.get("myFile").get(0);
        
        String uid = req.getParameter("uid");
        String type = getContentType(blobKey);

        
        if (blobKey == null) {
            res.sendRedirect("/");
        } 
        else if(type == null || !type.toLowerCase().equals("image")){
        	blobstoreService.delete(blobKey);
        	res.sendRedirect("/");
        }
        else {
        	 ImagesService imagesService = ImagesServiceFactory.getImagesService();
             String imageUrl = imagesService.getServingUrl(blobKey);
             
        	Blob.add(uid, blobKey.getKeyString(), imageUrl);
            res.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
        }
    }
    
    private String getContentType(BlobKey blobKey){
    	if(blobKey != null){
    		BlobInfoFactory blobInfoFactory = new BlobInfoFactory();
    		BlobInfo blobInfo = blobInfoFactory.loadBlobInfo(blobKey);
            String type = blobInfo.getContentType();
            
            String [] ty = type.split("/");
            
            if(ty.length > 0){
            	return ty[0];
            }
    	}
    	return null;  
    }
}

