package edu.sjsu.students.shuangwu.opinions.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.common.base.Strings;

public class BlobStoreServe extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	private static final String DEFAULT_IMAGE_URL = "/image/default_question_image.jpg";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		String blobKeyParam = req.getParameter("blob-key");
		if (Strings.isNullOrEmpty(blobKeyParam)) {
			// redirect to default image
			res.sendRedirect(DEFAULT_IMAGE_URL);
		} else {
			try {
				BlobKey blobKey = new BlobKey(blobKeyParam);
				byte[] blobDataHeader = blobstoreService.fetchData(blobKey, 0L,
						4L);
				blobstoreService.serve(blobKey, res);
			} catch (Exception ex) {
				// redirect to default image
				res.sendRedirect(DEFAULT_IMAGE_URL);
			}
		}
	}
}
