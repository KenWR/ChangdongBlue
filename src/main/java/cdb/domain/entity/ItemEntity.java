package cdb.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cdb.domain.etc.BaseTime;
import cdb.domain.etc.enums.ItemCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Item")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	private String title; // 자재 이름

	private String content; // 자재 설명

	private int price; // 자재 가격

	@Enumerated(EnumType.STRING)
	private ItemCategory ItemCategory; // 자재 분류

}
