package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileDB {

  private String fileId;
  private String fileName;
  private String contentType;
  private long fileSize;
  private byte[] fileData;
  private String userId;

  public FileDB() {
  }

  public FileDB(String fileName, String contentType, long fileSize, byte[] fileData, String userId) {
    this.fileName = fileName;
    this.contentType = contentType;
    this.fileSize = fileSize;
    this.fileData = fileData;
    this.userId = userId;
  }

  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public long getFileSize() {
    return fileSize;
  }

  public void setFileSize(long fileSize) {
    this.fileSize = fileSize;
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
