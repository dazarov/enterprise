package com.daniel.blog.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PhotoBlogPageable implements Pageable {
	private int pageNumber;
	private int pageSize;
	
	public PhotoBlogPageable(int pageNumber, int pageSize){
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
		return new PhotoBlogPageable(pageNumber+1, pageSize);
	}

	@Override
	public Pageable previousOrFirst() {
		int newPage = pageNumber-1;
		if(newPage < 0){
			newPage = 0;
		}
		return new PhotoBlogPageable(newPage, pageSize);
	}

	@Override
	public Pageable first() {
		return new PhotoBlogPageable(0, pageSize);
	}

	@Override
	public boolean hasPrevious() {
		return pageNumber > 0;
	}

}
