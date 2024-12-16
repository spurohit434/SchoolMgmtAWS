package com.wg.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResponse<T> {
	private List<T> content;
	private int page;
	private int size;
	private int totalElements;
	private int totalPages;

	public PaginatedResponse(List<T> content, int page, int size, int totalElements) {
		this.content = content;
		this.page = page;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = (int) Math.ceil((double) totalElements / size);
	}
}
