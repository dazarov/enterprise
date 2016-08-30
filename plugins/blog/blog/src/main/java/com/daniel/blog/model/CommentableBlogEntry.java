package com.daniel.blog.model;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.daniel.blog.model.converters.LanguageConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="COMMENTABLE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CommentableBlogEntry extends AbstractEntity {
	
	// Fields
	
	@Column(name="VISITED")
	private Long visited;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "blogEntry", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = false)
	@JsonManagedReference
	Set<Comment> comments = new TreeSet<>();
	
	@Column(name="LANGUAGE")
	@Basic
	@Convert(converter = LanguageConverter.class)
	private Language language;
	
	@ManyToOne
    @JoinColumn(name="BLOG_ID", nullable=true, foreignKey = @ForeignKey(name = "BLOG_ID"))
	private Blog blog;
	
	// Methods
	
	public void setVisited(Long visited){
		this.visited = visited;
	}
	
	public Long getVisited(){
		return visited;
	}

	
	public Set<Comment> getComments(){
		return comments;
	}
	
	public void addComment(Comment comment){
		comments.add(comment);
		comment.setBlogEntry(this);
	}
	
	public void removeComment(Comment comment){
		comments.remove(comment);
		comment.setBlogEntry(null);
	}
	
	public void setLanguage(Language language){
		this.language = language;
	}
	
	public Language getLanguage(){
		return language;
	}
	
	public void setBlog(Blog blog){
		this.blog = blog;
	}
	
	public Blog getBlog(){
		return blog;
	}

}
