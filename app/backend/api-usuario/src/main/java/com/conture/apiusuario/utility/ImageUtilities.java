package com.conture.apiusuario.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public final class ImageUtilities {
	public static byte[] ByteArrayFrom(MultipartFile multipartFile) throws IOException {
		return multipartFile.getBytes();
	}
}
