package com.daniel.blog.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PhotoBlogPagable implements Pageable {
	private int pageNumber;
	private int pageSize;
	
	public PhotoBlogPagable(int pageNumber, int pageSize){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize+1;
	}

	@Override
	public int getOffset() {
		return pageNumber*pageSize;
	}

	@Override
	public Sort getSort() {
		return null;
	}

	@Override
	public Pageable next() {
		return new PhotoBlogPagable(pageNumber+1, pageSize);
	}

	@Override
	public Pageable previousOrFirst() {
		int newPage = pageNumber-1;
		if(newPage < 0){
			newPage = 0;
		}
		return new PhotoBlogPagable(newPage, pageSize);
	}

	@Override
	public Pageable first() {
		return new PhotoBlogPagable(0, pageSize);
	}

	@Override
	public boolean hasPrevious() {
		return pageNumber > 0;
	}

}
