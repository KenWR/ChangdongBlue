package cdb.utilities;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileUtils {

	public static Map<String, String> fileUpload(final MultipartFile gImg, final String location) {
		final var cpr = new ClassPathResource("static" + location);
		File folder = null;
		String fileName = null;
		String orgName = null;
		try {
			folder = cpr.getFile();
			orgName = gImg.getOriginalFilename();
			final var idx = neverNull(orgName).lastIndexOf(".");// 파일이름중에서 마직막(.)의 인덱스번호
			fileName = orgName.substring(0, idx) + "_" + System.nanoTime() / 1000000 + orgName.substring(idx);// .+확장자
			gImg.transferTo(new File(folder, fileName));
		} catch (final IOException e) {
			e.printStackTrace();
			log.catching(e);
		}
		final Map<String, String> tempFile = new HashMap<>();
		tempFile.put("newName", fileName);
		tempFile.put("orgName", orgName);
		tempFile.put("url", location + fileName);
		return tempFile;
	}

	public static void moveUploadLocationFromTemp(final String[] newName, final String url) {
		final var cpr = new ClassPathResource("static" + url + "temp/");
		// "/images/upload/"
		for (final String name : newName) try {
			// temp경로의 파일
			final var file = new File(cpr.getFile(), name);
			file.renameTo(new File(cpr.getFile().getParent(), name));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static String neverNull(final String orgName) {
		if (orgName.contains(".")) return orgName;
		throw new NullPointerException("파일 확장자를 구분할 수 없습니다.");
	}

	private FileUtils() {

	}

}
