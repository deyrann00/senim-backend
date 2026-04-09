package com.senim.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blacklisted_funds")
public class BlacklistedFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String name_kz;
    private String name_ru;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String description_kz;

    @Column(columnDefinition = "TEXT")
    private String description_ru;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @Column(columnDefinition = "TEXT")
    private String reason_kz;

    @Column(columnDefinition = "TEXT")
    private String reason_ru;

    private String link;
    private String status;
    private String source;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getName_kz() { return name_kz; }
    public void setName_kz(String name_kz) { this.name_kz = name_kz; }
    public String getName_ru() { return name_ru; }
    public void setName_ru(String name_ru) { this.name_ru = name_ru; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDescription_kz() { return description_kz; }
    public void setDescription_kz(String description_kz) { this.description_kz = description_kz; }
    public String getDescription_ru() { return description_ru; }
    public void setDescription_ru(String description_ru) { this.description_ru = description_ru; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getReason_kz() { return reason_kz; }
    public void setReason_kz(String reason_kz) { this.reason_kz = reason_kz; }
    public String getReason_ru() { return reason_ru; }
    public void setReason_ru(String reason_ru) { this.reason_ru = reason_ru; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}