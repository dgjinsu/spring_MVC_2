package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;
    private String storeFileName; //사용자가 같은 이름으로 파일을 저장하면 로컬 저장소에 이름이 겹치기 때문에 따로 분리함

    public UploadFile(String originalFilename, String storeFileName) {
        this.uploadFileName = originalFilename;
        this.storeFileName = storeFileName;
    }
}
