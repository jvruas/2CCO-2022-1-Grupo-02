package com.conture.apiusuario.services;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class S3Service {
	@Autowired
	private AmazonS3 amazonS3Client;

	public boolean existsObject(String bucketName, String objectName) {
		return this.amazonS3Client.doesObjectExist(bucketName, objectName);
	}

	public void putObject(String bucketName, String objectName, byte[] image) {
		this.amazonS3Client.putObject(bucketName, objectName, new ByteArrayInputStream(image), new ObjectMetadata());

		// FIXME: Acho que seria melhor salver o nome do bucket e do objeto do que o link
//		return this.amazonS3Client.getUrl(bucketName, objectName).toString();
	}

	public void deleteObject(String bucketName, String objectName) {
		this.amazonS3Client.deleteObject(bucketName, objectName);
	}

	public byte[] getObject(String bucketName, String objectName) throws IOException {
		S3Object s3Object = this.amazonS3Client.getObject(bucketName, objectName);

		return s3Object.getObjectContent().readAllBytes();
	}
}
