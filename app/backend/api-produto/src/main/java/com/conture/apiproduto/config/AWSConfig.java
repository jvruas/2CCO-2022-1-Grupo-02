package com.conture.apiproduto.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
	private InstanceProfileCredentialsProvider instanceProfile = new InstanceProfileCredentialsProvider(true);

	public AWSCredentials credentials() {
		AWSCredentials credentials = this.instanceProfile.getCredentials();
		return credentials;
	}

	@Bean
	public AmazonS3 amazonS3() {
		AmazonS3 s3Client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(this.credentials()))
				.withRegion(Regions.US_EAST_1)
				.build();
		return s3Client;
	}
}
