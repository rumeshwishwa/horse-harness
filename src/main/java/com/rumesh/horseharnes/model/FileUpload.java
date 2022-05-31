package com.rumesh.horseharnes.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "FILE_UPLOAD")
@Data
public class FileUpload {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "FILE_UPLOAD_ID")
    private String fileUploadId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "IS_VALID_INSERT")
    private Boolean isValidInsert;
}
