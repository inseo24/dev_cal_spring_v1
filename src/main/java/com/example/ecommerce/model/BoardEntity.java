package com.example.ecommerce.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Board")
public class BoardEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String boardId;
	
	@Column(columnDefinition = "INT(11) NOT NULL UNIQUE KEY auto_increment")
	private int boardNumber;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity userId;
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "boardId")
	private List<ImageEntity> images = new ArrayList<>(); 
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "boardId")
	private List<CommentEntity> comments = new ArrayList<>();
	
	private String title;
	private String content;
	private LocalDateTime createdTime; 
	private LocalDateTime modified_date;
			
	@PrePersist
	public void createdTime() {
		this.createdTime = LocalDateTime.now();
	}
	

}
