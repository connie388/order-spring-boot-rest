package com.rest.order.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productlines")
public class ProductLine extends AuditModel {
  @Id
  @NotNull
  @Size(max = 50)
  @Column(name = "productLine")
  private String productLine;

  @Size(max = 4000)
  @Column(name = "textDescription", length = 4000)
  private String textDescription;

  @Column(name = "htmlDescription", columnDefinition = "MEDIUMTEXT")
  private String htmlDescription;

  @Column(name = "imageUrl")
  @Lob
  private String imageUrl;

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @OneToMany(mappedBy = "prodLine", fetch = FetchType.LAZY)
  private Set<Product> products = new HashSet<>();
}
