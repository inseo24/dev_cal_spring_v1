package com.example.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Product")
public class ProductEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	
	@Column(columnDefinition = "LONGTEXT")
	private Long regDate;
	
	@Column(length = 100) 
	private String name;
	
	@Column(length = 11) 
	private int limit_count;
	
	@Column(length = 11)
	private int total_count;
	
	@Column(length = 11)
	private int price;
	
	@Column(length = 100)
	private String product_nm;
	
	private String category_nm;
	
	
	
	
	
}
