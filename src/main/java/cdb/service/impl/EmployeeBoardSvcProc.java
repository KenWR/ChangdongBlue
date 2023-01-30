package cdb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cdb.domain.dto.EmployeeBoardDTO;
import cdb.domain.entity.BoardEntity;
import cdb.domain.etc.enums.BoardCategory;
import cdb.domain.repository.BoardRepository;
import cdb.service.EmployeeBoardSvc;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeBoardSvcProc implements EmployeeBoardSvc {

	private final BoardRepository boardRepository;

	@Override
	public EmployeeBoardDTO getEmployeeBoardDetail(final long no) {
		final var result = boardRepository.findById(no).orElseThrow();
		return toDTO(result);
	}

	@Override
	public List<EmployeeBoardDTO> getEmployeeBoardList(final Pageable pageable) {
		final var result = boardRepository.findAllByBoardCategory(BoardCategory.EMPLOYEE, pageable);
		return result.stream().map(this::toDTO).collect(Collectors.toList());
	}

	private EmployeeBoardDTO toDTO(final BoardEntity arg0) {
		if (arg0 == null) return null;

		final var employeeBoardDTO = new EmployeeBoardDTO();

		employeeBoardDTO.setBoardCategory(arg0.getBoardCategory());
		employeeBoardDTO.setContent(arg0.getContent());
		employeeBoardDTO.setCreatedDate(arg0.getCreatedDate());
		employeeBoardDTO.setNo(arg0.getNo());
		employeeBoardDTO.setTitle(arg0.getTitle());
		employeeBoardDTO.setUpdatedDate(arg0.getUpdatedDate());

		return employeeBoardDTO;
	}

}
