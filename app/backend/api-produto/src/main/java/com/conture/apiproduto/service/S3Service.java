package com.conture.apiproduto.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class S3Service {
	@Autowired
	private AmazonS3 amazonS3Client;

	private String defaultBucketName = "conture-image-test-bucket";

	public boolean existsObject(String bucketName, String objectName) {
		return this.amazonS3Client.doesObjectExist(bucketName, objectName);
	}

	public void putObject(String bucketName, String objectName, byte[] image) {
		this.amazonS3Client.putObject(bucketName, objectName, new ByteArrayInputStream(image), new ObjectMetadata());
	}

	public void putObject(String objectName, byte[] image) {
		this.putObject(this.defaultBucketName, objectName, image);
	}

	public void deleteObject(String bucketName, String objectName) {
		this.amazonS3Client.deleteObject(bucketName, objectName);
	}

	public byte[] getObject(String bucketName, String objectName) throws IOException {
		S3Object s3Object = this.amazonS3Client.getObject(bucketName, objectName);

		return s3Object.getObjectContent().readAllBytes();
	}

	public String getDefaultBucketName() {
		return this.defaultBucketName;
	}
}
