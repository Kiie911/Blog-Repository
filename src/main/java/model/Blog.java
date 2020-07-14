package model;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(columnDefinition = "nvarchar(3000)", nullable = false)
    private String detail;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    public Blog() {
    }

    public Blog(Long id, String name, String detail) {
        this.id = id;
        this.name = name;
        this.detail = detail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
