package edu.sjsu.students.shuangwu.opinions.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class BlobStoreUpload extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("myFile");

		if (blobKey != null) {
			PrintWriter writer = res.getWriter();
			writer.println("<script type='text/javascript'>");
			writer.printf("window.parent.setBlobKeyFromUploader('%s');\n", blobKey.getKeyString());
			writer.println("</script>");
			writer.println("<a href='/upload.jsp'>Change picture?</a>");
			writer.close();
		}
	}
}
