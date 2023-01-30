package cdb.domain.dto.reg;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import cdb.domain.entity.EmployeeEntity;
import cdb.domain.entity.ImgFileEntity;
import cdb.utilities.FileUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeInsertDTO {

	// @NotEmpty(message = "이름을 입력해주세요")
	private String name;

	// @Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String phone;

	@NotEmpty
	private String addr;

	@NotEmpty
	private String password;

	private boolean gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	private String[] newName;

	private String[] orgName;

	public List<ImgFileEntity> toImgFile(final EmployeeEntity employee, final String url) {
		final List<ImgFileEntity> imgs = new ArrayList<>();
		for (var i = 0; i < orgName.length; i++) {
			if ("".equals(orgName[i]) || orgName[i] == null) continue;
			var defImg = false;
			if (i == 0) defImg = true;
			final var img = ImgFileEntity	.builder()
																		.url(url)
																		.orgName(orgName[i])
																		.newName(newName[i])
																		.defImg(defImg)
																		.employee(employee)
																		.build();
			imgs.add(img);
		}
		// temp 폴더 상위폴더인 upload로 이동
		FileUtils.moveUploadLocationFromTemp(newName, url);
		return imgs;
	}

}
